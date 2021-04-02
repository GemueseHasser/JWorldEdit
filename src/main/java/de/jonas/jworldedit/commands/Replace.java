package de.jonas.jworldedit.commands;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.CommandUtil;
import de.jonas.jworldedit.CuboidSelection;
import de.jonas.jworldedit.PermissionType;
import de.jonas.jworldedit.Positions;
import de.jonas.jworldedit.SelectionLocation;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static de.jonas.jworldedit.CommandUtil.WRONG_MATERIAL_MESSAGE;

/**
 * Es wird der Befehl, um eine bestimmte Blockart oder alle gesetzten Blöcke zu ersetzen, implementiert.
 */
@NotNull
public final class Replace implements CommandExecutor {
    //<editor-fold desc="implementation">
    @Override
    public boolean onCommand(
        @NotNull final CommandSender sender,
        @NotNull final Command cmd,
        @NotNull final String label,
        @NotNull final String[] args
    ) {
        final CommandUtil util = new CommandUtil(
            sender,
            1,
            2,
            args,
            "replace <oldMaterial> <newMaterial> | <newMaterial>",
            PermissionType.REPLACE
        );

        if (util.check()) {
            return true;
        }

        final Player player = util.getPlayer();

        final Material materialOne = JWorldEdit.getInstance().getMaterial(args[0]);

        assert player != null;
        final Positions positions = JWorldEdit.POSITIONS.get(player.getUniqueId());
        positions.initializeSelection();

        final CuboidSelection selection = positions.getSelection();

        assert selection != null;
        positions.addOldSelection(selection);

        if (materialOne == null) {
            player.sendMessage(JWorldEdit.getPrefix() + WRONG_MATERIAL_MESSAGE);
            return true;
        }

        int count = 0;

        if (args.length == 1) {
            for (@NotNull final SelectionLocation selectionLocation : selection.getAllLocations()) {
                final Location location = selectionLocation.getLocation();
                if (location.getBlock().getType().equals(Material.AIR)) {
                    continue;
                }
                location.getBlock().setType(materialOne);
                count++;
            }
            player.sendMessage(JWorldEdit.getPrefix() + "Es wurden " + count + " Blöcke ersetzt!");
            return true;
        }

        final Material materialTwo = JWorldEdit.getInstance().getMaterial(args[1]);

        if (materialTwo == null) {
            player.sendMessage(JWorldEdit.getPrefix() + WRONG_MATERIAL_MESSAGE);
            return true;
        }

        for (@NotNull final SelectionLocation selectionLocation : selection.getAllLocations()) {
            final Location location = selectionLocation.getLocation();
            if (!location.getBlock().getType().equals(materialOne)) {
                continue;
            }
            location.getBlock().setType(materialTwo);
            count++;
        }
        player.sendMessage(JWorldEdit.getPrefix() + "Es wurden " + count + " Blöcke ersetzt!");
        return true;
    }
    //</editor-fold>
}
