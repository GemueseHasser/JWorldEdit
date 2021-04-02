package de.jonas.jworldedit;

import de.jonas.JWorldEdit;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Range;

/**
 * Mithilfe dieser Util-Klasse, kann man eine Implementation eines {@link CommandExecutor} vereinfachen und sich lästige
 * Abfragen sparen.
 */
public final class CommandUtil {

    //<editor-fold desc="CONSTANTS">
    /** Die Nachricht, die gesendet wird, wenn ein falsches bzw. nicht existierendes {@link Material} angegeben wurde. */
    public static final String WRONG_MATERIAL_MESSAGE = "Bitte wähle ein gültiges Material!";
    /** Die Nachricht, die gesendet wird, wenn ein {@link Player} nicht die nötigen Rechte für einen Befehl hat. */
    private static final String NO_PERMISSIONS = "Dazu hast du keine Rechte!";
    /** Die Nachricht, die gesendet wird, wenn der {@link CommandSender} kein {@link Player} ist. */
    private static final String NO_PLAYER = "Du musst ein Spieler sein!";
    //</editor-fold>


    //<editor-fold desc="LOCAL FIELDS">
    /** Der {@link CommandSender}, der den Befehl absendet. */
    private final CommandSender sender;
    /** Die minimale Länge des Befehls. */
    private final int minLength;
    /** Die maximale Länge des Befehls. */
    private final int maxLength;
    /** Die neben dem eigentlichen Befehl angegebenen Argumente. */
    private final String[] args;
    /** Der Befehl, der eingegeben werden soll. */
    private final String command;
    /** Der {@link PermissionType} der für den Befehl benötigt wird. */
    private final PermissionType permissionType;

    /**
     * Der {@link Player Spieler}, der deklariert wird, sobald überprüft wurde, dass der {@link CommandSender} kein
     * {@link Player Spieler} ist. Wenn der {@link CommandSender} also kein {@link Player Spieler} ist, ist dieser
     * {@link Player Spieler} null.
     */
    @Nullable
    @Getter
    private Player player;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Es wird eine neue und vollständig unabhängige Instanz des {@link CommandUtil} erzeugt. Ein {@link CommandUtil}
     * kann das Überprüfen des {@link CommandSender}, der Rechte des {@link CommandSender} (zufalls er ein {@link Player
     * Spieler} ist) und das Überprüfen der Befehls-Länge deutlich vereinfachen.
     *
     * @param sender         Der {@link CommandSender}, der den Befehl abschickt.
     * @param minLength      Die minimale Argumenten-Länge des Befehls.
     * @param maxLength      Die maximale Argumenten-Länge des Befehls.
     * @param args           Die neben dem eigentlichen Befehl angegebenen Argumente.
     * @param command        Der Name des eigentlichen Befehls.
     * @param permissionType Der {@link PermissionType}, den der jeweilige {@link Player Spieler} benötigt, um diesen
     *                       Befehl ausführen zu können.
     */
    public CommandUtil(
        @NotNull final CommandSender sender,
        @Range(from = 0, to = Integer.MAX_VALUE) final int minLength,
        @Range(from = 0, to = Integer.MAX_VALUE) final int maxLength,
        @NotNull final String[] args,
        @NotNull final String command,
        @NotNull final PermissionType permissionType
    ) {
        this.sender = sender;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.args = args;
        this.command = command;
        this.permissionType = permissionType;
    }
    //</editor-fold>


    /**
     * Prüft den {@link CommandSender} auf eine {@link Player Spieler-Instanz}, die Rechte des {@link Player Spielers },
     * zufalls der {@link CommandSender} ein {@link Player Spieler} ist und die Argumenten-Länge.
     */
    public boolean check() {
        // check if player instanceof sender
        if (!(sender instanceof Player)) {
            sender.sendMessage(JWorldEdit.getPrefix() + NO_PLAYER);
            return true;
        }

        // declare player
        this.player = (Player) sender;

        // check if player hast enough permissions
        if (!player.hasPermission(permissionType.getPermission())) {
            player.sendMessage(JWorldEdit.getPrefix() + NO_PERMISSIONS);
            return true;
        }

        // check if command-length is enough
        if (!(args.length >= minLength && args.length <= maxLength)) {
            player.sendMessage(JWorldEdit.getPrefix() + "Bitte benutze //" + command);
            return true;
        }
        return false;
    }

}
