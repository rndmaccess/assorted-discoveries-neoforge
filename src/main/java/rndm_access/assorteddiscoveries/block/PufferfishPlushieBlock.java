package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class PufferfishPlushieBlock extends AbstractSimplePlushieBlock {
    public static final MapCodec<PufferfishPlushieBlock> CODEC = simpleCodec(PufferfishPlushieBlock::new);
    private static final VoxelShape SHAPE = Block.box(1.5D, 0.0D, 2.5D, 14.5D, 8.5D, 11.5D);

    public PufferfishPlushieBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NonNull MapCodec<PufferfishPlushieBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getNorthShape() {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING);
    }
}
