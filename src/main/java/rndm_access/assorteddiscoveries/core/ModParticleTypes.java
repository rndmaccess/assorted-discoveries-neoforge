package rndm_access.assorteddiscoveries.core;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModParticleTypes {
    public static final SimpleParticleType WHITE_EMBER = register("white_ember");
    public static final SimpleParticleType ORANGE_EMBER = register("orange_ember");
    public static final SimpleParticleType MAGENTA_EMBER = register("magenta_ember");
    public static final SimpleParticleType LIGHT_BLUE_EMBER = register("light_blue_ember");
    public static final SimpleParticleType YELLOW_EMBER = register("yellow_ember");
    public static final SimpleParticleType LIME_EMBER = register("lime_ember");
    public static final SimpleParticleType PINK_EMBER = register("pink_ember");
    public static final SimpleParticleType GRAY_EMBER = register("gray_ember");
    public static final SimpleParticleType LIGHT_GRAY_EMBER = register("light_gray_ember");
    public static final SimpleParticleType CYAN_EMBER = register("cyan_ember");
    public static final SimpleParticleType PURPLE_EMBER = register("purple_ember");
    public static final SimpleParticleType BLUE_EMBER = register("blue_ember");
    public static final SimpleParticleType BROWN_EMBER = register("brown_ember");
    public static final SimpleParticleType GREEN_EMBER = register("green_ember");
    public static final SimpleParticleType RED_EMBER = register("red_ember");
    public static final SimpleParticleType BLACK_EMBER = register("black_ember");
    public static final SimpleParticleType WHITE_FLAME = register("white_flame");
    public static final SimpleParticleType ORANGE_FLAME = register("orange_flame");
    public static final SimpleParticleType MAGENTA_FLAME = register("magenta_flame");
    public static final SimpleParticleType LIGHT_BLUE_FLAME = register("light_blue_flame");
    public static final SimpleParticleType YELLOW_FLAME = register("yellow_flame");
    public static final SimpleParticleType LIME_FLAME = register("lime_flame");
    public static final SimpleParticleType PINK_FLAME = register("pink_flame");
    public static final SimpleParticleType GRAY_FLAME = register("gray_flame");
    public static final SimpleParticleType LIGHT_GRAY_FLAME = register("light_gray_flame");
    public static final SimpleParticleType CYAN_FLAME = register("cyan_flame");
    public static final SimpleParticleType PURPLE_FLAME = register("purple_flame");
    public static final SimpleParticleType BLUE_FLAME = register("blue_flame");
    public static final SimpleParticleType BROWN_FLAME = register("brown_flame");
    public static final SimpleParticleType GREEN_FLAME = register("green_flame");
    public static final SimpleParticleType RED_FLAME = register("red_flame");
    public static final SimpleParticleType BLACK_FLAME = register("black_flame");
    public static final SimpleParticleType BLOOD_KELP_SPORE = register("blood_kelp_spore");
    public static final SimpleParticleType WITCHS_CRADLE_SPORE = register("witchs_cradle_spore");
    public static final SimpleParticleType BOG_BLOSSOM_NECTAR = register("bog_blossom_nectar");
    public static final SimpleParticleType SOUL_EMBER = register("soul_ember");

    private static SimpleParticleType register(String name) {
        SimpleParticleType particle = FabricParticleTypes.simple();
        return Registry.register(BuiltInRegistries.PARTICLE_TYPE, AssortedDiscoveries.makeModId(name), particle);
    }

    /**
     * Called during mod initialization to register every particle type.
     */
    public static void register() {
        AssortedDiscoveries.LOGGER.info("Registered particle types");
    }
}