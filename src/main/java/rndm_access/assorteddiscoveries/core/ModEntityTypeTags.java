package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModEntityTypeTags {
    public static final TagKey<EntityType<?>> BLUEBERRY_BUSH_IMMUNE_ENTITY_TYPES
            = of("blueberry_bush_immune_entity_types");
    public static final TagKey<EntityType<?>> CINDERSNAP_BERRY_BUSH_IMMUNE_ENTITY_TYPES
            = of("cindersnap_berry_bush_immune_entity_types");
    public static final TagKey<EntityType<?>> FROSTBITE_BERRY_BUSH_IMMUNE_ENTITY_TYPES
            = of("frostbite_berry_bush_immune_entity_types");
    public static final TagKey<EntityType<?>> WITCHS_CRADLE_IMMUNE_ENTITY_TYPES
            = of("witchs_cradle_immune_entity_types");

    private static TagKey<EntityType<?>> of(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, AssortedDiscoveries.makeModId(path));
    }
}
