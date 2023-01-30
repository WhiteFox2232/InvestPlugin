package fr.whitefox.investplugin.events;

import fr.whitefox.investplugin.Main;
import fr.whitefox.investplugin.utils.Message;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ChunkEvent implements Listener {

    private FileConfiguration config = Main.getInstance().getConfig();

    @EventHandler
    public void chunkInteraction(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (event.getFrom().getChunk() != event.getTo().getChunk()) {
            if (event.getTo().getChunk().getX() == config.getInt("area.x") && event.getTo().getChunk().getZ() == config.getInt("area.z")) {
                // Welcome !
                player.sendTitle(Message.TITLE_JOIN_ZONE, null, 0, 40, 0);
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0f, 1.0f);
            } else if (event.getFrom().getChunk().getX() == config.getInt("area.x") && event.getFrom().getChunk().getZ() == config.getInt("area.z")) {
                // Goodbye !
                if (Main.player_tasks.containsKey(player)) {
                    player.sendMessage(Message.CHAT_LEAVE_INTERRUPT_INVEST);
                    if (Main.player_tasks.get(player) != null) Main.player_tasks.get(player).cancel();
                    Main.player_tasks.remove(player);
                }
                player.sendTitle(Message.TITLE_LEAVE_ZONE, null, 0, 40, 0);
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.0f);
            }
        }
    }
}
