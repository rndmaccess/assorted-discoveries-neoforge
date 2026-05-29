package rndm_access.assorteddiscoveries.config.json.deserializer.entries;

import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigObject;

public abstract class AbstractConfigEntry<T> extends ConfigObject {
    private final T value;

    public AbstractConfigEntry(String key, T value) {
        super(key);
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
