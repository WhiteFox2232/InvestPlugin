package fr.whitefox.investplugin.commands;

import fr.whitefox.investplugin.Main;
import fr.whitefox.investplugin.utils.Message;
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

    private static final String[] COMMANDS = {"area"};
    private static final String AREA = "area";
    FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], Arrays.asList(COMMANDS), completions);
        Collections.sort(completions);
        return completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(Message.CHAT_BAD_ARGS_CONFIG);
            return false;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.ERROR_NOT_A_PLAYER);
            return false;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase(AREA)) {
            configureArea(player);
            return true;
        }

        player.sendMessage(Message.CHAT_BAD_ARGS_CONFIG);
        return false;
    }

    private void configureArea(Player player) {
        player.sendMessage(Message.CHAT_COORDINATES + player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ());
        config.set("area.x", player.getLocation().getChunk().getX());
        config.set("area.z", player.getLocation().getChunk().getZ());
    }
}
