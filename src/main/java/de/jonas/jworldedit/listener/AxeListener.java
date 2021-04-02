package de.jonas.jworldedit.listener;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.Positions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Es wird mithilfe dieses {@link Listener} das Anklicken mit einer Axt in der Hand eines Blocks verarbeitet.
 */
public final class AxeListener implements Listener {
    //<editor-fold desc="implementation">
    @EventHandler
    public void onClickWithAxe(@NotNull final PlayerInteractEvent e) {
        // declare item in hand
        final ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();

        // check if player has a wooden axe in hand
        if (!hand.getType().equals(Material.WOODEN_AXE)) {
            return;
        }

        // check if player clicks with main-hand
        if (!Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            return;
        }

        // cancel event
        e.setCancelled(true);

        // declare clicked block
        final Block block = e.getClickedBlock();

        if (block == null) {
            return;
        }

        // declare location
        final Location location = block.getLocation();

        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            // set position 1
            JWorldEdit.POSITIONS.get(e.getPlayer().getUniqueId()).setOne(location);
            e.getPlayer().sendMessage(JWorldEdit.getPrefix() + "Position 1 gesetzt!");
        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            // set position 2
            JWorldEdit.POSITIONS.get(e.getPlayer().getUniqueId()).setTwo(location);
            e.getPlayer().sendMessage(JWorldEdit.getPrefix() + "Position 2 gesetzt!");
        }
    }
    //</editor-fold>
}
