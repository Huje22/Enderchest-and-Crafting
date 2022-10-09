package me.indian.enderchest.listeners;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.event.*;

public class InventoryListener implements Listener {
    private final Enderchest plugin;

    public InventoryListener(final Enderchest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerClickInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals(plugin.openName)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Crafting")) {
                if (plugin.getConfig().getBoolean("Crafting")) {
//                Player p = (Player) e.getWhoClicked();
                    p.openWorkbench(p.getLocation(), true);


                } else {
                    p.sendMessage(plugin.getConfig().getString("off-feature"));
                    Bukkit.getScheduler ().runTaskLater (plugin, () -> p.closeInventory(), 5);
                }
            }
        }

        if (e.getView().getTitle().equals(plugin.openName)) {
            e.setCancelled(true);
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "EnderChest")) {
                if (plugin.getConfig().getBoolean("EnderChest")) {
//                    Player p = (Player) e.getWhoClicked();
                    Inventory i = p.getEnderChest();
                    p.openInventory(i);


                } else {
                    p.sendMessage(plugin.getConfig().getString("off-feature"));
                    Bukkit.getScheduler ().runTaskLater (plugin, () -> p.closeInventory(), 5);
                }
            }
        }
    }
}