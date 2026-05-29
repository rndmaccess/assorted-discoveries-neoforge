package rndm_access.assorteddiscoveries.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import rndm_access.assorteddiscoveries.core.ModEntityTypeTags;
import rndm_access.assorteddiscoveries.core.ModItems;

public class BlueberryBushBlock extends AbstractBerryBushBlock {
    public static final MapCodec<BlueberryBushBlock> CODEC = simpleCodec(BlueberryBushBlock::new);

    public BlueberryBushBlock(BlockBehaviour.Properties settings) {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }

    @Override
    protected MapCodec<BlueberryBushBlock> codec() {
        return CODEC;
    }

    @Override
    protected Item berryItem() {
        return ModItems.BLUEBERRIES;
    }

    @Override
    protected TagKey<EntityType<?>> mobsImmune() {
        return ModEntityTypeTags.BLUEBERRY_BUSH_IMMUNE_ENTITY_TYPES;
    }

    @Override
    protected boolean bushDamages() {
        return false;
    }

    @Override
    protected boolean needsLightToGrow() {
        return true;
    }
}
