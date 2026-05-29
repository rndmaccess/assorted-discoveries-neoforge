package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import rndm_access.assorteddiscoveries.core.ModBlockTags;

public class ShortEnderGrassBlock extends VegetationBlock {
    public static final MapCodec<ShortEnderGrassBlock> CODEC = simpleCodec(ShortEnderGrassBlock::new);
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D,
            16.0D, 10.0D, 16.0D);

    public ShortEnderGrassBlock(BlockBehaviour.Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends VegetationBlock> codec() {
        return CODEC;
    }

    @Override
    public boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(ModBlockTags.ENDER_GRASS_PLANTABLE_ON);
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double x = pos.getX() + random.nextDouble();
        double y = pos.getY() + random.nextDouble();
        double z = pos.getZ() + random.nextDouble();

        world.addParticle(ParticleTypes.PORTAL, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos,
                                      CollisionContext context) {
        return SHAPE;
    }
}
