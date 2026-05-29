package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModBiomeTags {
    public static final TagKey<Biome> ORE_SMOKY_QUARTZ = of("has_feature/ore_smoky_quartz");
    public static final TagKey<Biome> PATCH_BLUEBERRY_BUSH = of("has_feature/patch_blueberry_bush");
    public static final TagKey<Biome> PATCH_CATTAIL_SWAMP = of("has_feature/patch_cattail_swamp");
    public static final TagKey<Biome> PATCH_CATTAIL_RIVER = of("has_feature/patch_cattail_river");
    public static final TagKey<Biome> PATCH_ENDER_PLANTS = of("has_feature/patch_ender_plants");
    public static final TagKey<Biome> PATCH_HUGE_PURPLE_MUSHROOM = of("has_feature/patch_huge_purple_mushroom");
    public static final TagKey<Biome> PATCH_WITCHS_CRADLE = of("has_feature/patch_witchs_cradle");
    public static final TagKey<Biome> BLOOD_KELP = of("has_feature/blood_kelp");
    public static final TagKey<Biome> ORE_BAUXITE = of("has_feature/ore_bauxite");
    public static final TagKey<Biome> BOG_BLOSSOM = of("has_feature/bog_blossom");
    public static final TagKey<Biome> PATCH_CINDERSNAP_BERRY_BUSH = of("has_feature/patch_cindersnap_berry_bush");
    public static final TagKey<Biome> PATCH_FROSTBITE_BERRY_BUSH = of("has_feature/patch_frostbite_berry_bush");
    public static final TagKey<Biome> PATCH_WILD_GREEN_ONIONS = of("has_feature/patch_wild_green_onions");

    private static TagKey<Biome> of(String path) {
        return TagKey.create(Registries.BIOME, AssortedDiscoveries.makeModId(path));
    }
}
