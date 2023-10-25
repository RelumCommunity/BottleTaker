package com.relumcommunity.bottletaker.main;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class VersionChecker implements Listener {
    public String pluginVersion = Main.getInstance().getDescription().getVersion();
    public VersionChecker() {
        Logger mlog = Main.getInstance().getLogger();
        try {
            HttpsURLConnection connection = (HttpsURLConnection)new URL("https://relumcommunity.com/progetti/plugins/bottletaker/version.json").openConnection();
            String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            if (!pluginVersion.equals(version)) {
                mlog.log(Level.WARNING, " ");
                mlog.log(Level.WARNING, "         -= BottleTaker =-");
                mlog.log(Level.WARNING, " You do not have the latest version!");
                mlog.log(Level.WARNING, " ");
                mlog.log(Level.WARNING, "Current: " + pluginVersion);
                mlog.log(Level.WARNING, "Latest: " + version);
                mlog.log(Level.WARNING, " ");
            }
            else {
                mlog.log(Level.INFO, " ");
                mlog.log(Level.INFO, "          -= BottleTaker =-");
                mlog.log(Level.INFO, " You are running the latest version!");
                mlog.log(Level.INFO, " ");
            }
        }
        catch (IOException e) {
            mlog.log(Level.SEVERE, " ");
            mlog.log(Level.SEVERE, "           -= BottleTaker =-");
            mlog.log(Level.SEVERE, "Could not make connection to RelumCommunity.com!");
            mlog.log(Level.SEVERE, " ");
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent j) {
    	Player p = j.getPlayer();
        if (p.hasPermission("bottletaker.updates")) {
            try {
                HttpsURLConnection connection = (HttpsURLConnection)new URL("https://relumcommunity.com/progetti/plugins/bottletaker/version.json").openConnection();
                String version = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
                if (!pluginVersion.equals(version)) {
                	String Message = (ChatColor.GRAY + "[BottleTaker] " + ChatColor.RED + " You are not in the latest version, please update the plugin from our plugin page");
					TextComponent MSG = new TextComponent(Message);
					MSG.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.AQUA + "Click to open the plugin page.").create()));
					MSG.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL , "https://relumcommunity.com/progetti/plugins/bottletaker/redirect.html"));
					((Player) p).spigot().sendMessage(MSG);
                }
            }
            catch (IOException e) {
            	Logger mlog = Main.getInstance().getLogger();
            	mlog.log(Level.SEVERE, " ");
                mlog.log(Level.SEVERE, "           -= BottleTaker =-");
                mlog.log(Level.SEVERE, "Could not make connection to RelumCommunity.com!");
                mlog.log(Level.SEVERE, " ");
            }
        }
    }
}
