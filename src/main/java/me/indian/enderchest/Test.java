package me.indian.enderchest;

import me.indian.enderchest.main.Enderchest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {

    private final Enderchest plugin;

    public Test(Enderchest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        // plugin = Main class | p = Player | you can get boolean form your config (if true enderchest has been opened)
        // refusal message , if open == false this message has been typed || closeinv if true inventory has been closed if open = false
        // sorry for my english ;)
        EnderChestApi.OpenEnderChest(plugin, p, false, "Enderchest not allowed", true);
        EnderChestApi.OpenCrafting(plugin, p, true, "Crafting not allowed", false);

        return false;
    }
}
