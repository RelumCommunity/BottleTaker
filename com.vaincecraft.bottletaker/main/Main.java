package com.vaincecraft.bottletaker.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.vaincecraft.bottletaker.events.BEvent;

public class Main extends JavaPlugin {
 	public static Main plugin;
 	public String pluginVersion = "V.1.2";
 	public void onEnable() {
 		plugin = this;
 		String versioncheck = Bukkit.getVersion();
		String version[] = versioncheck.split(" ");
		if (Bukkit.getVersion().contains("Spigot")) {
			String servercheck = Bukkit.getVersion();
			String server[] = servercheck.split("-");
			plugin.getServer().getConsoleSender().sendMessage("[BottleTaker INFO] " + ChatColor.YELLOW + "BottleTaker using: " + server[1] + " version " + version[1] + version[2]);
		}
		else plugin.getServer().getConsoleSender().sendMessage("[BottleTaker INFO] " + ChatColor.YELLOW + "BottleTaker using: " + Bukkit.getVersion() + ChatColor.RED + ("UNTESTED SERVER VERSION"));
		  
		plugin.getServer().getConsoleSender().sendMessage("[BottleTaker] " + ChatColor.GREEN + "BottleTaker has been enabled (" + pluginVersion + ")");
 		
 		Bukkit.getPluginManager().registerEvents(new BEvent(), this);
 	}
 	public void onDisable() {
 		plugin.getServer().getConsoleSender().sendMessage("[BottleTaker] " + ChatColor.RED + "BottleTaker has been disabled (" + pluginVersion + ")");
 	}
 	public static Main getInstance() {
 		return plugin;
 	}
}
