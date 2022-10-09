package me.indian.enderchest.commands;


import me.indian.enderchest.others.OpenInventory;
import me.indian.enderchest.main.Enderchest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Set implements CommandExecutor , Listener{

    private final Enderchest plugin;

    public Set(Enderchest plugin) {
        this.plugin = plugin;
    }

        public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if (!sender.hasPermission("endacrft.admin")) {
                    sender.sendMessage(plugin.getConfig().getString("admin-perms"));
                    return false;
                }

                if (args.length == 1) {
                    p.sendMessage("/set");
                } else {
                    OpenInventory.OpenInv(p, plugin);
                }
            } else {
                sender.sendMessage(plugin.getConfig().getString("player-not-be"));
            }
            return false;
        }
    }
