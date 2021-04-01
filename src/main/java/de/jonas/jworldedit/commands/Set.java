package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.CuboidSelection;
import de.jonas.jworldedit.Positions;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Set implements CommandExecutor {
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandUtil util = new CommandUtil(sender, 1, args, "set");

        if (util.check()) {
            return true;
        }

        final Player player = util.getPlayer();

        final String arg = args[0].toUpperCase();

        assert player != null;
        if (Material.valueOf(arg) == Material.AIR) {
            player.sendMessage(JWorldEdit.getPrefix() + "Bitte gib ein gültiges Material an!");
            return true;
        }

        final Material material = Material.valueOf(arg);

        Positions.initializeSelection();

        final CuboidSelection selection = Positions.getSelection();

        int count = 0;

        assert selection != null;
        for (final Location location : selection.getAllLocations()) {
            location.getBlock().setType(material);
            count++;
        }

        player.sendMessage(JWorldEdit.getPrefix() + "Es wurden alle " + count + " Blöcke gesetzt!");
        return true;
    }
}
