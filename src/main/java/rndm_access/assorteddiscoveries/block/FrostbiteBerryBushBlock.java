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
import rndm_access.assorteddiscoveries.core.*;

public class FrostbiteBerryBushBlock extends AbstractBerryBushBlock {
    public static final MapCodec<FrostbiteBerryBushBlock> CODEC = simpleCodec(FrostbiteBerryBushBlock::new);

    public FrostbiteBerryBushBlock(Properties settings) {
        super(settings);
        this.registerDefaultState(this.getStateDefinition().any().setValue(AGE, 0));
    }

    @Override
    protected MapCodec<FrostbiteBerryBushBlock> codec() {
        return CODEC;
    }

    @Override
    protected TagKey<EntityType<?>> mobsImmune() {
        return ModEntityTypeTags.FROSTBITE_BERRY_BUSH_IMMUNE_ENTITY_TYPES;
    }

    @Override
    protected boolean bushDamages() {
        return true;
    }

    @Override
    protected boolean needsLightToGrow() {
        return false;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        double x = pos.getX() + random.nextDouble();
        double y = pos.getY() + random.nextDouble();
        double z = pos.getZ() + random.nextDouble();
        double randNum = random.nextDouble();

        if(randNum < 0.2) {
            world.addParticle(ModParticleTypes.SOUL_EMBER, x, y, z, 0.0D,
                    0.0D, 0.0D);
        }
    }

    @Override
    protected Item berryItem() {
        return ModItems.FROSTBITE_BERRIES;
    }

    @Override
    public boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(ModBlockTags.FROSTBITE_BERRY_BUSH_PLANTABLE_ON);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
