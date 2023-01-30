package fr.whitefox.investplugin;

import fr.whitefox.investplugin.commands.ConfigCommand;
import fr.whitefox.investplugin.commands.InvestCommand;
import fr.whitefox.investplugin.events.ChunkEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    public static HashMap<Player, BukkitTask> player_tasks = new HashMap<>();
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ChunkEvent(), this);

        getCommand("invest").setExecutor(new InvestCommand());
        getCommand("invest").setTabCompleter(new InvestCommand());
        getCommand("config").setExecutor(new ConfigCommand());
        getCommand("config").setTabCompleter(new ConfigCommand());
    }

    @Override
    public void onDisable() {
        saveConfig();
        instance = null;
    }
}
