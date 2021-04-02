package de.jonas;

import de.jonas.jworldedit.Positions;
import de.jonas.jworldedit.commands.Pos1;
import de.jonas.jworldedit.commands.Pos2;
import de.jonas.jworldedit.commands.Set;
import de.jonas.jworldedit.listener.AxeListener;
import de.jonas.jworldedit.listener.JoinListener;
import lombok.Getter;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

import static net.md_5.bungee.api.ChatColor.GOLD;
import static net.md_5.bungee.api.ChatColor.GRAY;
import static net.md_5.bungee.api.ChatColor.RED;
import static net.md_5.bungee.api.ChatColor.WHITE;
import static net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention.NONE;

/**
 * Die Haupt- und Main-Klasse des {@link JavaPlugin Plugins}. Hier wird alles initalisiert und gestartet.
 */
@NotNull
public final class JWorldEdit extends JavaPlugin {

    //<editor-fold desc="CONSTANTS">
    /** Eine {@link HashMap}, die für jeden Spieler der online ist, eine {@link Positions Positions-Instanz} enthält. */
    public static final HashMap<UUID, Positions> POSITIONS = new HashMap<>();
    //</editor-fold>


    //<editor-fold desc="STATIC FIELDS">
    /** Die Instanz-Variable, womit auf das {@link JWorldEdit Plugin} zugegriffen werden kann. */
    @Getter
    private static JWorldEdit instance;
    /** Der Prefix, der vor jeder Chat-Ausgabe steht. */
    @Getter
    private static String prefix;
    //</editor-fold>

    //<editor-fold desc="setup and start">
    @Override
    public void onEnable() {
        // declare instance
        instance = this;

        // load prefix
        loadPrefix();

        // write enable message
        System.out.println("[JWorldEdit] Das Plugin wurde erfolgreich aktiviert!");

        // register commands
        registerCommand("/pos1", new Pos1());
        registerCommand("/pos2", new Pos2());
        registerCommand("/set", new Set());

        // register listener
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AxeListener(), this);
        pm.registerEvents(new JoinListener(), this);
    }
    //</editor-fold>

    //<editor-fold desc="stop">
    @Override
    public void onDisable() {
        // write disable message
        System.out.println("[JWorldEdit] Das Plugin wurde deaktiviert!");
    }
    //</editor-fold>

    /**
     * Lädt den Prefix, der vor jeder Chat-Ausgabe stehen soll.
     */
    private void loadPrefix() {
        final ComponentBuilder builder = new ComponentBuilder();

        builder.append(
            "[",
            NONE
        ).color(GRAY).bold(true)
            .append(
                "J",
                NONE
            ).color(GOLD).bold(true)
            .append(
                "World",
                NONE
            ).color(RED)
            .append(
                "Edit",
                NONE
            ).color(WHITE)
            .append(
                "]",
                NONE
            ).color(GRAY).bold(true)
            .append(
                " ",
                NONE
            ).color(GOLD);

        final StringBuilder stringBuilder = new StringBuilder();

        for (final BaseComponent component : builder.getParts()) {
            stringBuilder.append(component.toLegacyText());
        }

        prefix = stringBuilder.toString();
    }

    /**
     * Registriert einen bestimmten Befehl.
     *
     * @param command  Der Name des Befehls.
     * @param executor Der {@link CommandExecutor} des Befehls.
     */
    private void registerCommand(
        @NotNull final String command,
        @NotNull final CommandExecutor executor
    ) {
        Objects.requireNonNull(getCommand(command)).setExecutor(executor);
    }

    @NotNull
    @Override
    public String toString() {
        return "JWorldEdit - by Jonas0206 - \n Minecraft-Plugin, zum einfachen Editieren der Welten.";
    }
}
