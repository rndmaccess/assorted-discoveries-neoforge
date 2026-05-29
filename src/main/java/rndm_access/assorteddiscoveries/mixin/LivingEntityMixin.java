package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @ModifyReturnValue(method = "causeFallDamage", at = @At("RETURN"))
    public boolean causeFallDamage(boolean original, double fallDistance, float damageModifier,
                                   DamageSource damageSource) {
        boolean isRabbit = ((EntityAccessor) this).getType().equals(EntityType.RABBIT);

        // This lets rabbits fall 5 blocks before they take damage.
        if(isRabbit) {
            boolean isInRange = (Math.max(fallDistance - 4.0F, 0.0F)) == 0.0F;

            if(isInRange) {
                return false;
            }
        }
        return original;
    }
}
