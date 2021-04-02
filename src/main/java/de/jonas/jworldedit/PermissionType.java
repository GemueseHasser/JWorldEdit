package de.jonas.jworldedit;

import de.jonas.jworldedit.commands.Pos1;
import de.jonas.jworldedit.commands.Pos2;
import de.jonas.jworldedit.commands.Set;
import de.jonas.jworldedit.listener.AxeListener;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Ein {@link PermissionType} beinhaltet für einen Befehl die passende Permission.
 */
public enum PermissionType {

    /** Der {@link PermissionType} für den {@link Pos1 Pos1-Command}. */
    POS_1(
        "pos1"
    ),
    /** Der {@link PermissionType} für den {@link Pos2 Pos2-Command}. */
    POS_2(
        "pos2"
    ),
    /** Der {@link PermissionType} für den {@link AxeListener}. */
    AXE(
        "axe"
    ),
    /** Der {@link PermissionType} für den {@link Set Set-Command}. */
    SET(
        "set"
    ),
    REPLACE(
        "replace"
    ),
    EXPAND(
        "expand"
    );

    //<editor-fold desc="LOCAL FIELDS">
    /** Die jeweilige permission. */
    @NotNull
    @Getter
    private final String permission;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Mithilfe dieses Konstruktors wird ein neuer und vollständig unabhängiger {@link PermissionType} erzeugt. Es wird
     * mithilfe einer halben Permission eine fertige Permission erzeugt.
     *
     * @param permission Die halbe permission, woraus die fertige Permission erzeugt wird.
     */
    PermissionType(
        @NotNull final String permission
    ) {
        this.permission = "jworldedit." + permission;
    }
    //</editor-fold>

}
