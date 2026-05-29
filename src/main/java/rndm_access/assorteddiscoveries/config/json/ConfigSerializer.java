package rndm_access.assorteddiscoveries.config.json;

import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigCategory;
import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigObject;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.AbstractConfigEntry;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

public class ConfigSerializer {
    private int line;
    private int depth;
    private final Path configPath;
    private final ServerConfig config;

    public ConfigSerializer(ServerConfig config, Path configPath) {
        line = 0;
        depth = 0;
        this.configPath = configPath;
        this.config = config;
    }

    /*
     * This method is used to serialize to a new config file!
     */
    public void serialize() {
        // The config does not exist so we don't have any changes!
        this.serialize(null);
    }

    /**
     * This method is used to serialize new changes to a config file!
     * @param changeList The changes to serialize
     */
    public void serialize(Map<String, Object> changeList) {
        List<String> newContent = this.getContent(changeList);

        // To prevent partial files we first save it to a temporary file, then replace the config file!
        try (TempConfig tempFile = new TempConfig(configPath)) {
            Files.write(tempFile.getFile(), newContent);
            Files.move(tempFile.getFile(), configPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save the file!", e);
        }
    }

    private List<String> getContent(@Nullable Map<String, Object> changeList) {
        List<String> newContent = new ArrayList<>();
        List<ConfigObject> objects = config.getObjects();
        int size = objects.size();

        if (!objects.isEmpty()) {
            this.writeText("{", newContent);
            depth++;
            line++;

            for (int i = 0; i < size; i++) {
                ConfigObject object = objects.get(i);

                if (object.isComment()) {
                    String comment = object.getKey();
                    this.writeComment(newContent, comment);
                    continue;
                }

                ConfigCategory category = (ConfigCategory) object;
                this.writeCategory(newContent, category, changeList);

                if(i + 1 < size) {
                    this.writeText(",", newContent);
                }
                line++;
            }
            line++;
            depth--;
            this.writeText("}", newContent);
        }
        return newContent;
    }

    private void writeCategory(List<String> newContent, ConfigCategory category, Map<String, Object> changeList) {
        List<ConfigObject> objects = category.getJsonObjects();
        this.writeText("\"" + category.getKey() + "\": {", newContent);
        depth++;
        line++;

        for (int i = 0; i < objects.size(); i++) {
            ConfigObject component = objects.get(i);
            String key = component.getKey();

            if (component.isComment()) {
                this.writeComment(newContent, key);
                continue;
            }

            if (category.hasEntry(key)) {
                AbstractConfigEntry<?> entry = (AbstractConfigEntry<?>) component;
                writeEntry(newContent, category, entry, changeList);
            } else {
                ConfigCategory subCategory = category.getSubcategory(key);
                writeCategory(newContent, subCategory, changeList);
            }

            if (i + 1 < category.getJsonObjects().size()) {
                this.writeText(",", newContent);
                line++;
            } else {
                depth--;
                line++;
                this.writeText("}", newContent);
            }
        }
    }

    private void writeEntry(List<String> newContent, ConfigCategory category, AbstractConfigEntry<?> entry,
                            Map<String, Object> changeList) {
        StringBuilder entryLine = new StringBuilder();
        String key = entry.getKey();
        Object value = entry.getValue();
        entryLine.append("\"").append(entry.getKey()).append("\": ");

        if (category.hasStringEntry(key)) {
            if (changeList != null && changeList.containsKey(key)) {
                value = changeList.get(key);
            }
            entryLine.append("\"").append(value).append("\"");
        } else {
            if (changeList != null && changeList.containsKey(key)) {
                value = changeList.get(key);
            }
            entryLine.append(value);
        }
        this.writeText(entryLine.toString(), newContent);
    }

    private void writeComment(List<String> newContent, String comment) {
        this.writeText("// ", newContent);

        for (int i = 0; i < comment.length(); i++) {
            char token = comment.charAt(i);

            if(token == '\n') {
                line++;
                this.writeText("// ", newContent);
            } else {
                this.writeText(Character.toString(token), newContent);
            }
        }
        line++;
    }

    private void writeText(String value, List<String> newContent) {
        if (line >= newContent.size()) {
            if (depth > 0) {
                newContent.add("\t".repeat(depth) + value);
            } else {
                newContent.add(value);
            }
        } else {
            String lineContent = newContent.get(line);
            newContent.set(line, lineContent + value);
        }
    }
}
