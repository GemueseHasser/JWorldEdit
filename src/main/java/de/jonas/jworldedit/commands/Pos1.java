package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.PermissionType;
import de.jonas.jworldedit.Positions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Pos1 implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(JWorldEdit.getPrefix() + JWorldEdit.NO_PLAYER);
            return true;
        }

        final Player player = (Player) sender;

        if (!player.hasPermission(PermissionType.POS_1.getPermission())) {
            player.sendMessage(JWorldEdit.getPrefix() + JWorldEdit.NO_PERMISSIONS);
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(JWorldEdit.getPrefix() + "Bitte benutze //pos1");
            return true;
        }

        Positions.setOne(player.getLocation());
        player.sendMessage(JWorldEdit.getPrefix() + "Position 1 gesetzt!");
        return true;
    }
}
