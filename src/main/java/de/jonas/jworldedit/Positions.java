package de.jonas.jworldedit;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
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
        selection = new CuboidSelection(one, two);
    }

    private static boolean canInitializeSelection() {
        return one != null && two != null;
    }

}
