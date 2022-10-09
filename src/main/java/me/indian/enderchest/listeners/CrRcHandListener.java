package me.indian.enderchest.listeners;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;


public class CrRcHandListener implements Listener {

    private final Enderchest plugin;

    public CrRcHandListener(Enderchest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void InHandItem(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();

        if (p.hasPermission("endacrft.open")) {

            if (p.getInventory().getItemInMainHand().getType() == Material.CRAFTING_TABLE) {
                if (plugin.getConfig().getBoolean("Crafting-Hand")) {
                    p.openWorkbench(p.getLocation(), true);
                    p.sendMessage(plugin.getConfig().getString("Crafting-Open-Hand"));
                } else {
                    p.sendMessage(plugin.getConfig().getString("off-feature"));
                }
            }


            if (p.getInventory().getItemInMainHand().getType() == Material.ENDER_CHEST) {
                if (plugin.getConfig().getBoolean("EnderChest-Hand")) {
                    p.sendMessage(plugin.getConfig().getString("EnderChest-Open-Hand"));
                    Inventory i = p.getEnderChest();
                    p.openInventory(i);


                } else {
                    p.sendMessage(plugin.getConfig().getString("off-feature"));
                }
            }
        }
    }

}
