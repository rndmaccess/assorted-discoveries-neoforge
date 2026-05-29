package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HoglinPlushieBlock extends AbstractSimplePlushieBlock {
    public static final MapCodec<HoglinPlushieBlock> CODEC = simpleCodec(HoglinPlushieBlock::new);
    private static final VoxelShape NORTH_SHAPE = Block.box(3.0, 0.0, 1.0,
            12.0, 9.0, 15.0);

    public HoglinPlushieBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(WATERLOGGED, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<HoglinPlushieBlock> codec() {
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
