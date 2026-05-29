package rndm_access.assorteddiscoveries.config.json.deserializer;

import rndm_access.assorteddiscoveries.config.json.deserializer.entries.CommentConfigEntry;

public class ConfigObject {
    private final String key;

    public ConfigObject(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public boolean isComment() {
        return this instanceof CommentConfigEntry;
    }
}
