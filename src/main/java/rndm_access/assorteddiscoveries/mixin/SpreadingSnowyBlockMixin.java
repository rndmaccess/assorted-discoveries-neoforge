package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.SpreadingSnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

@Mixin(SpreadingSnowyBlock.class)
public abstract class SpreadingSnowyBlockMixin {
    @ModifyReturnValue(method = "canStayAlive", at = @At("RETURN"))
    private static boolean canStayAlive(boolean original, BlockState state, LevelReader level, BlockPos pos) {
        BlockState blockState = level.getBlockState(pos.above());

        if(blockState.is(BlockTags.SNOW) || blockState.is(ModBlockTags.SNOW_SLABS)
                || blockState.is(ModBlockTags.SNOW_STAIRS)) {
            return true;
        }
        return original;
    }
}
