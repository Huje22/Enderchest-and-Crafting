package me.indian.enderchest.command;

import java.util.Arrays;
import me.indian.enderchest.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class OpenCommand implements CommandExecutor {

    private final Enderchest plugin;

    public OpenCommand(final Enderchest plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        final Configuration config = this.plugin.getConfig();

        if (sender instanceof Player) {
            if (sender.hasPermission("endacrft.open")) {
                this.openEnderchestGui((Player) sender);
            } else {
                sender.sendMessage(config.getString("Open-Perm"));
            }
        } else {
            sender.sendMessage(config.getString("player-not-be"));
        }
        return false;
    }

    public void openEnderchestGui(final Player player) {
        final Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, this.plugin.openName);

        final ItemStack crafting = new ItemStack(Material.CRAFTING_TABLE);
        final ItemMeta c = crafting.getItemMeta();
        c.setDisplayName(ChatColor.GREEN + "Crafting");
        c.setLore(Arrays.asList("§e--------", this.plugin.getConfig().getString("CraftInfo")));
        crafting.setItemMeta(c);

        final ItemStack ender = new ItemStack(Material.ENDER_CHEST);
        final ItemMeta e = ender.getItemMeta();
        e.setDisplayName(ChatColor.AQUA + "EnderChest");
        e.setLore(Arrays.asList("§e--------", this.plugin.getConfig().getString("EnderInfo")));
        ender.setItemMeta(e);


        final ItemStack empty = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        final ItemMeta s = empty.getItemMeta();
        s.setDisplayName(ChatColor.BLACK + "empty");
        empty.setItemMeta(s);


        inv.setItem(0, empty);
        inv.setItem(1, crafting);
        inv.setItem(2, empty);
        inv.setItem(3, ender);
        inv.setItem(4, empty);

        player.openInventory(inv);
    }
}

