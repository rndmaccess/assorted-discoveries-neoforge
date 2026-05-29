package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModTreeConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?,?>> HUGE_PURPLE_MUSHROOM = of("huge_purple_mushroom");

    public static ResourceKey<ConfiguredFeature<?,?>> of(String path) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, AssortedDiscoveries.makeModId(path));
    }
}
