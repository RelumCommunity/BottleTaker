package com.relumcommunity.bottletaker.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.relumcommunity.bottletaker.events.BEvent;

public class Main extends JavaPlugin {
    private static Main instance;
    private final String pluginVersion = "V.2.0";

    public static Main getInstance() {
        return instance;
    }
    
    @Override
    public void onEnable() {
        instance = this;

        String ver = Bukkit.getVersion();
        ConsoleCommandSender cmsg = getServer().getConsoleSender();
        PluginManager bpm = Bukkit.getPluginManager();

        if (ver.contains("Spigot") || ver.contains("Paper")) {
            String[] splitVer = ver.split(" ");
            String[] serverType = ver.split("-");
            cmsg.sendMessage("[BottleTaker INFO] " + ChatColor.YELLOW + "BottleTaker using: " + serverType[1] + " version " + splitVer[1] + splitVer[2]);
        } else {
            cmsg.sendMessage("[BottleTaker INFO] " + ChatColor.YELLOW + "BottleTaker using: " + ver + ChatColor.RED + " UNTESTED SERVER VERSION");
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

    @Override
    public void onDisable() {
        ConsoleCommandSender cmsg = getServer().getConsoleSender();
        cmsg.sendMessage("[BottleTaker] " + ChatColor.RED + "BottleTaker has been disabled (" + pluginVersion + ")");
    }
}
