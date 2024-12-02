package me.indian.enderchest.listener;

import me.indian.enderchest.Enderchest;
import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class HandListener implements Listener {

    private final Enderchest plugin;

    public HandListener(final Enderchest plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    private void InHandItem(final PlayerInteractEvent event) {
        final Configuration config = this.plugin.getConfig();
        final Player player = event.getPlayer();
        final Action action = event.getAction();
        final ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        if (mainHandItem.getType() == Material.CRAFTING_TABLE || mainHandItem.getType() == Material.ENDER_CHEST) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                return;
            }
            if (mainHandItem.getType() == Material.CRAFTING_TABLE) {
                if (player.hasPermission("endacrft.open") || player.hasPermission("endacrft.crafting")) {
                    if (this.plugin.getConfig().getBoolean("Crafting-Hand")) {
                        player.openWorkbench(player.getLocation(), true);
                        player.sendMessage(config.getString("Crafting-Open-Hand"));
                    } else {
                        player.sendMessage(config.getString("off-feature"));
                    }
                }
            }

            if (mainHandItem.getType() == Material.ENDER_CHEST) {
                if (player.hasPermission("endacrft.open") || player.hasPermission("endacrft.enderchest")) {
                    if (config.getBoolean("EnderChest-Hand")) {
                        player.sendMessage(config.getString("EnderChest-Open-Hand"));
                        final Inventory i = player.getEnderChest();
                        player.openInventory(i);
                    } else {
                        player.sendMessage(config.getString("off-feature"));
                    }
                }
            }
        }
    }
}
