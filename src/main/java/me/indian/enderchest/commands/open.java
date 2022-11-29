package me.indian.enderchest.commands;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;


public class open implements CommandExecutor {

    private final Enderchest plugin;

    public open(Enderchest plugin) {
        this.plugin = plugin;

    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if (sender instanceof Player) {
            if (sender.hasPermission("endacrft.open")) {
                Player p = (Player) sender;
                Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, plugin.openName);

                ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
                ItemMeta c = crafting.getItemMeta();
                c.setDisplayName(ChatColor.GREEN + "Crafting");
                c.setLore(Arrays.asList("§e--------", plugin.getConfig().getString("CraftInfo")));
                crafting.setItemMeta(c);

                ItemStack ender = new ItemStack(Material.ENDER_CHEST);
                ItemMeta e = ender.getItemMeta();
                e.setDisplayName(ChatColor.AQUA + "EnderChest");
                e.setLore(Arrays.asList("§e--------", plugin.getConfig().getString("EnderInfo")));
                ender.setItemMeta(e);


                ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
                ItemMeta s = empty.getItemMeta();
                s.setDisplayName(ChatColor.BLACK + "empty");
                empty.setItemMeta(s);


                inv.setItem(0, empty);
                inv.setItem(1, crafting);
                inv.setItem(2, empty);
                inv.setItem(3, ender);
                inv.setItem(4, empty);

                p.openInventory(inv);

            } else {
                sender.sendMessage(plugin.getConfig().getString("Open-Perm"));
            }
        } else {
            sender.sendMessage(plugin.getConfig().getString("player-not-be"));
        }

        return false;
    }
}

