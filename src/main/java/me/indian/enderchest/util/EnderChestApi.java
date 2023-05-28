package me.indian.enderchest.util;

import me.indian.enderchest.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EnderChestApi {
    // this will be developed later
    private final Enderchest plugin = Enderchest.getInstance();

    public void openCrafting(Player p, Boolean open, String refusal, Boolean closeinv) {
        if (open) {
            p.openWorkbench(p.getLocation(), true);
        } else {
            p.sendMessage(refusal);
            if (closeinv) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 20);
            }
        }
    }

    public void openEnderChest(Player p, Boolean open, String refusal, Boolean closeinv) {
        if (open) {
            Inventory inv = p.getEnderChest();
            p.openInventory(inv);
        } else {
            p.sendMessage(refusal);
            if (closeinv) {
                Bukkit.getScheduler().runTaskLater(plugin, () -> p.closeInventory(), 20);
            }
        }
    }
}
