package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.worldgen.feature.BloodKelpFeature;
import rndm_access.assorteddiscoveries.worldgen.feature.CattailFeature;

@SuppressWarnings("unused")
public final class ModFeatures {
    public static final Feature<ProbabilityFeatureConfiguration> CATTAIL
            = register("cattail", new CattailFeature(ProbabilityFeatureConfiguration.CODEC));
    public static final Feature<NoneFeatureConfiguration> BLOOD_KELP
            = register("blood_kelp", new BloodKelpFeature(NoneFeatureConfiguration.CODEC));

    private static <C extends FeatureConfiguration, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(BuiltInRegistries.FEATURE, AssortedDiscoveries.makeModId(name), feature);
    }

    /**
     * Called during initialization to register every feature.
     */
    public static void register() {
        AssortedDiscoveries.LOGGER.info("Registered features");
    }
}
