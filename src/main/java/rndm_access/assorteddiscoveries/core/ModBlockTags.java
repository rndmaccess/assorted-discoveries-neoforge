package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import rndm_access.assorteddiscoveries.AssortedDiscoveries;

public final class ModBlockTags {
    public static final TagKey<Block> END_BONE_MEALABLE_BLOCKS = of("end_bone_mealable_blocks");
    public static final TagKey<Block> SNAPDRAGON_PLANTABLE_ON = of("snapdragon_plantable_on");
    public static final TagKey<Block> ENDER_GRASS_PLANTABLE_ON = of("ender_grass_plantable_on");
    public static final TagKey<Block> CINDERSNAP_BERRY_BUSH_PLANTABLE_ON
            = of("cindersnap_berry_bush_plantable_on");
    public static final TagKey<Block> FROSTBITE_BERRY_BUSH_PLANTABLE_ON
            = of("frostbite_berry_bush_plantable_on");
    public static final TagKey<Block> OVERWORLD_PLANTER_BOXES = of("overworld_planter_boxes");
    public static final TagKey<Block> NETHER_PLANTER_BOXES = of("nether_planter_boxes");
    public static final TagKey<Block> WOODEN_WALLS = of("wooden_walls");
    public static final TagKey<Block> SNOW_SLABS = of("snow_slabs");
    public static final TagKey<Block> SNOW_STAIRS = of("snow_stairs");
    public static final TagKey<Block> SNOW_WALLS = of("snow_walls");

    private static TagKey<Block> of(String name) {
        return TagKey.create(Registries.BLOCK, AssortedDiscoveries.makeModId(name));
    }
}
