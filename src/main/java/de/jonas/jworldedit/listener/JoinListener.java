package de.jonas.jworldedit.listener;

import de.jonas.JWorldEdit;
import de.jonas.jworldedit.Positions;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * Dieser {@link Listener} wird beim Joinen eines jeden {@link Player Spielers} ausgelöst. Hiermit bekommt dann jeder
 * {@link Player Spieler} eine {@link Positions Positions-Instanz} zugewiesen.
 */
@NotNull
public final class JoinListener implements Listener {
    //<editor-fold desc="implementation">
    @EventHandler
    public void onJoin(@NotNull final PlayerJoinEvent e) {
        final UUID uuid = e.getPlayer().getUniqueId();
        JWorldEdit.POSITIONS.put(uuid, new Positions());
    }
    //</editor-fold>
}
