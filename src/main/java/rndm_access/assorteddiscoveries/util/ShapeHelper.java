package rndm_access.assorteddiscoveries.util;

import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.List;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public final class ShapeHelper {
    private ShapeHelper() {}

    public static HashMap<Direction, VoxelShape> makeShapeRotMap(VoxelShape... northShapes) {
        HashMap<Direction, VoxelShape> shapes = new HashMap<>();
        VoxelShape northShape = combineShapes(northShapes);

        for(Direction direction : Direction.values()) {
            if(direction.getAxis().isHorizontal()) {
                shapes.put(direction, rotate(northShape, direction));
            }
        }
        return shapes;
    }

    public static List<VoxelShape> makeShapeRotList(VoxelShape... northShapes) {
        ImmutableList.Builder<VoxelShape> builder = ImmutableList.builder();
        VoxelShape northShape = combineShapes(northShapes);

        for(Direction direction : Direction.values()) {
            if(direction.getAxis().isHorizontal()) {
                builder.add(rotate(northShape, direction));
            }
        }
        return builder.build();
    }

    private static VoxelShape combineShapes(VoxelShape... northShapes) {
        VoxelShape northShape = Shapes.empty();

        for (VoxelShape temp : northShapes) {
            northShape = Shapes.or(northShape, temp);
        }
        return northShape;
    }

    private static VoxelShape rotate(VoxelShape source, Direction direction) {
        VoxelShape rotatedShape = Shapes.empty();

        for(AABB box : source.toAabbs()) {
            VoxelShape tempShape = rotateValues(direction, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
            rotatedShape = Shapes.or(tempShape, rotatedShape);
        }
        return rotatedShape;
    }

    private static VoxelShape rotateValues(Direction direction, double minX, double minY, double minZ, double maxX,
                                           double maxY, double maxZ) {
        double tempMinX = minX;
        double tempMaxX = maxX;
        double tempMinZ = minZ;

        switch (direction) {
            case EAST -> {
                minX = 1.0F - maxZ;
                minZ = tempMinX;
                maxX = 1.0F - tempMinZ;
                maxZ = tempMaxX;
            }
            case SOUTH -> {
                minX = 1.0F - maxX;
                minZ = 1.0F - maxZ;
                maxX = 1.0F - tempMinX;
                maxZ = 1.0F - tempMinZ;
            }
            case WEST -> {
                minX = minZ;
                minZ = 1.0F - maxX;
                maxX = maxZ;
                maxZ = 1.0F - tempMinX;
            }
            default -> {}
        }
        return Shapes.box(minX, minY, minZ, maxX, maxY, maxZ);
    }
}
