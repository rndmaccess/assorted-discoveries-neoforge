package rndm_access.assorteddiscoveries.conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.conditions.ICondition;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.Config;

import java.util.function.Function;

public record ConfigEntryEnabledResourceCondition(String configKey) implements ICondition {
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
    public boolean test(@NonNull IContext context) {
        ModConfigSpec.BooleanValue value = Config.MOD_SPEC.getValues().get(this.configKey);

        if (value == null) {
            AssortedDiscoveries.LOGGER.error("{} is not a known config entry!", this.configKey);
            return false; // Don't load the resource if we encounter an unknown config key!
        }
        return value.get();
    }

    @Override
    public @NonNull MapCodec<? extends ICondition> codec() {
        return CODEC;
    }
}
