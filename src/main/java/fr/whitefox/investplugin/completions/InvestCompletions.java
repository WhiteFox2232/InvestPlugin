package fr.whitefox.investplugin.completions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InvestCompletions implements TabCompleter {

    private static final String[] ARGS_BASE = {"area"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            return new ArrayList<>(Arrays.asList(ARGS_BASE));
        }

        return null;
    }
}
