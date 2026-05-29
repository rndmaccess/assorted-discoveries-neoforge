package rndm_access.assorteddiscoveries.block.state;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModBlockStateProperties {
    public static final IntegerProperty STACK_SIZE = IntegerProperty.create("stack_size", 1, 3);
    public static final IntegerProperty LENGTH = IntegerProperty.create("length", 0, 16);
    public static final BooleanProperty IS_SITTING = BooleanProperty.create("is_sitting");
}
