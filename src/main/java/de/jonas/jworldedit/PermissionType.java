package de.jonas.jworldedit;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public enum PermissionType {

    POS_1(
        "pos1"
    ),
    POS_2(
        "pos2"
    ),
    SET(
        "set"
    );

    //<editor-fold desc="LOCAL FIELDS">
    @NotNull
    @Getter
    private final String permission;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    PermissionType(
        @NotNull final String permission
    ) {
        this.permission = "jworldedit." + permission;
    }
    //</editor-fold>

}
