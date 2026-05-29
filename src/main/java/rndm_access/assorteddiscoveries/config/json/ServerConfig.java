package rndm_access.assorteddiscoveries.config.json;

import net.fabricmc.loader.api.FabricLoader;
import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigObject;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.BooleanConfigEntry;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.CommentConfigEntry;
import rndm_access.assorteddiscoveries.config.json.exceptions.JsonConfigException;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.AbstractConfigEntry;
import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigCategory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ServerConfig {
    private final List<ConfigObject> objects;
    private final HashMap<String, ConfigCategory> categories;
    private final Path path;
    private final String name;

    public ServerConfig(ServerConfig.Builder builder) {
        this.objects = builder.objects;
        this.categories = builder.categories;
        this.path = FabricLoader.getInstance().getConfigDir().resolve(builder.name + ".json5");
        this.name = builder.name;
    }

    public Path getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public Map<String, Boolean> toEntryMap() {
        Map<String, Boolean> hashMap = new HashMap<>();

        for (ConfigCategory category : categories.values()) {
            for (AbstractConfigEntry<?> entry : category.getEntries()) {
                if (entry instanceof BooleanConfigEntry) {
                    hashMap.put(entry.getKey(), (Boolean) entry.getValue());
                }
            }
        }
        return hashMap;
    }

    /**
     * @param entryName The config entry key we are looking for
     * @return The config entry associated with the key if it's found otherwise null
     */
    public AbstractConfigEntry<?> getEntry(String entryName) {
        List<ConfigCategory> categories = this.getCategories();
        ArrayDeque<ConfigCategory> stack = new ArrayDeque<>(categories.size());

        // Start by adding the root categories to the stack. We then will look in each category for the entry!
        for (ConfigCategory category : categories) {
            stack.push(category);
        }

        while (!stack.isEmpty()) {
            ConfigCategory currentCategory = stack.pop();

            // If the current category has the entry then we have found it!
            if (currentCategory.hasEntry(entryName)) {
                return currentCategory.getEntry(entryName);
            }

            // If the category has subcategories, push those onto the stack and look through those as well!
            if (currentCategory.hasSubCategories()) {
                for (ConfigCategory subcategory : currentCategory.getSubcategories()) {
                    stack.push(subcategory);
                }
            }
        }
        return null; // Entry not found!
    }

    public ConfigCategory getCategory(String name) {
        if(!this.hasCategory(name)) {
            throw new NoSuchElementException("The config does not have category " + name);
        }
        return categories.get(name);
    }

    public boolean hasCategory(String name) {
        return categories.containsKey(name);
    }

    public List<ConfigCategory> getCategories() {
        return categories.values().stream().toList();
    }

    public List<ConfigObject> getObjects() {
        return objects;
    }

    public void create() {
        if (path == null) {
            throw new JsonConfigException("The config path has not been set!");
        }

        if (!Files.exists(path)) {
            ConfigSerializer serializer = new ConfigSerializer(this, path);
            serializer.serialize();
        }
    }

    public void save() {
        if (path == null) {
            throw new JsonConfigException("The config path has not been set!");
        }

        ConfigSerializer serializer = new ConfigSerializer(this, path);
        serializer.serialize();
    }

    public void save(Map<String, Object> entryChangeList) {
        if (path == null) {
            throw new JsonConfigException("The config path has not been set!");
        }

        if (Files.exists(path)) {
            ConfigSerializer serializer = new ConfigSerializer(this, path);
            serializer.serialize(entryChangeList);
        }
    }

    public ServerConfig merge(ServerConfig anotherConfig) {
        ServerConfig.Builder config = new ServerConfig.Builder(this.name);

        for (ConfigObject object : this.getObjects()) {
            if (object.isComment()) {
                CommentConfigEntry comment = (CommentConfigEntry) object;
                config.addComment(comment);
                continue;
            }

            String categoryKey = object.getKey();
            ConfigCategory category = this.getCategory(categoryKey);

            if (anotherConfig.hasCategory(categoryKey)) {
                this.mergeCategories(config, anotherConfig, categoryKey, category);
            } else {
                config.addCategory(category);
            }
        }
        return config.build();
    }

    private void mergeCategories(ServerConfig.Builder configBuilder, ServerConfig anotherConfig, String categoryKey,
                                 ConfigCategory category) {
        ConfigCategory.Builder categoryBuilder = new ConfigCategory.Builder(categoryKey);

        for (ConfigObject categoryObject : category.getJsonObjects()) {
            String key = categoryObject.getKey();

            if (categoryObject.isComment()) {
                CommentConfigEntry comment = (CommentConfigEntry) categoryObject;
                categoryBuilder.addComment(comment);
            } else if (category.hasEntry(key)) {
                this.mergeEntries(anotherConfig, categoryBuilder, categoryObject, categoryKey, category);
            } else {
                this.mergeCategories(configBuilder, anotherConfig, key, category); // Also merge subcategories!
            }
        }
        configBuilder.addCategory(categoryBuilder.build());
    }

    private void mergeEntries(ServerConfig anotherConfig, ConfigCategory.Builder categoryBuilder,
                              ConfigObject categoryObject, String categoryKey, ConfigCategory category) {
        String entryKey = categoryObject.getKey();

        if (anotherConfig.getCategory(categoryKey).hasEntry(entryKey)) {
            AbstractConfigEntry<?> configEntry = anotherConfig.getCategory(categoryKey).getEntry(entryKey);
            categoryBuilder.addEntry(configEntry);
        } else {
            AbstractConfigEntry<?> configEntry = category.getEntry(entryKey);
            categoryBuilder.addEntry(configEntry);
        }
    }

    public static class Builder {
        public String name;
        private final List<ConfigObject> objects = new ArrayList<>();
        private final HashMap<String, ConfigCategory> categories = new LinkedHashMap<>();

        public Builder(String name) {
            this.name = name;
        }

        public ServerConfig.Builder addComment(CommentConfigEntry comment) {
            objects.add(comment);
            return this;
        }

        public ServerConfig.Builder addCategory(ConfigCategory category) {
            categories.put(category.getKey(), category);
            objects.add(category);
            return this;
        }

        public ServerConfig build() {
            return new ServerConfig(this);
        }
    }
}
