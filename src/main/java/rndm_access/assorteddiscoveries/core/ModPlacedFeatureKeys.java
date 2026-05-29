package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModPlacedFeatureKeys {
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL_SWAMP
            = of("patch_cattail_swamp");
    public static final ResourceKey<PlacedFeature> PATCH_CATTAIL_RIVER = of("patch_cattail_river");
    public static final ResourceKey<PlacedFeature> ORE_SMOKY_QUARTZ = of("ore_smoky_quartz");
    public static final ResourceKey<PlacedFeature> PATCH_HUGE_PURPLE_MUSHROOM = of("patch_huge_purple_mushroom");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_COMMON = of("patch_blueberry_bush_common");
    public static final ResourceKey<PlacedFeature> PATCH_BLUEBERRY_RARE = of("patch_blueberry_bush_rare");
    public static final ResourceKey<PlacedFeature> PATCH_WITCHS_CRADLE_COMMON = of("patch_witchs_cradle_common");
    public static final ResourceKey<PlacedFeature> PATCH_WITCHS_CRADLE_RARE = of("patch_witchs_cradle_rare");
    public static final ResourceKey<PlacedFeature> PATCH_ENDER_PLANTS = of("patch_ender_plants");
    public static final ResourceKey<PlacedFeature> BLOOD_KELP = of("blood_kelp");
    public static final ResourceKey<PlacedFeature> ORE_BAUXITE_LOWER = of("ore_bauxite_lower");
    public static final ResourceKey<PlacedFeature> ORE_BAUXITE_UPPER = of("ore_bauxite_upper");
    public static final ResourceKey<PlacedFeature> BOG_BLOSSOM = of("bog_blossom");
    public static final ResourceKey<PlacedFeature> PATCH_CINDERSNAP_BERRY_BUSH_COMMON
            = of("patch_cindersnap_berry_bush_common");
    public static final ResourceKey<PlacedFeature> PATCH_CINDERSNAP_BERRY_BUSH_RARE
            = of("patch_cindersnap_berry_bush_rare");
    public static final ResourceKey<PlacedFeature> PATCH_FROSTBITE_BERRY_BUSH_COMMON
            = of("patch_frostbite_berry_bush_common");
    public static final ResourceKey<PlacedFeature> PATCH_FROSTBITE_BERRY_BUSH_RARE
            = of("patch_frostbite_berry_bush_rare");
    public static final ResourceKey<PlacedFeature> PATCH_WILD_GREEN_ONIONS_COMMON
            = of("patch_wild_green_onions_common");
    public static final ResourceKey<PlacedFeature> PATCH_WILD_GREEN_ONIONS_RARE
            = of("patch_wild_green_onions_rare");

    public static ResourceKey<PlacedFeature> of(String path) {
        return ResourceKey.create(Registries.PLACED_FEATURE, AssortedDiscoveries.makeModId(path));
    }
}
