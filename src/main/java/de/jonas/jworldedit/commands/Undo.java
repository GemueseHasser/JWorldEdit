package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.CuboidSelection;
import de.jonas.jworldedit.PermissionType;
import de.jonas.jworldedit.Positions;
import de.jonas.jworldedit.SelectionLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Undo implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandUtil util = new CommandUtil(
            sender,
            0,
            0,
            args,
            "undo",
            PermissionType.UNDO
        );

        if (util.check()) {
            return true;
        }

        final Player player = util.getPlayer();
        assert player != null;
        final Positions positions = JWorldEdit.POSITIONS.get(player.getUniqueId());

        if (positions.oldSelections.isEmpty()) {
            player.sendMessage(JWorldEdit.getPrefix() + "Es gibt nichts, was rückgängig gemacht werden kann!");
            return true;
        }

        final CuboidSelection latest = positions.oldSelections.getLast();

        for (@NotNull final SelectionLocation selectionLocation : latest.getAllLocations()) {
            selectionLocation.getLocation().getBlock().setType(selectionLocation.getType());
        }

        positions.oldSelections.removeLast();

        player.sendMessage(JWorldEdit.getPrefix() + "Die letzte Aktion wurde rückgängig gemacht!");
        return true;
    }
}
