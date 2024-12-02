package me.indian.enderchest.command;

import java.util.Arrays;
import me.indian.enderchest.Enderchest;
import me.indian.enderchest.util.ColorUtil;
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

public class EnderChestCommand implements CommandExecutor {

    private final Enderchest plugin;

    public EnderChestCommand(final Enderchest plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String s, final String[] args) {
        final Configuration config = this.plugin.getConfig();

        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (sender.hasPermission("endacrft.admin")) {

                if (args.length == 0) {
                    player.sendMessage(ColorUtil.colorize(this.plugin.prefix + "&aUsage &b/enderchest &8[set, reload]"));
                }

                if (args[0].equalsIgnoreCase("set")) {
                    this.openSettings(player);
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    this.plugin.reloadConfig();
                    sender.sendMessage(ColorUtil.colorize(config.getString("config-reload")));
                    this.plugin.getLogger().info(config.getString("config-reload"));
                }
            } else {
                sender.sendMessage(config.getString("admin-perms"));
            }
        } else {
            sender.sendMessage(config.getString("player-not-be"));
        }
        return false;
    }


    public void openSettings(final Player player) {
        final Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, "/open options");

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
