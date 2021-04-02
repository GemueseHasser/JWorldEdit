package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.PermissionType;
import de.jonas.jworldedit.Positions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

/**
 * Es wird der Befehl, um die erste Position mithilfe eines Befehls zu setzen, implementiert.
 */
public final class Pos1 implements CommandExecutor {
    //<editor-fold desc="implementation">
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        // check command
        CommandUtil util = new CommandUtil(sender, 0, 0, args, "pos1", PermissionType.POS_1);

        if (util.check()) {
            return true;
        }

        // declare player
        final Player player = util.getPlayer();

        // set position 1
        assert player != null;
        JWorldEdit.POSITIONS.get(player.getUniqueId()).setOne(player.getLocation().clone().subtract(1, 1, 1));
        player.sendMessage(JWorldEdit.getPrefix() + "Position 1 gesetzt!");
        return true;
    }
    //</editor-fold>
}
