package de.jonas.jworldedit;

import de.jonas.JWorldEdit;
import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

public class CommandUtil {

    private static final String NO_PERMISSIONS = "Dazu hast du keine Rechte!";
    private static final String NO_PLAYER = "Du musst ein Spieler sein!";

    private final CommandSender sender;
    private final int length;
    private final String[] args;
    private final String command;

    @Nullable
    @Getter
    private Player player;

    public CommandUtil(
        @NotNull final CommandSender sender,
        @Range(from = 0, to = Integer.MAX_VALUE) final int length,
        @NotNull final String[] args,
        @NotNull final String command
    ) {
        this.sender = sender;
        this.length = length;
        this.args = args;
        this.command = command;
    }

    public boolean check() {
        if (!(sender instanceof Player)) {
            sender.sendMessage(JWorldEdit.getPrefix() + NO_PERMISSIONS);
            return true;
        }

        this.player = (Player) sender;

        if (!player.hasPermission(PermissionType.POS_2.getPermission())) {
            player.sendMessage(JWorldEdit.getPrefix() + NO_PLAYER);
            return true;
        }

        if (args.length != length) {
            player.sendMessage(JWorldEdit.getPrefix() + "Bitte benutze //" + command);
            return true;
        }
        return false;
    }

}
