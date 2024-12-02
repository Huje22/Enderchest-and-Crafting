package me.indian.enderchest.listener;

import me.indian.enderchest.Enderchest;
import me.indian.enderchest.util.ReopenUtil;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {
    private final Enderchest plugin;

    public InventoryListener(final Enderchest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void onPlayerClickInventory(final InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Configuration config = this.plugin.getConfig();
        final String inventoryName = event.getView().getTitle();
        String itemName = "wrongName";
        if (event.getCurrentItem() != null && event.getCurrentItem().getItemMeta() != null) {
            itemName = event.getCurrentItem().getItemMeta().getDisplayName();
        }

        if (inventoryName.equals(this.plugin.openName)) {
            event.setCancelled(true);

            if (itemName.equals(ChatColor.GREEN + "Crafting")) {
                if (config.getBoolean("Crafting")) {
                    player.openWorkbench(player.getLocation(), true);
                } else {
                    player.sendMessage(config.getString("off-feature"));
                    ReopenUtil.reOpenEnderchestInventory(player);
                }
            }

            if (itemName.equals(ChatColor.AQUA + "EnderChest")) {
                if (config.getBoolean("EnderChest")) {
                    final Inventory i = player.getEnderChest();
                    player.openInventory(i);
                } else {
                    player.sendMessage(config.getString("off-feature"));
                    ReopenUtil.reOpenEnderchestInventory(player);
                }
            }
        }
        if (inventoryName.equals("/open options")) {
            event.setCancelled(true);
            if (itemName.equals(ChatColor.AQUA + "EnderChest")) {
                event.setCancelled(true);
                if (config.getBoolean("EnderChest")) {
                    config.set("EnderChest", false);
                    config.set("EnderInfo", config.getString("message-off"));
                    this.plugin.saveConfig();
                    player.sendMessage("EnderChest " + config.getString("successfully-set") + " " + "false");
                    ReopenUtil.reOpenSettingsInventory(player);
                } else {
                    config.set("EnderChest", true);
                    config.set("EnderInfo", config.getString("message-on"));
                    this.plugin.saveConfig();
                    player.sendMessage("EnderChest " + config.getString("successfully-set") + " " + "true");
                    ReopenUtil.reOpenSettingsInventory(player);
                }
            }

            if (itemName.equals(ChatColor.GREEN + "Crafting")) {
                if (config.getBoolean("Crafting")) {
                    config.set("Crafting", false);
                    config.set("CraftInfo", config.getString("message-off"));
                    this.plugin.saveConfig();
                    player.sendMessage("Crafting " + config.getString("successfully-set") + "false");
                    ReopenUtil.reOpenSettingsInventory(player);
                } else {
                    config.set("Crafting", true);
                    config.set("CraftInfo", config.getString("message-on"));
                    this.plugin.saveConfig();
                    player.sendMessage("Crafting " + config.getString("successfully-set") + "true");
                    ReopenUtil.reOpenSettingsInventory(player);
                }
            }
        }
    }
}