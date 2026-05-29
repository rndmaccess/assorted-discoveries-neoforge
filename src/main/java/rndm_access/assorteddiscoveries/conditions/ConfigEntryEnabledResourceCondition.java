package rndm_access.assorteddiscoveries.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.config.ModServerConfig;
import rndm_access.assorteddiscoveries.config.json.ServerConfig;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.AbstractConfigEntry;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.BooleanConfigEntry;
import rndm_access.assorteddiscoveries.core.ModResourceConditionTypes;

import java.util.function.Function;

public record ConfigEntryEnabledResourceCondition(String configKey) implements ResourceCondition {
    public static final MapCodec<ConfigEntryEnabledResourceCondition> CODEC
            = RecordCodecBuilder.mapCodec((instance) -> {
        Function<ConfigEntryEnabledResourceCondition, String> key = ConfigEntryEnabledResourceCondition::getConfigKey;

        return instance.group(Codec.STRING.fieldOf("value").forGetter(key))
                .apply(instance, ConfigEntryEnabledResourceCondition::new);
    });

    public String getConfigKey() {
        return this.configKey;
    }

    @Override
    public ResourceConditionType<?> getType() {
        return ModResourceConditionTypes.CONFIG_ENTRY_ENABLED;
    }

    @Override
    public boolean test(@Nullable RegistryOps.@Nullable RegistryInfoLookup registryInfo) {
        ServerConfig config = ModServerConfig.getInstance();

        AbstractConfigEntry<?> entry = config.getEntry(configKey);
        if (entry == null) {
            AssortedDiscoveries.LOGGER.error("{} is not a known config entry!", this.configKey);
            return false; // Don't load the resource if we encounter an unknown config key!
        }

        if (entry instanceof BooleanConfigEntry) {
            return ((BooleanConfigEntry) entry).getValue();
        }
        return false;
    }
}
