package com.vaincecraft.bottletaker.general;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main
  extends JavaPlugin
{
  public static Main plugin;
  
  public void onEnable()
  {
    plugin = this;
	plugin.getServer().getConsoleSender().sendMessage("[BottleTaker] " + ChatColor.GREEN + "BottleTaker has been enabled (V.1.1)");

    Bukkit.getPluginManager().registerEvents(new BEvent(), this);
  }
  
  public void onDisable() {
	plugin.getServer().getConsoleSender().sendMessage("[BottleTaker] " + ChatColor.RED + "BottleTaker has been disabled (V.1.1)");

  }
  
  public static Main getInstance()
  {
    return plugin;
  }
}
