package rndm_access.assorteddiscoveries.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class RopeLadderBlockItem extends BlockItem {
    public RopeLadderBlockItem(Block block, Item.Properties settings) {
        super(block, settings);
    }

    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        BlockPos.MutableBlockPos mutablePos = context.getClickedPos().relative(context.getClickedFace().getOpposite()).mutable();
        BlockState state = context.getLevel().getBlockState(mutablePos);
        Direction down = Direction.DOWN;

        while (state.is(this.getBlock())) {
            mutablePos.move(down);
            state = context.getLevel().getBlockState(mutablePos);

            if (state.canBeReplaced(context)) {
                return BlockPlaceContext.at(context, mutablePos, down);
            }
        }
        return context;
    }
}
