package rndm_access.assorteddiscoveries;

import java.util.List;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_DYED_CAMPFIRES = BUILDER
            .define("enable_dyed_campfires", true);
    public static final ModConfigSpec.BooleanValue ENABLE_DYED_LANTERNS = BUILDER
            .define("enable_dyed_lanterns", true);
    public static final ModConfigSpec.BooleanValue ENABLE_DYED_TORCHES = BUILDER
            .define("enable_dyed_torches", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ALLAY_PLUSHIE = BUILDER
            .define("enable_allay_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BAT_PLUSHIE = BUILDER
            .define("enable_bat_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CAMEL_PLUSHIE = BUILDER
            .define("enable_camel_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CAT_PLUSHIES = BUILDER
            .define("enable_cat_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CHICKEN_PLUSHIES = BUILDER
            .define("enable_chicken_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_COW_PLUSHIES = BUILDER
            .define("enable_cow_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_HORSE_PLUSHIES = BUILDER
            .define("enable_horse_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MOOSHROOM_PLUSHIES = BUILDER
            .define("enable_mooshroom_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PIG_PLUSHIES = BUILDER
            .define("enable_pig_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PUFFERFISH_PLUSHIE = BUILDER
            .define("enable_pufferfish_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_RABBIT_PLUSHIES = BUILDER
            .define("enable_rabbit_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SHEEP_PLUSHIES = BUILDER
            .define("enable_sheep_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SQUID_PLUSHIES = BUILDER
            .define("enable_squid_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_STRIDER_PLUSHIES = BUILDER
            .define("enable_strider_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_VILLAGER_PLUSHIES = BUILDER
            .define("enable_villager_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BEE_PLUSHIE = BUILDER
            .define("enable_bee_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CAVE_SPIDER_PLUSHIE = BUILDER
            .define("enable_cave_spider_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ENDERMAN_PLUSHIE = BUILDER
            .define("enable_enderman_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PIGLIN_PLUSHIES = BUILDER
            .define("enable_piglin_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SPIDER_PLUSHIE = BUILDER
            .define("enable_spider_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WOLF_PLUSHIES = BUILDER
            .define("enable_wolf_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BLAZE_PLUSHIE = BUILDER
            .define("enable_blaze_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CREEPER_PLUSHIE = BUILDER
            .define("enable_creeper_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_GHAST_PLUSHIE = BUILDER
            .define("enable_ghast_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_GUARDIAN_PLUSHIE = BUILDER
            .define("enable_guardian_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_HOGLIN_PLUSHIES = BUILDER
            .define("enable_hoglin_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ILLAGER_PLUSHIES = BUILDER
            .define("enable_illager_plushies", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MAGMA_CUBE_PLUSHIE = BUILDER
            .define("enable_magma_cube_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PHANTOM_PLUSHIE = BUILDER
            .define("enable_phantom_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SHULKER_PLUSHIE = BUILDER
            .define("enable_shulker_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SKELETON_PLUSHIE = BUILDER
            .define("enable_skeleton_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SLIME_PLUSHIE = BUILDER
            .define("enable_slime_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_VEX_PLUSHIE = BUILDER
            .define("enable_vex_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WITCH_PLUSHIE = BUILDER
            .define("enable_witch_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WITHER_PLUSHIE = BUILDER
            .define("enable_wither_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ZOMBIE_PLUSHIE = BUILDER
            .define("enable_zombie_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CREAKING_PLUSHIE = BUILDER
            .define("enable_creaking_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WOODEN_WALLS = BUILDER
            .define("enable_wooden_walls", true);
    public static final ModConfigSpec.BooleanValue ENABLE_STRIPPED_WOODEN_WALLS = BUILDER
            .define("enable_stripped_wooden_walls", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ROPE_LADDERS = BUILDER
            .define("enable_rope_ladders", true);
    public static final ModConfigSpec.BooleanValue ENABLE_IRON_LADDERS = BUILDER
            .define("enable_iron_ladders", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BLACKSTONE_TILES = BUILDER
            .define("enable_blackstone_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_TWISTED_BLACKSTONE = BUILDER
            .define("enable_twisted_blackstone", true);
    public static final ModConfigSpec.BooleanValue ENABLE_TWISTED_BLACKSTONE_TILES = BUILDER
            .define("enable_twisted_blackstone_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_TWISTED_NETHERRACK = BUILDER
            .define("enable_twisted_netherrack", true);
    public static final ModConfigSpec.BooleanValue ENABLE_TWISTED_NETHER_BRICKS = BUILDER
            .define("enable_twisted_nether_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_TWISTED_POLISHED_BLACKSTONE_BRICKS = BUILDER
            .define("enable_twisted_polished_blackstone_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WEEPING_NETHERRACK = BUILDER
            .define("enable_weeping_netherrack", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WEEPING_NETHER_BRICKS = BUILDER
            .define("enable_weeping_nether_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WEEPING_BLACKSTONE = BUILDER
            .define("enable_weeping_blackstone", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WEEPING_POLISHED_BLACKSTONE_BRICKS = BUILDER
            .define("enable_weeping_polished_blackstone_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WEEPING_BLACKSTONE_TILES = BUILDER
            .define("enable_weeping_blackstone_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SMOKY_QUARTZ_BLOCKS = BUILDER
            .define("enable_smoky_quartz_blocks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SMOKY_QUARTZ_BRICKS = BUILDER
            .define("enable_smoky_quartz_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SMOOTH_SMOKY_QUARTZ = BUILDER
            .define("enable_smooth_smoky_quartz", true);
    public static final ModConfigSpec.BooleanValue ENABLE_QUARTZ_TILES = BUILDER
            .define("enable_quartz_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_QUARTZ_WALLS = BUILDER
            .define("enable_quartz_walls", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BAUXITE = BUILDER
            .define("enable_bauxite", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BAUXITE_BRICKS = BUILDER
            .define("enable_bauxite_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CRACKED_BAUXITE_BRICKS = BUILDER
            .define("enable_cracked_bauxite_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MOSSY_BAUXITE_BRICKS = BUILDER
            .define("enable_mossy_bauxite_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_STONE_TILES = BUILDER
            .define("enable_stone_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CRACKED_STONE_TILES = BUILDER
            .define("enable_cracked_stone_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MOSSY_STONE_TILES = BUILDER
            .define("enable_mossy_stone_tiles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CRACKED_STONE_BRICK_BLOCKS = BUILDER
            .define("enable_cracked_stone_brick_blocks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PLANTER_BOXES = BUILDER
            .define("enable_planter_boxes", true);
    public static final ModConfigSpec.BooleanValue ENABLE_GREEN_ONIONS = BUILDER
            .define("enable_green_onions", true);
    public static final ModConfigSpec.BooleanValue ENABLE_NOODLE_SOUP = BUILDER
            .define("enable_noodle_soup", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BLUEBERRIES = BUILDER
            .define("enable_blueberries", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BLUEBERRY_PIE = BUILDER
            .define("enable_blueberry_pie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BLUEBERRY_JUICE = BUILDER
            .define("enable_blueberry_juice", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SWEET_BERRY_PIE = BUILDER
            .define("enable_sweet_berry_pie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SWEET_BERRY_JUICE = BUILDER
            .define("enable_sweet_berry_juice", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CHOCOLATE_CAKE = BUILDER
            .define("enable_chocolate_cake", true);
    public static final ModConfigSpec.BooleanValue ENABLE_RED_VELVET_CAKE = BUILDER
            .define("enable_red_velvet_cake", true);
    public static final ModConfigSpec.BooleanValue ENABLE_FRIED_EGG = BUILDER
            .define("enable_fried_egg", true);
    public static final ModConfigSpec.BooleanValue ENABLE_HOGLIN_STEW = BUILDER
            .define("enable_hoglin_stew", true);
    public static final ModConfigSpec.BooleanValue ENABLE_FORESTS_BOUNTY = BUILDER
            .define("enable_forests_bounty", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WITCHS_CRADLES = BUILDER
            .define("enable_witchs_cradles", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PUDDING = BUILDER
            .define("enable_pudding", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CARAMEL_APPLE = BUILDER
            .define("enable_caramel_apple", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CINDERSNAP_BERRIES = BUILDER
            .define("enable_cindersnap_berries", true);
    public static final ModConfigSpec.BooleanValue ENABLE_FROSTBITE_BERRIES = BUILDER
            .define("enable_frostbite_berries", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PURPLE_MUSHROOMS = BUILDER
            .define("enable_purple_mushrooms", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CATTAILS = BUILDER
            .define("enable_cattails", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BOG_BLOSSOMS = BUILDER
            .define("enable_bog_blossoms", true);
    public static final ModConfigSpec.BooleanValue ENABLE_BLOOD_KELP = BUILDER
            .define("enable_blood_kelp", true);
    public static final ModConfigSpec.BooleanValue ENABLE_ENDER_PLANTS = BUILDER
            .define("enable_ender_plants", true);
    public static final ModConfigSpec.BooleanValue ENABLE_STONE_WALLS = BUILDER
            .define("enable_stone_walls", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CALCITE_BLOCKS = BUILDER
            .define("enable_calcite_blocks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_POLISHED_CALCITE = BUILDER
            .define("enable_polished_calcite", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CALCITE_BRICKS = BUILDER
            .define("enable_calcite_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CRACKED_CALCITE_BRICKS = BUILDER
            .define("enable_cracked_calcite_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MOSSY_CALCITE_BRICKS = BUILDER
            .define("enable_mossy_calcite_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_DRIPSTONE_BLOCKS = BUILDER
            .define("enable_dripstone_blocks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_POLISHED_DRIPSTONE = BUILDER
            .define("enable_polished_dripstone", true);
    public static final ModConfigSpec.BooleanValue ENABLE_DRIPSTONE_BRICKS = BUILDER
            .define("enable_dripstone_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CRACKED_DRIPSTONE_BRICKS = BUILDER
            .define("enable_cracked_dripstone_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_MOSSY_DRIPSTONE_BRICKS = BUILDER
            .define("enable_mossy_dripstone_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_QUARTZ_BRICK_BLOCKS = BUILDER
            .define("enable_quartz_brick_blocks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SNOW_BRICKS = BUILDER
            .define("enable_snow_bricks", true);
    public static final ModConfigSpec.BooleanValue ENABLE_PACKED_SNOW = BUILDER
            .define("enable_packed_snow", true);
    public static final ModConfigSpec.BooleanValue ENABLE_DIRT_SLABS = BUILDER
            .define("enable_dirt_slabs", true);
    public static final ModConfigSpec.BooleanValue ENABLE_SNIFFER_PLUSHIE = BUILDER
            .define("enable_sniffer_plushie", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CINDERSNAP_BERRY_JUICE = BUILDER
            .define("enable_cindersnap_berry_juice", true);
    public static final ModConfigSpec.BooleanValue ENABLE_FROSTBITE_BERRY_JUICE = BUILDER
            .define("enable_frostbite_berry_juice", true);
    public static final ModConfigSpec.BooleanValue ENABLE_CRIMSON_FORAGE_MIX = BUILDER
            .define("enable_crimson_forage_mix", true);
    public static final ModConfigSpec.BooleanValue ENABLE_WARPED_FORAGE_MIX = BUILDER
            .define("enable_warped_forage_mix", true);

    public static final ModConfigSpec MOD_SPEC = BUILDER.build();

    public static void register(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.SERVER, MOD_SPEC);
    }
}
