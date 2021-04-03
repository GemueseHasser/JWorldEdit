package de.jonas.jworldedit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

/**
 * Ein {@link BrushItem} besteht aus einem {@link ItemStack}, einem {@link Material} und einer Größe.
 */
@RequiredArgsConstructor
public final class BrushItem {
    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link ItemStack}, auf den die Brush kommt. */
    @NotNull
    @Getter
    private final ItemStack stack;
    /** Das {@link Material}, welches durch die Brush gesetzt wird. */
    @NotNull
    @Getter
    private final Material material;
    /** Die Größe der Brush. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    @Getter
    private final int size;
    //</editor-fold>
}
