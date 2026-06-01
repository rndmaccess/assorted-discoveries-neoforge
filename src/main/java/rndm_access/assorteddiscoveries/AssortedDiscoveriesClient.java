package rndm_access.assorteddiscoveries;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.color.block.BlockTintSources;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jspecify.annotations.NonNull;
import rndm_access.assorteddiscoveries.block.SheepPlushieBlock;
import rndm_access.assorteddiscoveries.client.block_entity.DyedCampfireBlockEntityRenderer;
import rndm_access.assorteddiscoveries.core.*;
import rndm_access.assorteddiscoveries.client.particle.BogBlossomNectarParticle;
import rndm_access.assorteddiscoveries.client.particle.SporeParticle;

import java.util.List;

@Mod(value = AssortedDiscoveries.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = AssortedDiscoveries.MOD_ID, value = Dist.CLIENT)
public class AssortedDiscoveriesClient {
    public AssortedDiscoveriesClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public void onClientSetup() {
        registerBlockEntityRenderers();

        /*
        ClientPlayNetworking.registerGlobalReceiver(BooleanEntriesS2CPayload.ID, (payload, context) -> {
            ModClientConfig.updateBoolEntries(payload.configMap());
            AssortedDiscoveries.LOGGER.info("{} received the server config data!", context.player().getName().getString());
        });
        */
    }

    private static void registerBlockEntityRenderers() {
        BlockEntityRenderers.register(ModBlockEntityTypes.DYED_CAMPFIRE.get(), DyedCampfireBlockEntityRenderer::new);
    }

    @SubscribeEvent
    private static void registerBlockColors(RegisterColorHandlersEvent.BlockTintSources event) {
        event.register(List.of(BlockTintSources.grassBlock()), ModBlocks.ENDERMAN_PLUSHIE.get(), ModBlocks.GRASS_SLAB);

        event.register(List.of(new BlockTintSource() {
            @Override
            public int color(@NonNull BlockState state) {
                return -1;
            }

            @Override
            public int colorInWorld(@NonNull BlockState state, @NonNull BlockAndTintGetter level, @NonNull BlockPos pos) {
                return ((SheepPlushieBlock) state.getBlock()).getColor().getTextureDiffuseColor();
            }
        }), ModBlocks.WHITE_SHEEP_PLUSHIE.get(), ModBlocks.ORANGE_SHEEP_PLUSHIE.get(),
                ModBlocks.MAGENTA_SHEEP_PLUSHIE.get(), ModBlocks.LIGHT_BLUE_SHEEP_PLUSHIE.get(),
                ModBlocks.YELLOW_SHEEP_PLUSHIE.get(), ModBlocks.LIME_SHEEP_PLUSHIE.get(),
                ModBlocks.PINK_SHEEP_PLUSHIE.get(), ModBlocks.GRAY_SHEEP_PLUSHIE.get(),
                ModBlocks.LIGHT_GRAY_SHEEP_PLUSHIE.get(), ModBlocks.CYAN_SHEEP_PLUSHIE.get(),
                ModBlocks.PURPLE_SHEEP_PLUSHIE.get(), ModBlocks.BLUE_SHEEP_PLUSHIE.get(),
                ModBlocks.BROWN_SHEEP_PLUSHIE.get(), ModBlocks.GREEN_SHEEP_PLUSHIE.get(),
                ModBlocks.RED_SHEEP_PLUSHIE.get(), ModBlocks.BLACK_SHEEP_PLUSHIE.get());
    }

    @SubscribeEvent
    private static void registerParticleProvider(RegisterParticleProvidersEvent event) {
        event.registerSpriteSet(ModParticleTypes.WHITE_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.ORANGE_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.MAGENTA_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.LIGHT_BLUE_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.YELLOW_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.LIME_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.PINK_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.GRAY_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.LIGHT_GRAY_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.CYAN_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.PURPLE_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BLUE_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BROWN_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.GREEN_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.RED_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BLACK_EMBER.get(), LavaParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.WHITE_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.ORANGE_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.MAGENTA_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.LIGHT_BLUE_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.YELLOW_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.LIME_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.PINK_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.GRAY_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.LIGHT_GRAY_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.CYAN_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.PURPLE_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BLUE_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BROWN_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.GREEN_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.RED_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BLACK_FLAME.get(), FlameParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BLOOD_KELP_SPORE.get(), SporeParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.WITCHS_CRADLE_SPORE.get(), SporeParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.BOG_BLOSSOM_NECTAR.get(), BogBlossomNectarParticle.Provider::new);
        event.registerSpriteSet(ModParticleTypes.SOUL_EMBER.get(), LavaParticle.Provider::new);
    }
}
