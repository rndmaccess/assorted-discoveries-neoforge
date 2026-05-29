package rndm_access.assorteddiscoveries.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.BeetrootBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import rndm_access.assorteddiscoveries.core.ModItems;

public class GreenOnionsBlock extends BeetrootBlock {
    private static final VoxelShape[] ONION_SHAPE_BY_AGE = new VoxelShape[] {
            Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D),
            Block.box(3.0D, 0.0D, 3.0D, 13.0D, 11.0D, 13.0D),
            Block.box(2.0D, 0.0D, 2.0D, 14.0D, 14.0D, 14.0D),
            Block.box(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D)
    };

    public GreenOnionsBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return ONION_SHAPE_BY_AGE[state.getValue(this.getAgeProperty())];
    }

    @Override
    protected ItemLike getBaseSeedId() { return ModItems.GREEN_ONION_SEEDS; }
}
