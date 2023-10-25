package com.relumcommunity.bottletaker.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.relumcommunity.bottletaker.main.Main;

public class BEvent implements Listener {
	public Boolean OffHand = true;
    public BEvent(String ver) {
    	if (ver.contains("1.7") || ver.contains("1.8") || (ver.contains("1.9") && !ver.contains("1.9.2") && !ver.contains("1.9.4"))) {
    		OffHand = false;
    	}
	}
	@EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        if (!e.getPlayer().hasPermission("bottletaker.bypass")) {
            ItemStack bottle = new ItemStack(Material.GLASS_BOTTLE);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
                @Override
                public void run() {
                    e.getPlayer().getInventory().removeItem(new ItemStack[] {bottle});
                    if (OffHand == true) {
	                    if (e.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.GLASS_BOTTLE)) {
	                        e.getPlayer().getInventory().setItemInOffHand(null);
	                    }
                    }
                }
            }, 2L);
        }
    }
}
