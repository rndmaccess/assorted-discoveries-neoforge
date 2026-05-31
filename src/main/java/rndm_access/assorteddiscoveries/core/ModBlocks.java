package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
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
            ModBlocks::makeSmokyQuartzSettings, true);
    public static final DeferredBlock<Block> CHISELED_SMOKY_QUARTZ_BLOCK = registerSimpleBlock("chiseled_smoky_quartz_block",
            ModBlocks::makeSmokyQuartzSettings, true);
    public static final DeferredBlock<Block> SMOKY_QUARTZ_BRICKS = registerSimpleBlock("smoky_quartz_bricks",
            ModBlocks::makeSmokyQuartzSettings, true);
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
            ModBlocks::makeSmokyQuartzSettings, true);
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
            ModBlocks::makeSnowBrickSettings, true);
    public static final DeferredBlock<Block> SNOW_BRICK_STAIRS = registerStairs("snow_brick_stairs",
            ModBlocks::makeSnowBrickSettings, SNOW_BRICKS);
    public static final DeferredBlock<Block> SNOW_BRICK_SLAB = registerSlab("snow_brick_slab",
            ModBlocks::makeSnowBrickSettings);
    public static final DeferredBlock<Block> SNOW_BRICK_WALL = registerWall("snow_brick_wall",
            ModBlocks::makeSnowBrickSettings);
    public static final DeferredBlock<Block> PACKED_SNOW = registerSimpleBlock("packed_snow",
            ModBlocks::makePackedSnowSettings, true);
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
    public static final DeferredBlock<Block> WHITE_CAMPFIRE = registerDyedCampfire("white_campfire",
            ModParticleTypes.WHITE_EMBER);





    public static final ResourceKey<Block> ORANGE_CAMPFIRE_KEY = makeRegistryKey("orange_campfire");
    public static final Block ORANGE_CAMPFIRE
            = registerDyedCampfire(ORANGE_CAMPFIRE_KEY, ModParticleTypes.ORANGE_EMBER);
    public static final ResourceKey<Block> MAGENTA_CAMPFIRE_KEY = makeRegistryKey("magenta_campfire");
    public static final Block MAGENTA_CAMPFIRE
            = registerDyedCampfire(MAGENTA_CAMPFIRE_KEY, ModParticleTypes.MAGENTA_EMBER);
    public static final ResourceKey<Block> LIGHT_BLUE_CAMPFIRE_KEY = makeRegistryKey("light_blue_campfire");
    public static final Block LIGHT_BLUE_CAMPFIRE
            = registerDyedCampfire(LIGHT_BLUE_CAMPFIRE_KEY, ModParticleTypes.LIGHT_BLUE_EMBER);
    public static final ResourceKey<Block> YELLOW_CAMPFIRE_KEY = makeRegistryKey("yellow_campfire");
    public static final Block YELLOW_CAMPFIRE
            = registerDyedCampfire(YELLOW_CAMPFIRE_KEY, ModParticleTypes.YELLOW_EMBER);
    public static final ResourceKey<Block> LIME_CAMPFIRE_KEY = makeRegistryKey("lime_campfire");
    public static final Block LIME_CAMPFIRE = registerDyedCampfire(LIME_CAMPFIRE_KEY, ModParticleTypes.LIME_EMBER);
    public static final ResourceKey<Block> PINK_CAMPFIRE_KEY = makeRegistryKey("pink_campfire");
    public static final Block PINK_CAMPFIRE = registerDyedCampfire(PINK_CAMPFIRE_KEY, ModParticleTypes.PINK_EMBER);
    public static final ResourceKey<Block> GRAY_CAMPFIRE_KEY = makeRegistryKey("gray_campfire");
    public static final Block GRAY_CAMPFIRE = registerDyedCampfire(GRAY_CAMPFIRE_KEY, ModParticleTypes.GRAY_EMBER);
    public static final ResourceKey<Block> LIGHT_GRAY_CAMPFIRE_KEY = makeRegistryKey("light_gray_campfire");
    public static final Block LIGHT_GRAY_CAMPFIRE
            = registerDyedCampfire(LIGHT_GRAY_CAMPFIRE_KEY, ModParticleTypes.LIGHT_GRAY_EMBER);
    public static final ResourceKey<Block> CYAN_CAMPFIRE_KEY = makeRegistryKey("cyan_campfire");
    public static final Block CYAN_CAMPFIRE = registerDyedCampfire(CYAN_CAMPFIRE_KEY, ModParticleTypes.CYAN_EMBER);
    public static final ResourceKey<Block> PURPLE_CAMPFIRE_KEY = makeRegistryKey("purple_campfire");
    public static final Block PURPLE_CAMPFIRE = registerDyedCampfire(PURPLE_CAMPFIRE_KEY, ModParticleTypes.PURPLE_EMBER);
    public static final ResourceKey<Block> BLUE_CAMPFIRE_KEY = makeRegistryKey("blue_campfire");
    public static final Block BLUE_CAMPFIRE = registerDyedCampfire(BLUE_CAMPFIRE_KEY, ModParticleTypes.BLUE_EMBER);
    public static final ResourceKey<Block> BROWN_CAMPFIRE_KEY = makeRegistryKey("brown_campfire");
    public static final Block BROWN_CAMPFIRE = registerDyedCampfire(BROWN_CAMPFIRE_KEY, ModParticleTypes.BROWN_EMBER);
    public static final ResourceKey<Block> GREEN_CAMPFIRE_KEY = makeRegistryKey("green_campfire");
    public static final Block GREEN_CAMPFIRE = registerDyedCampfire(GREEN_CAMPFIRE_KEY, ModParticleTypes.GREEN_EMBER);
    public static final ResourceKey<Block> RED_CAMPFIRE_KEY = makeRegistryKey("red_campfire");
    public static final Block RED_CAMPFIRE = registerDyedCampfire(RED_CAMPFIRE_KEY, ModParticleTypes.RED_EMBER);
    public static final ResourceKey<Block> BLACK_CAMPFIRE_KEY = makeRegistryKey("black_campfire");
    public static final Block BLACK_CAMPFIRE = registerDyedCampfire(BLACK_CAMPFIRE_KEY, ModParticleTypes.BLACK_EMBER);
    public static final ResourceKey<Block> WHITE_LANTERN_KEY = makeRegistryKey("white_lantern");
    public static final Block WHITE_LANTERN = registerLantern(WHITE_LANTERN_KEY);
    public static final ResourceKey<Block> ORANGE_LANTERN_KEY = makeRegistryKey("orange_lantern");
    public static final Block ORANGE_LANTERN = registerLantern(ORANGE_LANTERN_KEY);
    public static final ResourceKey<Block> MAGENTA_LANTERN_KEY = makeRegistryKey("magenta_lantern");
    public static final Block MAGENTA_LANTERN = registerLantern(MAGENTA_LANTERN_KEY);
    public static final ResourceKey<Block> LIGHT_BLUE_LANTERN_KEY = makeRegistryKey("light_blue_lantern");
    public static final Block LIGHT_BLUE_LANTERN = registerLantern(LIGHT_BLUE_LANTERN_KEY);
    public static final ResourceKey<Block> YELLOW_LANTERN_KEY = makeRegistryKey("yellow_lantern");
    public static final Block YELLOW_LANTERN = registerLantern(YELLOW_LANTERN_KEY);
    public static final ResourceKey<Block> LIME_LANTERN_KEY = makeRegistryKey("lime_lantern");
    public static final Block LIME_LANTERN = registerLantern(LIME_LANTERN_KEY);
    public static final ResourceKey<Block> PINK_LANTERN_KEY = makeRegistryKey("pink_lantern");
    public static final Block PINK_LANTERN = registerLantern(PINK_LANTERN_KEY);
    public static final ResourceKey<Block> GRAY_LANTERN_KEY = makeRegistryKey("gray_lantern");
    public static final Block GRAY_LANTERN = registerLantern(GRAY_LANTERN_KEY);
    public static final ResourceKey<Block> LIGHT_GRAY_LANTERN_KEY = makeRegistryKey("light_gray_lantern");
    public static final Block LIGHT_GRAY_LANTERN = registerLantern(LIGHT_GRAY_LANTERN_KEY);
    public static final ResourceKey<Block> CYAN_LANTERN_KEY = makeRegistryKey("cyan_lantern");
    public static final Block CYAN_LANTERN = registerLantern(CYAN_LANTERN_KEY);
    public static final ResourceKey<Block> PURPLE_LANTERN_KEY = makeRegistryKey("purple_lantern");
    public static final Block PURPLE_LANTERN = registerLantern(PURPLE_LANTERN_KEY);
    public static final ResourceKey<Block> BLUE_LANTERN_KEY = makeRegistryKey("blue_lantern");
    public static final Block BLUE_LANTERN = registerLantern(BLUE_LANTERN_KEY);
    public static final ResourceKey<Block> BROWN_LANTERN_KEY = makeRegistryKey("brown_lantern");
    public static final Block BROWN_LANTERN = registerLantern(BROWN_LANTERN_KEY);
    public static final ResourceKey<Block> GREEN_LANTERN_KEY = makeRegistryKey("green_lantern");
    public static final Block GREEN_LANTERN = registerLantern(GREEN_LANTERN_KEY);
    public static final ResourceKey<Block> RED_LANTERN_KEY = makeRegistryKey("red_lantern");
    public static final Block RED_LANTERN = registerLantern(RED_LANTERN_KEY);
    public static final ResourceKey<Block> BLACK_LANTERN_KEY = makeRegistryKey("black_lantern");
    public static final Block BLACK_LANTERN = registerLantern(BLACK_LANTERN_KEY);
    public static final ResourceKey<Block> WHITE_TORCH_KEY = makeRegistryKey("white_torch");
    public static final Block WHITE_TORCH = registerTorch(WHITE_TORCH_KEY, ModParticleTypes.WHITE_FLAME);
    public static final ResourceKey<Block> ORANGE_TORCH_KEY = makeRegistryKey("orange_torch");
    public static final Block ORANGE_TORCH = registerTorch(ORANGE_TORCH_KEY, ModParticleTypes.ORANGE_FLAME);
    public static final ResourceKey<Block> MAGENTA_TORCH_KEY = makeRegistryKey("magenta_torch");
    public static final Block MAGENTA_TORCH = registerTorch(MAGENTA_TORCH_KEY, ModParticleTypes.MAGENTA_FLAME);
    public static final ResourceKey<Block> LIGHT_BLUE_TORCH_KEY = makeRegistryKey("light_blue_torch");
    public static final Block LIGHT_BLUE_TORCH = registerTorch(LIGHT_BLUE_TORCH_KEY, ModParticleTypes.LIGHT_BLUE_FLAME);
    public static final ResourceKey<Block> YELLOW_TORCH_KEY = makeRegistryKey("yellow_torch");
    public static final Block YELLOW_TORCH = registerTorch(YELLOW_TORCH_KEY, ModParticleTypes.YELLOW_FLAME);
    public static final ResourceKey<Block> LIME_TORCH_KEY = makeRegistryKey("lime_torch");
    public static final Block LIME_TORCH = registerTorch(LIME_TORCH_KEY, ModParticleTypes.LIME_FLAME);
    public static final ResourceKey<Block> PINK_TORCH_KEY = makeRegistryKey("pink_torch");
    public static final Block PINK_TORCH = registerTorch(PINK_TORCH_KEY, ModParticleTypes.PINK_FLAME);
    public static final ResourceKey<Block> GRAY_TORCH_KEY = makeRegistryKey("gray_torch");
    public static final Block GRAY_TORCH = registerTorch(GRAY_TORCH_KEY, ModParticleTypes.GRAY_FLAME);
    public static final ResourceKey<Block> LIGHT_GRAY_TORCH_KEY = makeRegistryKey("light_gray_torch");
    public static final Block LIGHT_GRAY_TORCH = registerTorch(LIGHT_GRAY_TORCH_KEY, ModParticleTypes.LIGHT_GRAY_FLAME);
    public static final ResourceKey<Block> CYAN_TORCH_KEY = makeRegistryKey("cyan_torch");
    public static final Block CYAN_TORCH = registerTorch(CYAN_TORCH_KEY, ModParticleTypes.CYAN_FLAME);
    public static final ResourceKey<Block> PURPLE_TORCH_KEY = makeRegistryKey("purple_torch");
    public static final Block PURPLE_TORCH = registerTorch(PURPLE_TORCH_KEY, ModParticleTypes.PURPLE_FLAME);
    public static final ResourceKey<Block> BLUE_TORCH_KEY = makeRegistryKey("blue_torch");
    public static final Block BLUE_TORCH = registerTorch(BLUE_TORCH_KEY, ModParticleTypes.BLUE_FLAME);
    public static final ResourceKey<Block> BROWN_TORCH_KEY = makeRegistryKey("brown_torch");
    public static final Block BROWN_TORCH = registerTorch(BROWN_TORCH_KEY, ModParticleTypes.BROWN_FLAME);
    public static final ResourceKey<Block> GREEN_TORCH_KEY = makeRegistryKey("green_torch");
    public static final Block GREEN_TORCH = registerTorch(GREEN_TORCH_KEY, ModParticleTypes.GREEN_FLAME);
    public static final ResourceKey<Block> RED_TORCH_KEY = makeRegistryKey("red_torch");
    public static final Block RED_TORCH = registerTorch(RED_TORCH_KEY, ModParticleTypes.RED_FLAME);
    public static final ResourceKey<Block> BLACK_TORCH_KEY = makeRegistryKey("black_torch");
    public static final Block BLACK_TORCH = registerTorch(BLACK_TORCH_KEY, ModParticleTypes.BLACK_FLAME);
    public static final ResourceKey<Block> WHITE_WALL_TORCH_KEY = makeRegistryKey("white_wall_torch");
    public static final Block WHITE_WALL_TORCH = registerWallTorch(WHITE_WALL_TORCH_KEY, WHITE_TORCH, ModParticleTypes.WHITE_FLAME);
    public static final ResourceKey<Block> ORANGE_WALL_TORCH_KEY = makeRegistryKey("orange_wall_torch");
    public static final Block ORANGE_WALL_TORCH = registerWallTorch(ORANGE_WALL_TORCH_KEY, ORANGE_TORCH, ModParticleTypes.ORANGE_FLAME);
    public static final ResourceKey<Block> MAGENTA_WALL_TORCH_KEY = makeRegistryKey("magenta_wall_torch");
    public static final Block MAGENTA_WALL_TORCH = registerWallTorch(MAGENTA_WALL_TORCH_KEY, MAGENTA_TORCH, ModParticleTypes.MAGENTA_FLAME);
    public static final ResourceKey<Block> LIGHT_BLUE_WALL_TORCH_KEY = makeRegistryKey("light_blue_wall_torch");
    public static final Block LIGHT_BLUE_WALL_TORCH = registerWallTorch(LIGHT_BLUE_WALL_TORCH_KEY, LIGHT_BLUE_TORCH, ModParticleTypes.LIGHT_BLUE_FLAME);
    public static final ResourceKey<Block> YELLOW_WALL_TORCH_KEY = makeRegistryKey("yellow_wall_torch");
    public static final Block YELLOW_WALL_TORCH = registerWallTorch(YELLOW_WALL_TORCH_KEY, YELLOW_TORCH, ModParticleTypes.YELLOW_FLAME);
    public static final ResourceKey<Block> LIME_WALL_TORCH_KEY = makeRegistryKey("lime_wall_torch");
    public static final Block LIME_WALL_TORCH = registerWallTorch(LIME_WALL_TORCH_KEY, LIME_TORCH, ModParticleTypes.LIME_FLAME);
    public static final ResourceKey<Block> PINK_WALL_TORCH_KEY = makeRegistryKey("pink_wall_torch");
    public static final Block PINK_WALL_TORCH = registerWallTorch(PINK_WALL_TORCH_KEY, PINK_TORCH, ModParticleTypes.PINK_FLAME);
    public static final ResourceKey<Block> GRAY_WALL_TORCH_KEY = makeRegistryKey("gray_wall_torch");
    public static final Block GRAY_WALL_TORCH = registerWallTorch(GRAY_WALL_TORCH_KEY, GRAY_TORCH, ModParticleTypes.GRAY_FLAME);
    public static final ResourceKey<Block> LIGHT_GRAY_WALL_TORCH_KEY = makeRegistryKey("light_gray_wall_torch");
    public static final Block LIGHT_GRAY_WALL_TORCH = registerWallTorch(LIGHT_GRAY_WALL_TORCH_KEY, LIGHT_GRAY_TORCH, ModParticleTypes.LIGHT_GRAY_FLAME);
    public static final ResourceKey<Block> CYAN_WALL_TORCH_KEY = makeRegistryKey("cyan_wall_torch");
    public static final Block CYAN_WALL_TORCH = registerWallTorch(CYAN_WALL_TORCH_KEY, CYAN_TORCH, ModParticleTypes.CYAN_FLAME);
    public static final ResourceKey<Block> PURPLE_WALL_TORCH_KEY = makeRegistryKey("purple_wall_torch");
    public static final Block PURPLE_WALL_TORCH = registerWallTorch(PURPLE_WALL_TORCH_KEY, PURPLE_TORCH, ModParticleTypes.PURPLE_FLAME);
    public static final ResourceKey<Block> BLUE_WALL_TORCH_KEY = makeRegistryKey("blue_wall_torch");
    public static final Block BLUE_WALL_TORCH = registerWallTorch(BLUE_WALL_TORCH_KEY, BLUE_TORCH, ModParticleTypes.BLUE_FLAME);
    public static final ResourceKey<Block> BROWN_WALL_TORCH_KEY = makeRegistryKey("brown_wall_torch");
    public static final Block BROWN_WALL_TORCH = registerWallTorch(BROWN_WALL_TORCH_KEY, BROWN_TORCH, ModParticleTypes.BROWN_FLAME);
    public static final ResourceKey<Block> GREEN_WALL_TORCH_KEY = makeRegistryKey("green_wall_torch");
    public static final Block GREEN_WALL_TORCH = registerWallTorch(GREEN_WALL_TORCH_KEY, GREEN_TORCH, ModParticleTypes.GREEN_FLAME);
    public static final ResourceKey<Block> RED_WALL_TORCH_KEY = makeRegistryKey("red_wall_torch");
    public static final Block RED_WALL_TORCH = registerWallTorch(RED_WALL_TORCH_KEY, RED_TORCH, ModParticleTypes.RED_FLAME);
    public static final ResourceKey<Block> BLACK_WALL_TORCH_KEY = makeRegistryKey("black_wall_torch");
    public static final Block BLACK_WALL_TORCH = registerWallTorch(BLACK_WALL_TORCH_KEY, BLACK_TORCH, ModParticleTypes.BLACK_FLAME);
    public static final ResourceKey<Block> WITCHS_CRADLE_KEY = makeRegistryKey("witchs_cradle");
    public static final Block WITCHS_CRADLE = register(new WitchsCradleBlock(BlockBehaviour.Properties
            .ofFullCopy(Blocks.SWEET_BERRY_BUSH).lightLevel((state) -> 8).setId(WITCHS_CRADLE_KEY)),
            WITCHS_CRADLE_KEY, false);
    public static final ResourceKey<Block> BAUXITE_KEY = makeRegistryKey("bauxite");
    public static final Block BAUXITE = register(new Block(makeBauxiteSettings(BAUXITE_KEY)),
            BAUXITE_KEY, true);
    public static final ResourceKey<Block> BAUXITE_SLAB_KEY = makeRegistryKey("bauxite_slab");
    public static final Block BAUXITE_SLAB = registerSlab(BAUXITE_SLAB_KEY, makeBauxiteSettings(BAUXITE_SLAB_KEY));
    public static final ResourceKey<Block> BAUXITE_STAIRS_KEY = makeRegistryKey("bauxite_stairs");
    public static final Block BAUXITE_STAIRS
            = registerStairs(BAUXITE_STAIRS_KEY, makeBauxiteSettings(BAUXITE_STAIRS_KEY), BAUXITE);
    public static final ResourceKey<Block> BAUXITE_WALL_KEY = makeRegistryKey("bauxite_wall");
    public static final Block BAUXITE_WALL = registerWall(BAUXITE_WALL_KEY, makeBauxiteSettings(BAUXITE_WALL_KEY));
    public static final ResourceKey<Block> BAUXITE_BRICKS_KEY = makeRegistryKey("bauxite_bricks");
    public static final Block BAUXITE_BRICKS = register(new Block(makeBauxiteBricksSettings(BAUXITE_BRICKS_KEY)),
            BAUXITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> BAUXITE_BRICK_STAIRS_KEY = makeRegistryKey("bauxite_brick_stairs");
    public static final Block BAUXITE_BRICK_STAIRS
            = registerStairs(BAUXITE_BRICK_STAIRS_KEY,
            makeBauxiteBricksSettings(BAUXITE_BRICK_STAIRS_KEY), BAUXITE_BRICKS);
    public static final ResourceKey<Block> BAUXITE_BRICK_SLAB_KEY = makeRegistryKey("bauxite_brick_slab");
    public static final Block BAUXITE_BRICK_SLAB
            = registerSlab(BAUXITE_BRICK_SLAB_KEY, makeBauxiteBricksSettings(BAUXITE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> BAUXITE_BRICK_WALL_KEY = makeRegistryKey("bauxite_brick_wall");
    public static final Block BAUXITE_BRICK_WALL
            = registerWall(BAUXITE_BRICK_WALL_KEY, makeBauxiteBricksSettings(BAUXITE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> MOSSY_BAUXITE_BRICKS_KEY = makeRegistryKey("mossy_bauxite_bricks");
    public static final Block MOSSY_BAUXITE_BRICKS
            = register(new Block(makeBauxiteBricksSettings(MOSSY_BAUXITE_BRICKS_KEY)),
            MOSSY_BAUXITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> MOSSY_BAUXITE_BRICK_STAIRS_KEY
            = makeRegistryKey("mossy_bauxite_brick_stairs");
    public static final Block MOSSY_BAUXITE_BRICK_STAIRS
            = registerStairs(MOSSY_BAUXITE_BRICK_STAIRS_KEY, makeBauxiteBricksSettings(MOSSY_BAUXITE_BRICK_STAIRS_KEY),
            MOSSY_BAUXITE_BRICKS);
    public static final ResourceKey<Block> MOSSY_BAUXITE_BRICK_SLAB_KEY
            = makeRegistryKey("mossy_bauxite_brick_slab");
    public static final Block MOSSY_BAUXITE_BRICK_SLAB
            = registerSlab(MOSSY_BAUXITE_BRICK_SLAB_KEY, makeBauxiteBricksSettings(MOSSY_BAUXITE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> MOSSY_BAUXITE_BRICK_WALL_KEY
            = makeRegistryKey("mossy_bauxite_brick_wall");
    public static final Block MOSSY_BAUXITE_BRICK_WALL
            = registerWall(MOSSY_BAUXITE_BRICK_WALL_KEY, makeBauxiteBricksSettings(MOSSY_BAUXITE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> CRACKED_BAUXITE_BRICKS_KEY = makeRegistryKey("cracked_bauxite_bricks");
    public static final Block CRACKED_BAUXITE_BRICKS
            = register(new Block(makeBauxiteBricksSettings(CRACKED_BAUXITE_BRICKS_KEY)),
            CRACKED_BAUXITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> CRACKED_BAUXITE_BRICK_STAIRS_KEY
            = makeRegistryKey("cracked_bauxite_brick_stairs");
    public static final Block CRACKED_BAUXITE_BRICK_STAIRS
            = registerStairs(CRACKED_BAUXITE_BRICK_STAIRS_KEY,
            makeBauxiteBricksSettings(CRACKED_BAUXITE_BRICK_STAIRS_KEY), CRACKED_BAUXITE_BRICKS);
    public static final ResourceKey<Block> CRACKED_BAUXITE_BRICK_SLAB_KEY
            = makeRegistryKey("cracked_bauxite_brick_slab");
    public static final Block CRACKED_BAUXITE_BRICK_SLAB
            = registerSlab(CRACKED_BAUXITE_BRICK_SLAB_KEY, makeBauxiteBricksSettings(CRACKED_BAUXITE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> CRACKED_BAUXITE_BRICK_WALL_KEY
            = makeRegistryKey("cracked_bauxite_brick_wall");
    public static final Block CRACKED_BAUXITE_BRICK_WALL
            = registerWall(CRACKED_BAUXITE_BRICK_WALL_KEY, makeBauxiteBricksSettings(CRACKED_BAUXITE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> TWISTED_NETHER_BRICKS_KEY = makeRegistryKey("twisted_nether_bricks");
    public static final Block TWISTED_NETHER_BRICKS
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
                    .setId(TWISTED_NETHER_BRICKS_KEY)),
            TWISTED_NETHER_BRICKS_KEY, true);
    public static final ResourceKey<Block> TWISTED_NETHER_BRICK_STAIRS_KEY
            = makeRegistryKey("twisted_nether_brick_stairs");
    public static final Block TWISTED_NETHER_BRICK_STAIRS
            = registerStairs(TWISTED_NETHER_BRICK_STAIRS_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
                    .setId(TWISTED_NETHER_BRICK_STAIRS_KEY), TWISTED_NETHER_BRICKS);
    public static final ResourceKey<Block> TWISTED_NETHER_BRICK_SLAB_KEY
            = makeRegistryKey("twisted_nether_brick_slab");
    public static final Block TWISTED_NETHER_BRICK_SLAB
            = registerSlab(TWISTED_NETHER_BRICK_SLAB_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
            .setId(TWISTED_NETHER_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> TWISTED_NETHER_BRICK_WALL_KEY
            = makeRegistryKey("twisted_nether_brick_wall");
    public static final Block TWISTED_NETHER_BRICK_WALL
            = registerWall(TWISTED_NETHER_BRICK_WALL_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
            .setId(TWISTED_NETHER_BRICK_WALL_KEY));
    public static final ResourceKey<Block> TWISTED_NETHERRACK_KEY = makeRegistryKey("twisted_netherrack");
    public static final Block TWISTED_NETHERRACK = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
            .setId(TWISTED_NETHERRACK_KEY)), TWISTED_NETHERRACK_KEY, true);
    public static final ResourceKey<Block> TWISTED_NETHERRACK_STAIRS_KEY
            = makeRegistryKey("twisted_netherrack_stairs");
    public static final Block TWISTED_NETHERRACK_STAIRS = registerStairs(TWISTED_NETHERRACK_STAIRS_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).setId(TWISTED_NETHERRACK_STAIRS_KEY),
            TWISTED_NETHERRACK);
    public static final ResourceKey<Block> TWISTED_NETHERRACK_SLAB_KEY
            = makeRegistryKey("twisted_netherrack_slab");
    public static final Block TWISTED_NETHERRACK_SLAB = registerSlab(TWISTED_NETHERRACK_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).setId(TWISTED_NETHERRACK_SLAB_KEY));
    public static final ResourceKey<Block> TWISTED_NETHERRACK_WALL_KEY
            = makeRegistryKey("twisted_netherrack_wall");
    public static final Block TWISTED_NETHERRACK_WALL = registerWall(TWISTED_NETHERRACK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).setId(TWISTED_NETHERRACK_WALL_KEY));
    public static final ResourceKey<Block> WEEPING_NETHER_BRICKS_KEY = makeRegistryKey("weeping_nether_bricks");
    public static final Block WEEPING_NETHER_BRICKS
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
            .setId(WEEPING_NETHER_BRICKS_KEY)), WEEPING_NETHER_BRICKS_KEY, true);
    public static final ResourceKey<Block> WEEPING_NETHER_BRICK_STAIRS_KEY
            = makeRegistryKey("weeping_nether_brick_stairs");
    public static final Block WEEPING_NETHER_BRICK_STAIRS = registerStairs(WEEPING_NETHER_BRICK_STAIRS_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
                    .setId(WEEPING_NETHER_BRICK_STAIRS_KEY), WEEPING_NETHER_BRICKS);
    public static final ResourceKey<Block> WEEPING_NETHER_BRICK_SLAB_KEY
            = makeRegistryKey("weeping_nether_brick_slab");
    public static final Block WEEPING_NETHER_BRICK_SLAB = registerSlab(WEEPING_NETHER_BRICK_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
                .setId(WEEPING_NETHER_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> WEEPING_NETHER_BRICK_WALL_KEY
            = makeRegistryKey("weeping_nether_brick_wall");
    public static final Block WEEPING_NETHER_BRICK_WALL = registerWall(WEEPING_NETHER_BRICK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_BRICKS)
                .setId(WEEPING_NETHER_BRICK_WALL_KEY));
    public static final ResourceKey<Block> WEEPING_NETHERRACK_KEY = makeRegistryKey("weeping_netherrack");
    public static final Block WEEPING_NETHERRACK
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
            .setId(WEEPING_NETHERRACK_KEY)), WEEPING_NETHERRACK_KEY, true);
    public static final ResourceKey<Block> WEEPING_NETHERRACK_STAIRS_KEY
            = makeRegistryKey("weeping_netherrack_stairs");
    public static final Block WEEPING_NETHERRACK_STAIRS = registerStairs(WEEPING_NETHERRACK_STAIRS_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
                    .setId(WEEPING_NETHERRACK_STAIRS_KEY), WEEPING_NETHERRACK);
    public static final ResourceKey<Block> WEEPING_NETHERRACK_SLAB_KEY
            = makeRegistryKey("weeping_netherrack_slab");
    public static final Block WEEPING_NETHERRACK_SLAB = registerSlab(WEEPING_NETHERRACK_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK)
                    .setId(WEEPING_NETHERRACK_SLAB_KEY));
    public static final ResourceKey<Block> WEEPING_NETHERRACK_WALL_KEY = makeRegistryKey("weeping_netherrack_wall");
    public static final Block WEEPING_NETHERRACK_WALL = registerWall(WEEPING_NETHERRACK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.NETHERRACK).setId(WEEPING_NETHERRACK_WALL_KEY));
    public static final ResourceKey<Block> SNAPDRAGON_KEY = makeRegistryKey("snapdragon");
    public static final Block SNAPDRAGON = register(new SnapdragonBlock(MobEffects.LUCK, 8,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY).lightLevel((state) -> 8)
                    .setId(SNAPDRAGON_KEY)), SNAPDRAGON_KEY, true);
    public static final ResourceKey<Block> POTTED_SNAPDRAGON_KEY = makeRegistryKey("potted_snapdragon");
    public static final Block POTTED_SNAPDRAGON = registerPottedSnapdragon(BlockBehaviour.Properties
            .ofFullCopy(Blocks.POTTED_POPPY).lightLevel((state) -> 8).setId(POTTED_SNAPDRAGON_KEY));
    public static final ResourceKey<Block> POTTED_PURPLE_MUSHROOM_KEY = makeRegistryKey("potted_purple_mushroom");
    public static final Block POTTED_PURPLE_MUSHROOM = register(new FlowerPotBlock(ModBlocks.PURPLE_MUSHROOM,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_RED_MUSHROOM).setId(POTTED_PURPLE_MUSHROOM_KEY)),
            POTTED_PURPLE_MUSHROOM_KEY, false);
    public static final ResourceKey<Block> SHORT_ENDER_GRASS_KEY = makeRegistryKey("short_ender_grass");
    public static final Block SHORT_ENDER_GRASS
        = register(new ShortEnderGrassBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SHORT_GRASS)
            .lightLevel((state) -> 8).setId(SHORT_ENDER_GRASS_KEY)),
            SHORT_ENDER_GRASS_KEY, true);
    public static final ResourceKey<Block> CATTAIL_KEY = makeRegistryKey("cattail");
    public static final Block CATTAIL = register(new CattailBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_BROWN).noCollision().noOcclusion().sound(SoundType.WET_GRASS)
            .setId(CATTAIL_KEY)), CATTAIL_KEY, true);
    public static final ResourceKey<Block> CHOCOLATE_CAKE_KEY = makeRegistryKey("chocolate_cake");
    public static final Block CHOCOLATE_CAKE = registerCake(CHOCOLATE_CAKE_KEY);
    public static final ResourceKey<Block> RED_VELVET_CAKE_KEY = makeRegistryKey("red_velvet_cake");
    public static final Block RED_VELVET_CAKE = registerCake(RED_VELVET_CAKE_KEY);
    public static final ResourceKey<Block> CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("candle_chocolate_cake");
    public static final Block CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(CANDLE_CHOCOLATE_CAKE_KEY, Blocks.CANDLE);
    public static final ResourceKey<Block> WHITE_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("white_candle_chocolate_cake");
    public static final Block WHITE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(WHITE_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.WHITE_CANDLE);
    public static final ResourceKey<Block> ORANGE_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("orange_candle_chocolate_cake");
    public static final Block ORANGE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(ORANGE_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.ORANGE_CANDLE);
    public static final ResourceKey<Block> MAGENTA_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("magenta_candle_chocolate_cake");
    public static final Block MAGENTA_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(MAGENTA_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.MAGENTA_CANDLE);
    public static final ResourceKey<Block> LIGHT_BLUE_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("light_blue_candle_chocolate_cake");
    public static final Block LIGHT_BLUE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(LIGHT_BLUE_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.LIGHT_BLUE_CANDLE);
    public static final ResourceKey<Block> YELLOW_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("yellow_candle_chocolate_cake");
    public static final Block YELLOW_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(YELLOW_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.YELLOW_CANDLE);
    public static final ResourceKey<Block> LIME_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("lime_candle_chocolate_cake");
    public static final Block LIME_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(LIME_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.LIME_CANDLE);
    public static final ResourceKey<Block> PINK_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("pink_candle_chocolate_cake");
    public static final Block PINK_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(PINK_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.PINK_CANDLE);
    public static final ResourceKey<Block> GRAY_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("gray_candle_chocolate_cake");
    public static final Block GRAY_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(GRAY_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.GRAY_CANDLE);
    public static final ResourceKey<Block> LIGHT_GRAY_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("light_gray_candle_chocolate_cake");
    public static final Block LIGHT_GRAY_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(LIGHT_GRAY_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.LIGHT_GRAY_CANDLE);
    public static final ResourceKey<Block> CYAN_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("cyan_candle_chocolate_cake");
    public static final Block CYAN_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(CYAN_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.CYAN_CANDLE);
    public static final ResourceKey<Block> PURPLE_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("purple_candle_chocolate_cake");
    public static final Block PURPLE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(PURPLE_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.PURPLE_CANDLE);
    public static final ResourceKey<Block> BLUE_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("blue_candle_chocolate_cake");
    public static final Block BLUE_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(BLUE_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.BLUE_CANDLE);
    public static final ResourceKey<Block> BROWN_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("brown_candle_chocolate_cake");
    public static final Block BROWN_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(BROWN_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.BROWN_CANDLE);
    public static final ResourceKey<Block> GREEN_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("green_candle_chocolate_cake");
    public static final Block GREEN_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(GREEN_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.GREEN_CANDLE);
    public static final ResourceKey<Block> RED_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("red_candle_chocolate_cake");
    public static final Block RED_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(RED_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.RED_CANDLE);
    public static final ResourceKey<Block> BLACK_CANDLE_CHOCOLATE_CAKE_KEY
            = makeRegistryKey("black_candle_chocolate_cake");
    public static final Block BLACK_CANDLE_CHOCOLATE_CAKE
            = registerChocolateCandleCake(BLACK_CANDLE_CHOCOLATE_CAKE_KEY, Blocks.BLACK_CANDLE);
    public static final ResourceKey<Block> CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("candle_red_velvet_cake");
    public static final Block CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(CANDLE_RED_VELVET_CAKE_KEY, Blocks.CANDLE);
    public static final ResourceKey<Block> WHITE_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("white_candle_red_velvet_cake");
    public static final Block WHITE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(WHITE_CANDLE_RED_VELVET_CAKE_KEY, Blocks.WHITE_CANDLE);
    public static final ResourceKey<Block> ORANGE_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("orange_candle_red_velvet_cake");
    public static final Block ORANGE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(ORANGE_CANDLE_RED_VELVET_CAKE_KEY, Blocks.ORANGE_CANDLE);
    public static final ResourceKey<Block> MAGENTA_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("magenta_candle_red_velvet_cake");
    public static final Block MAGENTA_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(MAGENTA_CANDLE_RED_VELVET_CAKE_KEY, Blocks.MAGENTA_CANDLE);
    public static final ResourceKey<Block> LIGHT_BLUE_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("light_blue_candle_red_velvet_cake");
    public static final Block LIGHT_BLUE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(LIGHT_BLUE_CANDLE_RED_VELVET_CAKE_KEY, Blocks.LIGHT_BLUE_CANDLE);
    public static final ResourceKey<Block> YELLOW_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("yellow_candle_red_velvet_cake");
    public static final Block YELLOW_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(YELLOW_CANDLE_RED_VELVET_CAKE_KEY, Blocks.YELLOW_CANDLE);
    public static final ResourceKey<Block> LIME_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("lime_candle_red_velvet_cake");
    public static final Block LIME_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(LIME_CANDLE_RED_VELVET_CAKE_KEY, Blocks.LIME_CANDLE);
    public static final ResourceKey<Block> PINK_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("pink_candle_red_velvet_cake");
    public static final Block PINK_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(PINK_CANDLE_RED_VELVET_CAKE_KEY, Blocks.PINK_CANDLE);
    public static final ResourceKey<Block> GRAY_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("gray_candle_red_velvet_cake");
    public static final Block GRAY_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(GRAY_CANDLE_RED_VELVET_CAKE_KEY, Blocks.GRAY_CANDLE);
    public static final ResourceKey<Block> LIGHT_GRAY_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("light_gray_candle_red_velvet_cake");
    public static final Block LIGHT_GRAY_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(LIGHT_GRAY_CANDLE_RED_VELVET_CAKE_KEY, Blocks.LIGHT_GRAY_CANDLE);
    public static final ResourceKey<Block> CYAN_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("cyan_candle_red_velvet_cake");
    public static final Block CYAN_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(CYAN_CANDLE_RED_VELVET_CAKE_KEY, Blocks.CYAN_CANDLE);
    public static final ResourceKey<Block> PURPLE_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("purple_candle_red_velvet_cake");
    public static final Block PURPLE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(PURPLE_CANDLE_RED_VELVET_CAKE_KEY, Blocks.PURPLE_CANDLE);
    public static final ResourceKey<Block> BLUE_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("blue_candle_red_velvet_cake");
    public static final Block BLUE_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(BLUE_CANDLE_RED_VELVET_CAKE_KEY, Blocks.BLUE_CANDLE);
    public static final ResourceKey<Block> BROWN_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("brown_candle_red_velvet_cake");
    public static final Block BROWN_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(BROWN_CANDLE_RED_VELVET_CAKE_KEY, Blocks.BROWN_CANDLE);
    public static final ResourceKey<Block> GREEN_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("green_candle_red_velvet_cake");
    public static final Block GREEN_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(GREEN_CANDLE_RED_VELVET_CAKE_KEY, Blocks.GREEN_CANDLE);
    public static final ResourceKey<Block> RED_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("red_candle_red_velvet_cake");
    public static final Block RED_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(RED_CANDLE_RED_VELVET_CAKE_KEY, Blocks.RED_CANDLE);
    public static final ResourceKey<Block> BLACK_CANDLE_RED_VELVET_CAKE_KEY
            = makeRegistryKey("black_candle_red_velvet_cake");
    public static final Block BLACK_CANDLE_RED_VELVET_CAKE
            = registerRedVelvetCandleCake(BLACK_CANDLE_RED_VELVET_CAKE_KEY, Blocks.BLACK_CANDLE);
    public static final ResourceKey<Block> STONE_TILES_KEY = makeRegistryKey("stone_tiles");
    public static final Block STONE_TILES
            = register(new Block(makeStoneTileSettings(STONE_TILES_KEY)), STONE_TILES_KEY, true);
    public static final ResourceKey<Block> STONE_TILE_SLAB_KEY = makeRegistryKey("stone_tile_slab");
    public static final Block STONE_TILE_SLAB
            = registerSlab(STONE_TILE_SLAB_KEY, makeStoneTileSettings(STONE_TILE_SLAB_KEY));
    public static final ResourceKey<Block> STONE_TILE_STAIRS_KEY = makeRegistryKey("stone_tile_stairs");
    public static final Block STONE_TILE_STAIRS
            = registerStairs(STONE_TILE_STAIRS_KEY, makeStoneTileSettings(STONE_TILE_STAIRS_KEY), STONE_TILES);
    public static final ResourceKey<Block> STONE_TILE_WALL_KEY = makeRegistryKey("stone_tile_wall");
    public static final Block STONE_TILE_WALL
            = registerWall(STONE_TILE_WALL_KEY, makeStoneTileSettings(STONE_TILE_WALL_KEY));
    public static final ResourceKey<Block> MOSSY_STONE_TILES_KEY = makeRegistryKey("mossy_stone_tiles");
    public static final Block MOSSY_STONE_TILES = register(new Block(makeStoneTileSettings(MOSSY_STONE_TILES_KEY)),
            MOSSY_STONE_TILES_KEY, true);
    public static final ResourceKey<Block> MOSSY_STONE_TILE_SLAB_KEY = makeRegistryKey("mossy_stone_tile_slab");
    public static final Block MOSSY_STONE_TILE_SLAB
            = registerSlab(MOSSY_STONE_TILE_SLAB_KEY, makeStoneTileSettings(MOSSY_STONE_TILE_SLAB_KEY));
    public static final ResourceKey<Block> MOSSY_STONE_TILE_STAIRS_KEY = makeRegistryKey("mossy_stone_tile_stairs");
    public static final Block MOSSY_STONE_TILE_STAIRS
            = registerStairs(MOSSY_STONE_TILE_STAIRS_KEY,
            makeStoneTileSettings(MOSSY_STONE_TILE_STAIRS_KEY), MOSSY_STONE_TILES);
    public static final ResourceKey<Block> MOSSY_STONE_TILE_WALL_KEY = makeRegistryKey("mossy_stone_tile_wall");
    public static final Block MOSSY_STONE_TILE_WALL
            = registerWall(MOSSY_STONE_TILE_WALL_KEY, makeStoneTileSettings(MOSSY_STONE_TILE_WALL_KEY));
    public static final ResourceKey<Block> CRACKED_STONE_TILES_KEY = makeRegistryKey("cracked_stone_tiles");
    public static final Block CRACKED_STONE_TILES = register(new Block(makeStoneTileSettings(CRACKED_STONE_TILES_KEY)),
            CRACKED_STONE_TILES_KEY, true);
    public static final ResourceKey<Block> CRACKED_STONE_TILE_SLAB_KEY
            = makeRegistryKey("cracked_stone_tile_slab");
    public static final Block CRACKED_STONE_TILE_SLAB
            = registerSlab(CRACKED_STONE_TILE_SLAB_KEY, makeStoneTileSettings(CRACKED_STONE_TILE_SLAB_KEY));
    public static final ResourceKey<Block> CRACKED_STONE_TILE_STAIRS_KEY
            = makeRegistryKey("cracked_stone_tile_stairs");
    public static final Block CRACKED_STONE_TILE_STAIRS
            = registerStairs(CRACKED_STONE_TILE_STAIRS_KEY,
            makeStoneTileSettings(CRACKED_STONE_TILE_STAIRS_KEY), CRACKED_STONE_TILES);
    public static final ResourceKey<Block> CRACKED_STONE_TILE_WALL_KEY
            = makeRegistryKey("cracked_stone_tile_wall");
    public static final Block CRACKED_STONE_TILE_WALL
            = registerWall(CRACKED_STONE_TILE_WALL_KEY, makeStoneTileSettings(CRACKED_STONE_TILE_WALL_KEY));
    public static final ResourceKey<Block> SWEET_BERRY_PIE_KEY = makeRegistryKey("sweet_berry_pie");
    public static final Block SWEET_BERRY_PIE = registerPie(SWEET_BERRY_PIE_KEY);
    public static final ResourceKey<Block> BLUEBERRY_PIE_KEY = makeRegistryKey("blueberry_pie");
    public static final Block BLUEBERRY_PIE = registerPie(BLUEBERRY_PIE_KEY);
    public static final ResourceKey<Block> BLACKSTONE_TILES_KEY = makeRegistryKey("blackstone_tiles");
    public static final Block BLACKSTONE_TILES
            = register(new Block(makeBlackstoneTileSettings(BLACKSTONE_TILES_KEY)),
            BLACKSTONE_TILES_KEY, true);
    public static final ResourceKey<Block> BLACKSTONE_TILE_STAIRS_KEY = makeRegistryKey("blackstone_tile_stairs");
    public static final Block BLACKSTONE_TILE_STAIRS
            = registerStairs(BLACKSTONE_TILE_STAIRS_KEY,
            makeBlackstoneTileSettings(BLACKSTONE_TILE_STAIRS_KEY), BLACKSTONE_TILES);
    public static final ResourceKey<Block> BLACKSTONE_TILE_SLAB_KEY = makeRegistryKey("blackstone_tile_slab");
    public static final Block BLACKSTONE_TILE_SLAB
            = registerSlab(BLACKSTONE_TILE_SLAB_KEY, makeBlackstoneTileSettings(BLACKSTONE_TILE_SLAB_KEY));
    public static final ResourceKey<Block> BLACKSTONE_TILE_WALL_KEY = makeRegistryKey("blackstone_tile_wall");
    public static final Block BLACKSTONE_TILE_WALL
            = registerWall(BLACKSTONE_TILE_WALL_KEY, makeBlackstoneTileSettings(BLACKSTONE_TILE_WALL_KEY));
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_TILES_KEY = makeRegistryKey("twisted_blackstone_tiles");
    public static final Block TWISTED_BLACKSTONE_TILES
            = register(new Block(makeBlackstoneTileSettings(TWISTED_BLACKSTONE_TILES_KEY)),
            TWISTED_BLACKSTONE_TILES_KEY, true);
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_TILE_STAIRS_KEY
            = makeRegistryKey("twisted_blackstone_tile_stairs");
    public static final Block TWISTED_BLACKSTONE_TILE_STAIRS
            = registerStairs(TWISTED_BLACKSTONE_TILE_STAIRS_KEY,
            makeBlackstoneTileSettings(TWISTED_BLACKSTONE_TILE_STAIRS_KEY), TWISTED_BLACKSTONE_TILES);
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_TILE_SLAB_KEY
            = makeRegistryKey("twisted_blackstone_tile_slab");
    public static final Block TWISTED_BLACKSTONE_TILE_SLAB
            = registerSlab(TWISTED_BLACKSTONE_TILE_SLAB_KEY,
            makeBlackstoneTileSettings(TWISTED_BLACKSTONE_TILE_SLAB_KEY));
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_TILE_WALL_KEY
            = makeRegistryKey("twisted_blackstone_tile_wall");
    public static final Block TWISTED_BLACKSTONE_TILE_WALL
            = registerWall(TWISTED_BLACKSTONE_TILE_WALL_KEY,
            makeBlackstoneTileSettings(TWISTED_BLACKSTONE_TILE_WALL_KEY));
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_TILES_KEY
            = makeRegistryKey("weeping_blackstone_tiles");
    public static final Block WEEPING_BLACKSTONE_TILES
            = register(new Block(makeBlackstoneTileSettings(WEEPING_BLACKSTONE_TILES_KEY)),
            WEEPING_BLACKSTONE_TILES_KEY, true);
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_TILE_STAIRS_KEY
            = makeRegistryKey("weeping_blackstone_tile_stairs");
    public static final Block WEEPING_BLACKSTONE_TILE_STAIRS = registerStairs(WEEPING_BLACKSTONE_TILE_STAIRS_KEY,
            makeBlackstoneTileSettings(WEEPING_BLACKSTONE_TILE_STAIRS_KEY), WEEPING_BLACKSTONE_TILES);
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_TILE_SLAB_KEY
            = makeRegistryKey("weeping_blackstone_tile_slab");
    public static final Block WEEPING_BLACKSTONE_TILE_SLAB
            = registerSlab(WEEPING_BLACKSTONE_TILE_SLAB_KEY,
            makeBlackstoneTileSettings(WEEPING_BLACKSTONE_TILE_SLAB_KEY));
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_TILE_WALL_KEY
            = makeRegistryKey("weeping_blackstone_tile_wall");
    public static final Block WEEPING_BLACKSTONE_TILE_WALL
            = registerWall(WEEPING_BLACKSTONE_TILE_WALL_KEY,
            makeBlackstoneTileSettings(WEEPING_BLACKSTONE_TILE_WALL_KEY));
    public static final ResourceKey<Block> TWISTED_POLISHED_BLACKSTONE_BRICKS_KEY
            = makeRegistryKey("twisted_polished_blackstone_bricks");
    public static final Block TWISTED_POLISHED_BLACKSTONE_BRICKS
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(TWISTED_POLISHED_BLACKSTONE_BRICKS_KEY)),
            TWISTED_POLISHED_BLACKSTONE_BRICKS_KEY, true);
    public static final ResourceKey<Block> TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS_KEY
            = makeRegistryKey("twisted_polished_blackstone_brick_stairs");
    public static final Block TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS
            = registerStairs(TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(TWISTED_POLISHED_BLACKSTONE_BRICK_STAIRS_KEY),
            Blocks.POLISHED_BLACKSTONE_BRICKS);
    public static final ResourceKey<Block> TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB_KEY
            = makeRegistryKey("twisted_polished_blackstone_brick_slab");
    public static final Block TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB
            = registerSlab(TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(TWISTED_POLISHED_BLACKSTONE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> TWISTED_POLISHED_BLACKSTONE_BRICK_WALL_KEY
            = makeRegistryKey("twisted_polished_blackstone_brick_wall");
    public static final Block TWISTED_POLISHED_BLACKSTONE_BRICK_WALL
            = registerWall(TWISTED_POLISHED_BLACKSTONE_BRICK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(TWISTED_POLISHED_BLACKSTONE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> WEEPING_POLISHED_BLACKSTONE_BRICKS_KEY
            = makeRegistryKey("weeping_polished_blackstone_bricks");
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICKS
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(WEEPING_POLISHED_BLACKSTONE_BRICKS_KEY)),
            WEEPING_POLISHED_BLACKSTONE_BRICKS_KEY, true);
    public static final ResourceKey<Block> WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS_KEY
            = makeRegistryKey("weeping_polished_blackstone_brick_stairs");
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS
            = registerStairs(WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(WEEPING_POLISHED_BLACKSTONE_BRICK_STAIRS_KEY),
            WEEPING_POLISHED_BLACKSTONE_BRICKS);
    public static final ResourceKey<Block> WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB_KEY
            = makeRegistryKey("weeping_polished_blackstone_brick_slab");
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB
            = registerSlab(WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(WEEPING_POLISHED_BLACKSTONE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> WEEPING_POLISHED_BLACKSTONE_BRICK_WALL_KEY
            = makeRegistryKey("weeping_polished_blackstone_brick_wall");
    public static final Block WEEPING_POLISHED_BLACKSTONE_BRICK_WALL
            = registerWall(WEEPING_POLISHED_BLACKSTONE_BRICK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.POLISHED_BLACKSTONE_BRICKS)
                    .setId(WEEPING_POLISHED_BLACKSTONE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_KEY = makeRegistryKey("twisted_blackstone");
    public static final Block TWISTED_BLACKSTONE
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).setId(TWISTED_BLACKSTONE_KEY)),
            TWISTED_BLACKSTONE_KEY, true);
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_STAIRS_KEY
            = makeRegistryKey("twisted_blackstone_stairs");
    public static final Block TWISTED_BLACKSTONE_STAIRS
            = registerStairs(TWISTED_BLACKSTONE_STAIRS_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
            .setId(TWISTED_BLACKSTONE_STAIRS_KEY), Blocks.BLACKSTONE);
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_SLAB_KEY
            = makeRegistryKey("twisted_blackstone_slab");
    public static final Block TWISTED_BLACKSTONE_SLAB = registerSlab(TWISTED_BLACKSTONE_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).setId(TWISTED_BLACKSTONE_SLAB_KEY));
    public static final ResourceKey<Block> TWISTED_BLACKSTONE_WALL_KEY
            = makeRegistryKey("twisted_blackstone_wall");
    public static final Block TWISTED_BLACKSTONE_WALL = registerWall(TWISTED_BLACKSTONE_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).setId(TWISTED_BLACKSTONE_WALL_KEY));
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_KEY = makeRegistryKey("weeping_blackstone");
    public static final Block WEEPING_BLACKSTONE = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
            .setId(WEEPING_BLACKSTONE_KEY)), WEEPING_BLACKSTONE_KEY, true);
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_STAIRS_KEY
            = makeRegistryKey("weeping_blackstone_stairs");
    public static final Block WEEPING_BLACKSTONE_STAIRS
            = registerStairs(WEEPING_BLACKSTONE_STAIRS_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE)
                        .setId(WEEPING_BLACKSTONE_STAIRS_KEY), WEEPING_BLACKSTONE);
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_SLAB_KEY
            = makeRegistryKey("weeping_blackstone_slab");
    public static final Block WEEPING_BLACKSTONE_SLAB = registerSlab(WEEPING_BLACKSTONE_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).setId(WEEPING_BLACKSTONE_SLAB_KEY));
    public static final ResourceKey<Block> WEEPING_BLACKSTONE_WALL_KEY
            = makeRegistryKey("weeping_blackstone_wall");
    public static final Block WEEPING_BLACKSTONE_WALL = registerWall(WEEPING_BLACKSTONE_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BLACKSTONE).setId(WEEPING_BLACKSTONE_WALL_KEY));
    public static final ResourceKey<Block> QUARTZ_TILES_KEY = makeRegistryKey("quartz_tiles");
    public static final Block QUARTZ_TILES = register(new Block(makeQuartzTileSettings(QUARTZ_TILES_KEY)),
            QUARTZ_TILES_KEY, true);
    public static final ResourceKey<Block> QUARTZ_TILE_STAIRS_KEY = makeRegistryKey("quartz_tile_stairs");
    public static final Block QUARTZ_TILE_STAIRS = registerStairs(QUARTZ_TILE_STAIRS_KEY,
            makeQuartzTileSettings(QUARTZ_TILE_STAIRS_KEY), QUARTZ_TILES);
    public static final ResourceKey<Block> QUARTZ_TILE_SLAB_KEY = makeRegistryKey("quartz_tile_slab");
    public static final Block QUARTZ_TILE_SLAB
            = registerSlab(QUARTZ_TILE_SLAB_KEY, makeQuartzTileSettings(QUARTZ_TILE_SLAB_KEY));
    public static final ResourceKey<Block> QUARTZ_TILE_WALL_KEY = makeRegistryKey("quartz_tile_wall");
    public static final Block QUARTZ_TILE_WALL
            = registerWall(QUARTZ_TILE_WALL_KEY, makeQuartzTileSettings(QUARTZ_TILE_WALL_KEY));
    public static final ResourceKey<Block> CALCITE_BRICKS_KEY = makeRegistryKey("calcite_bricks");
    public static final Block CALCITE_BRICKS = register(new Block(makeCalciteSettings(CALCITE_BRICKS_KEY)),
            CALCITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> CALCITE_BRICK_STAIRS_KEY = makeRegistryKey("calcite_brick_stairs");
    public static final Block CALCITE_BRICK_STAIRS = registerStairs(CALCITE_BRICK_STAIRS_KEY,
            makeCalciteSettings(CALCITE_BRICK_STAIRS_KEY), CALCITE_BRICKS);
    public static final ResourceKey<Block> CALCITE_BRICK_SLAB_KEY = makeRegistryKey("calcite_brick_slab");
    public static final Block CALCITE_BRICK_SLAB = registerSlab(CALCITE_BRICK_SLAB_KEY,
            makeCalciteSettings(CALCITE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> CALCITE_BRICK_WALL_KEY = makeRegistryKey("calcite_brick_wall");
    public static final Block CALCITE_BRICK_WALL = registerWall(CALCITE_BRICK_WALL_KEY,
            makeCalciteSettings(CALCITE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> MOSSY_CALCITE_BRICKS_KEY = makeRegistryKey("mossy_calcite_bricks");
    public static final Block MOSSY_CALCITE_BRICKS
            = register(new Block(makeCalciteSettings(MOSSY_CALCITE_BRICKS_KEY)),
            MOSSY_CALCITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> MOSSY_CALCITE_BRICK_STAIRS_KEY
            = makeRegistryKey("mossy_calcite_brick_stairs");
    public static final Block MOSSY_CALCITE_BRICK_STAIRS = registerStairs(MOSSY_CALCITE_BRICK_STAIRS_KEY,
            makeCalciteSettings(MOSSY_CALCITE_BRICK_STAIRS_KEY), MOSSY_CALCITE_BRICKS);
    public static final ResourceKey<Block> MOSSY_CALCITE_BRICK_SLAB_KEY
            = makeRegistryKey("mossy_calcite_brick_slab");
    public static final Block MOSSY_CALCITE_BRICK_SLAB
            = registerSlab(MOSSY_CALCITE_BRICK_SLAB_KEY, makeCalciteSettings(MOSSY_CALCITE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> MOSSY_CALCITE_BRICK_WALL_KEY
            = makeRegistryKey("mossy_calcite_brick_wall");
    public static final Block MOSSY_CALCITE_BRICK_WALL = registerWall(MOSSY_CALCITE_BRICK_WALL_KEY,
            makeCalciteSettings(MOSSY_CALCITE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> CRACKED_CALCITE_BRICKS_KEY
            = makeRegistryKey("cracked_calcite_bricks");
    public static final Block CRACKED_CALCITE_BRICKS
            = register(new Block(makeCalciteSettings(CRACKED_CALCITE_BRICKS_KEY)),
            CRACKED_CALCITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> CRACKED_CALCITE_BRICK_STAIRS_KEY
            = makeRegistryKey("cracked_calcite_brick_stairs");
    public static final Block CRACKED_CALCITE_BRICK_STAIRS = registerStairs(CRACKED_CALCITE_BRICK_STAIRS_KEY,
            makeCalciteSettings(CRACKED_CALCITE_BRICK_STAIRS_KEY), CRACKED_CALCITE_BRICKS);
    public static final ResourceKey<Block> CRACKED_CALCITE_BRICK_SLAB_KEY
            = makeRegistryKey("cracked_calcite_brick_slab");
    public static final Block CRACKED_CALCITE_BRICK_SLAB = registerSlab(CRACKED_CALCITE_BRICK_SLAB_KEY,
            makeCalciteSettings(CRACKED_CALCITE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> CRACKED_CALCITE_BRICK_WALL_KEY
            = makeRegistryKey("cracked_calcite_brick_wall");
    public static final Block CRACKED_CALCITE_BRICK_WALL = registerWall(CRACKED_CALCITE_BRICK_WALL_KEY,
            makeCalciteSettings(CRACKED_CALCITE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> CHISELED_CALCITE_BRICKS_KEY
            = makeRegistryKey("chiseled_calcite_bricks");
    public static final Block CHISELED_CALCITE_BRICKS
            = register(new RotatedPillarBlock(makeCalciteSettings(CHISELED_CALCITE_BRICKS_KEY)),
            CHISELED_CALCITE_BRICKS_KEY, true);
    public static final ResourceKey<Block> DRIPSTONE_BRICKS_KEY = makeRegistryKey("dripstone_bricks");
    public static final Block DRIPSTONE_BRICKS = register(new Block(makeDripstoneSettings(DRIPSTONE_BRICKS_KEY)),
            DRIPSTONE_BRICKS_KEY, true);
    public static final ResourceKey<Block> DRIPSTONE_BRICK_STAIRS_KEY = makeRegistryKey("dripstone_brick_stairs");
    public static final Block DRIPSTONE_BRICK_STAIRS = registerStairs(DRIPSTONE_BRICK_STAIRS_KEY,
            makeDripstoneSettings(DRIPSTONE_BRICK_STAIRS_KEY), DRIPSTONE_BRICKS);
    public static final ResourceKey<Block> DRIPSTONE_BRICK_SLAB_KEY = makeRegistryKey("dripstone_brick_slab");
    public static final Block DRIPSTONE_BRICK_SLAB = registerSlab(DRIPSTONE_BRICK_SLAB_KEY,
            makeDripstoneSettings(DRIPSTONE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> DRIPSTONE_BRICK_WALL_KEY = makeRegistryKey("dripstone_brick_wall");
    public static final Block DRIPSTONE_BRICK_WALL = registerWall(DRIPSTONE_BRICK_WALL_KEY,
            makeDripstoneSettings(DRIPSTONE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> MOSSY_DRIPSTONE_BRICKS_KEY = makeRegistryKey("mossy_dripstone_bricks");
    public static final Block MOSSY_DRIPSTONE_BRICKS
            = register(new Block(makeDripstoneSettings(MOSSY_DRIPSTONE_BRICKS_KEY)),
            MOSSY_DRIPSTONE_BRICKS_KEY, true);
    public static final ResourceKey<Block> MOSSY_DRIPSTONE_BRICK_STAIRS_KEY
            = makeRegistryKey("mossy_dripstone_brick_stairs");
    public static final Block MOSSY_DRIPSTONE_BRICK_STAIRS = registerStairs(MOSSY_DRIPSTONE_BRICK_STAIRS_KEY,
            makeDripstoneSettings(MOSSY_DRIPSTONE_BRICK_STAIRS_KEY), MOSSY_DRIPSTONE_BRICKS);
    public static final ResourceKey<Block> MOSSY_DRIPSTONE_BRICK_SLAB_KEY
            = makeRegistryKey("mossy_dripstone_brick_slab");
    public static final Block MOSSY_DRIPSTONE_BRICK_SLAB = registerSlab(MOSSY_DRIPSTONE_BRICK_SLAB_KEY,
            makeDripstoneSettings(MOSSY_DRIPSTONE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> MOSSY_DRIPSTONE_BRICK_WALL_KEY
            = makeRegistryKey("mossy_dripstone_brick_wall");
    public static final Block MOSSY_DRIPSTONE_BRICK_WALL = registerWall(MOSSY_DRIPSTONE_BRICK_WALL_KEY,
            makeDripstoneSettings(MOSSY_DRIPSTONE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> CRACKED_DRIPSTONE_BRICKS_KEY
            = makeRegistryKey("cracked_dripstone_bricks");
    public static final Block CRACKED_DRIPSTONE_BRICKS
            = register(new Block(makeDripstoneSettings(CRACKED_DRIPSTONE_BRICKS_KEY)),
            CRACKED_DRIPSTONE_BRICKS_KEY, true);
    public static final ResourceKey<Block> CRACKED_DRIPSTONE_BRICK_STAIRS_KEY
            = makeRegistryKey("cracked_dripstone_brick_stairs");
    public static final Block CRACKED_DRIPSTONE_BRICK_STAIRS = registerStairs(CRACKED_DRIPSTONE_BRICK_STAIRS_KEY,
            makeDripstoneSettings(CRACKED_DRIPSTONE_BRICK_STAIRS_KEY), CRACKED_DRIPSTONE_BRICKS);
    public static final ResourceKey<Block> CRACKED_DRIPSTONE_BRICK_SLAB_KEY
            = makeRegistryKey("cracked_dripstone_brick_slab");
    public static final Block CRACKED_DRIPSTONE_BRICK_SLAB = registerSlab(CRACKED_DRIPSTONE_BRICK_SLAB_KEY,
            makeDripstoneSettings(CRACKED_DRIPSTONE_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> CRACKED_DRIPSTONE_BRICK_WALL_KEY
            = makeRegistryKey("cracked_dripstone_brick_wall");
    public static final Block CRACKED_DRIPSTONE_BRICK_WALL = registerWall(CRACKED_DRIPSTONE_BRICK_WALL_KEY,
            makeDripstoneSettings(CRACKED_DRIPSTONE_BRICK_WALL_KEY));
    public static final ResourceKey<Block> CHISELED_DRIPSTONE_BRICKS_KEY
            = makeRegistryKey("chiseled_dripstone_bricks");
    public static final Block CHISELED_DRIPSTONE_BRICKS
            = register(new Block(makeDripstoneSettings(CHISELED_DRIPSTONE_BRICKS_KEY)),
            CHISELED_DRIPSTONE_BRICKS_KEY, true);
    public static final ResourceKey<Block> BLOOD_KELP_KEY = makeRegistryKey("blood_kelp");
    public static final Block BLOOD_KELP
            = register(new BloodKelpBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP)
                    .lightLevel(getLuminanceFromState()).setId(BLOOD_KELP_KEY)),
            BLOOD_KELP_KEY, false);
    public static final ResourceKey<Block> BLOOD_KELP_PLANT_KEY = makeRegistryKey("blood_kelp_plant");
    public static final Block BLOOD_KELP_PLANT
            = register(new BloodKelpPlantBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.KELP_PLANT)
                    .lightLevel(getLuminanceFromState()).setId(BLOOD_KELP_PLANT_KEY)),
            BLOOD_KELP_PLANT_KEY, false);
    public static final ResourceKey<Block> DRIED_BLOOD_KELP_BLOCK_KEY = makeRegistryKey("dried_blood_kelp_block");
    public static final Block DRIED_BLOOD_KELP_BLOCK
            = register(new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DRIED_KELP_BLOCK)
                    .setId(DRIED_BLOOD_KELP_BLOCK_KEY)), DRIED_BLOOD_KELP_BLOCK_KEY, true);
    public static final ResourceKey<Block> BLOOD_KELP_LANTERN_KEY = makeRegistryKey("blood_kelp_lantern");
    public static final Block BLOOD_KELP_LANTERN
            = register(new RotatedPillarBlock(BlockBehaviour.Properties.of().mapColor(MapColor.SAND).strength(0.3F)
                    .sound(SoundType.GLASS).lightLevel((state) -> 15)
                    .setId(BLOOD_KELP_LANTERN_KEY)),
            BLOOD_KELP_LANTERN_KEY, true);
    public static final ResourceKey<Block> BOG_BLOSSOM_KEY = makeRegistryKey("bog_blossom");
    public static final Block BOG_BLOSSOM = register(new BogBlossomBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.PLANT).instabreak().noCollision().sound(SoundType.SPORE_BLOSSOM)
            .pushReaction(PushReaction.DESTROY).lightLevel((state) -> 5).setId(BOG_BLOSSOM_KEY)),
            BOG_BLOSSOM_KEY, true);
    public static final ResourceKey<Block> CINDERSNAP_BERRY_BUSH_KEY = makeRegistryKey("cindersnap_berry_bush");
    public static final Block CINDERSNAP_BERRY_BUSH
            = register(new CindersnapBerryBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.CRIMSON_HYPHAE)
            .randomTicks().noCollision().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)
            .lightLevel((state) -> 8).setId(CINDERSNAP_BERRY_BUSH_KEY)),
            CINDERSNAP_BERRY_BUSH_KEY, false);
    public static final ResourceKey<Block> FROSTBITE_BERRY_BUSH_KEY = makeRegistryKey("frostbite_berry_bush");
    public static final Block FROSTBITE_BERRY_BUSH
            = register(new FrostbiteBerryBushBlock(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN)
            .randomTicks().noCollision().sound(SoundType.NETHER_SPROUTS).pushReaction(PushReaction.DESTROY)
            .lightLevel((state) -> 5).setId(FROSTBITE_BERRY_BUSH_KEY)),
            FROSTBITE_BERRY_BUSH_KEY, false);
    public static final ResourceKey<Block> POLISHED_DRIPSTONE_KEY = makeRegistryKey("polished_dripstone");
    public static final Block POLISHED_DRIPSTONE
            = register(new Block(makeDripstoneSettings(POLISHED_DRIPSTONE_KEY)),
            POLISHED_DRIPSTONE_KEY, true);
    public static final ResourceKey<Block> POLISHED_DRIPSTONE_STAIRS_KEY
            = makeRegistryKey("polished_dripstone_stairs");
    public static final Block POLISHED_DRIPSTONE_STAIRS = registerStairs(POLISHED_DRIPSTONE_STAIRS_KEY,
            makeDripstoneSettings(POLISHED_DRIPSTONE_STAIRS_KEY), POLISHED_DRIPSTONE);
    public static final ResourceKey<Block> POLISHED_DRIPSTONE_SLAB_KEY
            = makeRegistryKey("polished_dripstone_slab");
    public static final Block POLISHED_DRIPSTONE_SLAB
            = registerSlab(POLISHED_DRIPSTONE_SLAB_KEY, makeDripstoneSettings(POLISHED_DRIPSTONE_SLAB_KEY));
    public static final ResourceKey<Block> POLISHED_DRIPSTONE_WALL_KEY
            = makeRegistryKey("polished_dripstone_wall");
    public static final Block POLISHED_DRIPSTONE_WALL = registerWall(POLISHED_DRIPSTONE_WALL_KEY,
            makeDripstoneSettings(POLISHED_DRIPSTONE_WALL_KEY));
    public static final ResourceKey<Block> POLISHED_CALCITE_KEY = makeRegistryKey("polished_calcite");
    public static final Block POLISHED_CALCITE = register(new Block(makeCalciteSettings(POLISHED_CALCITE_KEY)),
            POLISHED_CALCITE_KEY, true);
    public static final ResourceKey<Block> POLISHED_CALCITE_STAIRS_KEY
            = makeRegistryKey("polished_calcite_stairs");
    public static final Block POLISHED_CALCITE_STAIRS = registerStairs(POLISHED_CALCITE_STAIRS_KEY, 
            makeCalciteSettings(POLISHED_CALCITE_STAIRS_KEY), POLISHED_CALCITE);
    public static final ResourceKey<Block> POLISHED_CALCITE_SLAB_KEY = makeRegistryKey("polished_calcite_slab");
    public static final Block POLISHED_CALCITE_SLAB = registerSlab(POLISHED_CALCITE_SLAB_KEY,
            makeCalciteSettings(POLISHED_CALCITE_SLAB_KEY));
    public static final ResourceKey<Block> POLISHED_CALCITE_WALL_KEY = makeRegistryKey("polished_calcite_wall");
    public static final Block POLISHED_CALCITE_WALL = registerWall(POLISHED_CALCITE_WALL_KEY,
            makeCalciteSettings(POLISHED_CALCITE_WALL_KEY));
    public static final ResourceKey<Block> DRIPSTONE_STAIRS_KEY = makeRegistryKey("dripstone_stairs");
    public static final Block DRIPSTONE_STAIRS = registerStairs(DRIPSTONE_STAIRS_KEY,
            makeDripstoneSettings(DRIPSTONE_STAIRS_KEY), Blocks.DRIPSTONE_BLOCK);
    public static final ResourceKey<Block> DRIPSTONE_SLAB_KEY = makeRegistryKey("dripstone_slab");
    public static final Block DRIPSTONE_SLAB = registerSlab(DRIPSTONE_SLAB_KEY,
            makeDripstoneSettings(DRIPSTONE_SLAB_KEY));
    public static final ResourceKey<Block> DRIPSTONE_WALL_KEY = makeRegistryKey("dripstone_wall");
    public static final Block DRIPSTONE_WALL = registerWall(DRIPSTONE_WALL_KEY,
            makeDripstoneSettings(DRIPSTONE_WALL_KEY));
    public static final ResourceKey<Block> CALCITE_STAIRS_KEY = makeRegistryKey("calcite_stairs");
    public static final Block CALCITE_STAIRS = registerStairs(CALCITE_STAIRS_KEY,
            makeCalciteSettings(CALCITE_STAIRS_KEY), Blocks.CALCITE);
    public static final ResourceKey<Block> CALCITE_SLAB_KEY = makeRegistryKey("calcite_slab");
    public static final Block CALCITE_SLAB = registerSlab(CALCITE_SLAB_KEY, makeCalciteSettings(CALCITE_SLAB_KEY));
    public static final ResourceKey<Block> CALCITE_WALL_KEY = makeRegistryKey("calcite_wall");
    public static final Block CALCITE_WALL = registerWall(CALCITE_WALL_KEY, makeCalciteSettings(CALCITE_WALL_KEY));
    public static final ResourceKey<Block> BAMBOO_PLANTER_BOX_KEY = makeRegistryKey("bamboo_planter_box");
    public static final Block BAMBOO_PLANTER_BOX = registerPlanterBox(BAMBOO_PLANTER_BOX_KEY,
            Blocks.BAMBOO_PLANKS.defaultMapColor(), SoundType.BAMBOO_WOOD);
    public static final ResourceKey<Block> POTTED_CATTAIL_KEY = makeRegistryKey("potted_cattail");
    public static final Block POTTED_CATTAIL = register(new FlowerPotBlock(ModBlocks.CATTAIL,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_RED_MUSHROOM).setId(POTTED_CATTAIL_KEY)),
            POTTED_CATTAIL_KEY, false);
    public static final ResourceKey<Block> STONE_WALL_KEY = makeRegistryKey("stone_wall");
    public static final Block STONE_WALL = registerWall(STONE_WALL_KEY, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE)
            .setId(STONE_WALL_KEY));
    public static final ResourceKey<Block> QUARTZ_WALL_KEY = makeRegistryKey("quartz_wall");
    public static final Block QUARTZ_WALL = registerWall(QUARTZ_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BLOCK).setId(QUARTZ_WALL_KEY));
    public static final ResourceKey<Block> SMOOTH_QUARTZ_WALL_KEY = makeRegistryKey("smooth_quartz_wall");
    public static final Block SMOOTH_QUARTZ_WALL = registerWall(SMOOTH_QUARTZ_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.SMOOTH_QUARTZ).setId(SMOOTH_QUARTZ_WALL_KEY));
    public static final ResourceKey<Block> GRASS_SLAB_KEY = makeRegistryKey("grass_slab");
    public static final Block GRASS_SLAB
            = register(new GrassSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRASS_BLOCK)
                    .setId(GRASS_SLAB_KEY)),
            GRASS_SLAB_KEY, true);
    public static final ResourceKey<Block> PODZOL_SLAB_KEY = makeRegistryKey("podzol_slab");
    public static final Block PODZOL_SLAB = registerSnowySlab(PODZOL_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PODZOL).setId(PODZOL_SLAB_KEY));
    public static final ResourceKey<Block> MYCELIUM_SLAB_KEY = makeRegistryKey("mycelium_slab");
    public static final Block MYCELIUM_SLAB = registerSnowySlab(MYCELIUM_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.MYCELIUM).setId(MYCELIUM_SLAB_KEY));
    public static final ResourceKey<Block> DIRT_PATH_SLAB_KEY = makeRegistryKey("dirt_path_slab");
    public static final Block DIRT_PATH_SLAB
            = register(new DirtPathSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT_PATH)
                    .setId(DIRT_PATH_SLAB_KEY)),
            DIRT_PATH_SLAB_KEY, true);
    public static final ResourceKey<Block> DIRT_SLAB_KEY = makeRegistryKey("dirt_slab");
    public static final Block DIRT_SLAB
            = register(new DirtSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT)
                    .setId(DIRT_SLAB_KEY)),
            DIRT_SLAB_KEY, true);
    public static final ResourceKey<Block> COARSE_DIRT_SLAB_KEY = makeRegistryKey("coarse_dirt_slab");
    public static final Block COARSE_DIRT_SLAB = registerSlab(COARSE_DIRT_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.COARSE_DIRT).setId(COARSE_DIRT_SLAB_KEY));
    public static final ResourceKey<Block> ROOTED_DIRT_SLAB_KEY = makeRegistryKey("rooted_dirt_slab");
    public static final Block ROOTED_DIRT_SLAB = registerSlab(ROOTED_DIRT_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.ROOTED_DIRT).setId(ROOTED_DIRT_SLAB_KEY));
    public static final ResourceKey<Block> WILD_GREEN_ONIONS_KEY = makeRegistryKey("wild_green_onions");
    public static final Block WILD_GREEN_ONIONS
            = register(new WildGreenOnionsBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT)
            .noCollision().randomTicks().instabreak().sound(SoundType.CROP)
            .pushReaction(PushReaction.DESTROY).setId(WILD_GREEN_ONIONS_KEY)),
            WILD_GREEN_ONIONS_KEY, true);
    public static final ResourceKey<Block> CREAKING_PLUSHIE_KEY = makeRegistryKey("creaking_plushie");
    public static final Block CREAKING_PLUSHIE
            = register(new CreakingPlushieBlock(makePlushieSettings(CREAKING_PLUSHIE_KEY)),
            CREAKING_PLUSHIE_KEY, true);
    public static final ResourceKey<Block> QUARTZ_BRICK_STAIRS_KEY = makeRegistryKey("quartz_brick_stairs");
    public static final Block QUARTZ_BRICK_STAIRS = registerStairs(QUARTZ_BRICK_STAIRS_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).setId(QUARTZ_BRICK_STAIRS_KEY),
            Blocks.QUARTZ_BRICKS);
    public static final ResourceKey<Block> QUARTZ_BRICK_SLAB_KEY = makeRegistryKey("quartz_brick_slab");
    public static final Block QUARTZ_BRICK_SLAB = registerSlab(QUARTZ_BRICK_SLAB_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).setId(QUARTZ_BRICK_SLAB_KEY));
    public static final ResourceKey<Block> QUARTZ_BRICK_WALL_KEY = makeRegistryKey("quartz_brick_wall");
    public static final Block QUARTZ_BRICK_WALL = registerWall(QUARTZ_BRICK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.QUARTZ_BRICKS).setId(QUARTZ_BRICK_WALL_KEY));
    public static final ResourceKey<Block> SNIFFER_PLUSHIE_KEY = makeRegistryKey("sniffer_plushie");
    public static final Block SNIFFER_PLUSHIE
            = register(new SnifferPlushieBlock(makePlushieSettings(SNIFFER_PLUSHIE_KEY)),
            SNIFFER_PLUSHIE_KEY, true);
    public static final ResourceKey<Block> STRIPPED_PALE_OAK_WALL_KEY = makeRegistryKey("stripped_pale_oak_wall");
    public static final Block STRIPPED_PALE_OAK_WALL = registerWall(STRIPPED_PALE_OAK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS).setId(STRIPPED_PALE_OAK_WALL_KEY));
    public static final ResourceKey<Block> PALE_OAK_WALL_KEY = makeRegistryKey("pale_oak_wall");
    public static final Block PALE_OAK_WALL = registerWall(PALE_OAK_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.PALE_OAK_PLANKS).setId(PALE_OAK_WALL_KEY));
    public static final ResourceKey<Block> BAMBOO_ROPE_LADDER_KEY = makeRegistryKey("bamboo_rope_ladder");
    public static final Block BAMBOO_ROPE_LADDER = registerRopeLadder(BAMBOO_ROPE_LADDER_KEY);
    public static final ResourceKey<Block> STRIPPED_BAMBOO_WALL_KEY = makeRegistryKey("stripped_bamboo_wall");
    public static final Block STRIPPED_BAMBOO_WALL = registerWall(STRIPPED_BAMBOO_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).setId(STRIPPED_BAMBOO_WALL_KEY));
    public static final ResourceKey<Block> BAMBOO_WALL_KEY = makeRegistryKey("bamboo_wall");
    public static final Block BAMBOO_WALL = registerWall(BAMBOO_WALL_KEY,
            BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).setId(BAMBOO_WALL_KEY));
    public static final ResourceKey<Block> BLACK_WOLF_PLUSHIE_KEY = makeRegistryKey("black_wolf_plushie");
    public static final Block BLACK_WOLF_PLUSHIE = registerWolfPlushie(BLACK_WOLF_PLUSHIE_KEY);
    public static final ResourceKey<Block> ASHEN_WOLF_PLUSHIE_KEY = makeRegistryKey("ashen_wolf_plushie");
    public static final Block ASHEN_WOLF_PLUSHIE = registerWolfPlushie(ASHEN_WOLF_PLUSHIE_KEY);
    public static final ResourceKey<Block> CHESTNUT_WOLF_PLUSHIE_KEY = makeRegistryKey("chestnut_wolf_plushie");
    public static final Block CHESTNUT_WOLF_PLUSHIE = registerWolfPlushie(CHESTNUT_WOLF_PLUSHIE_KEY);
    public static final ResourceKey<Block> RUSTY_WOLF_PLUSHIE_KEY = makeRegistryKey("rusty_wolf_plushie");
    public static final Block RUSTY_WOLF_PLUSHIE = registerWolfPlushie(RUSTY_WOLF_PLUSHIE_KEY);

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

    private static DeferredBlock<Block> registerSimpleBlock(String name, Supplier<BlockBehaviour.Properties> properties, boolean shouldRegisterItem) {
        return register(name, Block::new, properties, shouldRegisterItem);
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

    private static DeferredBlock<Block> registerTorch(String name, SimpleParticleType particle) {
        Supplier<BlockBehaviour.Properties> torchSettings = () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH);
        return register(name, properties -> new TorchBlock(particle, properties),
                torchSettings, false);
    }

    private static DeferredBlock<Block> registerWallTorch(String name, Block standingTorch, SimpleParticleType particle) {
        Supplier<BlockBehaviour.Properties> wallTorchSettings = () -> wallVariant(standingTorch).noCollision().instabreak()
                .lightLevel((blockState) -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);
        return register(name, settings -> new WallTorchBlock(particle, settings),
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

    private static DeferredBlock<Block> registerDyedCampfire(String name, ParticleOptions emberParticle) {
        return register(name, properties -> new DyedCampfireBlock(properties, emberParticle),
                () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CAMPFIRE), true);
    }

    private static DeferredBlock<Block> registerLantern(String name) {
        return register(name, LanternBlock::new,
                () -> BlockBehaviour.Properties.ofFullCopy(Blocks.LANTERN), true);
    }

    private static DeferredBlock<Block> registerPottedSnapdragon(Supplier<BlockBehaviour.Properties> settings) {
        return register("potted_snapdragon",
                prop -> new PottedSnapdragonBlock(ModBlocks.SNAPDRAGON, prop),
                settings, false);
    }

    private static Block registerCake(ResourceKey<Block> blockKey) {
        Block moddedCakeBlock = new ModdedCakeBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE));
        return register(moddedCakeBlock, blockKey, true);
    }

    private static Block registerChocolateCandleCake(ResourceKey<Block> blockKey, Block candle) {
        return registerCandleCake(blockKey, ModBlocks.CHOCOLATE_CAKE, candle);
    }

    private static Block registerRedVelvetCandleCake(ResourceKey<Block> blockKey, Block candle) {
        return registerCandleCake(blockKey, ModBlocks.RED_VELVET_CAKE, candle);
    }

    private static Block registerCandleCake(ResourceKey<Block> blockKey, Block cake, Block candle) {
        Block candleCakeBlock = new ModdedCandleCakeBlock(cake, candle,
                BlockBehaviour.Properties.ofFullCopy(Blocks.CANDLE_CAKE));
        return register(candleCakeBlock, blockKey, false);
    }

    private static Block registerPie(ResourceKey<Block> blockKey) {
        BlockBehaviour.Properties pieSettings = BlockBehaviour.Properties.ofFullCopy(Blocks.CAKE);
        Block pieBlock = new PieBlock(pieSettings, 3, 0.6F);
        return register(pieBlock, blockKey, true);
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
