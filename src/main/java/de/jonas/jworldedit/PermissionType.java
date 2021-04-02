package de.jonas.jworldedit;

import de.jonas.jworldedit.commands.Expand;
import de.jonas.jworldedit.commands.Pos1;
import de.jonas.jworldedit.commands.Pos2;
import de.jonas.jworldedit.commands.Redo;
import de.jonas.jworldedit.commands.Replace;
import de.jonas.jworldedit.commands.Set;
import de.jonas.jworldedit.commands.Undo;
import de.jonas.jworldedit.listener.AxeListener;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

/**
 * Ein {@link PermissionType} beinhaltet für einen Befehl die passende Permission.
 */
@NotNull
public enum PermissionType {

    /** Der {@link PermissionType} für den {@link Pos1 Pos1-Command}. */
    @NotNull
    POS_1(
        "pos1"
    ),
    /** Der {@link PermissionType} für den {@link Pos2 Pos2-Command}. */
    @NotNull
    POS_2(
        "pos2"
    ),
    /** Der {@link PermissionType} für den {@link AxeListener}. */
    @NotNull
    AXE(
        "axe"
    ),
    /** Der {@link PermissionType} für den {@link Set Set-Command}. */
    @NotNull
    SET(
        "set"
    ),
    /** Der {@link PermissionType} für den {@link Replace Replace-Command}. */
    @NotNull
    REPLACE(
        "replace"
    ),
    /** Der {@link PermissionType} für den {@link Expand Expand-Command}. */
    @NotNull
    EXPAND(
        "expand"
    ),
    /** Der {@link PermissionType} für den {@link Undo Undo-Command}. */
    @NotNull
    UNDO(
        "undo"
    ),
    /** Der {@link PermissionType} für den {@link Redo Redo-Command} */
    @NotNull
    REDO(
        "redo"
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
    @NotNull
    PermissionType(
        @NotNull final String permission
    ) {
        this.permission = "jworldedit." + permission;
    }
    //</editor-fold>

}
