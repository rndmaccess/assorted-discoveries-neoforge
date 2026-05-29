package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

@Mixin(FenceGateBlock.class)
public abstract class FenceGateBlockMixin {
    @ModifyReturnValue(method = "isWall", at = @At("RETURN"))
    private boolean isWall(boolean original, BlockState state) {
        if(state.is(ModBlockTags.SNOW_WALLS) || state.is(ModBlockTags.WOODEN_WALLS)) {
            return true;
        }
        return original;
    }
}
