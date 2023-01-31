package fr.whitefox.investplugin.commands;

import fr.whitefox.investplugin.Main;
import fr.whitefox.investplugin.utils.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.StringUtil;

import java.util.*;

public class InvestCommand implements CommandExecutor, TabCompleter {

    private static final String[] COMMANDS = {"start"};
    public static HashMap<Player, BukkitTask> player_tasks = new HashMap<>();
    private FileConfiguration config = Main.getInstance().getConfig();
    private Main main = Main.getInstance();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        Collections.sort(completions);
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.ERROR_NOT_A_PLAYER);
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0 || args[0].equalsIgnoreCase("start")) {
            if (player.getLocation().getChunk().getX() != config.getInt("area.x") || player.getLocation().getChunk().getZ() != config.getInt("area.z")) {
                player.sendMessage(Message.CHAT_NOT_IN_INVEST_ZONE);
                return false;
            }

            BukkitTask existingTask = player_tasks.get(player);
            if (existingTask != null) {
                existingTask.cancel();
                player_tasks.remove(player);
            }

            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage(ChatColor.GREEN + "+5$");
                }
            }.runTaskTimer(main, 0, 20L);
            player_tasks.put(player, task);

            player.sendMessage(Message.CHAT_INVEST_STARTED);
            return true;
        }

        player.sendMessage(Message.CHAT_BAD_ARGS_INVEST);
        return false;
    }
}
