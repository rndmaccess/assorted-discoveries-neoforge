package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

@Mixin(WallBlock.class)
public abstract class WallBlockMixin {
    @ModifyReturnValue(method = "connectsTo", at = @At("RETURN"))
    private boolean connectTo(boolean original, BlockState state, boolean faceSolid, Direction direction) {
        if(state.is(ModBlockTags.SNOW_WALLS) || state.is(ModBlockTags.WOODEN_WALLS)) {
            return true;
        }
        return original;
    }
}
