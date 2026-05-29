package rndm_access.assorteddiscoveries.core;

import com.mojang.serialization.MapCodec;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.conditions.ConfigEntryEnabledResourceCondition;

public final class ModResourceConditionTypes {
    public static final ResourceConditionType<ConfigEntryEnabledResourceCondition> CONFIG_ENTRY_ENABLED
            = create("config_entry_enabled", ConfigEntryEnabledResourceCondition.CODEC);

    private static <T extends ResourceCondition> ResourceConditionType<T> create(String name, MapCodec<T> codec) {
        return ResourceConditionType.create(AssortedDiscoveries.makeModId(name), codec);
    }

    public static void register() {
        ResourceConditions.register(CONFIG_ENTRY_ENABLED);
        AssortedDiscoveries.LOGGER.info("Registered resource conditions");
    }
}
