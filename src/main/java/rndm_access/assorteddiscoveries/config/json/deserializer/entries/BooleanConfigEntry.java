package rndm_access.assorteddiscoveries.config.json.deserializer.entries;

public class BooleanConfigEntry extends AbstractConfigEntry<Boolean> {
    public BooleanConfigEntry(String key) {
        super(key, true);
    }

    public BooleanConfigEntry(String key, boolean value) {
        super(key, value);
    }
}
