package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.VoxelShape;
import rndm_access.assorteddiscoveries.block.state.ModBlockStateProperties;

public class CatPlushieBlock extends AbstractSimplePlushieBlock {
    public static final BooleanProperty IS_SITTING = ModBlockStateProperties.IS_SITTING;
    public static final MapCodec<CatPlushieBlock> CODEC = simpleCodec(CatPlushieBlock::new);
    private static final VoxelShape NORTH_SHAPE = Block.box(4.5D, 0.0D, 1.0D,
            11.5D, 9.5D, 14.5D);

    public CatPlushieBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.NORTH).setValue(IS_SITTING, false));
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        boolean value = state.getValue(IS_SITTING);

        world.setBlockAndUpdate(pos, state.setValue(IS_SITTING, !value));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected MapCodec<CatPlushieBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getNorthShape() {
        return NORTH_SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED, FACING, IS_SITTING);
    }
}
