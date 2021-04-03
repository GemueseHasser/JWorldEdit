package de.jonas.jworldedit;

import org.jetbrains.annotations.NotNull;

/**
 * Ein {@link BrushType} ist ein spezifischer Typ einer Brush, die man auf Items legen kann.
 */
public enum BrushType {
    /** Der {@link BrushType} für die normale Brush. */
    @NotNull
    NORMAL,
    /** Der {@link BrushType} für die Tunnel Brush. */
    @NotNull
    TUNNEL
}
