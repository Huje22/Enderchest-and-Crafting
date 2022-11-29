package me.indian.enderchest.main;


import me.indian.enderchest.Test;
import me.indian.enderchest.EnderChestApi;
import me.indian.enderchest.Utils.MessageApi;
import me.indian.enderchest.commands.Reload;
import me.indian.enderchest.commands.Set;
import me.indian.enderchest.commands.open;
import me.indian.enderchest.listeners.CrRcHandListener;
import me.indian.enderchest.listeners.InventoryListener;
import me.indian.enderchest.listeners.SetListener;
import me.indian.enderchest.others.EnderchestPlaceholders;
import me.indian.enderchest.others.Metrics;
import me.indian.enderchest.others.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Enderchest extends JavaPlugin implements Listener {

    public EnderChestApi enderChestApi;

    String prefix = getConfig().getString("prefix");
    public String openName = getConfig().getString("InvName");
    String version = getDescription().getVersion();


    @Override
    public void onEnable() {


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {

            new EnderchestPlaceholders(this).register();
            Bukkit.getConsoleSender().sendMessage("You using PlaceholderAPI ");
        } else {
            Bukkit.getConsoleSender().sendMessage("You not using PlaceholderAPI, not required :^ ");

        }


        Bukkit.getConsoleSender().sendMessage("------------------------------");
        Bukkit.getConsoleSender().sendMessage("\t " + prefix + " " + version);
        Bukkit.getConsoleSender().sendMessage("------------------------------");


        new UpdateChecker(this, 99276).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info(prefix + "There is not a new update available.");

            } else {
                getLogger().info(prefix + " There is a new update available.");
                getLogger().info(prefix + " Your version " + this.getDescription().getVersion() + " new version " + version);

            }
        });


        Metrics metrics = new Metrics(this, 13974);

        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));


        saveDefaultConfig();

        new EnderChestApi();

        getCommand("open").setExecutor(new open(this));
        getCommand("endacrftr").setExecutor(new Reload(this));
        getCommand("set").setExecutor(new Set(this));
        getCommand("entest").setExecutor(new Test(this));

        getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        getServer().getPluginManager().registerEvents(new SetListener(this), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new CrRcHandListener(this), this);


    }

    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(prefix + " disabling");
        Bukkit.getConsoleSender().sendMessage(prefix + " Version" + version);
    }


    @EventHandler
    public void OpJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();


        if (p.isOp()) {
            new UpdateChecker(this, 99276).getVersion(version -> {
                if (!(this.getDescription().getVersion().equals(version))) {
                    MessageApi.Chat(p, prefix + "§bThere is a new update available.");
                    MessageApi.HoverMessageCopy(p,
                            prefix + "§bYour version " + this.getDescription().getVersion() + " new version " + version,
                            "https://www.spigotmc.org/resources/enderchest-and-crafting.99276/",
                            "Copy download link");

//                    p.sendMessage( prefix + "§bYour version " + this.getDescription().getVersion() + " new version " + version);
                }
            });
        }
    }


}




