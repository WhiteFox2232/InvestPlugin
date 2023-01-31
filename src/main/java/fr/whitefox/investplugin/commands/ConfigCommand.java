package fr.whitefox.investplugin.commands;

import fr.whitefox.investplugin.Main;
import fr.whitefox.investplugin.utils.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigCommand implements CommandExecutor, TabCompleter {

    private static final String[] ARGS_BASE = {"area"};
    private static final String[] ARGS_AREA = {"set", "get"};
    private static final String AREA = "area";
    private static final String GET = "get";
    private static final String SET = "set";
    FileConfiguration config = Main.getInstance().getConfig();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(Arrays.asList(ARGS_BASE));
        } else if (args.length == 2) {
            if(args[0].equalsIgnoreCase(AREA)) {
                return new ArrayList<>(Arrays.asList(ARGS_AREA));
            }
        }

        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Message.ERROR_NOT_A_PLAYER);
            return false;
        }

        if (args.length != 2) {
            sender.sendMessage(Message.CHAT_BAD_ARGS_CONFIG);
            return false;
        }

        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase(AREA)) {
            if (args[1].equalsIgnoreCase(GET)) {
                getArea(player);
                return true;
            } else if (args[1].equalsIgnoreCase(SET)) {
                configureArea(player);
                return true;
            }
        }

        return false;
    }

    private void configureArea(Player player) {
        player.sendMessage(Message.CHAT_COORDINATES + player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ());
        config.set("area.x", player.getLocation().getChunk().getX());
        config.set("area.z", player.getLocation().getChunk().getZ());
    }

    public void getArea(Player player) {
        player.sendMessage(Message.CHAT_COORDINATES + config.getInt("area.x") + "," + config.getInt("area.z"));
    }
}
