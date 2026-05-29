package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HorsePlushieBlock extends AbstractSimplePlushieBlock {
    public static final MapCodec<HorsePlushieBlock> CODEC = simpleCodec(HorsePlushieBlock::new);
    private static final VoxelShape NORTH_SHAPE = Block.box(4.5D, 0.0D, 0.5D,
            11.5D, 12.5, 15.5D);

    public HorsePlushieBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<HorsePlushieBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getNorthShape() {
        return NORTH_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }
}
