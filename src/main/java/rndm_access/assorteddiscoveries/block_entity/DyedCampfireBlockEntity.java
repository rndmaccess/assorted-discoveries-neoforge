package rndm_access.assorteddiscoveries.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.core.ModBlockEntityTypes;

public class DyedCampfireBlockEntity extends CampfireBlockEntity {
    public DyedCampfireBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public @NonNull BlockEntityType<?> getType() {
        return ModBlockEntityTypes.DYED_CAMPFIRE;
    }

    @Override
    public boolean isValidBlockState(@NonNull BlockState state) {
        return ModBlockEntityTypes.DYED_CAMPFIRE.isValid(state);
    }
}
