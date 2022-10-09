package me.indian.enderchest.others;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class OpenInventory {

    public static void OpenInv(Player p , Enderchest plugin) {

        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "/open options");



        ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
        ItemMeta c = crafting.getItemMeta();
        c.setDisplayName(ChatColor.GREEN + "Crafting");
        c.setLore(Arrays.asList("§e--------",plugin.getConfig().getString("CraftInfo")));
        crafting.setItemMeta(c);

        ItemStack ender = new ItemStack(Material.ENDER_CHEST);
        ItemMeta e = ender.getItemMeta();
        e.setDisplayName(ChatColor.AQUA + "EnderChest");
        e.setLore(Arrays.asList("§e--------",plugin.getConfig().getString("EnderInfo")));
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

    }

}
