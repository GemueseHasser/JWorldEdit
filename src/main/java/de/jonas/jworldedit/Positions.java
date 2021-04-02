package de.jonas.jworldedit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

/**
 * In dieser Klasse werden die beiden {@link Location Locations} gespeichert, die der {@link Player Spieler} immer
 * wieder neu markieren kann. Zudem kann hier eine {@link CuboidSelection} aus diesen {@link Location Locations} erzeugt
 * werden.
 */
@NotNull
public final class Positions {

    //<editor-fold desc="CONSTANTS">
    /** Die Anzahl an Aktionen, die rückgängig gemacht werden können. */
    private static final int MAX_UNDO_REDO = 10;
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die letzten Aktionen. */
    @NotNull
    public final LinkedList<CuboidSelection> oldSelections = new LinkedList<>();
    /** Die neueren Aktionen, die bereits rückgängig gemacht wurden. */
    @NotNull
    public final LinkedList<CuboidSelection> newSelections = new LinkedList<>();
    /** Die erste {@link Location} der zwei markierten {@link Location Locations}. */
    @Nullable
    @Getter
    @Setter
    private Location one;
    /** Die zweite {@link Location} der zwei markierten {@link Location Locations}. */
    @Nullable
    @Getter
    @Setter
    private Location two;
    /**
     * Die {@link CuboidSelection}, die mithilfe der {@code #initializeSelection initializeSelection()} immer wieder neu
     * deklariert werden kann, da sich die {@link Location Locations} auch immer wieder ändern.*
     */
    @Nullable
    @Getter
    private CuboidSelection selection;
    //</editor-fold>

    /**
     * Initialisiert die {@link CuboidSelection} neu. Es werden aus den beiden angegebenen {@link Location Locations}
     * eine minimale und eine maximale {@link Location} erzeugt, woraus dann die {@link CuboidSelection} erzeugt wird.
     */
    public void initializeSelection() {
        // check positions
        if (!canInitializeSelection()) {
            System.out.println("One Position was null!");
            return;
        }

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
        this.selection = new CuboidSelection(min, max);
    }

    /**
     * Prüft, ob keine der beiden {@link Location Locations} null ist, damit die {@link CuboidSelection} erzeugt werden
     * kann.
     *
     * @return Wenn keine der beiden {@link Location Locations} null ist {@code true}, ansonsten {@code false}.
     */
    private boolean canInitializeSelection() {
        return this.one != null && this.two != null;
    }

    /**
     * Fügt eine bestimmte {@link CuboidSelection} zu der {@link LinkedList} der alten Aktionen hinzu.
     *
     * @param cuboidSelection Die {@link CuboidSelection}, die hinzugefügt wird.
     */
    public void addOldSelection(@NotNull final CuboidSelection cuboidSelection) {
        if (oldSelections.size() >= MAX_UNDO_REDO) {
            oldSelections.removeFirst();
        }
        oldSelections.addLast(cuboidSelection);
    }

    /**
     * Fügt eine bestimmte {@link CuboidSelection} zu der {@link LinkedList} der neuen Aktionen hinzu.
     *
     * @param cuboidSelection Die {@link CuboidSelection}, die hinzugefügt wird.
     */
    public void addNewSelection(@NotNull final CuboidSelection cuboidSelection) {
        if (newSelections.size() >= MAX_UNDO_REDO) {
            newSelections.removeFirst();
        }
        newSelections.addLast(cuboidSelection);
    }

}
