package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.worldgen.feature.BloodKelpFeature;
import rndm_access.assorteddiscoveries.worldgen.feature.CattailFeature;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public final class ModFeatures {
    private static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, AssortedDiscoveries.MOD_ID);

    public static final DeferredHolder<Feature<?>, Feature<ProbabilityFeatureConfiguration>> CATTAIL
            = FEATURES.register("cattail", () -> new CattailFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> BLOOD_KELP
            = FEATURES.register("blood_kelp", () -> new BloodKelpFeature(NoneFeatureConfiguration.CODEC));

    /**
     * Called during initialization to register every feature.
     */
    public static void register(IEventBus modEventBus) {
        FEATURES.register(modEventBus);
        AssortedDiscoveries.LOGGER.info("Registered features");
    }
}
