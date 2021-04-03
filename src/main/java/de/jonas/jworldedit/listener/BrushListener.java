package de.jonas.jworldedit.listener;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.BrushItem;
import de.jonas.jworldedit.BrushType;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import java.util.Objects;

/**
 * Mithilfe des {@link BrushListener} wird das Nutzen eines mit einer Brush belegten Items verarbeitet.
 */
@NotNull
public final class BrushListener implements Listener {

    //<editor-fold desc="CONSTANTS">
    /** Der Multiplikator der View-Distance des auslösenden {@link Player Spieler}. */
    private static final int VIEW_DISTANCE_MULTIPLY = 16;
    //</editor-fold>


    //<editor-fold desc="implementation">
    @EventHandler
    public void onClickWithItem(@NotNull final PlayerInteractEvent e) {
        // check if player clicks with right
        if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
            return;
        }
        // declare clicked item
        final ItemStack clicked = e.getItem();

        // check clicked item
        if (clicked == null) {
            return;
        }

        // cancel event
        e.setCancelled(true);

        // declare player
        final Player player = e.getPlayer();

        // check all brushed items on the server
        for (final BrushItem item : JWorldEdit.BRUSH_ITEMS) {
            // check which brushed item was use
            if (!item.getStack().equals(clicked)) {
                continue;
            }

            // declare eye position and block-iterator
            final Location eyePos = player.getEyeLocation();
            BlockIterator raytracer = new BlockIterator(
                eyePos,
                0.0D,
                player.getClientViewDistance() * VIEW_DISTANCE_MULTIPLY
            );

            // do stuff for normal brush
            if (item.getType().equals(BrushType.NORMAL)) {
                while (raytracer.hasNext()) {
                    Location location = raytracer.next().getLocation();
                    if (player.getWorld().getBlockAt(location).getType() != Material.AIR
                        && player.getWorld().getBlockAt(location).getType() != Material.CAVE_AIR
                        && player.getWorld().getBlockAt(location).getType() != Material.VOID_AIR
                    ) {
                        // place blocks
                        placeBlocks(location, item.getMaterial(), item.getSize());
                        return;
                    }
                }
                return;
            }

            // do stuff for tunnel brush
            if (item.getType().equals(BrushType.TUNNEL)) {
                while (raytracer.hasNext()) {
                    Location location = raytracer.next().getLocation();
                    if (player.getWorld().getBlockAt(location).getType() == Material.AIR
                        && player.getWorld().getBlockAt(location).getType() == Material.CAVE_AIR
                        && player.getWorld().getBlockAt(location).getType() == Material.VOID_AIR
                    ) {
                        continue;
                    }
                    // bomb tunnel
                    tunnel(location, item.getSize());
                }
                return;
            }
        }
    }
    //</editor-fold>

    /**
     * Es wird ein Tunnel ab einer bestimmten {@link Location} gesprengt.
     *
     * @param location Die {@link Location}, ab der der Tunnel gesprengt wird.
     * @param size     Die Größe (Durchmesser) des Tunnels.
     */
    private void tunnel(
        @NotNull final Location location,
        @Range(from = 0, to = Integer.MAX_VALUE) final int size
    ) {
        Objects.requireNonNull(location.getWorld()).createExplosion(location, size);
    }

    /**
     * Es wird ein Viereck an Blöcken um eine bestimmte {@link Location} gebaut.
     *
     * @param location Die {@link Location}, um die die Blöcke platziert werden.
     * @param material Das {@link Material}, aus dem die platzierten Blöcke bestehen.
     * @param size     Die Größe des Vierecks (Quaders), welches platziert wird.
     */
    private void placeBlocks(
        @NotNull final Location location,
        @NotNull final Material material,
        @Range(from = 0, to = Integer.MAX_VALUE) final int size
    ) {
        location.getBlock().setType(material);
        for (int x = location.getBlockX() - size / 2; x <= location.getBlockX() + size * 2; x++) {
            for (int y = location.getBlockY() - size / 2; y <= location.getBlockY() + size * 2; y++) {
                for (int z = location.getBlockZ() - size / 2; z <= location.getBlockZ() + size * 2; z++) {
                    final World world = location.getWorld();
                    assert world != null;
                    world.getBlockAt(x, y, z).setType(material);
                }
            }
        }
    }

}
