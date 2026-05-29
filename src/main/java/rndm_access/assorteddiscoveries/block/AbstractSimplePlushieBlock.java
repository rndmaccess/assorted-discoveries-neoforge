package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import rndm_access.assorteddiscoveries.util.ShapeHelper;

import java.util.HashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractSimplePlushieBlock extends AbstractPlushieBlock {
    private final HashMap<Direction, VoxelShape> shapes;

    public AbstractSimplePlushieBlock(Properties settings) {
        super(settings);
        this.shapes = ShapeHelper.makeShapeRotMap(this.getNorthShape());
    }

    @Override
    protected abstract MapCodec<? extends AbstractSimplePlushieBlock> codec();

    @Override
    protected abstract void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder);

    protected abstract VoxelShape getNorthShape();

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return shapes.get(state.getValue(FACING));
    }
}
