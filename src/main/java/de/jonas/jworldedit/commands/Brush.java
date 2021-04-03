package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.BrushItem;
import de.jonas.jworldedit.BrushType;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.PermissionType;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static de.jonas.jworldedit.CommandUtil.WRONG_MATERIAL_MESSAGE;

/**
 * Es wird der Befehl, mit dem man Items mit einer Brush belegen kann, implementiert.
 */
@NotNull
public final class Brush implements CommandExecutor {
    //<editor-fold desc="implementation">
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        // check command
        final CommandUtil util = new CommandUtil(
            sender,
            2,
            3,
            args,
            "brush <type> (<material>) <size>",
            PermissionType.BRUSH
        );

        if (util.check()) {
            return true;
        }

        // declare player
        final Player player = util.getPlayer();

        final Material material;
        final BrushType type;
        final int size;

        assert player != null;
        if (args.length == 2) {
            if (!args[0].equalsIgnoreCase("tunnel")) {
                player.sendMessage(JWorldEdit.getPrefix() + util.getWrongCommand());
                return true;
            }
            material = Material.AIR;
            type = BrushType.TUNNEL;
        } else {
            if (!args[0].equalsIgnoreCase("normal")) {
                player.sendMessage(JWorldEdit.getPrefix() + util.getWrongCommand());
                return true;
            }

            material = JWorldEdit.getInstance().getMaterial(args[1]);

            if (material == null) {
                player.sendMessage(JWorldEdit.getPrefix() + WRONG_MATERIAL_MESSAGE);
                return true;
            }
            type = BrushType.NORMAL;
        }

        try {
            size = Integer.parseInt(args[args.length - 1]);
        } catch (@NotNull final IllegalArgumentException ignored) {
            player.sendMessage(JWorldEdit.getPrefix() + util.getWrongCommand());
            return true;
        }

        final BrushItem brushItem = new BrushItem(
            player.getInventory().getItemInMainHand(),
            material,
            size,
            type
        );

        JWorldEdit.BRUSH_ITEMS.removeIf(item -> item.getStack().equals(player.getInventory().getItemInMainHand()));
        JWorldEdit.BRUSH_ITEMS.add(brushItem);

        player.sendMessage(JWorldEdit.getPrefix() + "Dein Item wurde mit einer Brush versehen!");
        return true;
    }
    //</editor-fold>
}
