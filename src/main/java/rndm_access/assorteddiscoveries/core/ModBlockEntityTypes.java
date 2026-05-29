package rndm_access.assorteddiscoveries.core;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.block_entity.DyedCampfireBlockEntity;

public final class ModBlockEntityTypes {
    public static final BlockEntityType<DyedCampfireBlockEntity> DYED_CAMPFIRE
            = register("dyed_campfire", FabricBlockEntityTypeBuilder.create(DyedCampfireBlockEntity::new,
            ModBlocks.WHITE_CAMPFIRE, ModBlocks.ORANGE_CAMPFIRE, ModBlocks.MAGENTA_CAMPFIRE,
            ModBlocks.LIGHT_BLUE_CAMPFIRE, ModBlocks.YELLOW_CAMPFIRE,
            ModBlocks.LIME_CAMPFIRE, ModBlocks.PINK_CAMPFIRE, ModBlocks.GRAY_CAMPFIRE,
            ModBlocks.LIGHT_GRAY_CAMPFIRE, ModBlocks.CYAN_CAMPFIRE,
            ModBlocks.PURPLE_CAMPFIRE, ModBlocks.BLUE_CAMPFIRE, ModBlocks.BROWN_CAMPFIRE,
            ModBlocks.GREEN_CAMPFIRE, ModBlocks.RED_CAMPFIRE, ModBlocks.BLACK_CAMPFIRE).build());

    private static <T extends BlockEntity> BlockEntityType<T> register(String name, BlockEntityType<T> type) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, AssortedDiscoveries.makeModId(name), type);
    }

    /**
     * Called during mod initialization to register every block entity type.
     */
    public static void register() {
        AssortedDiscoveries.LOGGER.info("Registered block entity types");
    }
}
