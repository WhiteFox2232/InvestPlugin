package fr.whitefox.investplugin.events;

import fr.whitefox.investplugin.Main;
import fr.whitefox.investplugin.commands.InvestCommand;
import fr.whitefox.investplugin.utils.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ChunkEvent implements Listener {

    private final int areaX = Main.getInstance().getConfig().getInt("area.x");
    private final int areaZ = Main.getInstance().getConfig().getInt("area.z");

    @EventHandler
    public void chunkInteraction(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (event.getFrom().getChunk() == event.getTo().getChunk()) {
            return;
        }

        if (event.getTo().getChunk().getX() == areaX && event.getTo().getChunk().getZ() == areaZ) {
            player.sendTitle(Message.TITLE_JOIN_ZONE, null, 0, 40, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0f, 1.0f);
        } else if (event.getFrom().getChunk().getX() == areaX && event.getFrom().getChunk().getZ() == areaZ) {
            player.sendTitle(Message.TITLE_LEAVE_ZONE, null, 0, 40, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 2.0f, 1.0f);

            if (InvestCommand.player_tasks.containsKey(player)) {
                player.sendMessage(Message.CHAT_LEAVE_INTERRUPT_INVEST);
                InvestCommand.player_tasks.get(player).cancel();
                InvestCommand.player_tasks.remove(player);
            }
        }
    }
}
