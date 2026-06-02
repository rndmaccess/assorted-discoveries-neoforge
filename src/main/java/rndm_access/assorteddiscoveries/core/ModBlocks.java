package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.MushroomBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.block.*;
import rndm_access.assorteddiscoveries.item.RopeLadderBlockItem;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

@SuppressWarnings("unused")
public final class ModBlocks {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(AssortedDiscoveries.MOD_ID);
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AssortedDiscoveries.MOD_ID);

    public static final DeferredBlock<Block> BAT_PLUSHIE = register("bat_plushie", BatPlushieBlock::new,
            ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> BLAZE_PLUSHIE = register("blaze_plushie", BlazePlushieBlock::new,
            ModBlocks::makeGlowingPlushieSettings, true);
    public static final DeferredBlock<Block> CAVE_SPIDER_PLUSHIE = register("cave_spider_plushie",
            CaveSpiderPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> TEMPERATE_CHICKEN_PLUSHIE = register("temperate_chicken_plushie",
            ChickenPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> TEMPERATE_COW_PLUSHIE = register("temperate_cow_plushie",
            CowPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> CREEPER_PLUSHIE = register("creeper_plushie",
            CreeperPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> ENDERMAN_PLUSHIE = register("enderman_plushie",
            EndermanPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> GHAST_PLUSHIE = register("ghast_plushie",
            GhastPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> GUARDIAN_PLUSHIE = register("guardian_plushie",
            GuardianPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> WHITE_HORSE_PLUSHIE = registerHorsePlushie("white_horse_plushie");
    public static final DeferredBlock<Block> GRAY_HORSE_PLUSHIE = registerHorsePlushie("gray_horse_plushie");
    public static final DeferredBlock<Block> BROWN_HORSE_PLUSHIE = registerHorsePlushie("brown_horse_plushie");
    public static final DeferredBlock<Block> BLACK_HORSE_PLUSHIE = registerHorsePlushie("black_horse_plushie");
    public static final DeferredBlock<Block> MAGMA_CUBE_PLUSHIE = registerCubePlushie("magma_cube_plushie");
    public static final DeferredBlock<Block> RED_MOOSHROOM_PLUSHIE
            = registerMooshroomPlushie("red_mooshroom_plushie");
    public static final DeferredBlock<Block> BROWN_MOOSHROOM_PLUSHIE
            = registerMooshroomPlushie("brown_mooshroom_plushie");
    public static final DeferredBlock<Block> OCELOT_PLUSHIE = registerCatPlushie("ocelot_plushie");
    public static final DeferredBlock<Block> TABBY_CAT_PLUSHIE = registerCatPlushie("tabby_cat_plushie");
    public static final DeferredBlock<Block> TUXEDO_CAT_PLUSHIE = registerCatPlushie("tuxedo_cat_plushie");
    public static final DeferredBlock<Block> RED_CAT_PLUSHIE = registerCatPlushie("red_cat_plushie");
    public static final DeferredBlock<Block> SIAMESE_CAT_PLUSHIE = registerCatPlushie("siamese_cat_plushie");
    public static final DeferredBlock<Block> BRITISH_SHORTHAIR_CAT_PLUSHIE
            = registerCatPlushie("british_shorthair_cat_plushie");
    public static final DeferredBlock<Block> CALICO_CAT_PLUSHIE = registerCatPlushie("calico_cat_plushie");
    public static final DeferredBlock<Block> PERSIAN_CAT_PLUSHIE = registerCatPlushie("persian_cat_plushie");
    public static final DeferredBlock<Block> RAGDOLL_CAT_PLUSHIE = registerCatPlushie("ragdoll_cat_plushie");
    public static final DeferredBlock<Block> WHITE_CAT_PLUSHIE = registerCatPlushie("white_cat_plushie");
    public static final DeferredBlock<Block> JELLIE_CAT_PLUSHIE = registerCatPlushie("jellie_cat_plushie");
    public static final DeferredBlock<Block> BLACK_CAT_PLUSHIE = registerCatPlushie("black_cat_plushie");
    public static final DeferredBlock<Block> TEMPERATE_PIG_PLUSHIE = register("temperate_pig_plushie",
            PigPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> BROWN_RABBIT_PLUSHIE = registerRabbitPlushie("brown_rabbit_plushie");
    public static final DeferredBlock<Block> WHITE_RABBIT_PLUSHIE = registerRabbitPlushie("white_rabbit_plushie");
    public static final DeferredBlock<Block> BLACK_RABBIT_PLUSHIE = registerRabbitPlushie("black_rabbit_plushie");
    public static final DeferredBlock<Block> WHITE_SPLOTCHED_RABBIT_PLUSHIE
            = registerRabbitPlushie("white_splotched_rabbit_plushie");
    public static final DeferredBlock<Block> GOLD_RABBIT_PLUSHIE = registerRabbitPlushie("gold_rabbit_plushie");
    public static final DeferredBlock<Block> TOAST_RABBIT_PLUSHIE = registerRabbitPlushie("toast_rabbit_plushie");
    public static final DeferredBlock<Block> SALT_RABBIT_PLUSHIE = registerRabbitPlushie("salt_rabbit_plushie");
    public static final DeferredBlock<Block> WHITE_SHEEP_PLUSHIE
            = registerSheepPlushie("white_sheep_plushie", DyeColor.WHITE);
    public static final DeferredBlock<Block> ORANGE_SHEEP_PLUSHIE
            = registerSheepPlushie("orange_sheep_plushie", DyeColor.ORANGE);
    public static final DeferredBlock<Block> MAGENTA_SHEEP_PLUSHIE
            = registerSheepPlushie("magenta_sheep_plushie", DyeColor.MAGENTA);
    public static final DeferredBlock<Block> LIGHT_BLUE_SHEEP_PLUSHIE
            = registerSheepPlushie("light_blue_sheep_plushie", DyeColor.LIGHT_BLUE);
    public static final DeferredBlock<Block> YELLOW_SHEEP_PLUSHIE
            = registerSheepPlushie("yellow_sheep_plushie", DyeColor.YELLOW);
    public static final DeferredBlock<Block> LIME_SHEEP_PLUSHIE
            = registerSheepPlushie("lime_sheep_plushie", DyeColor.LIME);
    public static final DeferredBlock<Block> PINK_SHEEP_PLUSHIE
            = registerSheepPlushie("pink_sheep_plushie", DyeColor.PINK);
    public static final DeferredBlock<Block> GRAY_SHEEP_PLUSHIE
            = registerSheepPlushie("gray_sheep_plushie", DyeColor.GRAY);
    public static final DeferredBlock<Block> LIGHT_GRAY_SHEEP_PLUSHIE
            = registerSheepPlushie("light_gray_sheep_plushie", DyeColor.LIGHT_GRAY);
    public static final DeferredBlock<Block> CYAN_SHEEP_PLUSHIE
            = registerSheepPlushie("cyan_sheep_plushie", DyeColor.CYAN);
    public static final DeferredBlock<Block> PURPLE_SHEEP_PLUSHIE
            = registerSheepPlushie("purple_sheep_plushie", DyeColor.PURPLE);
    public static final DeferredBlock<Block> BLUE_SHEEP_PLUSHIE
            = registerSheepPlushie("blue_sheep_plushie", DyeColor.BLUE);
    public static final DeferredBlock<Block> BROWN_SHEEP_PLUSHIE
            = registerSheepPlushie("brown_sheep_plushie", DyeColor.BROWN);
    public static final DeferredBlock<Block> GREEN_SHEEP_PLUSHIE
            = registerSheepPlushie("green_sheep_plushie", DyeColor.GREEN);
    public static final DeferredBlock<Block> RED_SHEEP_PLUSHIE
            = registerSheepPlushie("red_sheep_plushie", DyeColor.RED);
    public static final DeferredBlock<Block> BLACK_SHEEP_PLUSHIE
            = registerSheepPlushie("black_sheep_plushie", DyeColor.BLACK);
    public static final DeferredBlock<Block> SKELETON_PLUSHIE = register("skeleton_plushie",
            SkeletonPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> SLIME_PLUSHIE = registerCubePlushie("slime_plushie");
    public static final DeferredBlock<Block> SPIDER_PLUSHIE = register("spider_plushie",
            SpiderPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> SQUID_PLUSHIE = register("squid_plushie",
            SquidPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> GLOW_SQUID_PLUSHIE = register("glow_squid_plushie",
            SquidPlushieBlock::new, ModBlocks::makeGlowingPlushieSettings, true);
    public static final DeferredBlock<Block> BEE_PLUSHIE = register("bee_plushie",
            BeePlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> PLAINS_VILLAGER_PLUSHIE
            = registerVillagerPlushie("plains_villager_plushie");
    public static final DeferredBlock<Block> DESERT_VILLAGER_PLUSHIE = register("desert_villager_plushie",
            DesertVillagerPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> JUNGLE_VILLAGER_PLUSHIE
            = registerVillagerPlushie("jungle_villager_plushie");
    public static final DeferredBlock<Block> SAVANNA_VILLAGER_PLUSHIE
            = registerVillagerPlushie("savanna_villager_plushie");
    public static final DeferredBlock<Block> SNOWY_VILLAGER_PLUSHIE
            = registerShortHatVillagerPlushie("snowy_villager_plushie");
    public static final DeferredBlock<Block> SWAMP_VILLAGER_PLUSHIE
            = registerShortHatVillagerPlushie("swamp_villager_plushie");
    public static final DeferredBlock<Block> TAIGA_VILLAGER_PLUSHIE
            = registerVillagerPlushie("taiga_villager_plushie");
    public static final DeferredBlock<Block> WITCH_PLUSHIE = register("witch_plushie",
            WitchPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> PALE_WOLF_PLUSHIE = registerWolfPlushie("pale_wolf_plushie");
    public static final DeferredBlock<Block> ZOMBIE_PLUSHIE = registerZombiePlushie("zombie_plushie");
    public static final DeferredBlock<Block> PIGLIN_PLUSHIE = register("piglin_plushie",
            PiglinPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> ZOMBIFIED_PIGLIN_PLUSHIE
            = registerZombiePlushie("zombified_piglin_plushie");
    public static final DeferredBlock<Block> PUFFERFISH_PLUSHIE = register("pufferfish_plushie",
            PufferfishPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> WITHER_PLUSHIE = register("wither_plushie",
            WitherPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> STRIDER_PLUSHIE = registerStriderPlushie("strider_plushie");
    public static final DeferredBlock<Block> SHIVERING_STRIDER_PLUSHIE
            = registerStriderPlushie("shivering_strider_plushie");
    public static final DeferredBlock<Block> PHANTOM_PLUSHIE = register("phantom_plushie",
            PhantomPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> HOGLIN_PLUSHIE = registerHoglinPlushie("hoglin_plushie");
    public static final DeferredBlock<Block> ZOGLIN_PLUSHIE = registerHoglinPlushie("zoglin_plushie");
    public static final DeferredBlock<Block> ALLAY_PLUSHIE = registerAllayPlushie("allay_plushie");
    public static final DeferredBlock<Block> PILLAGER_PLUSHIE = registerVillagerPlushie("pillager_plushie");
    public static final DeferredBlock<Block> VINDICATOR_PLUSHIE = registerVillagerPlushie("vindicator_plushie");
    public static final DeferredBlock<Block> EVOKER_PLUSHIE = registerVillagerPlushie("evoker_plushie");
    public static final DeferredBlock<Block> SHULKER_PLUSHIE = register("shulker_plushie",
            ShulkerPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> VEX_PLUSHIE = registerAllayPlushie("vex_plushie");
    public static final DeferredBlock<Block> CAMEL_PLUSHIE = register("camel_plushie",
            CamelPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> NETHER_SMOKY_QUARTZ_ORE = register("nether_smoky_quartz_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5), properties),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_QUARTZ_ORE), true);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_BLOCK = registerSimpleBlock("smoky_quartz_block",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> CHISELED_SMOKY_QUARTZ_BLOCK = registerSimpleBlock("chiseled_smoky_quartz_block",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICKS = registerSimpleBlock("smoky_quartz_bricks",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICK_STAIRS = registerStairs("smoky_quartz_brick_stairs",
            ModBlocks::makeSmokyQuartzSettings, SMOKY_QUARTZ_BRICKS);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICK_SLAB = registerSlab("smoky_quartz_brick_slab",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICK_WALL = registerWall("smoky_quartz_brick_wall",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_PILLAR = register("smoky_quartz_pillar",
            RotatedPillarBlock::new, ModBlocks::makeSmokyQuartzSettings, true);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_STAIRS = registerStairs("smoky_quartz_stairs",
            ModBlocks::makeSmokyQuartzSettings, SMOKY_QUARTZ_BLOCK);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_SLAB = registerSlab("smoky_quartz_slab",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_WALL = registerWall("smoky_quartz_wall",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOOTH_SMOKY_QUARTZ = registerSimpleBlock("smooth_smoky_quartz",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOOTH_SMOKY_QUARTZ_STAIRS
            = registerStairs("smooth_smoky_quartz_stairs",
            ModBlocks::makeSmokyQuartzSettings, SMOOTH_SMOKY_QUARTZ);
    public static final DeferredBlock<Block> SMOOTH_SMOKY_QUARTZ_SLAB = registerSlab("smooth_smoky_quartz_slab",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> SMOOTH_SMOKY_QUARTZ_WALL = registerWall("smooth_smoky_quartz_wall",
            ModBlocks::makeSmokyQuartzSettings);
    public static final DeferredBlock<Block> CRACKED_STONE_BRICK_STAIRS
            = registerStairs("cracked_stone_brick_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS), () -> Blocks.CRACKED_STONE_BRICKS);
    public static final DeferredBlock<Block> CRACKED_STONE_BRICK_SLAB = registerSlab("cracked_stone_brick_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));
    public static final DeferredBlock<Block> CRACKED_STONE_BRICK_WALL = registerWall("cracked_stone_brick_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRACKED_STONE_BRICKS));
    public static final DeferredBlock<Block> BLUEBERRY_BUSH = register("blueberry_bush",
            BlueberryBushBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.GRASS)
                    .randomTicks().noCollision().sound(SoundType.SWEET_BERRY_BUSH)
                    .pushReaction(PushReaction.DESTROY), false);
    public static final DeferredBlock<Block> GREEN_ONIONS = register("green_onions",
            GreenOnionsBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
                    .noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY), false);
    public static final DeferredBlock<Block> OAK_PLANTER_BOX = registerPlanterBox("oak_planter_box",
            Blocks.OAK_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> SPRUCE_PLANTER_BOX = registerPlanterBox("spruce_planter_box",
            Blocks.SPRUCE_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> BIRCH_PLANTER_BOX = registerPlanterBox("birch_planter_box",
            Blocks.BIRCH_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> JUNGLE_PLANTER_BOX = registerPlanterBox("jungle_planter_box",
            Blocks.JUNGLE_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> ACACIA_PLANTER_BOX = registerPlanterBox("acacia_planter_box",
            Blocks.ACACIA_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> DARK_OAK_PLANTER_BOX = registerPlanterBox("dark_oak_planter_box",
            Blocks.DARK_OAK_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> MANGROVE_PLANTER_BOX = registerPlanterBox("mangrove_planter_box",
            Blocks.MANGROVE_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> CHERRY_PLANTER_BOX = registerPlanterBox("cherry_planter_box",
            Blocks.CHERRY_PLANKS.defaultMapColor(), SoundType.CHERRY_WOOD);
    public static final DeferredBlock<Block> PALE_OAK_PLANTER_BOX = registerPlanterBox("pale_oak_planter_box",
            Blocks.PALE_OAK_PLANKS.defaultMapColor(), SoundType.WOOD);
    public static final DeferredBlock<Block> CRIMSON_PLANTER_BOX
            = registerNetherPlanterBox("crimson_planter_box", Blocks.CRIMSON_PLANKS.defaultMapColor());
    public static final DeferredBlock<Block> WARPED_PLANTER_BOX
            = registerNetherPlanterBox("warped_planter_box", Blocks.WARPED_PLANKS.defaultMapColor());
    public static final DeferredBlock<Block> OAK_WALL = registerWall("oak_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> SPRUCE_WALL = registerWall("spruce_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    public static final DeferredBlock<Block> BIRCH_WALL = registerWall("birch_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
    public static final DeferredBlock<Block> JUNGLE_WALL = registerWall("jungle_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
    public static final DeferredBlock<Block> ACACIA_WALL = registerWall("acacia_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    public static final DeferredBlock<Block> DARK_OAK_WALL = registerWall("dark_oak_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
    public static final DeferredBlock<Block> MANGROVE_WALL = registerWall("mangrove_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    public static final DeferredBlock<Block> CRIMSON_WALL = registerWall("crimson_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));
    public static final DeferredBlock<Block> WARPED_WALL = registerWall("warped_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));
    public static final DeferredBlock<Block> CHERRY_WALL = registerWall("cherry_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_OAK_WALL = registerWall("stripped_oak_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_SPRUCE_WALL = registerWall("stripped_spruce_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_BIRCH_WALL = registerWall("stripped_birch_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_JUNGLE_WALL = registerWall("stripped_jungle_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_ACACIA_WALL = registerWall("stripped_acacia_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_DARK_OAK_WALL = registerWall("stripped_dark_oak_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_MANGROVE_WALL = registerWall("stripped_mangrove_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_CRIMSON_WALL = registerWall("stripped_crimson_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_WARPED_WALL = registerWall("stripped_warped_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.WARPED_PLANKS));
    public static final DeferredBlock<Block> STRIPPED_CHERRY_WALL = registerWall("stripped_cherry_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS));
    public static final DeferredBlock<Block> OAK_ROPE_LADDER = registerRopeLadder("oak_rope_ladder");
    public static final DeferredBlock<Block> SPRUCE_ROPE_LADDER = registerRopeLadder("spruce_rope_ladder");
    public static final DeferredBlock<Block> BIRCH_ROPE_LADDER = registerRopeLadder("birch_rope_ladder");
    public static final DeferredBlock<Block> JUNGLE_ROPE_LADDER = registerRopeLadder("jungle_rope_ladder");
    public static final DeferredBlock<Block> ACACIA_ROPE_LADDER = registerRopeLadder("acacia_rope_ladder");
    public static final DeferredBlock<Block> DARK_OAK_ROPE_LADDER = registerRopeLadder("dark_oak_rope_ladder");
    public static final DeferredBlock<Block> CRIMSON_ROPE_LADDER = registerRopeLadder("crimson_rope_ladder");
    public static final DeferredBlock<Block> WARPED_ROPE_LADDER = registerRopeLadder("warped_rope_ladder");
    public static final DeferredBlock<Block> MANGROVE_ROPE_LADDER = registerRopeLadder("mangrove_rope_ladder");
    public static final DeferredBlock<Block> CHERRY_ROPE_LADDER = registerRopeLadder("cherry_rope_ladder");
    public static final DeferredBlock<Block> PALE_OAK_ROPE_LADDER = registerRopeLadder("pale_oak_rope_ladder");
    public static final DeferredBlock<Block> IRON_LADDER = register("iron_ladder",
            LadderBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .requiresCorrectToolForDrops().strength(5.0F)
                    .sound(SoundType.METAL).noOcclusion(), true);
    public static final DeferredBlock<Block> SNOW_BRICKS = registerSimpleBlock("snow_bricks",
            ModBlocks::makeSnowBrickSettings);
    public static final DeferredBlock<Block> SNOW_BRICK_STAIRS = registerStairs("snow_brick_stairs",
            ModBlocks::makeSnowBrickSettings, SNOW_BRICKS);
    public static final DeferredBlock<Block> SNOW_BRICK_SLAB = registerSlab("snow_brick_slab",
            ModBlocks::makeSnowBrickSettings);
    public static final DeferredBlock<Block> SNOW_BRICK_WALL = registerWall("snow_brick_wall",
            ModBlocks::makeSnowBrickSettings);
    public static final DeferredBlock<Block> PACKED_SNOW = registerSimpleBlock("packed_snow",
            ModBlocks::makePackedSnowSettings);
    public static final DeferredBlock<Block> PACKED_SNOW_STAIRS = registerStairs("packed_snow_stairs",
            ModBlocks::makePackedSnowSettings, PACKED_SNOW);
    public static final DeferredBlock<Block> PACKED_SNOW_SLAB = registerSlab("packed_snow_slab",
            ModBlocks::makePackedSnowSettings);
    public static final DeferredBlock<Block> PACKED_SNOW_WALL = registerWall("packed_snow_wall",
            ModBlocks::makePackedSnowSettings);
    public static final DeferredBlock<Block> PURPLE_MUSHROOM = register("purple_mushroom",
            prop -> new MushroomBlock(ModTreeConfiguredFeatures.HUGE_PURPLE_MUSHROOM, prop),
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).pushReaction(PushReaction.DESTROY)
                    .noCollision().randomTicks().instabreak().sound(SoundType.GRASS)
                    .postProcess(ModBlocks::postProcessSelf), true);
    public static final DeferredBlock<Block> PURPLE_MUSHROOM_BLOCK = register("purple_mushroom_block",
            PurpleMushroomBlock::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE)
                    .instrument(NoteBlockInstrument.BASS).strength(0.2F)
                    .sound(SoundType.WOOD).ignitedByLava(), true);
    public static final DeferredBlock<Block> WHITE_CAMPFIRE
            = registerDyedCampfire("white_campfire", ModParticleTypes.WHITE_EMBER);
    public static final DeferredBlock<Block> ORANGE_CAMPFIRE
            = registerDyedCampfire("orange_campfire", ModParticleTypes.ORANGE_EMBER);
    public static final DeferredBlock<Block> MAGENTA_CAMPFIRE
            = registerDyedCampfire("magenta_campfire", ModParticleTypes.MAGENTA_EMBER);
    public static final DeferredBlock<Block> LIGHT_BLUE_CAMPFIRE
            = registerDyedCampfire("light_blue_campfire", ModParticleTypes.LIGHT_BLUE_EMBER);
    public static final DeferredBlock<Block> YELLOW_CAMPFIRE
            = registerDyedCampfire("yellow_campfire", ModParticleTypes.YELLOW_EMBER);
    public static final DeferredBlock<Block> LIME_CAMPFIRE
            = registerDyedCampfire("lime_campfire", ModParticleTypes.LIME_EMBER);
    public static final DeferredBlock<Block> PINK_CAMPFIRE
            = registerDyedCampfire("pink_campfire", ModParticleTypes.PINK_EMBER);
    public static final DeferredBlock<Block> GRAY_CAMPFIRE
            = registerDyedCampfire("gray_campfire", ModParticleTypes.GRAY_EMBER);
    public static final DeferredBlock<Block> LIGHT_GRAY_CAMPFIRE
            = registerDyedCampfire("light_gray_campfire", ModParticleTypes.LIGHT_GRAY_EMBER);
    public static final DeferredBlock<Block> CYAN_CAMPFIRE
            = registerDyedCampfire("cyan_campfire", ModParticleTypes.CYAN_EMBER);
    public static final DeferredBlock<Block> PURPLE_CAMPFIRE
            = registerDyedCampfire("purple_campfire", ModParticleTypes.PURPLE_EMBER);
    public static final DeferredBlock<Block> BLUE_CAMPFIRE
            = registerDyedCampfire("blue_campfire", ModParticleTypes.BLUE_EMBER);
    public static final DeferredBlock<Block> BROWN_CAMPFIRE
            = registerDyedCampfire("brown_campfire", ModParticleTypes.BROWN_EMBER);
    public static final DeferredBlock<Block> GREEN_CAMPFIRE
            = registerDyedCampfire("green_campfire", ModParticleTypes.GREEN_EMBER);
    public static final DeferredBlock<Block> RED_CAMPFIRE
            = registerDyedCampfire("red_campfire", ModParticleTypes.RED_EMBER);
    public static final DeferredBlock<Block> BLACK_CAMPFIRE
            = registerDyedCampfire("black_campfire", ModParticleTypes.BLACK_EMBER);
    public static final DeferredBlock<Block> WHITE_LANTERN = registerLantern("white_lantern");
    public static final DeferredBlock<Block> ORANGE_LANTERN = registerLantern("orange_lantern");
    public static final DeferredBlock<Block> MAGENTA_LANTERN = registerLantern("magenta_lantern");
    public static final DeferredBlock<Block> LIGHT_BLUE_LANTERN = registerLantern("light_blue_lantern");
    public static final DeferredBlock<Block> YELLOW_LANTERN = registerLantern("yellow_lantern");
    public static final DeferredBlock<Block> LIME_LANTERN = registerLantern("lime_lantern");
    public static final DeferredBlock<Block> PINK_LANTERN = registerLantern("pink_lantern");
    public static final DeferredBlock<Block> GRAY_LANTERN = registerLantern("gray_lantern");
    public static final DeferredBlock<Block> LIGHT_GRAY_LANTERN = registerLantern("light_gray_lantern");
    public static final DeferredBlock<Block> CYAN_LANTERN = registerLantern("cyan_lantern");
    public static final DeferredBlock<Block> PURPLE_LANTERN = registerLantern("purple_lantern");
    public static final DeferredBlock<Block> BLUE_LANTERN = registerLantern("blue_lantern");
    public static final DeferredBlock<Block> BROWN_LANTERN = registerLantern("brown_lantern");
    public static final DeferredBlock<Block> GREEN_LANTERN = registerLantern("green_lantern");
    public static final DeferredBlock<Block> RED_LANTERN = registerLantern("red_lantern");
    public static final DeferredBlock<Block> BLACK_LANTERN = registerLantern("black_lantern");
    public static final DeferredBlock<Block> WHITE_TORCH
            = registerTorch("white_torch", ModParticleTypes.WHITE_FLAME);
    public static final DeferredBlock<Block> ORANGE_TORCH
            = registerTorch("orange_torch", ModParticleTypes.ORANGE_FLAME);
    public static final DeferredBlock<Block> MAGENTA_TORCH
            = registerTorch("magenta_torch", ModParticleTypes.MAGENTA_FLAME);
    public static final DeferredBlock<Block> LIGHT_BLUE_TORCH
            = registerTorch("light_blue_torch", ModParticleTypes.LIGHT_BLUE_FLAME);
    public static final DeferredBlock<Block> YELLOW_TORCH
            = registerTorch("yellow_torch", ModParticleTypes.YELLOW_FLAME);
    public static final DeferredBlock<Block> LIME_TORCH
            = registerTorch("lime_torch", ModParticleTypes.LIME_FLAME);
    public static final DeferredBlock<Block> PINK_TORCH
            = registerTorch("pink_torch", ModParticleTypes.PINK_FLAME);
    public static final DeferredBlock<Block> GRAY_TORCH
            = registerTorch("gray_torch", ModParticleTypes.GRAY_FLAME);
    public static final DeferredBlock<Block> LIGHT_GRAY_TORCH
            = registerTorch("light_gray_torch", ModParticleTypes.LIGHT_GRAY_FLAME);
    public static final DeferredBlock<Block> CYAN_TORCH
            = registerTorch("cyan_torch", ModParticleTypes.CYAN_FLAME);
    public static final DeferredBlock<Block> PURPLE_TORCH
            = registerTorch("purple_torch", ModParticleTypes.PURPLE_FLAME);
    public static final DeferredBlock<Block> BLUE_TORCH
            = registerTorch("blue_torch", ModParticleTypes.BLUE_FLAME);
    public static final DeferredBlock<Block> BROWN_TORCH
            = registerTorch("brown_torch", ModParticleTypes.BROWN_FLAME);
    public static final DeferredBlock<Block> GREEN_TORCH
            = registerTorch("green_torch", ModParticleTypes.GREEN_FLAME);
    public static final DeferredBlock<Block> RED_TORCH
            = registerTorch("red_torch", ModParticleTypes.RED_FLAME);
    public static final DeferredBlock<Block> BLACK_TORCH
            = registerTorch("black_torch", ModParticleTypes.BLACK_FLAME);
    public static final DeferredBlock<Block> WHITE_WALL_TORCH
            = registerWallTorch("white_wall_torch", WHITE_TORCH, ModParticleTypes.WHITE_FLAME);
    public static final DeferredBlock<Block> ORANGE_WALL_TORCH
            = registerWallTorch("orange_wall_torch", ORANGE_TORCH, ModParticleTypes.ORANGE_FLAME);
    public static final DeferredBlock<Block> MAGENTA_WALL_TORCH
            = registerWallTorch("magenta_wall_torch", MAGENTA_TORCH, ModParticleTypes.MAGENTA_FLAME);
    public static final DeferredBlock<Block> LIGHT_BLUE_WALL_TORCH
            = registerWallTorch("light_blue_wall_torch", LIGHT_BLUE_TORCH, ModParticleTypes.LIGHT_BLUE_FLAME);
    public static final DeferredBlock<Block> YELLOW_WALL_TORCH
            = registerWallTorch("yellow_wall_torch", YELLOW_TORCH, ModParticleTypes.YELLOW_FLAME);
    public static final DeferredBlock<Block> LIME_WALL_TORCH
            = registerWallTorch("lime_wall_torch", LIME_TORCH, ModParticleTypes.LIME_FLAME);
    public static final DeferredBlock<Block> PINK_WALL_TORCH
            = registerWallTorch("pink_wall_torch", PINK_TORCH, ModParticleTypes.PINK_FLAME);
    public static final DeferredBlock<Block> GRAY_WALL_TORCH
            = registerWallTorch("gray_wall_torch", GRAY_TORCH, ModParticleTypes.GRAY_FLAME);
    public static final DeferredBlock<Block> LIGHT_GRAY_WALL_TORCH
            = registerWallTorch("light_gray_wall_torch", LIGHT_GRAY_TORCH, ModParticleTypes.LIGHT_GRAY_FLAME);
    public static final DeferredBlock<Block> CYAN_WALL_TORCH
            = registerWallTorch("cyan_wall_torch", CYAN_TORCH, ModParticleTypes.CYAN_FLAME);
    public static final DeferredBlock<Block> PURPLE_WALL_TORCH
            = registerWallTorch("purple_wall_torch", PURPLE_TORCH, ModParticleTypes.PURPLE_FLAME);
    public static final DeferredBlock<Block> BLUE_WALL_TORCH
            = registerWallTorch("blue_wall_torch", BLUE_TORCH, ModParticleTypes.BLUE_FLAME);
    public static final DeferredBlock<Block> BROWN_WALL_TORCH
            = registerWallTorch("brown_wall_torch", BROWN_TORCH, ModParticleTypes.BROWN_FLAME);
    public static final DeferredBlock<Block> GREEN_WALL_TORCH
            = registerWallTorch("green_wall_torch", GREEN_TORCH, ModParticleTypes.GREEN_FLAME);
    public static final DeferredBlock<Block> RED_WALL_TORCH
            = registerWallTorch("red_wall_torch", RED_TORCH, ModParticleTypes.RED_FLAME);
    public static final DeferredBlock<Block> BLACK_WALL_TORCH
            = registerWallTorch("black_wall_torch", BLACK_TORCH, ModParticleTypes.BLACK_FLAME);
    public static final DeferredBlock<Block> WITCHS_CRADLE = register("witchs_cradle", WitchsCradleBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> 8),
            false);
    public static final DeferredBlock<Block> BAUXITE
            = registerSimpleBlock("bauxite", ModBlocks::makeBauxiteSettings);
    public static final DeferredBlock<Block> BAUXITE_SLAB
            = registerSlab("bauxite_slab", ModBlocks::makeBauxiteSettings);
    public static final DeferredBlock<Block> BAUXITE_STAIRS
            = registerStairs("bauxite_stairs", ModBlocks::makeBauxiteSettings, BAUXITE);
    public static final DeferredBlock<Block> BAUXITE_WALL
            = registerWall("bauxite_wall", ModBlocks::makeBauxiteSettings);
    public static final DeferredBlock<Block> BAUXITE_BRICKS = registerSimpleBlock("bauxite_bricks",
            ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> BAUXITE_BRICK_STAIRS = registerStairs("bauxite_brick_stairs",
            ModBlocks::makeBauxiteBricksSettings, BAUXITE_BRICKS);
    public static final DeferredBlock<Block> BAUXITE_BRICK_SLAB
            = registerSlab("bauxite_brick_slab", ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> BAUXITE_BRICK_WALL
            = registerWall("bauxite_brick_wall", ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> MOSSY_BAUXITE_BRICKS = registerSimpleBlock("mossy_bauxite_bricks",
            ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> MOSSY_BAUXITE_BRICK_STAIRS
            = registerStairs("mossy_bauxite_brick_stairs",
            ModBlocks::makeBauxiteBricksSettings, MOSSY_BAUXITE_BRICKS);
    public static final DeferredBlock<Block> MOSSY_BAUXITE_BRICK_SLAB
            = registerSlab("mossy_bauxite_brick_slab", ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> MOSSY_BAUXITE_BRICK_WALL
            = registerWall("mossy_bauxite_brick_wall", ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> CRACKED_BAUXITE_BRICKS
            = registerSimpleBlock("cracked_bauxite_bricks",
            ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> CRACKED_BAUXITE_BRICK_STAIRS
            = registerStairs("cracked_bauxite_brick_stairs",
            ModBlocks::makeBauxiteBricksSettings, CRACKED_BAUXITE_BRICKS);
    public static final DeferredBlock<Block> CRACKED_BAUXITE_BRICK_SLAB
            = registerSlab("cracked_bauxite_brick_slab", ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> CRACKED_BAUXITE_BRICK_WALL
            = registerWall("cracked_bauxite_brick_wall", ModBlocks::makeBauxiteBricksSettings);
    public static final DeferredBlock<Block> TWISTED_NETHER_BRICKS
            = registerSimpleBlock("twisted_nether_bricks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> TWISTED_NETHER_BRICK_STAIRS
            = registerStairs("twisted_nether_brick_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS), TWISTED_NETHER_BRICKS);
    public static final DeferredBlock<Block> TWISTED_NETHER_BRICK_SLAB
            = registerSlab("twisted_nether_brick_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> TWISTED_NETHER_BRICK_WALL
            = registerWall("twisted_nether_brick_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> TWISTED_NETHERRACK
            = registerSimpleBlock("twisted_netherrack",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
    public static final DeferredBlock<Block> TWISTED_NETHERRACK_STAIRS
            = registerStairs("twisted_netherrack_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK), TWISTED_NETHERRACK);
    public static final DeferredBlock<Block> TWISTED_NETHERRACK_SLAB
            = registerSlab("twisted_netherrack_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
    public static final DeferredBlock<Block> TWISTED_NETHERRACK_WALL
            = registerWall("twisted_netherrack_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
    public static final DeferredBlock<Block> WEEPING_NETHER_BRICKS
            = registerSimpleBlock("weeping_nether_bricks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> WEEPING_NETHER_BRICK_STAIRS
            = registerStairs("weeping_nether_brick_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS), WEEPING_NETHER_BRICKS);
    public static final DeferredBlock<Block> WEEPING_NETHER_BRICK_SLAB
            = registerSlab("weeping_nether_brick_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> WEEPING_NETHER_BRICK_WALL
            = registerWall("weeping_nether_brick_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS));
    public static final DeferredBlock<Block> WEEPING_NETHERRACK
            = registerSimpleBlock("weeping_netherrack",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
    public static final DeferredBlock<Block> WEEPING_NETHERRACK_STAIRS
            = registerStairs("weeping_netherrack_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK), WEEPING_NETHERRACK);
    public static final DeferredBlock<Block> WEEPING_NETHERRACK_SLAB
            = registerSlab("weeping_netherrack_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
    public static final DeferredBlock<Block> WEEPING_NETHERRACK_WALL
            = registerWall("weeping_netherrack_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK));
    public static final DeferredBlock<Block> SNAPDRAGON
            = register("snapdragon", prop -> new SnapdragonBlock(MobEffects.LUCK, 8, prop),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).lightLevel((state) -> 8),
            true);
    public static final DeferredBlock<Block> POTTED_SNAPDRAGON
            = registerPottedSnapdragon(() -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)
            .lightLevel((state) -> 8));
    public static final DeferredBlock<Block> POTTED_PURPLE_MUSHROOM = register("potted_purple_mushroom",
            prop -> new FlowerPotBlock(() -> (FlowerPotBlock)Blocks.FLOWER_POT, ModBlocks.PURPLE_MUSHROOM, prop),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_RED_MUSHROOM), false);
    public static final DeferredBlock<Block> SHORT_ENDER_GRASS
        = register("short_ender_grass", ShortEnderGrassBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
                    .lightLevel((state) -> 8), true);
    public static final DeferredBlock<Block> CATTAIL = register("cattail", CattailBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollision()
                    .noOcclusion().sound(SoundType.WET_GRASS), true);
    public static final DeferredBlock<Block> CHOCOLATE_CAKE = registerCake("chocolate_cake");
    public static final DeferredBlock<Block> RED_VELVET_CAKE = registerCake("red_velvet_cake");
    public static final DeferredBlock<Block> CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("candle_chocolate_cake", () -> Blocks.CANDLE);
    public static final DeferredBlock<Block> WHITE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("white_candle_chocolate_cake", () -> Blocks.WHITE_CANDLE);
    public static final DeferredBlock<Block> ORANGE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("orange_candle_chocolate_cake", () -> Blocks.ORANGE_CANDLE);
    public static final DeferredBlock<Block> MAGENTA_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("magenta_candle_chocolate_cake", () -> Blocks.MAGENTA_CANDLE);
    public static final DeferredBlock<Block> LIGHT_BLUE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("light_blue_candle_chocolate_cake", () -> Blocks.LIGHT_BLUE_CANDLE);
    public static final DeferredBlock<Block> YELLOW_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("yellow_candle_chocolate_cake", () -> Blocks.YELLOW_CANDLE);
    public static final DeferredBlock<Block> LIME_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("lime_candle_chocolate_cake", () -> Blocks.LIME_CANDLE);
    public static final DeferredBlock<Block> PINK_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("pink_candle_chocolate_cake", () -> Blocks.PINK_CANDLE);
    public static final DeferredBlock<Block> GRAY_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("gray_candle_chocolate_cake", () -> Blocks.GRAY_CANDLE);
    public static final DeferredBlock<Block> LIGHT_GRAY_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("light_gray_candle_chocolate_cake", () -> Blocks.LIGHT_GRAY_CANDLE);
    public static final DeferredBlock<Block> CYAN_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("cyan_candle_chocolate_cake", () -> Blocks.CYAN_CANDLE);
    public static final DeferredBlock<Block> PURPLE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("purple_candle_chocolate_cake", () -> Blocks.PURPLE_CANDLE);
    public static final DeferredBlock<Block> BLUE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("blue_candle_chocolate_cake", () -> Blocks.BLUE_CANDLE);
    public static final DeferredBlock<Block> BROWN_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("brown_candle_chocolate_cake", () -> Blocks.BROWN_CANDLE);
    public static final DeferredBlock<Block> GREEN_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("green_candle_chocolate_cake", () -> Blocks.GREEN_CANDLE);
    public static final DeferredBlock<Block> RED_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("red_candle_chocolate_cake", () -> Blocks.RED_CANDLE);
    public static final DeferredBlock<Block> BLACK_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake("black_candle_chocolate_cake", () -> Blocks.BLACK_CANDLE);
    public static final DeferredBlock<Block> CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("candle_red_velvet_cake", () -> Blocks.CANDLE);
    public static final DeferredBlock<Block> WHITE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("white_candle_red_velvet_cake", () -> Blocks.WHITE_CANDLE);
    public static final DeferredBlock<Block> ORANGE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("orange_candle_red_velvet_cake", () -> Blocks.ORANGE_CANDLE);
    public static final DeferredBlock<Block> MAGENTA_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("magenta_candle_red_velvet_cake", () -> Blocks.MAGENTA_CANDLE);
    public static final DeferredBlock<Block> LIGHT_BLUE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("light_blue_candle_red_velvet_cake", () -> Blocks.LIGHT_BLUE_CANDLE);
    public static final DeferredBlock<Block> YELLOW_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("yellow_candle_red_velvet_cake", () -> Blocks.YELLOW_CANDLE);
    public static final DeferredBlock<Block> LIME_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("lime_candle_red_velvet_cake", () -> Blocks.LIME_CANDLE);
    public static final DeferredBlock<Block> PINK_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("pink_candle_red_velvet_cake", () -> Blocks.PINK_CANDLE);
    public static final DeferredBlock<Block> GRAY_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("gray_candle_red_velvet_cake", () -> Blocks.GRAY_CANDLE);
    public static final DeferredBlock<Block> LIGHT_GRAY_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("light_gray_candle_red_velvet_cake", () -> Blocks.LIGHT_GRAY_CANDLE);
    public static final DeferredBlock<Block> CYAN_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("cyan_candle_red_velvet_cake", () -> Blocks.CYAN_CANDLE);
    public static final DeferredBlock<Block> PURPLE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("purple_candle_red_velvet_cake", () -> Blocks.PURPLE_CANDLE);
    public static final DeferredBlock<Block> BLUE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("blue_candle_red_velvet_cake", () -> Blocks.BLUE_CANDLE);
    public static final DeferredBlock<Block> BROWN_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("brown_candle_red_velvet_cake", () -> Blocks.BROWN_CANDLE);
    public static final DeferredBlock<Block> GREEN_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("green_candle_red_velvet_cake", () -> Blocks.GREEN_CANDLE);
    public static final DeferredBlock<Block> RED_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("red_candle_red_velvet_cake", () -> Blocks.RED_CANDLE);
    public static final DeferredBlock<Block> BLACK_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake("black_candle_red_velvet_cake", () -> Blocks.BLACK_CANDLE);
    public static final DeferredBlock<Block> STONE_TILES
            = registerSimpleBlock("stone_tiles", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> STONE_TILE_SLAB
            = registerSlab("stone_tile_slab", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> STONE_TILE_STAIRS
            = registerStairs("stone_tile_stairs", ModBlocks::makeStoneTileSettings, STONE_TILES);
    public static final DeferredBlock<Block> STONE_TILE_WALL
            = registerWall("stone_tile_wall", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> MOSSY_STONE_TILES
            = registerSimpleBlock("mossy_stone_tiles", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> MOSSY_STONE_TILE_SLAB
            = registerSlab("mossy_stone_tile_slab", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> MOSSY_STONE_TILE_STAIRS
            = registerStairs("mossy_stone_tile_stairs", ModBlocks::makeStoneTileSettings, MOSSY_STONE_TILES);
    public static final DeferredBlock<Block> MOSSY_STONE_TILE_WALL
            = registerWall("mossy_stone_tile_wall", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> CRACKED_STONE_TILES
            = registerSimpleBlock("cracked_stone_tiles", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> CRACKED_STONE_TILE_SLAB
            = registerSlab("cracked_stone_tile_slab", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> CRACKED_STONE_TILE_STAIRS
            = registerStairs("cracked_stone_tile_stairs", ModBlocks::makeStoneTileSettings, CRACKED_STONE_TILES);
    public static final DeferredBlock<Block> CRACKED_STONE_TILE_WALL
            = registerWall("cracked_stone_tile_wall", ModBlocks::makeStoneTileSettings);
    public static final DeferredBlock<Block> SWEET_BERRY_PIE = registerPie("sweet_berry_pie");
    public static final DeferredBlock<Block> BLUEBERRY_PIE = registerPie("blueberry_pie");
    public static final DeferredBlock<Block> BLACKSTONE_TILES
            = registerSimpleBlock("blackstone_tiles", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> BLACKSTONE_TILE_STAIRS
            = registerStairs("blackstone_tile_stairs", ModBlocks::makeBlackstoneTileSettings, BLACKSTONE_TILES);
    public static final DeferredBlock<Block> BLACKSTONE_TILE_SLAB
            = registerSlab("blackstone_tile_slab", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> BLACKSTONE_TILE_WALL
            = registerWall("blackstone_tile_wall", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_TILES
            = registerSimpleBlock("twisted_blackstone_tiles",
            ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_TILE_STAIRS
            = registerStairs("twisted_blackstone_tile_stairs",
            ModBlocks::makeBlackstoneTileSettings, TWISTED_BLACKSTONE_TILES);
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_TILE_SLAB
            = registerSlab("twisted_blackstone_tile_slab", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_TILE_WALL
            = registerWall("twisted_blackstone_tile_wall", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_TILES
            = registerSimpleBlock("weeping_blackstone_tiles", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_TILE_STAIRS
            = registerStairs("weeping_blackstone_tile_stairs",
            ModBlocks::makeBlackstoneTileSettings, WEEPING_BLACKSTONE_TILES);
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_TILE_SLAB
            = registerSlab("weeping_blackstone_tile_slab", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_TILE_WALL
            = registerWall("weeping_blackstone_tile_wall", ModBlocks::makeBlackstoneTileSettings);
    public static final DeferredBlock<Block> TWISTED_POLISHED_BLACKSTONE_BRICKS
            = registerSimpleBlock("twisted_polished_blackstone_bricks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final DeferredBlock<Block> TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS
            = registerStairs("twisted_polished_blackstone_brick_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS),
            () -> Blocks.POLISHED_BLACKSTONE_BRICKS);
    public static final DeferredBlock<Block> TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB
            = registerSlab("twisted_polished_blackstone_brick_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final DeferredBlock<Block> TWISTED_POLISHED_BLACKSTONE_BRICK_WALL
            = registerWall("twisted_polished_blackstone_brick_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final DeferredBlock<Block> WEEPING_POLISHED_BLACKSTONE_BRICKS
            = registerSimpleBlock("weeping_polished_blackstone_bricks",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final DeferredBlock<Block> WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS
            = registerStairs("weeping_polished_blackstone_brick_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS),
            WEEPING_POLISHED_BLACKSTONE_BRICKS);
    public static final DeferredBlock<Block> WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB
            = registerSlab("weeping_polished_blackstone_brick_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final DeferredBlock<Block> WEEPING_POLISHED_BLACKSTONE_BRICK_WALL
            = registerWall("weeping_polished_blackstone_brick_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS));
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE
            = registerSimpleBlock("twisted_blackstone",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_STAIRS
            = registerStairs("twisted_blackstone_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE), () -> Blocks.BLACKSTONE);
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_SLAB = registerSlab("twisted_blackstone_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final DeferredBlock<Block> TWISTED_BLACKSTONE_WALL = registerWall("twisted_blackstone_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE
            = registerSimpleBlock("weeping_blackstone",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_STAIRS
            = registerStairs("weeping_blackstone_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE), WEEPING_BLACKSTONE);
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_SLAB = registerSlab("weeping_blackstone_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final DeferredBlock<Block> WEEPING_BLACKSTONE_WALL = registerWall("weeping_blackstone_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE));
    public static final DeferredBlock<Block> QUARTZ_TILES
            = registerSimpleBlock("quartz_tiles", ModBlocks::makeQuartzTileSettings);
    public static final DeferredBlock<Block> QUARTZ_TILE_STAIRS = registerStairs("quartz_tile_stairs",
            ModBlocks::makeQuartzTileSettings, QUARTZ_TILES);
    public static final DeferredBlock<Block> QUARTZ_TILE_SLAB
            = registerSlab("quartz_tile_slab", ModBlocks::makeQuartzTileSettings);
    public static final DeferredBlock<Block> QUARTZ_TILE_WALL
            = registerWall("quartz_tile_wall", ModBlocks::makeQuartzTileSettings);
    public static final DeferredBlock<Block> CALCITE_BRICKS
            = registerSimpleBlock("calcite_bricks", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CALCITE_BRICK_STAIRS = registerStairs("calcite_brick_stairs",
            ModBlocks::makeCalciteSettings, CALCITE_BRICKS);
    public static final DeferredBlock<Block> CALCITE_BRICK_SLAB = registerSlab("calcite_brick_slab",
            ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CALCITE_BRICK_WALL = registerWall("calcite_brick_wall",
            ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> MOSSY_CALCITE_BRICKS
            = registerSimpleBlock("mossy_calcite_bricks", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> MOSSY_CALCITE_BRICK_STAIRS
            = registerStairs("mossy_calcite_brick_stairs", ModBlocks::makeCalciteSettings, MOSSY_CALCITE_BRICKS);
    public static final DeferredBlock<Block> MOSSY_CALCITE_BRICK_SLAB
            = registerSlab("mossy_calcite_brick_slab", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> MOSSY_CALCITE_BRICK_WALL
            = registerWall("mossy_calcite_brick_wall", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CRACKED_CALCITE_BRICKS
            = registerSimpleBlock("cracked_calcite_bricks", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CRACKED_CALCITE_BRICK_STAIRS
            = registerStairs("cracked_calcite_brick_stairs",
            ModBlocks::makeCalciteSettings, CRACKED_CALCITE_BRICKS);
    public static final DeferredBlock<Block> CRACKED_CALCITE_BRICK_SLAB
            = registerSlab("cracked_calcite_brick_slab", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CRACKED_CALCITE_BRICK_WALL
            = registerWall("cracked_calcite_brick_wall", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CHISELED_CALCITE_BRICKS
            = register("chiseled_calcite_bricks", RotatedPillarBlock::new,
            ModBlocks::makeCalciteSettings, true);
    public static final DeferredBlock<Block> DRIPSTONE_BRICKS
            = registerSimpleBlock("dripstone_bricks", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> DRIPSTONE_BRICK_STAIRS
            = registerStairs("dripstone_brick_stairs", ModBlocks::makeDripstoneSettings, DRIPSTONE_BRICKS);
    public static final DeferredBlock<Block> DRIPSTONE_BRICK_SLAB
            = registerSlab("dripstone_brick_slab", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> DRIPSTONE_BRICK_WALL
            = registerWall("dripstone_brick_wall", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> MOSSY_DRIPSTONE_BRICKS
            = registerSimpleBlock("mossy_dripstone_bricks", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> MOSSY_DRIPSTONE_BRICK_STAIRS
            = registerStairs("mossy_dripstone_brick_stairs",
            ModBlocks::makeDripstoneSettings, MOSSY_DRIPSTONE_BRICKS);
    public static final DeferredBlock<Block> MOSSY_DRIPSTONE_BRICK_SLAB
            = registerSlab("mossy_dripstone_brick_slab", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> MOSSY_DRIPSTONE_BRICK_WALL
            = registerWall("mossy_dripstone_brick_wall", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> CRACKED_DRIPSTONE_BRICKS
            = registerSimpleBlock("cracked_dripstone_bricks",
            ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> CRACKED_DRIPSTONE_BRICK_STAIRS
            = registerStairs("cracked_dripstone_brick_stairs",
            ModBlocks::makeDripstoneSettings, CRACKED_DRIPSTONE_BRICKS);
    public static final DeferredBlock<Block> CRACKED_DRIPSTONE_BRICK_SLAB
            = registerSlab("cracked_dripstone_brick_slab", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> CRACKED_DRIPSTONE_BRICK_WALL
            = registerWall("cracked_dripstone_brick_wall", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> CHISELED_DRIPSTONE_BRICKS
            = registerSimpleBlock("chiseled_dripstone_bricks",
            ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> BLOOD_KELP
            = register("blood_kelp", BloodKelpBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.KELP)
                    .lightLevel(getLuminanceFromState()), false);
    public static final DeferredBlock<Block> BLOOD_KELP_PLANT
            = register("blood_kelp_plant", BloodKelpPlantBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.KELP_PLANT)
                    .lightLevel(getLuminanceFromState()), false);
    public static final DeferredBlock<Block> DRIED_BLOOD_KELP_BLOCK
            = registerSimpleBlock("dried_blood_kelp_block",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DRIED_KELP_BLOCK));
    public static final DeferredBlock<Block> BLOOD_KELP_LANTERN
            = register("blood_kelp_lantern", RotatedPillarBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.3F)
                    .sound(SoundType.GLASS).lightLevel((state) -> 15), true);
    public static final DeferredBlock<Block> BOG_BLOSSOM = register("bog_blossom", BogBlossomBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).instabreak()
                    .noCollision().sound(SoundType.SPORE_BLOSSOM).pushReaction(PushReaction.DESTROY)
                    .lightLevel((state) -> 5), true);
    public static final DeferredBlock<Block> CINDERSNAP_BERRY_BUSH
            = register("cindersnap_berry_bush", CindersnapBerryBushBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_HYPHAE)
                    .randomTicks().noCollision().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)
                    .lightLevel((state) -> 8), false);
    public static final DeferredBlock<Block> FROSTBITE_BERRY_BUSH
            = register("frostbite_berry_bush", FrostbiteBerryBushBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN)
                    .randomTicks().noCollision().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)
                    .lightLevel((state) -> 5), false);
    public static final DeferredBlock<Block> POLISHED_DRIPSTONE
            = registerSimpleBlock("polished_dripstone", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> POLISHED_DRIPSTONE_STAIRS
            = registerStairs("polished_dripstone_stairs",
            ModBlocks::makeDripstoneSettings, POLISHED_DRIPSTONE);
    public static final DeferredBlock<Block> POLISHED_DRIPSTONE_SLAB
            = registerSlab("polished_dripstone_slab", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> POLISHED_DRIPSTONE_WALL
            = registerWall("polished_dripstone_wall", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> POLISHED_CALCITE
            = registerSimpleBlock("polished_calcite", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> POLISHED_CALCITE_STAIRS
            = registerStairs("polished_calcite_stairs", ModBlocks::makeCalciteSettings, POLISHED_CALCITE);
    public static final DeferredBlock<Block> POLISHED_CALCITE_SLAB
            = registerSlab("polished_calcite_slab", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> POLISHED_CALCITE_WALL
            = registerWall("polished_calcite_wall", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> DRIPSTONE_STAIRS
            = registerStairs("dripstone_stairs", ModBlocks::makeDripstoneSettings,
            () -> Blocks.DRIPSTONE_BLOCK);
    public static final DeferredBlock<Block> DRIPSTONE_SLAB
            = registerSlab("dripstone_slab", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> DRIPSTONE_WALL
            = registerWall("dripstone_wall", ModBlocks::makeDripstoneSettings);
    public static final DeferredBlock<Block> CALCITE_STAIRS
            = registerStairs("calcite_stairs", ModBlocks::makeCalciteSettings, () -> Blocks.CALCITE);
    public static final DeferredBlock<Block> CALCITE_SLAB
            = registerSlab("calcite_slab", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> CALCITE_WALL
            = registerWall("calcite_wall", ModBlocks::makeCalciteSettings);
    public static final DeferredBlock<Block> BAMBOO_PLANTER_BOX = registerPlanterBox("bamboo_planter_box",
            Blocks.BAMBOO_PLANKS.defaultMapColor(), SoundType.BAMBOO_WOOD);
    public static final DeferredBlock<Block> POTTED_CATTAIL = register("potted_cattail",
            prop -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, ModBlocks.CATTAIL, prop),
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_RED_MUSHROOM), false);
    public static final DeferredBlock<Block> STONE_WALL
            = registerWall("stone_wall", () -> BlockBehaviour.Properties.ofFullCopy(Blocks.STONE));
    public static final DeferredBlock<Block> QUARTZ_WALL
            = registerWall("quartz_wall", () -> BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK));
    public static final DeferredBlock<Block> SMOOTH_QUARTZ_WALL
            = registerWall("smooth_quartz_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ));
    public static final DeferredBlock<Block> GRASS_SLAB
            = register("grass_slab", GrassSlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK), true);
    public static final DeferredBlock<Block> PODZOL_SLAB = registerSnowySlab("podzol_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL));
    public static final DeferredBlock<Block> MYCELIUM_SLAB = registerSnowySlab("mycelium_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.MYCELIUM));
    public static final DeferredBlock<Block> DIRT_PATH_SLAB
            = register("dirt_path_slab", DirtPathSlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH), true);
    public static final DeferredBlock<Block> DIRT_SLAB
            = register("dirt_slab", DirtSlabBlock::new,
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT), true);
    public static final DeferredBlock<Block> COARSE_DIRT_SLAB = registerSlab("coarse_dirt_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT));
    public static final DeferredBlock<Block> ROOTED_DIRT_SLAB = registerSlab("rooted_dirt_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.ROOTED_DIRT));
    public static final DeferredBlock<Block> WILD_GREEN_ONIONS
            = register("wild_green_onions", WildGreenOnionsBlock::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
                    .noCollision().randomTicks().instabreak().sound(SoundType.CROP)
                    .pushReaction(PushReaction.DESTROY), true);
    public static final DeferredBlock<Block> CREAKING_PLUSHIE
            = register("creaking_plushie", CreakingPlushieBlock::new,
            ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> QUARTZ_BRICK_STAIRS = registerStairs("quartz_brick_stairs",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS), () -> Blocks.QUARTZ_BRICKS);
    public static final DeferredBlock<Block> QUARTZ_BRICK_SLAB = registerSlab("quartz_brick_slab",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS));
    public static final DeferredBlock<Block> QUARTZ_BRICK_WALL = registerWall("quartz_brick_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS));
    public static final DeferredBlock<Block> SNIFFER_PLUSHIE = register("sniffer_plushie",
            SnifferPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    public static final DeferredBlock<Block> STRIPPED_PALE_OAK_WALL = registerWall("stripped_pale_oak_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS));
    public static final DeferredBlock<Block> PALE_OAK_WALL = registerWall("pale_oak_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS));
    public static final DeferredBlock<Block> BAMBOO_ROPE_LADDER = registerRopeLadder("bamboo_rope_ladder");
    public static final DeferredBlock<Block> STRIPPED_BAMBOO_WALL = registerWall("stripped_bamboo_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
    public static final DeferredBlock<Block> BAMBOO_WALL = registerWall("bamboo_wall",
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS));
    public static final DeferredBlock<Block> BLACK_WOLF_PLUSHIE = registerWolfPlushie("black_wolf_plushie");
    public static final DeferredBlock<Block> ASHEN_WOLF_PLUSHIE = registerWolfPlushie("ashen_wolf_plushie");
    public static final DeferredBlock<Block> CHESTNUT_WOLF_PLUSHIE = registerWolfPlushie("chestnut_wolf_plushie");
    public static final DeferredBlock<Block> RUSTY_WOLF_PLUSHIE = registerWolfPlushie("rusty_wolf_plushie");

    private static ResourceKey<Block> makeRegistryKey(String name) {
        return ResourceKey.create(Registries.BLOCK, AssortedDiscoveries.makeModId(name));
    }

    private static BlockPos postProcessSelf(final BlockState state, final BlockGetter blockGetter, final BlockPos blockPos) {
        return blockPos;
    }

    private static ToIntFunction<BlockState> getLuminanceFromState() {
        return (state) -> state.getValue(BlockStateProperties.LIT) ? 10 : 0;
    }

    private static <B extends Block> DeferredBlock<Block> register(String name, Function<BlockBehaviour.Properties, ? extends B> block, Supplier<BlockBehaviour.Properties> properties, boolean shouldRegisterItem) {
        DeferredBlock<Block> deferredBlock = BLOCKS.registerBlock(name, block, properties);
        if (shouldRegisterItem) {
            ITEMS.registerSimpleBlockItem(name, deferredBlock, Item.Properties::new);
        }
        return deferredBlock;
    }

    private static DeferredBlock<Block> registerSimpleBlock(String name, Supplier<BlockBehaviour.Properties> properties) {
        return register(name, Block::new, properties, true);
    }

    private static DeferredBlock<Block> registerHorsePlushie(String name) {
        return register(name, HorsePlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerCubePlushie(String name) {
        BlockBehaviour.Properties settings = BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.NONE)
                .strength(0.2F).sound(SoundType.WOOL)
                .pushReaction(PushReaction.DESTROY);
        return register(name, CubePlushieBlock::new, () -> settings, true);
    }

    private static DeferredBlock<Block> registerMooshroomPlushie(String name) {
        return register(name, MooshroomPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerWolfPlushie(String name) {
        return register(name, WolfPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerCatPlushie(String name) {
        return register(name, CatPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerRabbitPlushie(String name) {
        return register(name, RabbitPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerSheepPlushie(String name, DyeColor color) {
        return register(name, settings -> new SheepPlushieBlock(color, settings),
                ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerVillagerPlushie(String name) {
        return register(name, VillagerPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerShortHatVillagerPlushie(String name) {
        return register(name, ShortHatVillagerPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerZombiePlushie(String name) {
        return register(name, ZombiePlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerStriderPlushie(String name) {
        return register(name, StriderPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerHoglinPlushie(String name) {
        return register(name, HoglinPlushieBlock::new, ModBlocks::makePlushieSettings, true);
    }

    private static DeferredBlock<Block> registerAllayPlushie(String name) {
        return register(name, AllayPlushieBlock::new, ModBlocks::makeGlowingPlushieSettings, true);
    }

    private static DeferredBlock<Block> registerPlanterBox(String name, MapColor color, SoundType soundGroup) {
        BlockBehaviour.Properties planterBoxSettings = BlockBehaviour.Properties.of().mapColor(color)
                .strength(2.5F).sound(soundGroup).ignitedByLava();
        return register(name, PlanterBoxBlock::new, () -> planterBoxSettings, true);
    }

    private static DeferredBlock<Block> registerNetherPlanterBox(String name, MapColor color) {
        BlockBehaviour.Properties blockSettings = BlockBehaviour.Properties.of().mapColor(color).strength(2.5F)
                .sound(SoundType.NETHER_WOOD);
        return register(name, PlanterBoxBlock::new, () -> blockSettings, true);
    }

    public static DeferredBlock<Block> registerRopeLadder(String name) {
        DeferredBlock<Block> deferredBlock = BLOCKS.registerBlock(name, RopeLadderBlock::new,
                () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LADDER));
        ITEMS.registerItem(name, properties -> new RopeLadderBlockItem(deferredBlock.get(), properties),
                Item.Properties::new);
        return deferredBlock;
    }

    private static DeferredBlock<Block> registerTorch(String name, Supplier<SimpleParticleType> particle) {
        Supplier<BlockBehaviour.Properties> torchSettings = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH);
        return register(name, properties -> new TorchBlock(particle.get(), properties),
                torchSettings, false);
    }

    private static DeferredBlock<Block> registerWallTorch(String name, Supplier<Block> standingTorch, Supplier<SimpleParticleType> particle) {
        Supplier<BlockBehaviour.Properties> wallTorchSettings = () -> wallVariant(standingTorch.get()).noCollision()
                .instabreak().lightLevel((blockState) -> 14).sound(SoundType.WOOD)
                .pushReaction(PushReaction.DESTROY);
        return register(name, settings -> new WallTorchBlock(particle.get(), settings),
                wallTorchSettings, false);
    }

    private static BlockBehaviour.Properties wallVariant(Block block) {
        return BlockBehaviour.Properties.of().overrideLootTable(block.getLootTable())
                .overrideDescription(block.getDescriptionId());
    }

    private static DeferredBlock<Block> registerStairs(String name, Supplier<BlockBehaviour.Properties> settings,
                                                       Supplier<Block> baseBlock) {
        return register(name, properties -> new StairBlock(baseBlock.get().defaultBlockState(), properties),
                settings, true);
    }

    private static DeferredBlock<Block> registerSlab(String name, Supplier<BlockBehaviour.Properties> settings) {
        return register(name, SlabBlock::new, settings, true);
    }

    private static DeferredBlock<Block> registerWall(String name, Supplier<BlockBehaviour.Properties> settings) {
        return register(name, WallBlock::new, settings, true);
    }

    private static DeferredBlock<Block> registerDyedCampfire(String name, Supplier<SimpleParticleType> emberParticle) {
        return register(name, properties -> new DyedCampfireBlock(properties, emberParticle.get()),
                () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE), true);
    }

    private static DeferredBlock<Block> registerLantern(String name) {
        return register(name, LanternBlock::new,
                () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN), true);
    }

    private static DeferredBlock<Block> registerPottedSnapdragon(Supplier<BlockBehaviour.Properties> settings) {
        return register("potted_snapdragon",
                prop -> new PottedSnapdragonBlock(ModBlocks.SNAPDRAGON.get(), prop),
                settings, false);
    }

    private static DeferredBlock<Block> registerCake(String name) {
        return register(name, ModdedCakeBlock::new,
                () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE), true);
    }

    private static DeferredBlock<Block> registerChocolateCandleCake(String name, Supplier<Block> candle) {
        return registerCandleCake(name, ModBlocks.CHOCOLATE_CAKE, candle);
    }

    private static DeferredBlock<Block> registerRedVelvetCandleCake(String name, Supplier<Block> candle) {
        return registerCandleCake(name, ModBlocks.RED_VELVET_CAKE, candle);
    }

    private static DeferredBlock<Block> registerCandleCake(String name, Supplier<Block> cake, Supplier<Block> candle) {
        Function<BlockBehaviour.Properties, ModdedCandleCakeBlock> candleCakeBlock 
                = prop -> new ModdedCandleCakeBlock(cake.get(), candle.get(), prop);
        return register(name, candleCakeBlock, () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE_CAKE), false);
    }

    private static DeferredBlock<Block> registerPie(String name) {
        Supplier<BlockBehaviour.Properties> pieProperties = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE);
        Function<BlockBehaviour.Properties, PieBlock> pieBlock = prop -> new PieBlock(prop, 3, 0.6F);
        return register(name, pieBlock, pieProperties, true);
    }

    private static DeferredBlock<Block> registerSnowySlab(String name, Supplier<BlockBehaviour.Properties> settings) {
        return register(name, SnowySlabBlock::new, settings, true);
    }

    private static BlockBehaviour.Properties makeBauxiteSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).requiresCorrectToolForDrops()
                .strength(0.3F);
    }

    private static BlockBehaviour.Properties makeBauxiteBricksSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).requiresCorrectToolForDrops()
                .strength(0.4F);
    }

    private static BlockBehaviour.Properties makeSmokyQuartzSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK)
                .requiresCorrectToolForDrops().strength(0.8F);
    }

    private static BlockBehaviour.Properties makeStoneTileSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
                .sound(SoundType.DEEPSLATE_TILES);
    }

    private static BlockBehaviour.Properties makeBlackstoneTileSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
                .sound(SoundType.DEEPSLATE_TILES);
    }

    private static BlockBehaviour.Properties makeQuartzTileSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK)
                .sound(SoundType.DEEPSLATE_TILES);
    }

    private static BlockBehaviour.Properties makePlushieSettings() {
        return BlockBehaviour.Properties.of().ignitedByLava().mapColor(MapColor.NONE)
                .strength(0.2F).sound(SoundType.WOOL);
    }

    private static BlockBehaviour.Properties makeGlowingPlushieSettings() {
        return BlockBehaviour.Properties.of().ignitedByLava()
                .mapColor(MapColor.NONE).strength(0.2F).sound(SoundType.WOOL)
                .lightLevel((state) -> 10);
    }

    private static BlockBehaviour.Properties makeSnowBrickSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
                .strength(0.4F).requiresCorrectToolForDrops().sound(SoundType.SNOW);
    }

    private static BlockBehaviour.Properties makePackedSnowSettings() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.SNOW)
                .strength(0.6F).requiresCorrectToolForDrops().sound(SoundType.SNOW);
    }

    private static BlockBehaviour.Properties makeCalciteSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE);
    }

    private static BlockBehaviour.Properties makeDripstoneSettings() {
        return BlockBehaviour.Properties.ofFullCopy(Blocks.DRIPSTONE_BLOCK);
    }

    /**
     * Called during mod initialization to make sure that every block
     * is registered and available later during gameplay.
     */
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        AssortedDiscoveries.LOGGER.info("Registered blocks");
    }
}
