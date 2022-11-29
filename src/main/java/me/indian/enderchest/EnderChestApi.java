package me.indian.enderchest;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderChestApi {

    public static void OpenCrafting(Enderchest plugin, Player p, Boolean open, String refusal, Boolean closeinv) {

        if (open == true) {
            p.openWorkbench(p.getLocation(), true);
        } else {
            p.sendMessage(refusal);
            if (closeinv == true) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 20);
            }
        }
    }

    public static void OpenEnderChest(Enderchest plugin, Player p, Boolean open, String refusal, Boolean closeinv) {

        if (open == true) {

            Inventory inv = p.getEnderChest();
            p.openInventory(inv);

        } else {
            p.sendMessage(refusal);
            if (closeinv == true) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 20);
            }
        }
    }
}
