package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.Positions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Pos2 implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandUtil util = new CommandUtil(sender, 0, args, "pos2");

        if (util.check()) {
            return true;
        }

        final Player player = util.getPlayer();

        assert player != null;
        Positions.setTwo(player.getLocation());
        player.sendMessage(JWorldEdit.getPrefix() + "Position 2 gesetzt!");
        return true;
    }
}
