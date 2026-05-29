package rndm_access.assorteddiscoveries;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rndm_access.assorteddiscoveries.config.BooleanEntriesS2CPayload;
import rndm_access.assorteddiscoveries.config.ModServerConfig;
import rndm_access.assorteddiscoveries.config.ModServerConfigKeys;
import rndm_access.assorteddiscoveries.config.ModClientConfig;
import rndm_access.assorteddiscoveries.config.json.ServerConfig;
import rndm_access.assorteddiscoveries.config.json.deserializer.entries.BooleanConfigEntry;
import rndm_access.assorteddiscoveries.core.*;

import java.util.Optional;

@Mod(AssortedDiscoveries.MOD_ID)
public class AssortedDiscoveries {
    public static final String MOD_ID = "assorted-discoveries";
    public static final Logger LOGGER = LoggerFactory.getLogger("AssortedDiscoveries");
    private static final ResourceKey<@NotNull CreativeModeTab> MOD_CREATIVE_TAB_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB, makeModId("item_group"));
    public static final CreativeModeTab MOD_CREATIVE_TAB = CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.ENDERMAN_PLUSHIE.asItem()))
            .title(Component.translatable("itemGroup." + MOD_ID))
            .build();

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.assorteddiscoveries")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SLIME_PLUSHIE)) {
                    output.accept(ModBlocks.SLIME_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_MAGMA_CUBE_PLUSHIE)) {
                    output.accept(ModBlocks.MAGMA_CUBE_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CAT_PLUSHIES)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WOLF_PLUSHIES)) {
                    output.accept(ModBlocks.PALE_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.ASHEN_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.CHESTNUT_WOLF_PLUSHIE.asItem());
                    output.accept(ModBlocks.RUSTY_WOLF_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ZOMBIE_PLUSHIE)) {
                    output.accept(ModBlocks.ZOMBIE_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SKELETON_PLUSHIE)) {
                    output.accept(ModBlocks.SKELETON_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ENDERMAN_PLUSHIE)) {
                    output.accept(ModBlocks.ENDERMAN_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CREEPER_PLUSHIE)) {
                    output.accept(ModBlocks.CREEPER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SPIDER_PLUSHIE)) {
                    output.accept(ModBlocks.SPIDER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CAVE_SPIDER_PLUSHIE)) {
                    output.accept(ModBlocks.CAVE_SPIDER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_GUARDIAN_PLUSHIE)) {
                    output.accept(ModBlocks.GUARDIAN_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PHANTOM_PLUSHIE)) {
                    output.accept(ModBlocks.PHANTOM_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BAT_PLUSHIE)) {
                    output.accept(ModBlocks.BAT_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SQUID_PLUSHIES)) {
                    output.accept(ModBlocks.SQUID_PLUSHIE.asItem());
                    output.accept(ModBlocks.GLOW_SQUID_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BEE_PLUSHIE)) {
                    output.accept(ModBlocks.BEE_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PIGLIN_PLUSHIES)) {
                    output.accept(ModBlocks.PIGLIN_PLUSHIE.asItem());
                    output.accept(ModBlocks.ZOMBIFIED_PIGLIN_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_HOGLIN_PLUSHIES)) {
                    output.accept(ModBlocks.HOGLIN_PLUSHIE.asItem());
                    output.accept(ModBlocks.ZOGLIN_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_GHAST_PLUSHIE)) {
                    output.accept(ModBlocks.GHAST_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BLAZE_PLUSHIE)) {
                    output.accept(ModBlocks.BLAZE_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_STRIDER_PLUSHIES)) {
                    output.accept(ModBlocks.STRIDER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SHIVERING_STRIDER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CHICKEN_PLUSHIES)) {
                    output.accept(ModBlocks.TEMPERATE_CHICKEN_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PIG_PLUSHIES)) {
                    output.accept(ModBlocks.TEMPERATE_PIG_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_COW_PLUSHIES)) {
                    output.accept(ModBlocks.TEMPERATE_COW_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_MOOSHROOM_PLUSHIES)) {
                    output.accept(ModBlocks.RED_MOOSHROOM_PLUSHIE.asItem());
                    output.accept(ModBlocks.BROWN_MOOSHROOM_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SHEEP_PLUSHIES)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_HORSE_PLUSHIES)) {
                    output.accept(ModBlocks.WHITE_HORSE_PLUSHIE.asItem());
                    output.accept(ModBlocks.GRAY_HORSE_PLUSHIE.asItem());
                    output.accept(ModBlocks.BROWN_HORSE_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_HORSE_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_RABBIT_PLUSHIES)) {
                    output.accept(ModBlocks.BROWN_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.WHITE_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.BLACK_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.WHITE_SPLOTCHED_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.GOLD_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.TOAST_RABBIT_PLUSHIE.asItem());
                    output.accept(ModBlocks.SALT_RABBIT_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ILLAGER_PLUSHIES)) {
                    output.accept(ModBlocks.PILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.VINDICATOR_PLUSHIE.asItem());
                    output.accept(ModBlocks.EVOKER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_VILLAGER_PLUSHIES)) {
                    output.accept(ModBlocks.PLAINS_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.DESERT_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.JUNGLE_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SAVANNA_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SNOWY_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.SWAMP_VILLAGER_PLUSHIE.asItem());
                    output.accept(ModBlocks.TAIGA_VILLAGER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WITCH_PLUSHIE)) {
                    output.accept(ModBlocks.WITCH_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PUFFERFISH_PLUSHIE)) {
                    output.accept(ModBlocks.PUFFERFISH_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WITHER_PLUSHIE)) {
                    output.accept(ModBlocks.WITHER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ALLAY_PLUSHIE)) {
                    output.accept(ModBlocks.ALLAY_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_VEX_PLUSHIE)) {
                    output.accept(ModBlocks.VEX_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SHULKER_PLUSHIE)) {
                    output.accept(ModBlocks.SHULKER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CAMEL_PLUSHIE)) {
                    output.accept(ModBlocks.CAMEL_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CREAKING_PLUSHIE)) {
                    output.accept(ModBlocks.CREAKING_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SNIFFER_PLUSHIE)) {
                    output.accept(ModBlocks.SNIFFER_PLUSHIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PLANTER_BOXES)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WOODEN_WALLS)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_STRIPPED_WOODEN_WALLS)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ROPE_LADDERS)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_IRON_LADDERS)) {
                    output.accept(ModBlocks.IRON_LADDER.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_DYED_CAMPFIRES)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_DYED_LANTERNS)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_DYED_TORCHES)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_TWISTED_NETHERRACK)) {
                    output.accept(ModBlocks.TWISTED_NETHERRACK.asItem());
                    output.accept(ModBlocks.TWISTED_NETHERRACK_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_NETHERRACK_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_NETHERRACK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WEEPING_NETHERRACK)) {
                    output.accept(ModBlocks.WEEPING_NETHERRACK.asItem());
                    output.accept(ModBlocks.WEEPING_NETHERRACK_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_NETHERRACK_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_NETHERRACK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_TWISTED_NETHER_BRICKS)) {
                    output.accept(ModBlocks.TWISTED_NETHER_BRICKS.asItem());
                    output.accept(ModBlocks.TWISTED_NETHER_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_NETHER_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_NETHER_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WEEPING_NETHER_BRICKS)) {
                    output.accept(ModBlocks.WEEPING_NETHER_BRICKS.asItem());
                    output.accept(ModBlocks.WEEPING_NETHER_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_NETHER_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_NETHER_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_TWISTED_BLACKSTONE)) {
                    output.accept(ModBlocks.TWISTED_BLACKSTONE.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WEEPING_BLACKSTONE)) {
                    output.accept(ModBlocks.WEEPING_BLACKSTONE.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_TWISTED_POLISHED_BLACKSTONE_BRICKS)) {
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_POLISHED_BLACKSTONE_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WEEPING_POLISHED_BLACKSTONE_BRICKS)) {
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_POLISHED_BLACKSTONE_BRICK_WALL.asItem());
                }

                boolean blackstoneTilesEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BLACKSTONE_TILES);
                if (blackstoneTilesEnabled) {
                    output.accept(ModBlocks.BLACKSTONE_TILES.asItem());
                    output.accept(ModBlocks.BLACKSTONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.BLACKSTONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.BLACKSTONE_TILE_WALL.asItem());
                }

                if (blackstoneTilesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_TWISTED_BLACKSTONE_TILES)) {
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILES.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.TWISTED_BLACKSTONE_TILE_WALL.asItem());
                }

                if (blackstoneTilesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WEEPING_BLACKSTONE_TILES)) {
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILES.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.WEEPING_BLACKSTONE_TILE_WALL.asItem());
                }

                boolean smokyQuartzBlocksEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SMOKY_QUARTZ_BLOCKS);
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

                if (smokyQuartzBlocksEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SMOKY_QUARTZ_BRICKS)) {
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICKS.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.SMOKY_QUARTZ_BRICK_WALL.asItem());
                }

                if (smokyQuartzBlocksEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SMOOTH_SMOKY_QUARTZ)) {
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ.asItem());
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ_STAIRS.asItem());
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ_SLAB.asItem());
                    output.accept(ModBlocks.SMOOTH_SMOKY_QUARTZ_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_QUARTZ_BRICK_BLOCKS)) {
                    output.accept(ModBlocks.QUARTZ_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.QUARTZ_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.QUARTZ_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_QUARTZ_TILES)) {
                    output.accept(ModBlocks.QUARTZ_TILES.asItem());
                    output.accept(ModBlocks.QUARTZ_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.QUARTZ_TILE_SLAB.asItem());
                    output.accept(ModBlocks.QUARTZ_TILE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_QUARTZ_WALLS)) {
                    output.accept(ModBlocks.QUARTZ_WALL.asItem());
                    output.accept(ModBlocks.SMOOTH_QUARTZ_WALL.asItem());
                }

                boolean bauxiteEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BAUXITE);
                if (bauxiteEnabled) {
                    output.accept(ModBlocks.BAUXITE.asItem());
                    output.accept(ModBlocks.BAUXITE_SLAB.asItem());
                    output.accept(ModBlocks.BAUXITE_STAIRS.asItem());
                    output.accept(ModBlocks.BAUXITE_WALL.asItem());
                }

                boolean bauxiteBricksEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BAUXITE_BRICKS);
                if (bauxiteEnabled && bauxiteBricksEnabled) {
                    output.accept(ModBlocks.BAUXITE_BRICKS.asItem());
                    output.accept(ModBlocks.BAUXITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.BAUXITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.BAUXITE_BRICK_WALL.asItem());
                }

                if (bauxiteEnabled && bauxiteBricksEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CRACKED_BAUXITE_BRICKS)) {
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICKS.asItem());
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_BAUXITE_BRICK_WALL.asItem());
                }

                if (bauxiteEnabled && bauxiteBricksEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_MOSSY_BAUXITE_BRICKS)) {
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICKS.asItem());
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_BAUXITE_BRICK_WALL.asItem());
                }

                boolean stoneTilesEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_STONE_TILES);
                if (stoneTilesEnabled) {
                    output.accept(ModBlocks.STONE_TILES.asItem());
                    output.accept(ModBlocks.STONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.STONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.STONE_TILE_WALL.asItem());
                }

                if (stoneTilesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CRACKED_STONE_TILES)) {
                    output.accept(ModBlocks.CRACKED_STONE_TILES.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_TILE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_MOSSY_STONE_TILES)) {
                    output.accept(ModBlocks.MOSSY_STONE_TILES.asItem());
                    output.accept(ModBlocks.MOSSY_STONE_TILE_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_STONE_TILE_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_STONE_TILE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CRACKED_STONE_BRICK_BLOCKS)) {
                    output.accept(ModBlocks.CRACKED_STONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_STONE_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_STONE_WALLS)) {
                    output.accept(ModBlocks.STONE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CALCITE_BLOCKS)) {
                    output.accept(ModBlocks.CALCITE_STAIRS.asItem());
                    output.accept(ModBlocks.CALCITE_SLAB.asItem());
                    output.accept(ModBlocks.CALCITE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_POLISHED_CALCITE)) {
                    output.accept(ModBlocks.POLISHED_CALCITE.asItem());
                    output.accept(ModBlocks.POLISHED_CALCITE_STAIRS.asItem());
                    output.accept(ModBlocks.POLISHED_CALCITE_SLAB.asItem());
                    output.accept(ModBlocks.POLISHED_CALCITE_WALL.asItem());
                }

                boolean calciteBricksEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CALCITE_BRICKS);
                if (calciteBricksEnabled) {
                    output.accept(ModBlocks.CALCITE_BRICKS.asItem());
                    output.accept(ModBlocks.CALCITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CALCITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CALCITE_BRICK_WALL.asItem());
                    output.accept(ModBlocks.CHISELED_CALCITE_BRICKS.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CRACKED_CALCITE_BRICKS) && calciteBricksEnabled) {
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICKS.asItem());
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_CALCITE_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_MOSSY_CALCITE_BRICKS) && calciteBricksEnabled) {
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICKS.asItem());
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_CALCITE_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_DRIPSTONE_BLOCKS)) {
                    output.accept(ModBlocks.DRIPSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.DRIPSTONE_SLAB.asItem());
                    output.accept(ModBlocks.DRIPSTONE_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_POLISHED_DRIPSTONE)) {
                    output.accept(ModBlocks.POLISHED_DRIPSTONE.asItem());
                    output.accept(ModBlocks.POLISHED_DRIPSTONE_STAIRS.asItem());
                    output.accept(ModBlocks.POLISHED_DRIPSTONE_SLAB.asItem());
                    output.accept(ModBlocks.POLISHED_DRIPSTONE_WALL.asItem());
                }

                boolean dripstoneBricksEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_DRIPSTONE_BRICKS);
                if (dripstoneBricksEnabled) {
                    output.accept(ModBlocks.DRIPSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.DRIPSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.DRIPSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.DRIPSTONE_BRICK_WALL.asItem());
                    output.accept(ModBlocks.CHISELED_DRIPSTONE_BRICKS.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CRACKED_DRIPSTONE_BRICKS) && dripstoneBricksEnabled) {
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.CRACKED_DRIPSTONE_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_MOSSY_DRIPSTONE_BRICKS) && dripstoneBricksEnabled) {
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICKS.asItem());
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.MOSSY_DRIPSTONE_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SNOW_BRICKS)) {
                    output.accept(ModBlocks.SNOW_BRICKS.asItem());
                    output.accept(ModBlocks.SNOW_BRICK_STAIRS.asItem());
                    output.accept(ModBlocks.SNOW_BRICK_SLAB.asItem());
                    output.accept(ModBlocks.SNOW_BRICK_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PACKED_SNOW)) {
                    output.accept(ModBlocks.PACKED_SNOW.asItem());
                    output.accept(ModBlocks.PACKED_SNOW_STAIRS.asItem());
                    output.accept(ModBlocks.PACKED_SNOW_SLAB.asItem());
                    output.accept(ModBlocks.PACKED_SNOW_WALL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_DIRT_SLABS)) {
                    output.accept(ModBlocks.GRASS_SLAB.asItem());
                    output.accept(ModBlocks.PODZOL_SLAB.asItem());
                    output.accept(ModBlocks.MYCELIUM_SLAB.asItem());
                    output.accept(ModBlocks.DIRT_PATH_SLAB.asItem());
                    output.accept(ModBlocks.DIRT_SLAB.asItem());
                    output.accept(ModBlocks.ROOTED_DIRT_SLAB.asItem());
                    output.accept(ModBlocks.COARSE_DIRT_SLAB.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PURPLE_MUSHROOMS)) {
                    output.accept(ModBlocks.PURPLE_MUSHROOM_BLOCK.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CATTAILS)) {
                    output.accept(ModBlocks.CATTAIL.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BOG_BLOSSOMS)) {
                    output.accept(ModBlocks.BOG_BLOSSOM.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_ENDER_PLANTS)) {
                    output.accept(ModBlocks.SNAPDRAGON.asItem());
                    output.accept(ModBlocks.SHORT_ENDER_GRASS.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PURPLE_MUSHROOMS)) {
                    output.accept(ModBlocks.PURPLE_MUSHROOM.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BLOOD_KELP)) {
                    output.accept(ModBlocks.DRIED_BLOOD_KELP_BLOCK);
                    output.accept(ModBlocks.BLOOD_KELP_LANTERN);
                    output.accept(ModItems.BLOOD_KELP_SEED_CLUSTER);
                    output.accept(ModItems.BLOOD_KELP);
                    output.accept(ModItems.DRIED_BLOOD_KELP);
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_GREEN_ONIONS)) {
                    output.accept(ModBlocks.WILD_GREEN_ONIONS.asItem());
                    output.accept(ModItems.GREEN_ONION_SEEDS);
                    output.accept(ModItems.GREEN_ONION);
                }

                boolean caramelAppleEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CARAMEL_APPLE);
                if (caramelAppleEnabled) {
                    output.accept(ModItems.CARAMEL);
                }

                boolean forestsBountyEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_FORESTS_BOUNTY);
                if (forestsBountyEnabled) {
                    output.accept(ModItems.SPRUCE_CONE);
                }

                boolean noodleSoupEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_NOODLE_SOUP);
                if (noodleSoupEnabled) {
                    output.accept(ModItems.NOODLES);
                }

                boolean witchsCradleEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WITCHS_CRADLES);
                if (witchsCradleEnabled) {
                    output.accept(ModItems.WITCHS_CRADLE_BRANCH);
                }

                boolean blueberriesEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BLUEBERRIES);
                if (blueberriesEnabled) {
                    output.accept(ModItems.BLUEBERRIES);
                }

                boolean cindersnapBerriesEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CINDERSNAP_BERRIES);
                if (cindersnapBerriesEnabled) {
                    output.accept(ModItems.CINDERSNAP_BERRIES);
                }

                boolean frostbiteBerriesEnabled = ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_FROSTBITE_BERRIES);
                if (frostbiteBerriesEnabled) {
                    output.accept(ModItems.FROSTBITE_BERRIES);
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_FRIED_EGG)) {
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

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_HOGLIN_STEW)) {
                    output.accept(ModItems.HOGLIN_STEW);
                }

                if (witchsCradleEnabled) {
                    output.accept(ModItems.WITCHS_CRADLE_SOUP);
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_PUDDING)) {
                    output.accept(ModItems.BERRY_PUDDING);
                    output.accept(ModItems.PUDDING);
                }

                if (frostbiteBerriesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_WARPED_FORAGE_MIX)) {
                    output.accept(ModItems.WARPED_FORAGE_MIX);
                }

                if (cindersnapBerriesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CRIMSON_FORAGE_MIX)) {
                    output.accept(ModItems.CRIMSON_FORAGE_MIX);
                }

                if (blueberriesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BLUEBERRY_JUICE)) {
                    output.accept(ModItems.BLUEBERRY_JUICE);
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SWEET_BERRY_JUICE)) {
                    output.accept(ModItems.SWEET_BERRY_JUICE);
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CINDERSNAP_BERRY_JUICE)
                        && cindersnapBerriesEnabled) {
                    output.accept(ModItems.CINDERSNAP_BERRY_JUICE);
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_FROSTBITE_BERRY_JUICE)
                        && frostbiteBerriesEnabled) {
                    output.accept(ModItems.FROSTBITE_BERRY_JUICE);
                }

                if (blueberriesEnabled && ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_BLUEBERRY_PIE)) {
                    output.accept(ModBlocks.BLUEBERRY_PIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_SWEET_BERRY_PIE)) {
                    output.accept(ModBlocks.SWEET_BERRY_PIE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_CHOCOLATE_CAKE)) {
                    output.accept(ModBlocks.CHOCOLATE_CAKE.asItem());
                }

                if (ModClientConfig.getBoolEntries().get(ModServerConfigKeys.ENABLE_RED_VELVET_CAKE)) {
                    output.accept(ModBlocks.RED_VELVET_CAKE.asItem());
                }
            }).build());

    @Override
    public void onInitialize(IEventBus modEventBus, ModContainer modContainer) {
        // Config
        AssortedDiscoveries.registerConfigEvents();
        ModResourceConditionTypes.register();

        // General Registries
        ModBlocks.register(modEventBus);
        ModItems.register();
        AssortedDiscoveries.modifyCreativeTabs();
        ModBlockEntityTypes.register();
        ModParticleTypes.register();
        ModSoundEvents.register();
        AssortedDiscoveries.registerFuel();
        AssortedDiscoveries.registerCompostables();
        AssortedDiscoveries.modifyLootTables();
        AssortedDiscoveries.registerVillagerInteractions();

        // World Generation Registries
        ModFeatures.register();
        AssortedDiscoveries.addFeaturesToBiomes();
    }

    public static Identifier makeModId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    private static void registerConfigEvents() {
        PayloadTypeRegistry.clientboundPlay().register(BooleanEntriesS2CPayload.ID, BooleanEntriesS2CPayload.CODEC);

        ServerLifecycleEvents.SERVER_STARTED.register(AssortedDiscoveries::initConfigOnServer);
        ServerPlayerEvents.JOIN.register(AssortedDiscoveries::onJoin);
    }

    private static void initConfigOnServer(MinecraftServer server) {
        if (!server.overworld().isClientSide()) {
            ModClientConfig.updateBoolEntries(ModServerConfig.getInstance().toEntryMap());
            LOGGER.info("Loaded server config");
        }
    }

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

    private static void registerVillagerInteractions() {
        VillagerInteractionRegistries.registerCompostable(ModItems.GREEN_ONION);
        VillagerInteractionRegistries.registerFood(ModItems.GREEN_ONION, 1);
    }

    private static void addFeaturesToBiomes() {
        ServerConfig config = ModServerConfig.getInstance();
        BooleanConfigEntry configEntry;

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_CATTAILS);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_CATTAIL_SWAMP),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_CATTAIL_SWAMP);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_CATTAIL_RIVER),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_CATTAIL_RIVER);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_SMOKY_QUARTZ_BLOCKS);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.ORE_SMOKY_QUARTZ),
                    GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatureKeys.ORE_SMOKY_QUARTZ);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_PURPLE_MUSHROOMS);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_HUGE_PURPLE_MUSHROOM)
                            .and(BiomeSelectors.excludeByKey(Biomes.PALE_GARDEN)),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_HUGE_PURPLE_MUSHROOM);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_BLUEBERRIES);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_BLUEBERRY_BUSH)
                            .and(BiomeSelectors.excludeByKey(Biomes.PALE_GARDEN)),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_BLUEBERRY_COMMON);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_BLUEBERRY_BUSH)
                            .and(BiomeSelectors.excludeByKey(Biomes.PALE_GARDEN)),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_BLUEBERRY_RARE);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_WITCHS_CRADLES);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_WITCHS_CRADLE),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_WITCHS_CRADLE_COMMON);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_WITCHS_CRADLE),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_WITCHS_CRADLE_RARE);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_ENDER_PLANTS);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_ENDER_PLANTS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_ENDER_PLANTS);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_BLOOD_KELP);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.BLOOD_KELP),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.BLOOD_KELP);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_BOG_BLOSSOMS);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.BOG_BLOSSOM),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.BOG_BLOSSOM);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_BAUXITE);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.ORE_BAUXITE),
                    GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatureKeys.ORE_BAUXITE_LOWER);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.ORE_BAUXITE),
                    GenerationStep.Decoration.UNDERGROUND_ORES, ModPlacedFeatureKeys.ORE_BAUXITE_UPPER);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_CINDERSNAP_BERRIES);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_CINDERSNAP_BERRY_BUSH),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_CINDERSNAP_BERRY_BUSH_COMMON);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_CINDERSNAP_BERRY_BUSH),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_CINDERSNAP_BERRY_BUSH_RARE);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_FROSTBITE_BERRIES);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_FROSTBITE_BERRY_BUSH),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_FROSTBITE_BERRY_BUSH_COMMON);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_FROSTBITE_BERRY_BUSH),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_FROSTBITE_BERRY_BUSH_RARE);
        }

        configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_GREEN_ONIONS);
        if (configEntry.getValue()) {
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_WILD_GREEN_ONIONS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_WILD_GREEN_ONIONS_COMMON);
            BiomeModifications.addFeature(BiomeSelectors.tag(ModBiomeTags.PATCH_WILD_GREEN_ONIONS),
                    GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatureKeys.PATCH_WILD_GREEN_ONIONS_RARE);
        }
    }

    private static void registerFuel() {
        FuelValueEvents.BUILD.register((builder, context) ->
                builder.add(ModBlocks.DRIED_BLOOD_KELP_BLOCK, 4000));
    }

    private static void registerCompostables() {
        CompostableRegistry.INSTANCE.add(ModItems.BLUEBERRIES, 0.3F);
        CompostableRegistry.INSTANCE.add(ModItems.CINDERSNAP_BERRIES, 0.3F);
        CompostableRegistry.INSTANCE.add(ModItems.FROSTBITE_BERRIES, 0.3F);
        CompostableRegistry.INSTANCE.add(ModItems.WITCHS_CRADLE_BRANCH, 0.3F);
        CompostableRegistry.INSTANCE.add(ModItems.BLOOD_KELP_SEED_CLUSTER, 0.3F);
        CompostableRegistry.INSTANCE.add(ModItems.BLOOD_KELP, 0.3F);
        CompostableRegistry.INSTANCE.add(ModItems.DRIED_BLOOD_KELP, 0.3F);
        CompostableRegistry.INSTANCE.add(ModBlocks.DRIED_BLOOD_KELP_BLOCK, 0.5F);
        CompostableRegistry.INSTANCE.add(ModBlocks.SNAPDRAGON, 0.65F);
        CompostableRegistry.INSTANCE.add(ModBlocks.SHORT_ENDER_GRASS, 0.3F);
        CompostableRegistry.INSTANCE.add(ModBlocks.PURPLE_MUSHROOM_BLOCK, 0.85F);
        CompostableRegistry.INSTANCE.add(ModBlocks.PURPLE_MUSHROOM, 0.65F);
        CompostableRegistry.INSTANCE.add(ModBlocks.CATTAIL, 0.5F);
        CompostableRegistry.INSTANCE.add(ModItems.GREEN_ONION, 0.65F);
        CompostableRegistry.INSTANCE.add(ModItems.GREEN_ONION_SEEDS, 0.3F);
    }

    /*
    TODO: Add a global loot modifier for this
    private static void modifyLootTables() {
        Optional<ResourceKey<@NotNull LootTable>> spruceLeavesLootTableId = Blocks.SPRUCE_LEAVES.getLootTable();

        LootTableEvents.MODIFY.register((key, tableBuilder, source,
                                         registries) -> {
            if(source.isBuiltin() && spruceLeavesLootTableId.isPresent() && spruceLeavesLootTableId.get().equals(key)) {
                modifySpruceLeavesLootTable(registries, tableBuilder);
            }
        });
    }

    private static void modifySpruceLeavesLootTable(HolderLookup.Provider registries, LootTable.Builder builder) {
        ServerConfig config = ModServerConfig.getInstance();
        BooleanConfigEntry configEntry = (BooleanConfigEntry) config.getEntry(ModServerConfigKeys.ENABLE_FORESTS_BOUNTY);

        if (configEntry.getValue()) {
            Optional<Holder.Reference<Enchantment>> fortune = registries.get(Enchantments.FORTUNE);
            assert fortune.isPresent();
            Holder<Enchantment> fortuneEnchant = Holder.direct(fortune.get().value());

            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(BonusLevelTableCondition.bonusLevelFlatChance(fortuneEnchant, 0.02F, 0.023F,
                            0.025F, 0.035F, 0.1F))
                    .add(LootItem.lootTableItem(ModItems.SPRUCE_CONE))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)));

            builder.withPool(poolBuilder);
        }
    }
    */
}
