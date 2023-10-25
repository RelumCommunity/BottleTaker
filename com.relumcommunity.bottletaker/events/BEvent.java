package com.relumcommunity.bottletaker.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import com.relumcommunity.bottletaker.main.Main;

public class BEvent implements Listener {
    private final boolean offHand;

    public BEvent(String ver) {
        this.offHand = !(ver.contains("1.7") || ver.contains("1.8") || (ver.contains("1.9") && !ver.contains("1.9.2") && !ver.contains("1.9.4")));
    }

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e) {
        if (!e.getPlayer().hasPermission("bottletaker.bypass")) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), () -> {
                ItemStack bottle = new ItemStack(Material.GLASS_BOTTLE);
                e.getPlayer().getInventory().remove(bottle);
                if (offHand && e.getPlayer().getInventory().getItemInOffHand().getType() == Material.GLASS_BOTTLE) {
                    e.getPlayer().getInventory().setItemInOffHand(null);
                }
            }, 2L);
        }
    }
}
