package rndm_access.assorteddiscoveries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rndm_access.assorteddiscoveries.core.*;

@Mod(AssortedDiscoveries.MOD_ID)
public class AssortedDiscoveries {
    public static final String MOD_ID = "assorteddiscoveries";
    public static final Logger LOGGER = LoggerFactory.getLogger("AssortedDiscoveries");
    private static final ResourceKey<@NotNull CreativeModeTab> MOD_CREATIVE_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, makeModId("item_group"));
    public static final CreativeModeTab MOD_CREATIVE_TAB = CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.ENDERMAN_PLUSHIE.asItem()))
            .title(Component.translatable("itemGroup." + MOD_ID))
            .build();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> ASSORTED_DISCOVERIES_TAB = CREATIVE_MODE_TABS.register("mod_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + MOD_ID))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ModBlocks.ENDERMAN_PLUSHIE.get().asItem().getDefaultInstance())
            .displayItems((parameters, output) -> {
                if (Config.ENABLE_SLIME_PLUSHIE.get()) {
                    output.accept(ModBlocks.SLIME_PLUSHIE.asItem());
                }

                if (Config.ENABLE_MAGMA_CUBE_PLUSHIE.get()) {
                    output.accept(ModBlocks.MAGMA_CUBE_PLUSHIE.asItem());
                }

                if (Config.ENABLE_CAT_PLUSHIES.get()) {
                    output.accept(ModBlocks.OCELOT_PLUSHIE.asItem());
                    output.accept(ModBlocks.WHITE_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.TABBY_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.TUXEDO_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.RED_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.SIAMESE_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.BRITISH_SHORTHAIR_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.CALICO_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.PERSIAN_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.RAGDOLL_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_CAT_PLUSHIE.asItem());
                    output.accept(ModBlocks.JELLIE_CAT_PLUSHIE.asItem());
                }

                if (Config.ENABLE_WOLF_PLUSHIES.get()) {
                    output.accept(ModBlocks.PALE_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.ASHEN_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.CHESTNUT_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.RUSTY_WOLF_PLUSHIE.asItem());
                }

                if (Config.ENABLE_ZOMBIE_PLUSHIE.get()) {
                    output.accept(ModBlocks.ZOMBIE_PLUSHIE.asItem());
                }

                if (Config.ENABLE_SKELETON_PLUSHIE.get()) {
                    output.accept(ModBlocks.SKELETON_PLUSHIE.asItem());
                }

                if (Config.ENABLE_ENDERMAN_PLUSHIE.get()) {
                    output.accept(ModBlocks.ENDERMAN_PLUSHIE.asItem());
                }

                if (Config.ENABLE_CREEPER_PLUSHIE.get()) {
                    output.accept(ModBlocks.CREEPER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_SPIDER_PLUSHIE.get()) {
                    output.accept(ModBlocks.SPIDER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_CAVE_SPIDER_PLUSHIE.get()) {
                    output.accept(ModBlocks.CAVE_SPIDER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_GUARDIAN_PLUSHIE.get()) {
                    output.accept(ModBlocks.GUARDIAN_PLUSHIE.asItem());
                }

                if (Config.ENABLE_PHANTOM_PLUSHIE.get()) {
                    output.accept(ModBlocks.PHANTOM_PLUSHIE.asItem());
                }

                if (Config.ENABLE_BAT_PLUSHIE.get()) {
                    output.accept(ModBlocks.BAT_PLUSHIE.asItem());
                }

                if (Config.ENABLE_SQUID_PLUSHIES.get()) {
                    output.accept(ModBlocks.SQUID_PLUSHIE.asItem());
                    output.accept(ModBlocks.GLOW_SQUID_PLUSHIE.asItem());
                }

                if (Config.ENABLE_BEE_PLUSHIE.get()) {
                    output.accept(ModBlocks.BEE_PLUSHIE.asItem());
                }

                if (Config.ENABLE_PIGLIN_PLUSHIES.get()) {
                    output.accept(ModBlocks.PIGLIN_PLUSHIE.asItem());
                    output.accept(ModBlocks.ZOMBIFIED_PIGLIN_PLUSHIE.asItem());
                }

                if (Config.ENABLE_HOGLIN_PLUSHIES.get()) {
                    output.accept(ModBlocks.HOGLIN_PLUSHIE.asItem());
                    output.accept(ModBlocks.ZOGLIN_PLUSHIE.asItem());
                }

                if (Config.ENABLE_GHAST_PLUSHIE.get()) {
                    output.accept(ModBlocks.GHAST_PLUSHIE.asItem());
                }

                if (Config.ENABLE_BLAZE_PLUSHIE.get()) {
                    output.accept(ModBlocks.BLAZE_PLUSHIE.asItem());
                }

                if (Config.ENABLE_STRIDER_PLUSHIES.get()) {
                    output.accept(ModBlocks.STRIDER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SHIVERING_STRIDER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_CHICKEN_PLUSHIES.get()) {
                    output.accept(ModBlocks.TEMPERATE_CHICKEN_PLUSHIE.asItem());
                }

                if (Config.ENABLE_PIG_PLUSHIES.get()) {
                    output.accept(ModBlocks.TEMPERATE_PIG_PLUSHIE.asItem());
                }

                if (Config.ENABLE_COW_PLUSHIES.get()) {
                    output.accept(ModBlocks.TEMPERATE_COW_PLUSHIE.asItem());
                }

                if (Config.ENABLE_MOOSHROOM_PLUSHIES.get()) {
                    output.accept(ModBlocks.RED_MOOSHROOM_PLUSHIE.asItem());
                    output.accept(ModBlocks.BROWN_MOOSHROOM_PLUSHIE.asItem());
                }

                if (Config.ENABLE_SHEEP_PLUSHIES.get()) {
                    output.accept(ModBlocks.WHITE_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.ORANGE_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.MAGENTA_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.LIGHT_BLUE_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.YELLOW_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.LIME_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.PINK_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.GRAY_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.LIGHT_GRAY_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.CYAN_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.PURPLE_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLUE_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.BROWN_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.RED_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.GREEN_SHEEP_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_SHEEP_PLUSHIE.asItem());
                }

                if (Config.ENABLE_HORSE_PLUSHIES.get()) {
                    output.accept(ModBlocks.WHITE_HORSE_PLUSHIE.asItem());
                    output.accept(ModBlocks.GRAY_HORSE_PLUSHIE.asItem());
                    output.accept(ModBlocks.BROWN_HORSE_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_HORSE_PLUSHIE.asItem());
                }

                if (Config.ENABLE_RABBIT_PLUSHIES.get()) {
                    output.accept(ModBlocks.BROWN_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.WHITE_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.WHITE_SPLOTCHED_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.GOLD_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.TOAST_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.SALT_RABBIT_PLUSHIE.asItem());
                }

                if (Config.ENABLE_ILLAGER_PLUSHIES.get()) {
                    output.accept(ModBlocks.PILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.VINDICATOR_PLUSHIE.asItem());
                    output.accept(ModBlocks.EVOKER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_VILLAGER_PLUSHIES.get()) {
                    output.accept(ModBlocks.PLAINS_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.DESERT_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.JUNGLE_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SAVANNA_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SNOWY_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SWAMP_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.TAIGA_VILLAGER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_WITCH_PLUSHIE.get()) {
                    output.accept(ModBlocks.WITCH_PLUSHIE.asItem());
                }

                if (Config.ENABLE_PUFFERFISH_PLUSHIE.get()) {
                    output.accept(ModBlocks.PUFFERFISH_PLUSHIE.asItem());
                }

                if (Config.ENABLE_WITHER_PLUSHIE.get()) {
                    output.accept(ModBlocks.WITHER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_ALLAY_PLUSHIE.get()) {
                    output.accept(ModBlocks.ALLAY_PLUSHIE.asItem());
                }

                if (Config.ENABLE_VEX_PLUSHIE.get()) {
                    output.accept(ModBlocks.VEX_PLUSHIE.asItem());
                }

                if (Config.ENABLE_SHULKER_PLUSHIE.get()) {
                    output.accept(ModBlocks.SHULKER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_CAMEL_PLUSHIE.get()) {
                    output.accept(ModBlocks.CAMEL_PLUSHIE.asItem());
                }

                if (Config.ENABLE_CREAKING_PLUSHIE.get()) {
                    output.accept(ModBlocks.CREAKING_PLUSHIE.asItem());
                }

                if (Config.ENABLE_SNIFFER_PLUSHIE.get()) {
                    output.accept(ModBlocks.SNIFFER_PLUSHIE.asItem());
                }

                if (Config.ENABLE_PLANTER_BOXES.get()) {
                    output.accept(ModBlocks.OAK_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.SPRUCE_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.BIRCH_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.JUNGLE_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.ACACIA_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.DARK_OAK_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.MANGROVE_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.CHERRY_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.BAMBOO_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.PALE_OAK_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.CRIMSON_PLANTER_BOX.asItem());
                    output.accept(ModBlocks.WARPED_PLANTER_BOX.asItem());
                }

                if (Config.ENABLE_WOODEN_WALLS.get()) {
                    output.accept(ModBlocks.OAK_WALL.asItem());
                    output.accept(ModBlocks.SPRUCE_WALL.asItem());
                    output.accept(ModBlocks.BIRCH_WALL.asItem());
                    output.accept(ModBlocks.JUNGLE_WALL.asItem());
                    output.accept(ModBlocks.ACACIA_WALL.asItem());
                    output.accept(ModBlocks.DARK_OAK_WALL.asItem());
                    output.accept(ModBlocks.MANGROVE_WALL.asItem());
                    output.accept(ModBlocks.CHERRY_WALL.asItem());
                    output.accept(ModBlocks.BAMBOO_WALL.asItem());
                    output.accept(ModBlocks.PALE_OAK_WALL.asItem());
                    output.accept(ModBlocks.CRIMSON_WALL.asItem());
                    output.accept(ModBlocks.WARPED_WALL.asItem());
                }

                if (Config.ENABLE_STRIPPED_WOODEN_WALLS.get()) {
                    output.accept(ModBlocks.STRIPPED_OAK_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_SPRUCE_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_BIRCH_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_JUNGLE_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_ACACIA_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_DARK_OAK_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_MANGROVE_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_CHERRY_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_BAMBOO_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_PALE_OAK_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_CRIMSON_WALL.asItem());
                    output.accept(ModBlocks.STRIPPED_WARPED_WALL.asItem());
                }

                if (Config.ENABLE_ROPE_LADDERS.get()) {
                    output.accept(ModBlocks.OAK_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.SPRUCE_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.BIRCH_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.JUNGLE_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.ACACIA_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.DARK_OAK_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.MANGROVE_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.CHERRY_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.BAMBOO_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.PALE_OAK_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.CRIMSON_ROPE_LADDER.asItem());
                    output.accept(ModBlocks.WARPED_ROPE_LADDER.asItem());
                }

                if (Config.ENABLE_IRON_LADDERS.get()) {
                    output.accept(ModBlocks.IRON_LADDER.asItem());
                }

                if (Config.ENABLE_DYED_CAMPFIRES.get()) {
                    output.accept(ModBlocks.WHITE_CAMPFIRE.asItem());
                    output.accept(ModBlocks.ORANGE_CAMPFIRE.asItem());
                    output.accept(ModBlocks.MAGENTA_CAMPFIRE.asItem());
                    output.accept(ModBlocks.LIGHT_BLUE_CAMPFIRE.asItem());
                    output.accept(ModBlocks.YELLOW_CAMPFIRE.asItem());
                    output.accept(ModBlocks.LIME_CAMPFIRE.asItem());
                    output.accept(ModBlocks.PINK_CAMPFIRE.asItem());
                    output.accept(ModBlocks.GRAY_CAMPFIRE.asItem());
                    output.accept(ModBlocks.LIGHT_GRAY_CAMPFIRE.asItem());
                    output.accept(ModBlocks.CYAN_CAMPFIRE.asItem());
                    output.accept(ModBlocks.PURPLE_CAMPFIRE.asItem());
                    output.accept(ModBlocks.BLUE_CAMPFIRE.asItem());
                    output.accept(ModBlocks.BROWN_CAMPFIRE.asItem());
                    output.accept(ModBlocks.GREEN_CAMPFIRE.asItem());
                    output.accept(ModBlocks.RED_CAMPFIRE.asItem());
                    output.accept(ModBlocks.BLACK_CAMPFIRE.asItem());
                }

                if (Config.ENABLE_DYED_LANTERNS.get()) {
                    output.accept(ModBlocks.WHITE_LANTERN.asItem());
                    output.accept(ModBlocks.ORANGE_LANTERN.asItem());
                    output.accept(ModBlocks.MAGENTA_LANTERN.asItem());
                    output.accept(ModBlocks.LIGHT_BLUE_LANTERN.asItem());
                    output.accept(ModBlocks.YELLOW_LANTERN.asItem());
                    output.accept(ModBlocks.LIME_LANTERN.asItem());
                    output.accept(ModBlocks.PINK_LANTERN.asItem());
                    output.accept(ModBlocks.GRAY_LANTERN.asItem());
                    output.accept(ModBlocks.LIGHT_GRAY_LANTERN.asItem());
                    output.accept(ModBlocks.CYAN_LANTERN.asItem());
                    output.accept(ModBlocks.PURPLE_LANTERN.asItem());
                    output.accept(ModBlocks.BLUE_LANTERN.asItem());
                    output.accept(ModBlocks.BROWN_LANTERN.asItem());
                    output.accept(ModBlocks.GREEN_LANTERN.asItem());
                    output.accept(ModBlocks.RED_LANTERN.asItem());
                    output.accept(ModBlocks.BLACK_LANTERN.asItem());
                }

                if (Config.ENABLE_DYED_TORCHES.get()) {
                    output.accept(ModItems.WHITE_TORCH);
                    output.accept(ModItems.ORANGE_TORCH);
                    output.accept(ModItems.MAGENTA_TORCH);
                    output.accept(ModItems.LIGHT_BLUE_TORCH);
                    output.accept(ModItems.YELLOW_TORCH);
                    output.accept(ModItems.LIME_TORCH);
                    output.accept(ModItems.PINK_TORCH);
                    output.accept(ModItems.GRAY_TORCH);
                    output.accept(ModItems.LIGHT_GRAY_TORCH);
                    output.accept(ModItems.CYAN_TORCH);
                    output.accept(ModItems.PURPLE_TORCH);
                    output.accept(ModItems.BLUE_TORCH);
                    output.accept(ModItems.BROWN_TORCH);
                    output.accept(ModItems.GREEN_TORCH);
                    output.accept(ModItems.RED_TORCH);
                    output.accept(ModItems.BLACK_TORCH);
                }

                if (Config.ENABLE_TWISTED_NETHERRACK.get()) {
                    output.accept(ModBlocks.TWISTED_NETHERRACK.asItem());
                    output.accept(ModBlocks.TWISTED_NETHERRACK_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_NETHERRACK_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_NETHERRACK_WALL.asItem());
                }

                if (Config.ENABLE_WEEPING_NETHERRACK.get()) {
                    output.accept(ModBlocks.WEEPING_NETHERRACK.asItem());
                    output.accept(ModBlocks.WEEPING_NETHERRACK_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_NETHERRACK_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_NETHERRACK_WALL.asItem());
                }

                if (Config.ENABLE_TWISTED_NETHER_BRICKS.get()) {
                    output.accept(ModBlocks.TWISTED_NETHER_BRICKS.asItem());
                    output.accept(ModBlocks.TWISTED_NETHER_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_NETHER_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_NETHER_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_WEEPING_NETHER_BRICKS.get()) {
                    output.accept(ModBlocks.WEEPING_NETHER_BRICKS.asItem());
                    output.accept(ModBlocks.WEEPING_NETHER_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_NETHER_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_NETHER_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_TWISTED_BLACKSTONE.get()) {
                    output.accept(ModBlocks.TWISTED_BLACKSTONE.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_WALL.asItem());
                }

                if (Config.ENABLE_WEEPING_BLACKSTONE.get()) {
                    output.accept(ModBlocks.WEEPING_BLACKSTONE.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_WALL.asItem());
                }

                if (Config.ENABLE_TWISTED_POLISHED_BLACKSTONE_BRICKS.get()) {
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_WEEPING_POLISHED_BLACKSTONE_BRICKS.get()) {
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_WALL.asItem());
                }

                boolean blackstoneTilesEnabled = Config.ENABLE_BLACKSTONE_TILES.get();
                if (blackstoneTilesEnabled) {
                    output.accept(ModBlocks.BLACKSTONE_TILES.asItem());
                    output.accept(ModBlocks.BLACKSTONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.BLACKSTONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.BLACKSTONE_TILE_WALL.asItem());
                }

                if (blackstoneTilesEnabled && Config.ENABLE_TWISTED_BLACKSTONE_TILES.get()) {
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILES.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILE_WALL.asItem());
                }

                if (blackstoneTilesEnabled && Config.ENABLE_WEEPING_BLACKSTONE_TILES.get()) {
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILES.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILE_WALL.asItem());
                }

                boolean smokyQuartzBlocksEnabled = Config.ENABLE_SMOKY_QUARTZ_BLOCKS.get();
                if (smokyQuartzBlocksEnabled) {
                    output.accept(ModBlocks.NETHER_SMOKY_QUARTZ_ORE.asItem());
                    output.accept(ModItems.SMOKY_QUARTZ);
                    output.accept(ModBlocks.SMOKY_QUARTZ_BLOCK.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_STAIRS.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_SLAB.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_WALL.asItem());
                    output.accept(ModBlocks.CHISELED_SMOKY_QUARTZ_BLOCK.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_PILLAR.asItem());
                }

                if (smokyQuartzBlocksEnabled && Config.ENABLE_SMOKY_QUARTZ_BRICKS.get()) {
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICKS.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICK_WALL.asItem());
                }

                if (smokyQuartzBlocksEnabled && Config.ENABLE_SMOOTH_SMOKY_QUARTZ.get()) {
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ.asItem());
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS.asItem());
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ_SLAB.asItem());
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ_WALL.asItem());
                }

                if (Config.ENABLE_QUARTZ_BRICK_BLOCKS.get()) {
                    output.accept(ModBlocks.QUARTZ_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.QUARTZ_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.QUARTZ_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_QUARTZ_TILES.get()) {
                    output.accept(ModBlocks.QUARTZ_TILES.asItem());
                    output.accept(ModBlocks.QUARTZ_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.QUARTZ_TILE_SLAB.asItem());
                    output.accept(ModBlocks.QUARTZ_TILE_WALL.asItem());
                }

                if (Config.ENABLE_QUARTZ_WALLS.get()) {
                    output.accept(ModBlocks.QUARTZ_WALL.asItem());
                    output.accept(ModBlocks.SMOOTH_QUARTZ_WALL.asItem());
                }

                boolean bauxiteEnabled = Config.ENABLE_BAUXITE.get();
                if (bauxiteEnabled) {
                    output.accept(ModBlocks.BAUXITE.asItem());
                    output.accept(ModBlocks.BAUXITE_SLAB.asItem());
                    output.accept(ModBlocks.BAUXITE_STAIRS.asItem());
                    output.accept(ModBlocks.BAUXITE_WALL.asItem());
                }

                boolean bauxiteBricksEnabled = Config.ENABLE_BAUXITE_BRICKS.get();
                if (bauxiteEnabled && bauxiteBricksEnabled) {
                    output.accept(ModBlocks.BAUXITE_BRICKS.asItem());
                    output.accept(ModBlocks.BAUXITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.BAUXITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.BAUXITE_BRICK_WALL.asItem());
                }

                if (bauxiteEnabled && bauxiteBricksEnabled && Config.ENABLE_CRACKED_BAUXITE_BRICKS.get()) {
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICKS.asItem());
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICK_WALL.asItem());
                }

                if (bauxiteEnabled && bauxiteBricksEnabled && Config.ENABLE_MOSSY_BAUXITE_BRICKS.get()) {
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICKS.asItem());
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICK_WALL.asItem());
                }

                boolean stoneTilesEnabled = Config.ENABLE_STONE_TILES.get();
                if (stoneTilesEnabled) {
                    output.accept(ModBlocks.STONE_TILES.asItem());
                    output.accept(ModBlocks.STONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.STONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.STONE_TILE_WALL.asItem());
                }

                if (stoneTilesEnabled && Config.ENABLE_CRACKED_STONE_TILES.get()) {
                    output.accept(ModBlocks.CRACKED_STONE_TILES.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_TILE_WALL.asItem());
                }

                if (Config.ENABLE_MOSSY_STONE_TILES.get()) {
                    output.accept(ModBlocks.MOSSY_STONE_TILES.asItem());
                    output.accept(ModBlocks.MOSSY_STONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_STONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_STONE_TILE_WALL.asItem());
                }

                if (Config.ENABLE_CRACKED_STONE_BRICK_BLOCKS.get()) {
                    output.accept(ModBlocks.CRACKED_STONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_STONE_WALLS.get()) {
                    output.accept(ModBlocks.STONE_WALL.asItem());
                }

                if (Config.ENABLE_CALCITE_BLOCKS.get()) {
                    output.accept(ModBlocks.CALCITE_STAIRS.asItem());
                    output.accept(ModBlocks.CALCITE_SLAB.asItem());
                    output.accept(ModBlocks.CALCITE_WALL.asItem());
                }

                if (Config.ENABLE_POLISHED_CALCITE.get()) {
                    output.accept(ModBlocks.POLISHED_CALCITE.asItem());
                    output.accept(ModBlocks.POLISHED_CALCITE_STAIRS.asItem());
                    output.accept(ModBlocks.POLISHED_CALCITE_SLAB.asItem());
                    output.accept(ModBlocks.POLISHED_CALCITE_WALL.asItem());
                }

                boolean calciteBricksEnabled = Config.ENABLE_CALCITE_BRICKS.get();
                if (calciteBricksEnabled) {
                    output.accept(ModBlocks.CALCITE_BRICKS.asItem());
                    output.accept(ModBlocks.CALCITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CALCITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CALCITE_BRICK_WALL.asItem());
                    output.accept(ModBlocks.CHISELED_CALCITE_BRICKS.asItem());
                }

                if (Config.ENABLE_CRACKED_CALCITE_BRICKS.get() && calciteBricksEnabled) {
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICKS.asItem());
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_MOSSY_CALCITE_BRICKS.get() && calciteBricksEnabled) {
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICKS.asItem());
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_DRIPSTONE_BLOCKS.get()) {
                    output.accept(ModBlocks.DRIPSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.DRIPSTONE_SLAB.asItem());
                    output.accept(ModBlocks.DRIPSTONE_WALL.asItem());
                }

                if (Config.ENABLE_POLISHED_DRIPSTONE.get()) {
                    output.accept(ModBlocks.POLISHED_DRIPSTONE.asItem());
                    output.accept(ModBlocks.POLISHED_DRIPSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.POLISHED_DRIPSTONE_SLAB.asItem());
                    output.accept(ModBlocks.POLISHED_DRIPSTONE_WALL.asItem());
                }

                boolean dripstoneBricksEnabled = Config.ENABLE_DRIPSTONE_BRICKS.get();
                if (dripstoneBricksEnabled) {
                    output.accept(ModBlocks.DRIPSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.DRIPSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.DRIPSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.DRIPSTONE_BRICK_WALL.asItem());
                    output.accept(ModBlocks.CHISELED_DRIPSTONE_BRICKS.asItem());
                }

                if (Config.ENABLE_CRACKED_DRIPSTONE_BRICKS.get() && dripstoneBricksEnabled) {
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_MOSSY_DRIPSTONE_BRICKS.get() && dripstoneBricksEnabled) {
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_SNOW_BRICKS.get()) {
                    output.accept(ModBlocks.SNOW_BRICKS.asItem());
                    output.accept(ModBlocks.SNOW_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.SNOW_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.SNOW_BRICK_WALL.asItem());
                }

                if (Config.ENABLE_PACKED_SNOW.get()) {
                    output.accept(ModBlocks.PACKED_SNOW.asItem());
                    output.accept(ModBlocks.PACKED_SNOW_STAIRS.asItem());
                    output.accept(ModBlocks.PACKED_SNOW_SLAB.asItem());
                    output.accept(ModBlocks.PACKED_SNOW_WALL.asItem());
                }

                if (Config.ENABLE_DIRT_SLABS.get()) {
                    output.accept(ModBlocks.GRASS_SLAB.asItem());
                    output.accept(ModBlocks.PODZOL_SLAB.asItem());
                    output.accept(ModBlocks.MYCELIUM_SLAB.asItem());
                    output.accept(ModBlocks.DIRT_PATH_SLAB.asItem());
                    output.accept(ModBlocks.DIRT_SLAB.asItem());
                    output.accept(ModBlocks.ROOTED_DIRT_SLAB.asItem());
                    output.accept(ModBlocks.COARSE_DIRT_SLAB.asItem());
                }

                if (Config.ENABLE_PURPLE_MUSHROOMS.get()) {
                    output.accept(ModBlocks.PURPLE_MUSHROOM_BLOCK.asItem());
                }

                if (Config.ENABLE_CATTAILS.get()) {
                    output.accept(ModBlocks.CATTAIL.asItem());
                }

                if (Config.ENABLE_BOG_BLOSSOMS.get()) {
                    output.accept(ModBlocks.BOG_BLOSSOM.asItem());
                }

                if (Config.ENABLE_ENDER_PLANTS.get()) {
                    output.accept(ModBlocks.SNAPDRAGON.asItem());
                    output.accept(ModBlocks.SHORT_ENDER_GRASS.asItem());
                }

                if (Config.ENABLE_PURPLE_MUSHROOMS.get()) {
                    output.accept(ModBlocks.PURPLE_MUSHROOM.asItem());
                }

                if (Config.ENABLE_BLOOD_KELP.get()) {
                    output.accept(ModBlocks.DRIED_BLOOD_KELP_BLOCK);
                    output.accept(ModBlocks.BLOOD_KELP_LANTERN);
                    output.accept(ModItems.BLOOD_KELP_SEED_CLUSTER);
                    output.accept(ModItems.BLOOD_KELP);
                    output.accept(ModItems.DRIED_BLOOD_KELP);
                }

                if (Config.ENABLE_GREEN_ONIONS.get()) {
                    output.accept(ModBlocks.WILD_GREEN_ONIONS.asItem());
                    output.accept(ModItems.GREEN_ONION_SEEDS);
                    output.accept(ModItems.GREEN_ONION);
                }

                boolean caramelAppleEnabled = Config.ENABLE_CARAMEL_APPLE.get();
                if (caramelAppleEnabled) {
                    output.accept(ModItems.CARAMEL);
                }

                boolean forestsBountyEnabled = Config.ENABLE_FORESTS_BOUNTY.get();
                if (forestsBountyEnabled) {
                    output.accept(ModItems.SPRUCE_CONE);
                }

                boolean noodleSoupEnabled = Config.ENABLE_NOODLE_SOUP.get();
                if (noodleSoupEnabled) {
                    output.accept(ModItems.NOODLES);
                }

                boolean witchsCradleEnabled = Config.ENABLE_WITCHS_CRADLES.get();
                if (witchsCradleEnabled) {
                    output.accept(ModItems.WITCHS_CRADLE_BRANCH);
                }

                boolean blueberriesEnabled = Config.ENABLE_BLUEBERRIES.get();
                if (blueberriesEnabled) {
                    output.accept(ModItems.BLUEBERRIES);
                }

                boolean cindersnapBerriesEnabled = Config.ENABLE_CINDERSNAP_BERRIES.get();
                if (cindersnapBerriesEnabled) {
                    output.accept(ModItems.CINDERSNAP_BERRIES);
                }

                boolean frostbiteBerriesEnabled = Config.ENABLE_FROSTBITE_BERRIES.get();
                if (frostbiteBerriesEnabled) {
                    output.accept(ModItems.FROSTBITE_BERRIES);
                }

                if (Config.ENABLE_FRIED_EGG.get()) {
                    output.accept(ModItems.FRIED_EGG);
                }

                if (caramelAppleEnabled) {
                    output.accept(ModItems.CARAMEL_APPLE);
                }

                if (forestsBountyEnabled) {
                    output.accept(ModItems.FORESTS_BOUNTY);
                }

                if (noodleSoupEnabled) {
                    output.accept(ModItems.NOODLE_SOUP);
                }

                if (Config.ENABLE_HOGLIN_STEW.get()) {
                    output.accept(ModItems.HOGLIN_STEW);
                }

                if (witchsCradleEnabled) {
                    output.accept(ModItems.WITCHS_CRADLE_SOUP);
                }

                if (Config.ENABLE_PUDDING.get()) {
                    output.accept(ModItems.BERRY_PUDDING);
                    output.accept(ModItems.PUDDING);
                }

                if (frostbiteBerriesEnabled && Config.ENABLE_WARPED_FORAGE_MIX.get()) {
                    output.accept(ModItems.WARPED_FORAGE_MIX);
                }

                if (cindersnapBerriesEnabled && Config.ENABLE_CRIMSON_FORAGE_MIX.get()) {
                    output.accept(ModItems.CRIMSON_FORAGE_MIX);
                }

                if (blueberriesEnabled && Config.ENABLE_BLUEBERRY_JUICE.get()) {
                    output.accept(ModItems.BLUEBERRY_JUICE);
                }

                if (Config.ENABLE_SWEET_BERRY_JUICE.get()) {
                    output.accept(ModItems.SWEET_BERRY_JUICE);
                }

                if (Config.ENABLE_CINDERSNAP_BERRY_JUICE.get() && cindersnapBerriesEnabled) {
                    output.accept(ModItems.CINDERSNAP_BERRY_JUICE);
                }

                if (Config.ENABLE_FROSTBITE_BERRY_JUICE.get() && frostbiteBerriesEnabled) {
                    output.accept(ModItems.FROSTBITE_BERRY_JUICE);
                }

                if (blueberriesEnabled && Config.ENABLE_BLUEBERRY_PIE.get()) {
                    output.accept(ModBlocks.BLUEBERRY_PIE.asItem());
                }

                if (Config.ENABLE_SWEET_BERRY_PIE.get()) {
                    output.accept(ModBlocks.SWEET_BERRY_PIE.asItem());
                }

                if (Config.ENABLE_CHOCOLATE_CAKE.get()) {
                    output.accept(ModBlocks.CHOCOLATE_CAKE.asItem());
                }

                if (Config.ENABLE_RED_VELVET_CAKE.get()) {
                    output.accept(ModBlocks.RED_VELVET_CAKE.asItem());
                }
            }).build());

    public AssortedDiscoveries(IEventBus modEventBus, ModContainer modContainer) {
        // Config
        Config.register(modContainer);
        //AssortedDiscoveries.registerConfigEvents();
        ModResourceConditionTypes.register(modEventBus);

        // General Registries
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ModBlockEntityTypes.register(modEventBus);
        ModParticleTypes.register(modEventBus);
        ModSoundEvents.register(modEventBus);
        //AssortedDiscoveries.modifyLootTables();

        // World Generation Registries
        ModFeatures.register(modEventBus);
    }

    @SubscribeEvent
    public static void livingEntityFallEvent(LivingFallEvent event) {
        boolean isRabbit = event.getEntity().getType() == EntityType.RABBIT;
        boolean isInRange = (Math.max(event.getDistance() - 4.0F, 0.0F)) == 0.0F;

        if (isRabbit && isInRange) {
            event.setCanceled(true);
        }
    }

    public static Identifier makeModId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    /*
    TODO: Maybe remove
    private static void registerConfigEvents() {
        PayloadTypeRegistry.clientboundPlay().register(BooleanEntriesS2CPayload.ID, BooleanEntriesS2CPayload.CODEC);

        ServerLifecycleEvents.SERVER_STARTED.register(AssortedDiscoveries::initConfigOnServer);
        ServerPlayerEvents.JOIN.register(AssortedDiscoveries::onJoin);
    }
    */

    /*
    private static void initConfigOnServer(MinecraftServer server) {
        if (!server.overworld().isClientSide()) {
            ModClientConfig.updateBoolEntries(ModServerConfig.getInstance().toEntryMap());
            LOGGER.info("Loaded server config");
        }
    }
    */

    /*
    @SuppressWarnings("resource")
    private static void onJoin(ServerPlayer player) {
        // If I use the auto-closable on level it closes the world too early and breaks loading!
        if (!player.level().isClientSide()) {
            sendConfigEntriesToPlayers(player);
        }
    }

    private static void sendConfigEntriesToPlayers(ServerPlayer player) {
        BooleanEntriesS2CPayload payload = new BooleanEntriesS2CPayload(ModClientConfig.getBoolEntries());
        String playerName = player.getName().getString();

        if (ServerPlayNetworking.canSend(player, payload.type())) {
            ServerPlayNetworking.send(player, payload);
            LOGGER.info("Sent server config data to {}!", playerName);
        }
    }
    */
}
