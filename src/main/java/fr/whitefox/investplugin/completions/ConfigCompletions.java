package fr.whitefox.investplugin.completions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigCompletions implements TabCompleter {

    private static final String[] ARGS_BASE = {"area"};
    private static final String[] ARGS_AREA = {"set", "get"};
    private static final String AREA = "area";

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(Arrays.asList(ARGS_BASE));
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase(AREA)) {
                return new ArrayList<>(Arrays.asList(ARGS_AREA));
            }
        }

        return null;
    }
}
