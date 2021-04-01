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

public class AxeListener implements Listener {

    @EventHandler
    public void onClickWithAxe(@NotNull final PlayerInteractEvent e) {
        final ItemStack hand = e.getPlayer().getInventory().getItemInMainHand();

        if (!hand.getType().equals(Material.WOODEN_AXE)) {
            return;
        }

        if (!Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            return;
        }

        e.setCancelled(true);

        final Block block = e.getClickedBlock();

        if (block == null) {
            return;
        }

        final Location location = block.getLocation();

        if (e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            // set position 1
            Positions.setOne(location);
            e.getPlayer().sendMessage(JWorldEdit.getPrefix() + "Position 1 gesetzt!");
        } else if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            // set position 2
            Positions.setTwo(location);
            e.getPlayer().sendMessage(JWorldEdit.getPrefix() + "Position 2 gesetzt!");
        }
    }

}
