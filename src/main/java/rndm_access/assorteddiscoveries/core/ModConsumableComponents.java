package rndm_access.assorteddiscoveries.core;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.Consumables;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public final class ModConsumableComponents {
    public static final Consumable WITCHS_CRADLE_SOUP = Consumables.defaultFood()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.NIGHT_VISION, 2400, 0,
                            true, true))).build();
    public static final Consumable NETHER_FOOD = Consumables.defaultDrink()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 2400,
                            0, true, true))).build();
}
