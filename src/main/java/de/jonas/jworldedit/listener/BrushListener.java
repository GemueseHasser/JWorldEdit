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

public final class BrushListener implements Listener {

    @EventHandler
    public void onClickWithItem(@NotNull final PlayerInteractEvent e) {
        if (!(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.RIGHT_CLICK_AIR))) {
            return;
        }
        final ItemStack clicked = e.getItem();

        if (clicked == null) {
            return;
        }

        e.setCancelled(true);

        final Player player = e.getPlayer();
        for (final BrushItem item : JWorldEdit.BRUSH_ITEMS) {
            if (!item.getStack().equals(clicked)) {
                continue;
            }

            final Location eyePos = player.getEyeLocation();
            BlockIterator raytracer = new BlockIterator(eyePos, 0.0D, player.getClientViewDistance() * 16);

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

    private void tunnel(
        @NotNull final Location location,
        @Range(from = 0, to = Integer.MAX_VALUE) final int size
    ) {
        Objects.requireNonNull(location.getWorld()).createExplosion(location, size);
    }

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
