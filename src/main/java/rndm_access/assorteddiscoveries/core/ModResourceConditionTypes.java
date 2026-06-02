package rndm_access.assorteddiscoveries.core;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.conditions.ICondition;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.conditions.ConfigEntryEnabledResourceCondition;

import java.util.function.Supplier;

public final class ModResourceConditionTypes {
    public static final DeferredRegister<MapCodec<? extends ICondition>> CONDITION_CODECS =
            DeferredRegister.create(NeoForgeRegistries.Keys.CONDITION_CODECS, AssortedDiscoveries.MOD_ID);

    public static final Supplier<MapCodec<ConfigEntryEnabledResourceCondition>> CONFIG_ENTRY_ENABLED
            = CONDITION_CODECS.register("config_entry_enabled", () -> ConfigEntryEnabledResourceCondition.CODEC);

    public static void register(IEventBus modEventBus) {
        CONDITION_CODECS.register(modEventBus);
        AssortedDiscoveries.LOGGER.info("Registered resource conditions");
    }
}
