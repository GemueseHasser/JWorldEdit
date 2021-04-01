package de.jonas;

import lombok.Getter;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static net.md_5.bungee.api.ChatColor.GOLD;
import static net.md_5.bungee.api.ChatColor.GRAY;
import static net.md_5.bungee.api.ChatColor.RED;
import static net.md_5.bungee.api.ChatColor.WHITE;
import static net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention.NONE;

public class JWorldEdit extends JavaPlugin {

    @Getter
    private static JWorldEdit instance;

    @Getter
    private static String prefix;

    @Override
    public void onEnable() {
        instance = this;

        loadPrefix();

        System.out.println(prefix + "Das Plugin wurde erfolgreich aktiviert!");
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

    @NotNull
    @Override
    public String toString() {
        return "JWorldEdit - by Jonas0206 - \n Minecraft-Plugin, zum einfachen Editieren der Welten.";
    }
}
