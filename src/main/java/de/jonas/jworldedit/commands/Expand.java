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

import static de.jonas.jworldedit.CommandUtil.NULL_POSITION;

/**
 * Es wird der Befehl, um den zuvor markierten Bereich zu erweitern, implementiert.
 */
public class Expand implements CommandExecutor {
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
            1,
            1,
            args,
            "expand <vert>",
            PermissionType.EXPAND
        );

        if (util.check()) {
            return true;
        }

        // declare player
        final Player player = util.getPlayer();

        // check command args
        assert player != null;
        if (!args[0].equalsIgnoreCase("vert")) {
            player.sendMessage(util.getWrongCommand());
        }

        // declare positions
        final Positions positions = JWorldEdit.POSITIONS.get(player.getUniqueId());

        // check if positions are not null
        if (positions.getOne() == null || positions.getTwo() == null) {
            player.sendMessage(JWorldEdit.getPrefix() + NULL_POSITION);
            return true;
        }

        // expand vert
        positions.getOne().setY(0);
        positions.getTwo().setY(255);

        player.sendMessage(JWorldEdit.getPrefix() + "Der Bereich wurde erweitert!");
        return true;
    }
    //</editor-fold>
}
