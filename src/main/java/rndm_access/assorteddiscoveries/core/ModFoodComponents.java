package rndm_access.assorteddiscoveries.core;

import net.minecraft.world.food.FoodProperties;

public final class ModFoodComponents {
    public static final FoodProperties FRIED_EGG = (new FoodProperties.Builder()).nutrition(5)
            .saturationModifier(0.6F).build();
    public static final FoodProperties GREEN_ONION = new FoodProperties.Builder().nutrition(1)
            .saturationModifier(0.6F).build();
    public static final FoodProperties NOODLE_SOUP = createBasicStew(8);
    public static final FoodProperties PUDDING = createBasicStew(3);
    public static final FoodProperties BERRY_PUDDING = createBasicStew(6);
    public static final FoodProperties CARAMEL = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.3F).build();
    public static final FoodProperties CARAMEL_APPLE = new FoodProperties.Builder().nutrition(4)
            .saturationModifier(0.6F).build();
    public static final FoodProperties WITCHS_CRADLE_BRANCH = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.1F).build();
    public static final FoodProperties WITCHS_CRADLE_SOUP = new FoodProperties.Builder().nutrition(6)
            .saturationModifier(0.6F).alwaysEdible().build();
    public static final FoodProperties BLUEBERRIES = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.1F).build();
    public static final FoodProperties JUICE = new FoodProperties.Builder().nutrition(6)
            .saturationModifier(0.6F).build();
    public static final FoodProperties SPRUCE_CONE = new FoodProperties.Builder().nutrition(2)
            .saturationModifier(0.3F).build();
    public static final FoodProperties FORESTS_BOUNTY = createBasicStew(5);
    public static final FoodProperties HOGLIN_STEW = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.8F).build();
    public static final FoodProperties NETHER_BERRIES = new FoodProperties.Builder().nutrition(3)
            .saturationModifier(0.2F).build();
    public static final FoodProperties NETHER_FORAGE = new FoodProperties.Builder().nutrition(8)
            .saturationModifier(0.8F).build();

    private static FoodProperties createBasicStew(int hunger) {
        return new FoodProperties.Builder().nutrition(hunger).saturationModifier(0.6F).build();
    }
}
