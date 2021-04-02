package de.jonas.jworldedit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * Eine {@link SelectionLocation} besteht aus einer {@link Location} und einem {@link Material}.
 */
@RequiredArgsConstructor
public class SelectionLocation {
    //<editor-fold desc="LOCAL FIELDS">
    /** Die {@link Location} der {@link SelectionLocation}. */
    @NotNull
    @Getter
    private final Location location;
    /** Das {@link Material} der {@link SelectionLocation}. */
    @NotNull
    @Getter
    private final Material type;
    //</editor-fold>
}
