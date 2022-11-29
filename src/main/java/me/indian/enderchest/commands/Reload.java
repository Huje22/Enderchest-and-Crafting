package me.indian.enderchest.commands;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {

    private final Enderchest plugin;

    public Reload(Enderchest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("admin-perms")) {
            sender.sendMessage(plugin.getConfig().getString("admin-perms"));
            return false;
        }


        plugin.reloadConfig();
        sender.sendMessage(plugin.getConfig().getString("config-reload"));
        Bukkit.getConsoleSender().sendMessage(plugin.getConfig().getString("config-reload"));


        return false;
    }
}
