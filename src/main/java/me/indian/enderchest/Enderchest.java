package me.indian.enderchest;

import me.indian.enderchest.command.EnderChestCommand;
import me.indian.enderchest.command.OpenCommand;
import me.indian.enderchest.listener.HandListener;
import me.indian.enderchest.listener.InventoryListener;
import me.indian.enderchest.other.Metrics;
import me.indian.enderchest.other.UpdateChecker;
import me.indian.enderchest.util.ColorUtil;
import me.indian.enderchest.util.EnderChestApi;
import me.indian.enderchest.util.MessageApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Enderchest extends JavaPlugin implements Listener {

    private static Enderchest instance;
    public final String prefix = this.getConfig().getString("prefix");
    public final String openName = this.getConfig().getString("InvName");
    public final String version = this.getDescription().getVersion();
    private EnderChestApi enderChestApi;
    private EnderChestCommand enderChestCommand;
    private OpenCommand openCommand;

    @Override
    public void onLoad() {
        this.saveDefaultConfig();
        instance = this;
        this.enderChestApi = new EnderChestApi();
        this.enderChestCommand = new EnderChestCommand(this);
        this.openCommand = new OpenCommand(this);
    }

    @Override
    public void onEnable() {
        final PluginManager pluginManager = Bukkit.getPluginManager();

        this.getLogger().info("------------------------------");
        this.getLogger().info("\t " + this.prefix + " " + this.version);
        this.getLogger().info("------------------------------");

        new UpdateChecker(this).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                this.getLogger().info(ColorUtil.colorize(this.prefix + "&a There is not a new update available."));

            } else {
                this.getLogger().info(ColorUtil.colorize(this.prefix + "&a There is a new update available."));
                this.getLogger().info(ColorUtil.colorize(this.prefix + "&a Your version " + this.getDescription().getVersion() + " new version " + version));

            }
        });

        new Metrics(this, 13974);

        this.getCommand("open").setExecutor(this.openCommand);
        this.getCommand("enderchest").setExecutor(this.enderChestCommand);

        pluginManager.registerEvents(new InventoryListener(this), this);
        pluginManager.registerEvents(this, this);
        pluginManager.registerEvents(new HandListener(this), this);
    }

    public void onDisable() {
        this.getLogger().info(ColorUtil.colorize(this.prefix + "&c Disabling"));
        this.getLogger().info(ColorUtil.colorize(this.prefix + "&b Version &6" + this.version));
    }

    @EventHandler
    public void opJoin(final PlayerJoinEvent e) {
        final Player player = e.getPlayer();

        if (player.isOp()) {
            new UpdateChecker(this).getVersion(version -> {
                if (!(this.getDescription().getVersion().equals(version))) {
                    MessageApi.chat(player, ColorUtil.colorize(this.prefix + "&bThere is a new update available."));
                    MessageApi.hoverMessageCopy(player,
                            ColorUtil.colorize(this.prefix + "&bYour version " + this.getDescription().getVersion() + " new version " + version),
                            ColorUtil.colorize("https://www.spigotmc.org/resources/enderchest-and-crafting.99276/"),
                            ColorUtil.colorize("Copy download link"));
                }
            });
        }
    }

    public static Enderchest getInstance() {
        return instance;
    }

    public EnderChestApi getApi() {
        return this.enderChestApi;
    }

    public EnderChestCommand getEnderChestCommand() {
        return this.enderChestCommand;
    }

    public OpenCommand getOpenCommand() {
        return this.openCommand;
    }
}