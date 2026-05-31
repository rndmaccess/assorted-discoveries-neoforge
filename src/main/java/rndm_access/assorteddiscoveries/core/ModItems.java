package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
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

    public static final DeferredItem<Item> WHITE_TORCH = registerBlockItem("white_torch",
            ModBlocks.WHITE_TORCH, ModBlocks.WHITE_WALL_TORCH);
    public static final DeferredItem<Item> ORANGE_TORCH = registerBlockItem("orange_torch",
            ModBlocks.ORANGE_TORCH, ModBlocks.ORANGE_WALL_TORCH);
    public static final DeferredItem<Item> MAGENTA_TORCH = registerBlockItem("magenta_torch",
            ModBlocks.MAGENTA_TORCH, ModBlocks.MAGENTA_WALL_TORCH);
    public static final DeferredItem<Item> LIGHT_BLUE_TORCH = registerBlockItem("light_blue_torch",
            ModBlocks.LIGHT_BLUE_TORCH, ModBlocks.LIGHT_BLUE_WALL_TORCH);
    public static final DeferredItem<Item> YELLOW_TORCH = registerBlockItem("yellow_torch",
            ModBlocks.YELLOW_TORCH, ModBlocks.YELLOW_WALL_TORCH);
    public static final DeferredItem<Item> LIME_TORCH = registerBlockItem("lime_torch",
            ModBlocks.LIME_TORCH, ModBlocks.LIME_WALL_TORCH);
    public static final DeferredItem<Item> PINK_TORCH = registerBlockItem("pink_torch",
            ModBlocks.PINK_TORCH, ModBlocks.PINK_WALL_TORCH);
    public static final DeferredItem<Item> GRAY_TORCH = registerBlockItem("gray_torch",
            ModBlocks.GRAY_TORCH, ModBlocks.GRAY_WALL_TORCH);
    public static final DeferredItem<Item> LIGHT_GRAY_TORCH = registerBlockItem("light_gray_torch",
            ModBlocks.LIGHT_GRAY_TORCH, ModBlocks.LIGHT_GRAY_WALL_TORCH);
    public static final DeferredItem<Item> CYAN_TORCH = registerBlockItem("cyan_torch",
            ModBlocks.CYAN_TORCH, ModBlocks.CYAN_WALL_TORCH);
    public static final DeferredItem<Item> PURPLE_TORCH = registerBlockItem("purple_torch",
            ModBlocks.PURPLE_TORCH, ModBlocks.PURPLE_WALL_TORCH);
    public static final DeferredItem<Item> BLUE_TORCH = registerBlockItem("blue_torch",
            ModBlocks.BLUE_TORCH, ModBlocks.BLUE_WALL_TORCH);
    public static final DeferredItem<Item> BROWN_TORCH = registerBlockItem("brown_torch",
            ModBlocks.BROWN_TORCH, ModBlocks.BROWN_WALL_TORCH);
    public static final DeferredItem<Item> GREEN_TORCH = registerBlockItem("green_torch",
            ModBlocks.GREEN_TORCH, ModBlocks.GREEN_WALL_TORCH);
    public static final DeferredItem<Item> RED_TORCH = registerBlockItem("red_torch",
            ModBlocks.RED_TORCH, ModBlocks.RED_WALL_TORCH);
    public static final DeferredItem<Item> BLACK_TORCH = registerBlockItem("black_torch",
            ModBlocks.BLACK_TORCH, ModBlocks.BLACK_WALL_TORCH);
    public static final Item GREEN_ONION_SEEDS = registerBlockItem("green_onion_seeds", ModBlocks.GREEN_ONIONS);



    public static final ResourceKey<Item> GREEN_ONION_KEY = makeRegistryKey("green_onion");
    public static final Item GREEN_ONION
            = register(new Item(new Item.Properties().food(ModFoodComponents.GREEN_ONION)
            .setId(GREEN_ONION_KEY)), GREEN_ONION_KEY);
    public static final ResourceKey<Item> BLUEBERRIES_KEY = makeRegistryKey("blueberries");
    public static final Item BLUEBERRIES = registerBlockItem(BLUEBERRIES_KEY, ModBlocks.BLUEBERRY_BUSH.get(),
            new Item.Properties().food(ModFoodComponents.BLUEBERRIES).setId(BLUEBERRIES_KEY));
    public static final ResourceKey<Item> SWEET_BERRY_JUICE_KEY = makeRegistryKey("sweet_berry_juice");
    public static final Item SWEET_BERRY_JUICE = register(new Item(new Item.Properties()
                .food(ModFoodComponents.JUICE, Consumables.DEFAULT_DRINK).stacksTo(16)
                .usingConvertsTo(Items.GLASS_BOTTLE).setId(SWEET_BERRY_JUICE_KEY)), SWEET_BERRY_JUICE_KEY);
    public static final ResourceKey<Item> BLUEBERRY_JUICE_KEY = makeRegistryKey("blueberry_juice");
    public static final Item BLUEBERRY_JUICE = register(new Item(new Item.Properties()
                .food(ModFoodComponents.JUICE, Consumables.DEFAULT_DRINK).stacksTo(16)
                .usingConvertsTo(Items.GLASS_BOTTLE).setId(BLUEBERRY_JUICE_KEY)), BLUEBERRY_JUICE_KEY);
    public static final ResourceKey<Item> NOODLES_KEY = makeRegistryKey("noodles");
    public static final Item NOODLES = register(new Item(new Item.Properties().setId(NOODLES_KEY)), NOODLES_KEY);
    public static final ResourceKey<Item> NOODLE_SOUP_KEY = makeRegistryKey("noodle_soup");
    public static final Item NOODLE_SOUP = register(new Item(new Item.Properties().food(ModFoodComponents.NOODLE_SOUP)
            .stacksTo(1).usingConvertsTo(Items.BOWL).setId(NOODLE_SOUP_KEY)), NOODLE_SOUP_KEY);
    public static final ResourceKey<Item> PUDDING_KEY = makeRegistryKey("pudding");
    public static final Item PUDDING = register(new Item(new Item.Properties()
            .food(ModFoodComponents.PUDDING).stacksTo(1).usingConvertsTo(Items.BOWL).setId(PUDDING_KEY)),
            PUDDING_KEY);
    public static final ResourceKey<Item> BERRY_PUDDING_KEY = makeRegistryKey("berry_pudding");
    public static final Item BERRY_PUDDING = register(new Item(new Item.Properties()
            .food(ModFoodComponents.BERRY_PUDDING).stacksTo(1).usingConvertsTo(Items.BOWL)
            .setId(BERRY_PUDDING_KEY)), BERRY_PUDDING_KEY);
    public static final ResourceKey<Item> SMOKY_QUARTZ_KEY = makeRegistryKey("smoky_quartz");
    public static final Item SMOKY_QUARTZ = register(new Item(new Item.Properties().setId(SMOKY_QUARTZ_KEY)),
            SMOKY_QUARTZ_KEY);
    public static final ResourceKey<Item> CARAMEL_APPLE_KEY = makeRegistryKey("caramel_apple");
    public static final Item CARAMEL_APPLE = register(new Item(new Item.Properties()
            .food(ModFoodComponents.CARAMEL_APPLE).stacksTo(1).usingConvertsTo(Items.STICK)
            .setId(CARAMEL_APPLE_KEY)), CARAMEL_APPLE_KEY);
    public static final ResourceKey<Item> CARAMEL_KEY = makeRegistryKey("caramel");
    public static final Item CARAMEL = register(new Item(new Item.Properties().food(ModFoodComponents.CARAMEL)
            .setId(CARAMEL_KEY)), CARAMEL_KEY);
    public static final ResourceKey<Item> SPRUCE_CONE_KEY = makeRegistryKey("spruce_cone");
    public static final Item SPRUCE_CONE = register(new Item(new Item.Properties().food(ModFoodComponents.SPRUCE_CONE)
            .setId(SPRUCE_CONE_KEY)), SPRUCE_CONE_KEY);
    public static final ResourceKey<Item> FORESTS_BOUNTY_KEY = makeRegistryKey("forests_bounty");
    public static final Item FORESTS_BOUNTY = register(new Item(new Item.Properties()
            .food(ModFoodComponents.FORESTS_BOUNTY).stacksTo(1).usingConvertsTo(Items.BOWL)
            .setId(FORESTS_BOUNTY_KEY)), FORESTS_BOUNTY_KEY);
    public static final ResourceKey<Item> WITCHS_CRADLE_BRANCH_KEY = makeRegistryKey("witchs_cradle_branch");
    public static final Item WITCHS_CRADLE_BRANCH = registerBlockItem(WITCHS_CRADLE_BRANCH_KEY, ModBlocks.WITCHS_CRADLE,
            new Item.Properties().food(ModFoodComponents.WITCHS_CRADLE_BRANCH).setId(WITCHS_CRADLE_BRANCH_KEY));
    public static final ResourceKey<Item> WITCHS_CRADLE_SOUP_KEY = makeRegistryKey("witchs_cradle_soup");
    public static final Item WITCHS_CRADLE_SOUP = register(new Item(new Item.Properties()
            .food(ModFoodComponents.WITCHS_CRADLE_SOUP, ModConsumableComponents.WITCHS_CRADLE_SOUP)
            .stacksTo(1).usingConvertsTo(Items.BOWL).setId(WITCHS_CRADLE_SOUP_KEY)), WITCHS_CRADLE_SOUP_KEY);
    public static final ResourceKey<Item> FRIED_EGG_KEY = makeRegistryKey("fried_egg");
    public static final Item FRIED_EGG = register(new Item(new Item.Properties()
            .food(ModFoodComponents.FRIED_EGG).stacksTo(16).setId(FRIED_EGG_KEY)), FRIED_EGG_KEY);
    public static final ResourceKey<Item> BLOOD_KELP_SEED_CLUSTER_KEY = makeRegistryKey("blood_kelp_seed_cluster");
    public static final Item BLOOD_KELP_SEED_CLUSTER = registerBlockItem(BLOOD_KELP_SEED_CLUSTER_KEY, ModBlocks.BLOOD_KELP);
    public static final ResourceKey<Item> BLOOD_KELP_KEY = makeRegistryKey("blood_kelp");
    public static final Item BLOOD_KELP = register(new Item(new Item.Properties()
            .setId(BLOOD_KELP_KEY)), BLOOD_KELP_KEY);
    public static final ResourceKey<Item> DRIED_BLOOD_KELP_KEY = makeRegistryKey("dried_blood_kelp");
    public static final Item DRIED_BLOOD_KELP = register(new Item(new Item.Properties()
            .food(Foods.DRIED_KELP).setId(DRIED_BLOOD_KELP_KEY)), DRIED_BLOOD_KELP_KEY);
    public static final ResourceKey<Item> HOGLIN_STEW_KEY = makeRegistryKey("hoglin_stew");
    public static final Item HOGLIN_STEW = register(new Item(new Item.Properties()
            .food(ModFoodComponents.HOGLIN_STEW).stacksTo(1).usingConvertsTo(Items.BOWL)
            .setId(HOGLIN_STEW_KEY)), HOGLIN_STEW_KEY);
    public static final ResourceKey<Item> CINDERSNAP_BERRIES_KEY = makeRegistryKey("cindersnap_berries");
    public static final Item CINDERSNAP_BERRIES = registerBlockItem(CINDERSNAP_BERRIES_KEY,
            ModBlocks.CINDERSNAP_BERRY_BUSH, new Item.Properties().food(ModFoodComponents.NETHER_BERRIES)
                    .setId(CINDERSNAP_BERRIES_KEY));
    public static final ResourceKey<Item> FROSTBITE_BERRIES_KEY = makeRegistryKey("frostbite_berries");
    public static final Item FROSTBITE_BERRIES = registerBlockItem(FROSTBITE_BERRIES_KEY,
            ModBlocks.FROSTBITE_BERRY_BUSH, new Item.Properties().food(ModFoodComponents.NETHER_BERRIES)
                    .setId(FROSTBITE_BERRIES_KEY));
    public static final ResourceKey<Item> CINDERSNAP_BERRY_JUICE_KEY = makeRegistryKey("cindersnap_berry_juice");
    public static final Item CINDERSNAP_BERRY_JUICE = register(new Item(new Item.Properties()
                .food(ModFoodComponents.JUICE, ModConsumableComponents.NETHER_FOOD).stacksTo(16)
                .usingConvertsTo(Items.GLASS_BOTTLE).setId(CINDERSNAP_BERRY_JUICE_KEY)), CINDERSNAP_BERRY_JUICE_KEY);
    public static final ResourceKey<Item> FROSTBITE_BERRY_JUICE_KEY = makeRegistryKey("frostbite_berry_juice");
    public static final Item FROSTBITE_BERRY_JUICE = register(new Item(new Item.Properties()
            .food(ModFoodComponents.JUICE, ModConsumableComponents.NETHER_FOOD).stacksTo(16)
            .usingConvertsTo(Items.GLASS_BOTTLE).setId(FROSTBITE_BERRY_JUICE_KEY)), FROSTBITE_BERRY_JUICE_KEY);
    public static final ResourceKey<Item> WARPED_FORAGE_MIX_KEY = makeRegistryKey("warped_forage_mix");
    public static final Item WARPED_FORAGE_MIX = register(new Item(new Item.Properties()
            .food(ModFoodComponents.NETHER_FORAGE, ModConsumableComponents.NETHER_FOOD)
            .setId(WARPED_FORAGE_MIX_KEY)), WARPED_FORAGE_MIX_KEY);
    public static final ResourceKey<Item> CRIMSON_FORAGE_MIX_KEY = makeRegistryKey("crimson_forage_mix");
    public static final Item CRIMSON_FORAGE_MIX = register(new Item(new Item.Properties()
            .food(ModFoodComponents.NETHER_FORAGE, ModConsumableComponents.NETHER_FOOD)
            .setId(CRIMSON_FORAGE_MIX_KEY)), CRIMSON_FORAGE_MIX_KEY);

    private static ResourceKey<Item> makeRegistryKey(String name) {
        return ResourceKey.create(Registries.ITEM, AssortedDiscoveries.makeModId(name));
    }

    private static DeferredItem<Item> register(String name, Function<Item.Properties, ? extends Item> item,
                                               Supplier<Item.Properties> itemProperties) {
        return ITEMS.registerItem(name, item, itemProperties);
    }

    private static DeferredItem<Item> registerBlockItem(String name, Supplier<Block> standingBlock, Supplier<Block> wallBlock) {
        final Function<Item.Properties, StandingAndWallBlockItem> blockItem
                = prop -> new StandingAndWallBlockItem(standingBlock.get(), wallBlock.get(), Direction.DOWN, prop);
        //Item.BY_BLOCK.put(standingBlock, blockItem);
        //Item.BY_BLOCK.put(wallBlock, blockItem);
        return register(name, blockItem, Item.Properties::new);
    }

    private static DeferredItem<Item> registerBlockItem(String name, Supplier<Block> block) {
        final Function<Item.Properties, BlockItem> blockItem = prop -> new BlockItem(block.get(), prop);
        //Item.BY_BLOCK.put(block, blockItem);
        return register(name, blockItem, Item.Properties::new);
    }

    private static DeferredItem<Item> registerBlockItem(String name, Supplier<Block> block, Supplier<Item.Properties> properties) {
        final Function<Item.Properties, BlockItem> blockItem = prop -> new BlockItem(block.get(), prop);
        //Item.BY_BLOCK.put(block, blockItem);
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
