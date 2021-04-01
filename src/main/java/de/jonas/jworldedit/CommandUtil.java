package de.jonas.jworldedit;

import de.jonas.JWorldEdit;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public final class CommandUtil {

    //<editor-fold desc="CONSTANTS">
    private static final String NO_PERMISSIONS = "Dazu hast du keine Rechte!";
    private static final String NO_PLAYER = "Du musst ein Spieler sein!";
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    private final CommandSender sender;
    private final int length;
    private final String[] args;
    private final String command;
    private final PermissionType permissionType;

    @Nullable
    @Getter
    private Player player;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    public CommandUtil(
        @NotNull final CommandSender sender,
        @Range(from = 0, to = Integer.MAX_VALUE) final int length,
        @NotNull final String[] args,
        @NotNull final String command,
        @NotNull final PermissionType permissionType
    ) {
        this.sender = sender;
        this.length = length;
        this.args = args;
        this.command = command;
        this.permissionType = permissionType;
    }
    //</editor-fold>


    public boolean check() {
        // check if player instanceof sender
        if (!(sender instanceof Player)) {
            sender.sendMessage(JWorldEdit.getPrefix() + NO_PERMISSIONS);
            return true;
        }

        // declare player
        this.player = (Player) sender;

        // check if player hast enough permissions
        if (!player.hasPermission(permissionType.getPermission())) {
            player.sendMessage(JWorldEdit.getPrefix() + NO_PLAYER);
            return true;
        }

        // check if command-length is enough
        if (args.length != length) {
            player.sendMessage(JWorldEdit.getPrefix() + "Bitte benutze //" + command);
            return true;
        }
        return false;
    }

}
