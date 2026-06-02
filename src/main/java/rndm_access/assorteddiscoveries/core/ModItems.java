package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.Direction;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

import java.util.function.Function;
import java.util.function.Supplier;

public final class ModItems {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(AssortedDiscoveries.MOD_ID);

    public static final DeferredItem<Item> WHITE_TORCH = registerTorchBlockItem("white_torch",
            ModBlocks.WHITE_TORCH, ModBlocks.WHITE_WALL_TORCH);
    public static final DeferredItem<Item> ORANGE_TORCH = registerTorchBlockItem("orange_torch",
            ModBlocks.ORANGE_TORCH, ModBlocks.ORANGE_WALL_TORCH);
    public static final DeferredItem<Item> MAGENTA_TORCH = registerTorchBlockItem("magenta_torch",
            ModBlocks.MAGENTA_TORCH, ModBlocks.MAGENTA_WALL_TORCH);
    public static final DeferredItem<Item> LIGHT_BLUE_TORCH = registerTorchBlockItem("light_blue_torch",
            ModBlocks.LIGHT_BLUE_TORCH, ModBlocks.LIGHT_BLUE_WALL_TORCH);
    public static final DeferredItem<Item> YELLOW_TORCH = registerTorchBlockItem("yellow_torch",
            ModBlocks.YELLOW_TORCH, ModBlocks.YELLOW_WALL_TORCH);
    public static final DeferredItem<Item> LIME_TORCH = registerTorchBlockItem("lime_torch",
            ModBlocks.LIME_TORCH, ModBlocks.LIME_WALL_TORCH);
    public static final DeferredItem<Item> PINK_TORCH = registerTorchBlockItem("pink_torch",
            ModBlocks.PINK_TORCH, ModBlocks.PINK_WALL_TORCH);
    public static final DeferredItem<Item> GRAY_TORCH = registerTorchBlockItem("gray_torch",
            ModBlocks.GRAY_TORCH, ModBlocks.GRAY_WALL_TORCH);
    public static final DeferredItem<Item> LIGHT_GRAY_TORCH = registerTorchBlockItem("light_gray_torch",
            ModBlocks.LIGHT_GRAY_TORCH, ModBlocks.LIGHT_GRAY_WALL_TORCH);
    public static final DeferredItem<Item> CYAN_TORCH = registerTorchBlockItem("cyan_torch",
            ModBlocks.CYAN_TORCH, ModBlocks.CYAN_WALL_TORCH);
    public static final DeferredItem<Item> PURPLE_TORCH = registerTorchBlockItem("purple_torch",
            ModBlocks.PURPLE_TORCH, ModBlocks.PURPLE_WALL_TORCH);
    public static final DeferredItem<Item> BLUE_TORCH = registerTorchBlockItem("blue_torch",
            ModBlocks.BLUE_TORCH, ModBlocks.BLUE_WALL_TORCH);
    public static final DeferredItem<Item> BROWN_TORCH = registerTorchBlockItem("brown_torch",
            ModBlocks.BROWN_TORCH, ModBlocks.BROWN_WALL_TORCH);
    public static final DeferredItem<Item> GREEN_TORCH = registerTorchBlockItem("green_torch",
            ModBlocks.GREEN_TORCH, ModBlocks.GREEN_WALL_TORCH);
    public static final DeferredItem<Item> RED_TORCH = registerTorchBlockItem("red_torch",
            ModBlocks.RED_TORCH, ModBlocks.RED_WALL_TORCH);
    public static final DeferredItem<Item> BLACK_TORCH = registerTorchBlockItem("black_torch",
            ModBlocks.BLACK_TORCH, ModBlocks.BLACK_WALL_TORCH);
    public static final DeferredItem<Item> GREEN_ONION_SEEDS = registerBlockItem("green_onion_seeds",
            ModBlocks.GREEN_ONIONS);
    public static final DeferredItem<Item> GREEN_ONION = ITEMS.registerSimpleItem("green_onion",
            () -> new Item.Properties().food(ModFoodComponents.GREEN_ONION));
    public static final DeferredItem<Item> BLUEBERRIES = registerBlockItem("blueberries", ModBlocks.BLUEBERRY_BUSH,
            () -> new Item.Properties().food(ModFoodComponents.BLUEBERRIES));
    public static final DeferredItem<Item> SWEET_BERRY_JUICE = ITEMS.registerSimpleItem("sweet_berry_juice",
            () -> new Item.Properties()
                    .food(ModFoodComponents.JUICE, Consumables.DEFAULT_DRINK).stacksTo(16)
                    .usingConvertsTo(Items.GLASS_BOTTLE));
    public static final DeferredItem<Item> BLUEBERRY_JUICE = ITEMS.registerSimpleItem("blueberry_juice",
            () -> new Item.Properties()
                    .food(ModFoodComponents.JUICE, Consumables.DEFAULT_DRINK).stacksTo(16)
                    .usingConvertsTo(Items.GLASS_BOTTLE));
    public static final DeferredItem<Item> NOODLES = ITEMS.registerSimpleItem("noodles", Item.Properties::new);
    public static final DeferredItem<Item> NOODLE_SOUP = ITEMS.registerSimpleItem("noodle_soup",
            () -> new Item.Properties().food(ModFoodComponents.NOODLE_SOUP)
                    .stacksTo(1).usingConvertsTo(Items.BOWL));
    public static final DeferredItem<Item> PUDDING = ITEMS.registerSimpleItem("pudding",
            () -> new Item.Properties().food(ModFoodComponents.PUDDING).stacksTo(1).usingConvertsTo(Items.BOWL));
    public static final DeferredItem<Item> BERRY_PUDDING = ITEMS.registerSimpleItem("berry_pudding",
            () -> new Item.Properties().food(ModFoodComponents.BERRY_PUDDING)
                    .stacksTo(1).usingConvertsTo(Items.BOWL));
    public static final DeferredItem<Item> SMOKY_QUARTZ
            = ITEMS.registerSimpleItem("smoky_quartz", Item.Properties::new);
    public static final DeferredItem<Item> CARAMEL_APPLE = ITEMS.registerSimpleItem("caramel_apple",
            () -> new Item.Properties().food(ModFoodComponents.CARAMEL_APPLE)
                    .stacksTo(1).usingConvertsTo(Items.STICK));
    public static final DeferredItem<Item> CARAMEL = ITEMS.registerSimpleItem("caramel",
            () -> new Item.Properties().food(ModFoodComponents.CARAMEL));
    public static final DeferredItem<Item> SPRUCE_CONE = ITEMS.registerSimpleItem("spruce_cone",
            () -> new Item.Properties().food(ModFoodComponents.SPRUCE_CONE));
    public static final DeferredItem<Item> FORESTS_BOUNTY = ITEMS.registerSimpleItem("forests_bounty",
            () -> new Item.Properties().food(ModFoodComponents.FORESTS_BOUNTY)
                    .stacksTo(1).usingConvertsTo(Items.BOWL));
    public static final DeferredItem<Item> WITCHS_CRADLE_BRANCH = registerBlockItem("witchs_cradle_branch",
            ModBlocks.WITCHS_CRADLE, () -> new Item.Properties().food(ModFoodComponents.WITCHS_CRADLE_BRANCH));
    public static final DeferredItem<Item> WITCHS_CRADLE_SOUP = ITEMS.registerSimpleItem("witchs_cradle_soup",
            () -> new Item.Properties()
                    .food(ModFoodComponents.WITCHS_CRADLE_SOUP, ModConsumableComponents.WITCHS_CRADLE_SOUP)
                    .stacksTo(1).usingConvertsTo(Items.BOWL));
    public static final DeferredItem<Item> FRIED_EGG = ITEMS.registerSimpleItem("fried_egg",
            () -> new Item.Properties().food(ModFoodComponents.FRIED_EGG).stacksTo(16));
    public static final DeferredItem<Item> BLOOD_KELP_SEED_CLUSTER
            = registerBlockItem("blood_kelp_seed_cluster", ModBlocks.BLOOD_KELP);
    public static final DeferredItem<Item> BLOOD_KELP
            = ITEMS.registerSimpleItem("blood_kelp", Item.Properties::new);
    public static final DeferredItem<Item> DRIED_BLOOD_KELP = ITEMS.registerSimpleItem("dried_blood_kelp",
            () -> new Item.Properties().food(Foods.DRIED_KELP));
    public static final DeferredItem<Item> HOGLIN_STEW = ITEMS.registerSimpleItem("hoglin_stew",
            () -> new Item.Properties().food(ModFoodComponents.HOGLIN_STEW)
                    .stacksTo(1).usingConvertsTo(Items.BOWL));
    public static final DeferredItem<Item> CINDERSNAP_BERRIES = registerBlockItem("cindersnap_berries",
            ModBlocks.CINDERSNAP_BERRY_BUSH, () -> new Item.Properties().food(ModFoodComponents.NETHER_BERRIES));
    public static final DeferredItem<Item> FROSTBITE_BERRIES = registerBlockItem("frostbite_berries",
            ModBlocks.FROSTBITE_BERRY_BUSH, () -> new Item.Properties().food(ModFoodComponents.NETHER_BERRIES));
    public static final DeferredItem<Item> CINDERSNAP_BERRY_JUICE
            = ITEMS.registerSimpleItem("cindersnap_berry_juice", () -> new Item.Properties()
            .food(ModFoodComponents.JUICE, ModConsumableComponents.NETHER_FOOD).stacksTo(16)
            .usingConvertsTo(Items.GLASS_BOTTLE));
    public static final DeferredItem<Item> FROSTBITE_BERRY_JUICE
            = ITEMS.registerSimpleItem("frostbite_berry_juice", () -> new Item.Properties()
            .food(ModFoodComponents.JUICE, ModConsumableComponents.NETHER_FOOD).stacksTo(16)
            .usingConvertsTo(Items.GLASS_BOTTLE));
    public static final DeferredItem<Item> WARPED_FORAGE_MIX = ITEMS.registerSimpleItem("warped_forage_mix",
            () -> new Item.Properties().food(ModFoodComponents.NETHER_FORAGE, ModConsumableComponents.NETHER_FOOD));
    public static final DeferredItem<Item> CRIMSON_FORAGE_MIX = ITEMS.registerSimpleItem("crimson_forage_mix",
            () -> new Item.Properties().food(ModFoodComponents.NETHER_FORAGE, ModConsumableComponents.NETHER_FOOD));

    private static DeferredItem<Item> register(String name, Function<Item.Properties, ? extends Item> item,
                                               Supplier<Item.Properties> itemProperties) {
        return ITEMS.registerItem(name, item, itemProperties);
    }

    private static DeferredItem<Item> registerTorchBlockItem(String name, Supplier<Block> standingBlock, Supplier<Block> wallBlock) {
        final Function<Item.Properties, StandingAndWallBlockItem> blockItem
                = prop -> new StandingAndWallBlockItem(standingBlock.get(), wallBlock.get(), Direction.DOWN, prop);
        return register(name, blockItem, Item.Properties::new);
    }

    private static DeferredItem<Item> registerBlockItem(String name, Supplier<Block> block) {
        final Function<Item.Properties, BlockItem> blockItem = prop -> new BlockItem(block.get(), prop);
        return register(name, blockItem, Item.Properties::new);
    }

    private static DeferredItem<Item> registerBlockItem(String name, Supplier<Block> block, Supplier<Item.Properties> properties) {
        final Function<Item.Properties, BlockItem> blockItem = prop -> new BlockItem(block.get(), prop);
        return register(name, blockItem, properties);
    }

    /**
     * Called during mod initialization to register every item.
     */
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        AssortedDiscoveries.LOGGER.info("Registered Items");
    }
}
