package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;
import rndm_access.assorteddiscoveries.block_entity.DyedCampfireBlockEntity;

import java.util.function.Supplier;

public final class ModBlockEntityTypes {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES
            = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AssortedDiscoveries.MOD_ID);

    public static final Supplier<BlockEntityType<DyedCampfireBlockEntity>> DYED_CAMPFIRE
            = BLOCK_ENTITIES.register("dyed_campfire", () -> new BlockEntityType<>(DyedCampfireBlockEntity::new,
            ModBlocks.WHITE_CAMPFIRE.get(), ModBlocks.ORANGE_CAMPFIRE.get(), ModBlocks.MAGENTA_CAMPFIRE.get(),
            ModBlocks.LIGHT_BLUE_CAMPFIRE.get(), ModBlocks.YELLOW_CAMPFIRE.get(),
            ModBlocks.LIME_CAMPFIRE.get(), ModBlocks.PINK_CAMPFIRE.get(), ModBlocks.GRAY_CAMPFIRE.get(),
            ModBlocks.LIGHT_GRAY_CAMPFIRE.get(), ModBlocks.CYAN_CAMPFIRE.get(),
            ModBlocks.PURPLE_CAMPFIRE.get(), ModBlocks.BLUE_CAMPFIRE.get(), ModBlocks.BROWN_CAMPFIRE.get(),
            ModBlocks.GREEN_CAMPFIRE.get(), ModBlocks.RED_CAMPFIRE.get(), ModBlocks.BLACK_CAMPFIRE.get()));

    /**
     * Called during mod initialization to register every block entity type.
     */
    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);

        AssortedDiscoveries.LOGGER.info("Registered block entity types");
    }
}
