package de.jonas.jworldedit.listener;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.BrushItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.BlockIterator;
import org.jetbrains.annotations.NotNull;

public final class BrushListener implements Listener {

    @EventHandler
    public void onClickWithItem(@NotNull final PlayerInteractEvent e) {
        if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
            return;
        }
        final ItemStack clicked = e.getItem();
        final Player player = e.getPlayer();
        for (final BrushItem item : JWorldEdit.BRUSH_ITEMS) {
            if (!item.getStack().equals(clicked)) {
                continue;
            }
            final Location eyePos = player.getEyeLocation();
            BlockIterator raytracer = new BlockIterator(eyePos, 0.0D, player.getClientViewDistance() * 16);
            while (raytracer.hasNext()) {
                Location location = raytracer.next().getLocation();
                if (player.getWorld().getBlockAt(location).getType() != Material.AIR && player.getWorld().getBlockAt(
                    location).getType() != Material.CAVE_AIR && player.getWorld().getBlockAt(location).getType() != Material.VOID_AIR) {
                    player.getWorld().createExplosion(location, item.getSize());
                    return;
                }
            }
        }
    }

}
