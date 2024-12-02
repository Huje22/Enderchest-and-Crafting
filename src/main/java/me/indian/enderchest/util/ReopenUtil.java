package me.indian.enderchest.util;

import me.indian.enderchest.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ReopenUtil {

    private static final Enderchest plugin = Enderchest.getInstance();

    public static void reOpenSettingsInventory(final Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> player.closeInventory(), 5);
        Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.getEnderChestCommand().openSettings(player), 5);
    }

    public static void reOpenEnderchestInventory(final Player player) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> player.closeInventory(), 5);
        Bukkit.getScheduler().runTaskLater(plugin, () -> plugin.getOpenCommand().openEnderchestGui(player), 5);
    }

}
