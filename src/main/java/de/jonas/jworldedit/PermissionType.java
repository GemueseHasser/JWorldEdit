package de.jonas.jworldedit;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;

public enum PermissionType {

    POS_1(
        "pos1"
    ),
    POS_2(
        "pos2"
    );

    @NotNull
    @Getter
    private final String permission;

    PermissionType(
        @NotNull final String permission
    ) {
        this.permission = "jworldedit" + permission;
    }

}
