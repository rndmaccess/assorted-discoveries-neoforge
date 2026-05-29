package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class WildGreenOnionsBlock extends VegetationBlock {
    public static final MapCodec<WildGreenOnionsBlock> CODEC = simpleCodec(WildGreenOnionsBlock::new);
    private static final VoxelShape SHAPE = Block.box(1.0D, 0.0D, 1.0D,
            15.0D, 16.0D, 15.0D);

    public WildGreenOnionsBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<WildGreenOnionsBlock> codec() {
        return CODEC;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos,
                                      CollisionContext context) {
        return SHAPE;
    }
}
