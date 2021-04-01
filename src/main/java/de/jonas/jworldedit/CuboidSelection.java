package de.jonas.jworldedit;

import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Eine {@link CuboidSelection} ist ein quadratisch markierter Bereich (welcher im drei-dimensionalen Raum liegt).
 * Dieser Bereich wird mithilfe einer minimal kleinen und einer maximal großen {@link Location} erzeugt. Auch alle
 * {@link Location Locations}, die sich zwischen diesen {@link Location Locations} befindet, also praktisch in dem
 * markierten Bereich, werden gespeichert, damit man diesen Bereich später noch editieren kann.
 */
@NotNull
public final class CuboidSelection {

    //<editor-fold desc="LOCAL FIELDS">
    /** Die kleinste {@link Location}, die in dieser {@link CuboidSelection} vorkommt. */
    @NotNull
    private final Location min;
    /** Die größte {@link Location}, die in dieser {@link CuboidSelection} vorkommt. */
    @NotNull
    private final Location max;
    /** Alle {@link Location Locations}, die zwischen der kleinsten und größten {@link Location} liegen. */
    @NotNull
    @Getter
    private final List<Location> allLocations;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue und vollständig unabhängige {@link CuboidSelection}. Es wird nur die kleinste und größte {@link
     * Location} angegeben, die sich in diesem quadrat befindet. Mithilfe dieser {@link Location Locations} werden dann
     * auch alle {@link Location Locations}, die dazwischen liegen berechnet und gespeichert.
     *
     * @param min Die kleinste {@link Location}, die in dieser {@link CuboidSelection} vorkommt.
     * @param max Die größte {@link Location}, die in dieser {@link CuboidSelection} vorkommt.
     */
    public CuboidSelection(
        @NotNull final Location min,
        @NotNull final Location max
    ) {
        this.min = min;
        this.max = max;

        this.allLocations = new ArrayList<>();

        // check if locations are right
        if (!isCorrect()) {
            System.out.println("Incorrect Locations for cuboid-selcetion. Is the min position higher than the max "
                + "position? Are both positions in the same world?");
            return;
        }

        // declare world
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
    //</editor-fold>


    /**
     * Prüft, ob die angegebenen zwei {@link Location Locations} (die kleinste und größte) passen und verarbeitet werden
     * können.
     *
     * @return Wenn die angegebenen {@link Location Locations} zusammen passen {@code true}, ansonsten {@code false}.
     */
    private boolean isCorrect() {
        return min.getX() <= max.getX() && min.getY() <= max.getY() && min.getZ() <= max.getZ() && Objects.equals(
            min.getWorld(),
            max.getWorld()
        );
    }

}
