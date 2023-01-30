package fr.whitefox.investplugin.commands;

import fr.whitefox.investplugin.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConfigCommand implements CommandExecutor, TabCompleter {

    FileConfiguration config = Main.getInstance().getConfig();
    private static final String[] COMMANDS = { "area" };

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        Collections.sort(completions);
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage(noArgs());
            return false;
        }

        if(args[0].equalsIgnoreCase("area")) {
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Zone d'investissement configurée dans le chunk actuel.");
            player.sendMessage(ChatColor.AQUA + "Coordinates : " + player.getLocation().getChunk().getX()  + "," + player.getLocation().getChunk().getZ());
            config.set("area.x", player.getLocation().getChunk().getX());
            config.set("area.z", player.getLocation().getChunk().getZ());
            return true;
        }

        player.sendMessage(noArgs());
        return false;
    }

    public String noArgs() {
        return "§cUsage : /config area";
    }
}
