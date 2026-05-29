package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SnowyBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

@Mixin(SnowyBlock.class)
public abstract class SnowyBlockMixin {
    @ModifyReturnValue(method = "updateShape", at = @At("RETURN"))
    private BlockState getStateForNeighborUpdate(BlockState original, BlockState state, LevelReader level,
                                                 ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour,
                                                 BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
        if(directionToNeighbour == Direction.UP && this.isSnowSlabOrStairs(level, neighbourPos, neighbourState)) {
            return state.setValue(SnowyBlock.SNOWY, true);
        }
        return original;
    }

    @ModifyReturnValue(method = "getStateForPlacement", at = @At("RETURN"))
    private BlockState getPlacementState(BlockState original, BlockPlaceContext context) {
        Level world = context.getLevel();
        BlockPos neighborPos = context.getClickedPos().above();
        BlockState neighborState = context.getLevel().getBlockState(neighborPos);
        BlockState placedState = Block.byItem(context.getItemInHand().getItem()).defaultBlockState();

        if(this.isSnowSlabOrStairs(world, neighborPos, neighborState)) {
            return placedState.setValue(SnowyBlock.SNOWY, true);
        }
        return original;
    }

    @Unique
    private boolean isSnowSlabOrStairs(LevelReader world, BlockPos pos, BlockState state) {
        boolean isCovered = state.isFaceSturdy(world, pos, Direction.DOWN);
        return (state.is(ModBlockTags.SNOW_STAIRS) && isCovered)
                || (state.is(ModBlockTags.SNOW_SLABS) && isCovered);
    }
}
