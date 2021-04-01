package de.jonas.jworldedit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@NotNull
public final class Positions {

    @Nullable
    @Getter
    @Setter
    private static Location one;

    @Nullable
    @Getter
    @Setter
    private static Location two;

    @Nullable
    @Getter
    private static CuboidSelection selection;

    public static void initializeSelection() {
        if (!canInitializeSelection()) {
            System.out.println("One Position was null!");
            return;
        }
        assert one != null;
        assert two != null;

        final double minX;
        final double maxX;
        final double minY;
        final double maxY;
        final double minZ;
        final double maxZ;

        minX = Math.min(one.getX(), two.getX());
        maxX = Math.max(one.getX(), two.getX());
        minY = Math.min(one.getY(), two.getY());
        maxY = Math.max(one.getY(), two.getY());
        minZ = Math.min(one.getZ(), two.getZ());
        maxZ = Math.max(one.getZ(), two.getZ());

        final World world = one.getWorld();

        final Location min = new Location(world, minX, minY, minZ);
        final Location max = new Location(world, maxX, maxY, maxZ);

        selection = new CuboidSelection(min, max);
    }

    private static boolean canInitializeSelection() {
        return one != null && two != null;
    }

}
