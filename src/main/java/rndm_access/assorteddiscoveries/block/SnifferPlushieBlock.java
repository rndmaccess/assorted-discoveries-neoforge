package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SnifferPlushieBlock extends AbstractSimplePlushieBlock {
    public static final MapCodec<SnifferPlushieBlock> CODEC = simpleCodec(SnifferPlushieBlock::new);
    private static final VoxelShape NORTH_SHAPE = Block.box(2.0D, 0.0D, 1.0D,
            14.0D, 9.5D, 15.0D);

    public SnifferPlushieBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected MapCodec<? extends AbstractSimplePlushieBlock> codec() {
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
