package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.CuboidSelection;
import de.jonas.jworldedit.PermissionType;
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
        // check command
        final CommandUtil util = new CommandUtil(sender, 1, args, "set <material>", PermissionType.SET);

        if (util.check()) {
            return true;
        }

        // declare player
        final Player player = util.getPlayer();

        // declare material
        final String arg = args[0].toUpperCase();
        final Material material;

        assert player != null;
        try {
            material = Material.valueOf(arg);
        } catch (@NotNull final IllegalArgumentException ignored) {
            player.sendMessage(JWorldEdit.getPrefix() + "Bitte wähle ein gültiges Material!");
            return true;
        }

        // initialize cuboid-selection
        Positions.initializeSelection();

        // declare cuboid-selection
        final CuboidSelection selection = Positions.getSelection();

        int count = 0;

        // set materials
        assert selection != null;
        for (final Location location : selection.getAllLocations()) {
            location.getBlock().setType(material);
            count++;
        }

        player.sendMessage(JWorldEdit.getPrefix() + "Es wurden alle " + count + " Blöcke gesetzt!");
        return true;
    }
}
