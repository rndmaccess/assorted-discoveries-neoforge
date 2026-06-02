package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS
            = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, AssortedDiscoveries.MOD_ID);

    public static final Holder<SoundEvent> BLOCK_MUSHROOM_BOUNCE = register("block.mushroom_bounce");

    private static Holder<SoundEvent> register(String name) {
        return SOUND_EVENTS.register(name, SoundEvent::createVariableRangeEvent);
    }

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
        AssortedDiscoveries.LOGGER.info("Registered sound events");
    }
}
