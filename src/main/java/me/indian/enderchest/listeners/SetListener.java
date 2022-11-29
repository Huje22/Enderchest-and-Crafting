package me.indian.enderchest.listeners;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SetListener implements Listener {

    private final Enderchest plugin;

    public SetListener(Enderchest plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void InventoryClickEvent(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equals("/open options")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "EnderChest")) {
                e.setCancelled(true);
                if (plugin.getConfig().getBoolean("EnderChest") == true) {
                    plugin.getConfig().set("EnderChest", false);
                    plugin.getConfig().set("EnderInfo", plugin.getConfig().getString("message-off"));
                    plugin.saveConfig();
                    p.sendMessage("EnderChest " + this.plugin.getConfig().getString("successfully-set") + " " + "false");
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                } else {
                    plugin.getConfig().set("EnderChest", true);
                    plugin.getConfig().set("EnderInfo", plugin.getConfig().getString("message-on"));
                    plugin.saveConfig();
                    p.sendMessage("EnderChest " + this.plugin.getConfig().getString("successfully-set") + " " + " true");
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                }
            }
        }
        if (e.getView().getTitle().equals("/open options")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Crafting")) {
                e.setCancelled(true);

                if (plugin.getConfig().getBoolean("Crafting") == true) {
                    plugin.getConfig().set("Crafting", false);
                    plugin.getConfig().set("CraftInfo", plugin.getConfig().getString("message-off"));
                    plugin.saveConfig();
                    p.sendMessage("Crafting " + this.plugin.getConfig().getString("successfully-set") + "false");
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                } else {
                    plugin.getConfig().set("Crafting", true);
                    plugin.getConfig().set("CraftInfo", plugin.getConfig().getString("message-on"));
                    plugin.saveConfig();
                    p.sendMessage("Crafting " + this.plugin.getConfig().getString("successfully-set") + "true");
                    Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 5);
                }

            }
        }
        if (e.getView().getTitle().equals("/open options")) {
            if (e.getCurrentItem().getItemMeta().getDisplayName().equals(ChatColor.BLACK + "pustka")) {
                e.setCancelled(true);
            }
        }
    }


}
