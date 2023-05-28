package me.indian.enderchest.other;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.indian.enderchest.Enderchest;
import org.bukkit.entity.Player;

public class EnderchestPlaceholders extends PlaceholderExpansion {

    private final Enderchest plugin;

    public EnderchestPlaceholders(Enderchest plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "EnderchestAndCrafting";
    }

    @Override
    public String getAuthor() {
        return String.valueOf(plugin.getDescription().getAuthors()).replace("[", "").replace("]", "");
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if (identifier.equals("version")) {
            return plugin.getDescription().getVersion();
        }
        if (identifier.equals("expansion_version")) {
            return this.getVersion();
        }

        if (identifier.equals("crafting_status")) {
            return plugin.getConfig().getString("CraftInfo");
        }
        if (identifier.equals("enderchest_status")) {
            return plugin.getConfig().getString("EnderInfo");
        }
        if (identifier.equals("enderchest_hand_status")) {
            String status = plugin.getConfig().getString("message-off");
            if (plugin.getConfig().getBoolean("EnderChest-Hand")) {
                status = plugin.getConfig().getString("message-on");
            }
            return status;
        }

        if (identifier.equals("crafting_hand_status")) {
            String status = plugin.getConfig().getString("message-off");
            if (plugin.getConfig().getBoolean("Crafting-Hand")) {
                status = plugin.getConfig().getString("message-on");
            }
            return status;
        }
        return null;
    }
}