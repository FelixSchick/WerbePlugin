package de.illegalacces.werbeplugin;

import de.illegalacces.werbeplugin.commands.TPWerbeCommand;
import de.illegalacces.werbeplugin.commands.WerbungCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class WerbePlugin extends JavaPlugin {
    public static HashMap< String, Player > werbung = new HashMap<>();
    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getCommand("werbung").setExecutor(new WerbungCommand());
        getCommand("tpw").setExecutor(new TPWerbeCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
