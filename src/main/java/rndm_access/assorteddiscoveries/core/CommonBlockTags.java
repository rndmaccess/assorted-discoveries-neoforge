package rndm_access.assorteddiscoveries.core;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class CommonBlockTags {
    public static final TagKey<Block> MYCELIUM = bind("mycelium");
    public static final TagKey<Block> PODZOL = bind("podzol");

    private static TagKey<Block> bind(String name) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", name));
    }
}
