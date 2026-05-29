package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.core.ModEntityTypeTags;
import rndm_access.assorteddiscoveries.core.ModItems;
import rndm_access.assorteddiscoveries.core.ModParticleTypes;

public class WitchsCradleBlock extends AbstractBerryBushBlock {
    public static final MapCodec<WitchsCradleBlock> CODEC = simpleCodec(WitchsCradleBlock::new);
    private static final VoxelShape SMALL_SHAPE = Block.box(3.0D, 0.0D, 3.0D,
            13.0D, 9.0D, 13.0D);
    private static final VoxelShape MEDIUM_SHAPE = Block.box(2.0D, 0.0D, 2.0D,
            14.0D, 11.0D, 14.0D);
    private static final VoxelShape LARGE_SHAPE = Block.box(1.0D, 0.0D, 1.0D,
            15.0D, 11.0D, 15.0D);
    private static final VoxelShape GIANT_SHAPE = Block.box(0.0D, 0.0D, 0.0D,
            16.0D, 12.0D, 16.0D);

    public WitchsCradleBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<WitchsCradleBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected TagKey<EntityType<?>> mobsImmune() {
        return ModEntityTypeTags.WITCHS_CRADLE_IMMUNE_ENTITY_TYPES;
    }

    @Override
    protected boolean bushDamages() {
        return true;
    }

    @Override
    protected boolean needsLightToGrow() {
        return true;
    }

    @Override
    protected Item berryItem() {
        return ModItems.WITCHS_CRADLE_BRANCH;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double x = pos.getX() + random.nextDouble();
        double y = pos.getY() + random.nextDouble();
        double z = pos.getZ() + random.nextDouble();

        world.addParticle(ModParticleTypes.WITCHS_CRADLE_SPORE, x, y, z, 0.0D,
                0.0D, 0.0D);
    }

    @Override
    public @NonNull VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos,
                                        CollisionContext context) {
        return switch (state.getValue(AGE)) {
            case 0 -> SMALL_SHAPE;
            case 1 -> MEDIUM_SHAPE;
            case 2 -> LARGE_SHAPE;
            default -> GIANT_SHAPE;
        };
    }
}
