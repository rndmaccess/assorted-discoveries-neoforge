package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;

public class ZombiePlushieBlock extends AbstractSimplePlushieBlock {
    public static final MapCodec<ZombiePlushieBlock> CODEC = simpleCodec(ZombiePlushieBlock::new);
    private static final VoxelShape NORTH_SHAPE = Block.box(2.5D, 0.0D, 3.5D,
            13.0D, 12.5D, 12.5D);

    public ZombiePlushieBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    protected @NonNull MapCodec<ZombiePlushieBlock> codec() {
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
