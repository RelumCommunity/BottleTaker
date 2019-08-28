package com.vaincecraft.bottletaker.general;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class BEvent
  implements Listener
{
  @EventHandler
  public void onEat(final PlayerItemConsumeEvent e)
  {
    final ItemStack bottle = new ItemStack(Material.GLASS_BOTTLE);
    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable()
    {
      public void run()
      {
        e.getPlayer().getInventory().removeItem(new ItemStack[] { bottle });
      }
    }, 2);
  }
}
