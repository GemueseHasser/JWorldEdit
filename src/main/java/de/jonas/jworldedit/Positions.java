package de.jonas.jworldedit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * In dieser Klasse werden die beiden {@link Location Locations} gespeichert, die der {@link Player Spieler} immer
 * wieder neu markieren kann. Zudem kann hier eine {@link CuboidSelection} aus diesen {@link Location Locations} erzeugt
 * werden.
 */
@NotNull
public final class Positions {

    //<editor-fold desc="STATIC FIELDS">
    /** Die erste {@link Location} der zwei markierten {@link Location Locations}. */
    @Nullable
    @Getter
    @Setter
    private static Location one;
    /** Die zweite {@link Location} der zwei markierten {@link Location Locations}. */
    @Nullable
    @Getter
    @Setter
    private static Location two;
    /**
     * Die {@link CuboidSelection}, die mithilfe der {@code #initializeSelection initializeSelection()} immer wieder neu
     * deklariert werden kann, da sich die {@link Location Locations} auch immer wieder ändern.*
     */
    @Nullable
    @Getter
    private static CuboidSelection selection;
    //</editor-fold>

    /**
     * Initialisiert die {@link CuboidSelection} neu. Es werden aus den beiden angegebenen {@link Location Locations}
     * eine minimale und eine maximale {@link Location} erzeugt, woraus dann die {@link CuboidSelection} erzeugt wird.
     */
    public static void initializeSelection() {
        // check positions
        if (!canInitializeSelection()) {
            System.out.println("One Position was null!");
            return;
        }
        assert one != null;
        assert two != null;

        // filter positions and declare min and max positions
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

        // declare world
        final World world = one.getWorld();

        // declare min and max positions
        final Location min = new Location(world, minX, minY, minZ);
        final Location max = new Location(world, maxX, maxY, maxZ);

        // declare cuboid-selection
        selection = new CuboidSelection(min, max);
    }

    /**
     * Prüft, ob keine der beiden {@link Location Locations} null ist, damit die {@link CuboidSelection} erzeugt werden
     * kann.
     *
     * @return Wenn keine der beiden {@link Location Locations} null ist {@code true}, ansonsten {@code false}.
     */
    private static boolean canInitializeSelection() {
        return one != null && two != null;
    }

}
