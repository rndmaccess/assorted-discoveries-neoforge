package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModSoundEvents {
    public static final SoundEvent BLOCK_MUSHROOM_BOUNCE = register("block.mushroom_bounce");

    private static SoundEvent register(String name) {
        SoundEvent sound = SoundEvent.createVariableRangeEvent(AssortedDiscoveries.makeModId(name));
        return Registry.register(BuiltInRegistries.SOUND_EVENT, sound.location(), sound);
    }

    public static void register() {
        AssortedDiscoveries.LOGGER.info("Registered sound events");
    }
}
