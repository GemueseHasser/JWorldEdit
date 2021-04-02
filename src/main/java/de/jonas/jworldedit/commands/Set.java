package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.CuboidSelection;
import de.jonas.jworldedit.PermissionType;
import de.jonas.jworldedit.Positions;
import de.jonas.jworldedit.SelectionLocation;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static de.jonas.jworldedit.CommandUtil.NULL_POSITION;
import static de.jonas.jworldedit.CommandUtil.WRONG_MATERIAL_MESSAGE;

/**
 * Es wird der Befehl, um den quadratischen Bereich zwischen den zuvor gesetzten Positionen mit einem bestimmten
 * Material auszufüllen, implementiert.
 */
@NotNull
public final class Set implements CommandExecutor {
    //<editor-fold desc="implementation">
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        // check command
        final CommandUtil util = new CommandUtil(sender, 1, 1, args, "set <material>", PermissionType.SET);

        if (util.check()) {
            return true;
        }

        // declare player
        final Player player = util.getPlayer();

        // declare material
        final Material material = JWorldEdit.getInstance().getMaterial(args[0]);

        assert player != null;
        if (material == null) {
            player.sendMessage(JWorldEdit.getPrefix() + WRONG_MATERIAL_MESSAGE);
            return true;
        }

        // initialize cuboid-selection
        final Positions positions = JWorldEdit.POSITIONS.get(player.getUniqueId());

        // check if positions are not null
        if (positions.getOne() == null || positions.getTwo() == null) {
            player.sendMessage(JWorldEdit.getPrefix() + NULL_POSITION);
            return true;
        }

        positions.initializeSelection();

        // declare cuboid-selection
        final CuboidSelection selection = positions.getSelection();

        assert selection != null;
        positions.addOldSelection(selection);

        int count = 0;

        // set materials
        for (@NotNull final SelectionLocation selectionLocation : selection.getAllLocations()) {
            selectionLocation.getLocation().getBlock().setType(material);
            count++;
        }

        player.sendMessage(JWorldEdit.getPrefix() + "Es wurden alle " + count + " Blöcke gesetzt!");
        return true;
    }
    //</editor-fold>
}
