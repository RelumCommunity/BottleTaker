package com.relumcommunity.bottletaker.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.relumcommunity.bottletaker.events.BEvent;

public class Main extends JavaPlugin {
    public static Main plugin;
    public String pluginVersion = "V.1.9";
    
    public void onEnable() {
    	plugin = this;
        String ver = Bukkit.getVersion();
        ConsoleCommandSender cmsg = Main.plugin.getServer().getConsoleSender();
        PluginManager bpm = Bukkit.getPluginManager();
        String versioncheck = ver;
        String[] version = versioncheck.split(" ");
        String servercheck = ver;
        String[] server = servercheck.split("-");
        if (ver.contains("Spigot") || ver.contains("Paper")) {
            cmsg.sendMessage("[BottleTaker INFO] " + ChatColor.YELLOW + "BottleTaker using: " + server[1] + " version " + version[1] + version[2]);
        }
        else {
            cmsg.sendMessage("[BottleTaker INFO] " + ChatColor.YELLOW + "BottleTaker using: " + ver + ChatColor.RED + "UNTESTED SERVER VERSION");
        }
        cmsg.sendMessage("[BottleTaker] " + ChatColor.GREEN + "BottleTaker has been enabled (" + pluginVersion + ")");
        if (ver.contains("1.9") && !ver.contains("1.9.2") && !ver.contains("1.9.4")) {
            cmsg.sendMessage("[BottleTaker WARN] " + ChatColor.RED + "BottleTaker does not fully support this spigot version!");
            cmsg.sendMessage("[BottleTaker WARN] " + ChatColor.YELLOW + "Empty bottle taker in main hand: " + ChatColor.GREEN + "Enabled");
            cmsg.sendMessage("[BottleTaker WARN] " + ChatColor.YELLOW + "Empty bottle taker in off hand: " + ChatColor.RED + "Disabled");
        }
        bpm.registerEvents(new BEvent(ver), this);
        bpm.registerEvents(new VersionChecker(), this);
    }
    public void onDisable() {
        ConsoleCommandSender cmsg = Main.plugin.getServer().getConsoleSender();
        cmsg.sendMessage("[BottleTaker] " + ChatColor.RED + "BottleTaker has been disabled (" + pluginVersion + ")");
    }
    public static Main getInstance() {
        return Main.plugin;
    }
}
