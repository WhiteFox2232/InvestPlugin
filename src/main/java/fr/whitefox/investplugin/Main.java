package fr.whitefox.investplugin;

import fr.whitefox.investplugin.commands.ConfigCommand;
import fr.whitefox.investplugin.commands.InvestCommand;
import fr.whitefox.investplugin.completions.ConfigCompletions;
import fr.whitefox.investplugin.completions.InvestCompletions;
import fr.whitefox.investplugin.events.ChunkEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    private static final String INVEST = "invest";
    private static final String CONFIG = "config";

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChunkEvent(), this);

        getCommand(INVEST).setExecutor(new InvestCommand());
        getCommand(INVEST).setTabCompleter(new InvestCompletions());
        getCommand(CONFIG).setExecutor(new ConfigCommand());
        getCommand(CONFIG).setTabCompleter(new ConfigCompletions());
    }

    @Override
    public void onDisable() {
        saveConfig();
        instance = null;
    }
}
