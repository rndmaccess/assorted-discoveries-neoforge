package rndm_access.assorteddiscoveries.config;

import net.fabricmc.loader.api.FabricLoader;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.config.json.*;
import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigDeserializer;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.BooleanConfigEntry;
import rndm_access.assorteddiscoveries.config.json.deserializer.ConfigCategory;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.CommentConfigEntry;
import rndm_access.assorteddiscoveries.config.json.exceptions.JsonSyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModServerConfig {
    private static String configError;
    private static volatile ServerConfig server_config;

    public static String getConfigError() {
        return configError;
    }

    public static synchronized ServerConfig getInstance() {
        if (server_config == null) {
            server_config = createOrLoad();
        }
        return server_config;
    }

    /**
     * This method will reparse the config from the config file!
     */
    public static synchronized void update() {
        server_config = createOrLoad();
    }

    private static ServerConfig createOrLoad() {
        ServerConfig defaultConfig = getDefaultConfig();
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve(AssortedDiscoveries.MOD_ID + ".json5");

        if (!Files.exists(configPath)) {
            defaultConfig.create();
            return defaultConfig;
        }
        return loadConfig(defaultConfig);
    }

    /**
     * This method loads the config from the server (The local config). This representation should only be used in
     * places that primarily run on the server such as in datapacks. For client side code such as item group additions
     * use the read-only hashmap representation found in the {@link ModClientConfig} class!
     *
     * @param defaultConfig A config that has all of its values set to their defaults. This config is merged with the
     *                      user config to autofill in default values so they are added to
     *                      the user config after its loaded if no exception occurs!
     * @return The server config with every category, entry, and comment from the config file.
     *         If there is an exception then the defaultConfig.
     */
    private static ServerConfig loadConfig(ServerConfig defaultConfig) {
        try {
            ConfigDeserializer deserializer = new ConfigDeserializer(AssortedDiscoveries.MOD_ID);
            ServerConfig loadedConfig = deserializer.deserialize();
            ServerConfig newConfig = defaultConfig.merge(loadedConfig);
            newConfig.save(); // Re-save the config with the values in memory when we load it so
                              // we can ensure any new default values are in the config file!
            return newConfig;
        } catch (IOException e) {
            AssortedDiscoveries.LOGGER.error("The config file is unreadable! Using the default config!");
            return defaultConfig;
        } catch (JsonSyntaxException e) {
            String errorMessage = e.getMessage();
            AssortedDiscoveries.LOGGER.error("Using the default config, because the config file could not be loaded:");
            AssortedDiscoveries.LOGGER.error(errorMessage);
            configError = errorMessage;
            return defaultConfig;
        }
    }

    private static ServerConfig getDefaultConfig() {
        CommentConfigEntry blackstoneTileComment = new CommentConfigEntry("This option requires " +
                "blackstone tiles!");
        CommentConfigEntry smokyQuartzBlocksComment = new CommentConfigEntry("This option requires " +
                "smoky quartz blocks!");
        CommentConfigEntry quartzBrickComment = new CommentConfigEntry("Whether quartz brick slabs, " +
                "quartz brick walls, and quartz brick stairs are enabled!");
        CommentConfigEntry bauxiteComment = new CommentConfigEntry("This option requires bauxite!");
        CommentConfigEntry requiredRestartComment = new CommentConfigEntry("Each option in the config " +
                "requires a game restart!");

        ConfigCategory buildingBlocksCategory = new ConfigCategory.Builder("building_blocks")
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PLANTER_BOXES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_DYED_CAMPFIRES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_DYED_LANTERNS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_DYED_TORCHES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WOODEN_WALLS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_STRIPPED_WOODEN_WALLS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_ROPE_LADDERS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_IRON_LADDERS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BLACKSTONE_TILES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_TWISTED_BLACKSTONE))
                .addComment(blackstoneTileComment)
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_TWISTED_BLACKSTONE_TILES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_TWISTED_NETHERRACK))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_TWISTED_NETHER_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_TWISTED_POLISHED_BLACKSTONE_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WEEPING_NETHERRACK))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WEEPING_NETHER_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WEEPING_BLACKSTONE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WEEPING_POLISHED_BLACKSTONE_BRICKS))
                .addComment(blackstoneTileComment)
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WEEPING_BLACKSTONE_TILES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SMOKY_QUARTZ_BLOCKS))
                .addComment(smokyQuartzBlocksComment)
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SMOKY_QUARTZ_BRICKS))
                .addComment(smokyQuartzBlocksComment)
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SMOOTH_SMOKY_QUARTZ))
                .addComment(quartzBrickComment)
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_QUARTZ_BRICK_BLOCKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_QUARTZ_TILES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_QUARTZ_WALLS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BAUXITE))
                .addComment(bauxiteComment)
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BAUXITE_BRICKS))
                .addComment(new CommentConfigEntry("This option requires bauxite and bauxite bricks!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CRACKED_BAUXITE_BRICKS))
                .addComment(new CommentConfigEntry("This option requires bauxite and bauxite bricks!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_MOSSY_BAUXITE_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_STONE_TILES))
                .addComment(new CommentConfigEntry("This option requires stone tiles!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CRACKED_STONE_TILES))
                .addComment(new CommentConfigEntry("This option requires stone tiles!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_MOSSY_STONE_TILES))
                .addComment(new CommentConfigEntry("Whether cracked stone brick slabs, " +
                        "cracked stone brick walls, and cracked stone brick stairs are enabled!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CRACKED_STONE_BRICK_BLOCKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_STONE_WALLS))
                .addComment(new CommentConfigEntry("Whether calcite slabs, calcite walls, " +
                        "and calcite stairs are enabled!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CALCITE_BLOCKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_POLISHED_CALCITE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CALCITE_BRICKS))
                .addComment(new CommentConfigEntry("This option requires calcite bricks!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CRACKED_CALCITE_BRICKS))
                .addComment(new CommentConfigEntry("This option requires calcite bricks!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_MOSSY_CALCITE_BRICKS))
                .addComment(new CommentConfigEntry("Whether dripstone slabs, " +
                        "dripstone walls, and dripstone stairs are enabled!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_DRIPSTONE_BLOCKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_POLISHED_DRIPSTONE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_DRIPSTONE_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CRACKED_DRIPSTONE_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_MOSSY_DRIPSTONE_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SNOW_BRICKS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PACKED_SNOW))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_DIRT_SLABS)).build();

        ConfigCategory plushiesCategory = new ConfigCategory.Builder("plushies")
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_ALLAY_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BAT_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CAMEL_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WOLF_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CAT_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CHICKEN_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_COW_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_HORSE_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_MOOSHROOM_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PIG_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PUFFERFISH_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_RABBIT_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SHEEP_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SQUID_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_STRIDER_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_VILLAGER_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SNIFFER_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BEE_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CAVE_SPIDER_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_ENDERMAN_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PIGLIN_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SPIDER_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BLAZE_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CREEPER_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_GHAST_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_GUARDIAN_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_HOGLIN_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_ILLAGER_PLUSHIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_MAGMA_CUBE_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PHANTOM_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SHULKER_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SKELETON_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SLIME_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_VEX_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WITCH_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WITHER_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_ZOMBIE_PLUSHIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CREAKING_PLUSHIE)).build();

        ConfigCategory foodCategory = new ConfigCategory.Builder("foods")
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_GREEN_ONIONS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_NOODLE_SOUP))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CHOCOLATE_CAKE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_RED_VELVET_CAKE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_FRIED_EGG))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_HOGLIN_STEW))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_FORESTS_BOUNTY))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PUDDING))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CARAMEL_APPLE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SWEET_BERRY_PIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_SWEET_BERRY_JUICE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BLUEBERRIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BLUEBERRY_PIE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BLUEBERRY_JUICE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CINDERSNAP_BERRIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_FROSTBITE_BERRIES))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CINDERSNAP_BERRY_JUICE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CRIMSON_FORAGE_MIX))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_FROSTBITE_BERRY_JUICE))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WARPED_FORAGE_MIX)).build();

        ConfigCategory plantsCategory = new ConfigCategory.Builder("plants")
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BLOOD_KELP))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_PURPLE_MUSHROOMS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_CATTAILS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_BOG_BLOSSOMS))
                .addComment(new CommentConfigEntry("Whether patches of ender grass and snapdragons should spawn!"))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_ENDER_PLANTS))
                .addEntry(new BooleanConfigEntry(ModServerConfigKeys.ENABLE_WITCHS_CRADLES)).build();

        ServerConfig.Builder config = new ServerConfig.Builder(AssortedDiscoveries.MOD_ID)
                .addComment(requiredRestartComment)
                .addCategory(buildingBlocksCategory)
                .addCategory(plushiesCategory)
                .addCategory(foodCategory)
                .addCategory(plantsCategory);
        return config.build();
    }
}
