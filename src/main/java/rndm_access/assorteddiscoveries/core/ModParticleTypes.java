package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

import java.util.function.Supplier;

public final class ModParticleTypes {
    private static final DeferredRegister<ParticleType<?>> PARTICLES
            = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, AssortedDiscoveries.MOD_ID);

    public static final Supplier<SimpleParticleType> WHITE_EMBER = register("white_ember");
    public static final Supplier<SimpleParticleType> ORANGE_EMBER = register("orange_ember");
    public static final Supplier<SimpleParticleType> MAGENTA_EMBER = register("magenta_ember");
    public static final Supplier<SimpleParticleType> LIGHT_BLUE_EMBER = register("light_blue_ember");
    public static final Supplier<SimpleParticleType> YELLOW_EMBER = register("yellow_ember");
    public static final Supplier<SimpleParticleType> LIME_EMBER = register("lime_ember");
    public static final Supplier<SimpleParticleType> PINK_EMBER = register("pink_ember");
    public static final Supplier<SimpleParticleType> GRAY_EMBER = register("gray_ember");
    public static final Supplier<SimpleParticleType> LIGHT_GRAY_EMBER = register("light_gray_ember");
    public static final Supplier<SimpleParticleType> CYAN_EMBER = register("cyan_ember");
    public static final Supplier<SimpleParticleType> PURPLE_EMBER = register("purple_ember");
    public static final Supplier<SimpleParticleType> BLUE_EMBER = register("blue_ember");
    public static final Supplier<SimpleParticleType> BROWN_EMBER = register("brown_ember");
    public static final Supplier<SimpleParticleType> GREEN_EMBER = register("green_ember");
    public static final Supplier<SimpleParticleType> RED_EMBER = register("red_ember");
    public static final Supplier<SimpleParticleType> BLACK_EMBER = register("black_ember");
    public static final Supplier<SimpleParticleType> WHITE_FLAME = register("white_flame");
    public static final Supplier<SimpleParticleType> ORANGE_FLAME = register("orange_flame");
    public static final Supplier<SimpleParticleType> MAGENTA_FLAME = register("magenta_flame");
    public static final Supplier<SimpleParticleType> LIGHT_BLUE_FLAME = register("light_blue_flame");
    public static final Supplier<SimpleParticleType> YELLOW_FLAME = register("yellow_flame");
    public static final Supplier<SimpleParticleType> LIME_FLAME = register("lime_flame");
    public static final Supplier<SimpleParticleType> PINK_FLAME = register("pink_flame");
    public static final Supplier<SimpleParticleType> GRAY_FLAME = register("gray_flame");
    public static final Supplier<SimpleParticleType> LIGHT_GRAY_FLAME = register("light_gray_flame");
    public static final Supplier<SimpleParticleType> CYAN_FLAME = register("cyan_flame");
    public static final Supplier<SimpleParticleType> PURPLE_FLAME = register("purple_flame");
    public static final Supplier<SimpleParticleType> BLUE_FLAME = register("blue_flame");
    public static final Supplier<SimpleParticleType> BROWN_FLAME = register("brown_flame");
    public static final Supplier<SimpleParticleType> GREEN_FLAME = register("green_flame");
    public static final Supplier<SimpleParticleType> RED_FLAME = register("red_flame");
    public static final Supplier<SimpleParticleType> BLACK_FLAME = register("black_flame");
    public static final Supplier<SimpleParticleType> BLOOD_KELP_SPORE = register("blood_kelp_spore");
    public static final Supplier<SimpleParticleType> WITCHS_CRADLE_SPORE = register("witchs_cradle_spore");
    public static final Supplier<SimpleParticleType> BOG_BLOSSOM_NECTAR = register("bog_blossom_nectar");
    public static final Supplier<SimpleParticleType> SOUL_EMBER = register("soul_ember");

    private static Supplier<SimpleParticleType> register(String name) {
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

    /**
     * Called during mod initialization to register every particle type.
     */
    public static void register(IEventBus modEventBus) {
        PARTICLES.register(modEventBus);
        AssortedDiscoveries.LOGGER.info("Registered particle types");
    }
}