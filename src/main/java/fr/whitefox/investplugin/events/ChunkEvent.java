package fr.whitefox.investplugin.events;

import fr.whitefox.investplugin.Main;
import fr.whitefox.investplugin.commands.InvestCommand;
import fr.whitefox.investplugin.utils.Message;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitTask;

public class ChunkEvent implements Listener {

    private FileConfiguration config = Main.getInstance().getConfig();

    @EventHandler
    public void chunkInteraction(PlayerMoveEvent event) {
        if (event.getFrom().getChunk() == event.getTo().getChunk()) {
            return;
        }

        Player player = event.getPlayer();

        if (event.getTo().getChunk().getX() == config.getInt("area.x") && event.getTo().getChunk().getZ() == config.getInt("area.z")) {
            joinZone(player);
        } else if (event.getFrom().getChunk().getX() == config.getInt("area.x") && event.getFrom().getChunk().getZ() == config.getInt("area.z")) {
            leaveZone(player);
        }
    }

    public void joinZone(Player player) {
        player.sendTitle(Message.TITLE_JOIN_ZONE, null, 0, 40, 0);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0f, 1.0f);
    }

    public void leaveZone(Player player) {
        player.sendTitle(Message.TITLE_LEAVE_ZONE, null, 0, 40, 0);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.0f);

        BukkitTask existingTask = InvestCommand.player_tasks.get(player);
        if (existingTask != null) {
            existingTask.cancel();
            InvestCommand.player_tasks.remove(player);
            player.sendMessage(Message.CHAT_LEAVE_INTERRUPT_INVEST);
        }
    }
}
