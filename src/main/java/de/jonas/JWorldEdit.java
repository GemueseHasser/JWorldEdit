package de.jonas;

import de.jonas.jworldedit.commands.Pos1;
import de.jonas.jworldedit.commands.Pos2;
import de.jonas.jworldedit.listener.AxeListener;
import lombok.Getter;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static net.md_5.bungee.api.ChatColor.GOLD;
import static net.md_5.bungee.api.ChatColor.GRAY;
import static net.md_5.bungee.api.ChatColor.RED;
import static net.md_5.bungee.api.ChatColor.WHITE;
import static net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention.NONE;

@NotNull
public final class JWorldEdit extends JavaPlugin {

    public static final String NO_PERMISSIONS = "Dazu hast du keine Rechte!";
    public static final String NO_PLAYER = "Du musst ein Spieler sein!";

    @Getter
    private static JWorldEdit instance;

    @Getter
    private static String prefix;

    @Override
    public void onEnable() {
        instance = this;

        loadPrefix();

        System.out.println(prefix + "Das Plugin wurde erfolgreich aktiviert!");

        // register commands
        registerCommand("/pos1", new Pos1());
        registerCommand("/pos2", new Pos2());

        // register listener
        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AxeListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println(prefix + "Das Plugin wurde deaktiviert!");
    }

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
            ).color(GRAY).bold(true);

        prefix = builder.getCurrentComponent().toString();
    }

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
