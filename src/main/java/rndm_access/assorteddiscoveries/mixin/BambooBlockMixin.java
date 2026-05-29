package rndm_access.assorteddiscoveries.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BambooStalkBlock.class)
public abstract class BambooBlockMixin {
    @ModifyReturnValue(method = "getStateForPlacement", at = @At("RETURN"))
    private BlockState getStateForPlacement(BlockState original, @NotNull BlockPlaceContext context) {
        if(isSlabBottom(context.getLevel(), context.getClickedPos())) {
            return null;
        }
        return original;
    }

    @ModifyReturnValue(method = "canSurvive", at = @At("RETURN"))
    private boolean onCanSurvive(boolean original, BlockState state, LevelReader level, BlockPos pos) {
        if (isSlabBottom(level, pos)) {
            return false;
        }
        return original;
    }

    @Unique
    private static boolean isSlabBottom(LevelReader world, BlockPos pos) {
        BlockState soil = world.getBlockState(pos.below());
        return soil.getBlock() instanceof SlabBlock && soil.getValue(SlabBlock.TYPE).equals(SlabType.BOTTOM);
    }
}