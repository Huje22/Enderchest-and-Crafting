package me.indian.enderchest;


import me.indian.enderchest.command.EnderChestCommand;
import me.indian.enderchest.command.OpenCommand;
import me.indian.enderchest.listener.HandListener;
import me.indian.enderchest.listener.InventoryListener;
import me.indian.enderchest.other.EnderchestPlaceholders;
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
    public final String prefix = getConfig().getString("prefix");
    public final String openName = getConfig().getString("InvName");
    public final String version = getDescription().getVersion();
    private EnderChestApi enderChestApi;
    private EnderChestCommand enderChestCommand;
    private OpenCommand openCommand;

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

    @Override
    public void onLoad() {
        saveDefaultConfig();
        instance = this;
        enderChestApi = new EnderChestApi();
        enderChestCommand = new EnderChestCommand(this);
        openCommand = new OpenCommand(this);
    }

    @Override
    public void onEnable() {
        final PluginManager pm = Bukkit.getPluginManager();
        if (pm.getPlugin("PlaceholderAPI") != null) {
            new EnderchestPlaceholders(this).register();
        } else {
            getLogger().info(ColorUtil.replaceColorCode("&bYou not using PlaceholderAPI, not required :^"));
        }

        this.getLogger().info("------------------------------");
        this.getLogger().info("\t " + prefix + " " + version);
        this.getLogger().info("------------------------------");


        new UpdateChecker(this).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info(ColorUtil.replaceColorCode(prefix + "&a There is not a new update available."));

            } else {
                getLogger().info(ColorUtil.replaceColorCode(prefix + "&a There is a new update available."));
                getLogger().info(ColorUtil.replaceColorCode(prefix + "&a Your version " + this.getDescription().getVersion() + " new version " + version));

            }
        });

        new Metrics(this, 13974);

        getCommand("open").setExecutor(new OpenCommand(this));
        getCommand("enderchest").setExecutor(new EnderChestCommand(this));

        pm.registerEvents(new InventoryListener(this), this);
        pm.registerEvents(this, this);
        pm.registerEvents(new HandListener(this), this);
    }

    public void onDisable() {
        this.getLogger().info(ColorUtil.replaceColorCode(prefix + "&c Disabling"));
        this.getLogger().info(ColorUtil.replaceColorCode(prefix + "&b Version &6" + version));
    }

    @EventHandler
    public void opJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        if (player.isOp()) {
            new UpdateChecker(this).getVersion(version -> {
                if (!(this.getDescription().getVersion().equals(version))) {
                    MessageApi.chat(player, ColorUtil.replaceColorCode(prefix + "&bThere is a new update available."));
                    MessageApi.hoverMessageCopy(player,
                            ColorUtil.replaceColorCode(prefix + "&bYour version " + this.getDescription().getVersion() + " new version " + version),
                            ColorUtil.replaceColorCode("https://www.spigotmc.org/resources/enderchest-and-crafting.99276/"),
                            ColorUtil.replaceColorCode("Copy download link"));
                }
            });
        }
    }
}