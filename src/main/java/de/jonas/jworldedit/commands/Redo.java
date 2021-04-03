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

/**
 * Es wird der Befehl, mit dem sich rückgängig gemachte Änderungen wiederherstellen lassen, implementiert.
 */
@NotNull
public final class Redo implements CommandExecutor {
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
            0,
            0,
            args,
            "redo",
            PermissionType.REDO
        );

        if (util.check()) {
            return true;
        }

        // declare player
        final Player player = util.getPlayer();
        assert player != null;
        final Positions positions = JWorldEdit.POSITIONS.get(player.getUniqueId());

        if (positions.getNewSelections().isEmpty()) {
            player.sendMessage(JWorldEdit.getPrefix() + "Es gibt nichts, was wiederhergestellt werden kann!");
            return true;
        }

        // declare latest cuboid selection (1 before)
        final CuboidSelection latest = positions.getNewSelections().getLast();
        positions.initializeSelection();
        final CuboidSelection newest = positions.getSelection();

        // restore 1 older cuboid selection
        for (@NotNull final SelectionLocation selectionLocation : latest.getAllLocations()) {
            selectionLocation.getLocation().getBlock().setType(selectionLocation.getType());
        }

        // remove restored selection and add to new selections
        assert newest != null;
        positions.addOldSelection(newest);
        positions.getNewSelections().removeLast();

        player.sendMessage(JWorldEdit.getPrefix() + "Die letzte Aktion wurde wiederhergestellt!");
        return true;
    }
    //</editor-fold>
}
