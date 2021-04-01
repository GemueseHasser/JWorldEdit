package de.jonas.jworldedit;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NotNull
public final class CuboidSelection {

    @NotNull
    private final Location min;

    @NotNull
    private final Location max;

    @NotNull
    @Getter
    private final List<Location> allLocations;

    public CuboidSelection(
        @NotNull final Location min,
        @NotNull final Location max
    ) {
        this.min = min;
        this.max = max;

        this.allLocations = new ArrayList<>();

        if (!isCorrect()) {
            System.out.println("Incorrect Locations for cuboid-selcetion. Is the min position higher than the max "
                + "position? Are both positions in the same world?");
            return;
        }

        final World world = min.getWorld();

        // declare all locations
        for (double x = min.getX(); x < max.getX() + 1; x++) {
            for (double y = min.getY(); y < max.getY() + 1; y++) {
                for (double z = min.getZ(); z < max.getZ() + 1; z++) {
                    allLocations.add(new Location(world, x, y, z));
                }
            }
        }
    }

    private boolean isCorrect() {
        return min.getX() < max.getX() && min.getY() < max.getY() && min.getZ() < max.getZ() && Objects.equals(
            min.getWorld(),
            max.getWorld()
        );
    }

}
