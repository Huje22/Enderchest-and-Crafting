package me.indian.enderchest.others;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.indian.enderchest.main.Enderchest;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
        return "IndianPL";
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

        if(identifier.equals("crafting_status")){
          String crinfo = plugin.getConfig().getString("CraftInfo");
            return crinfo ;
        }
        if(identifier.equals("enderchest_status")){
            String eninfo = plugin.getConfig().getString("EnderInfo");
            return eninfo ;
        }
        if(identifier.equals("enderchest_hand_status")){
            String status = "";
            if (plugin.getConfig().getBoolean("EnderChest-Hand")){
                status = plugin.getConfig().getString("message-on");
            } else{
                status = plugin.getConfig().getString("message-off");
            }
            return status;
        }

        if(identifier.equals("crafting_hand_status")){
            String status = "";
            if (plugin.getConfig().getBoolean("Crafting-Hand")){
                status = plugin.getConfig().getString("message-on");
            } else{
                status = plugin.getConfig().getString("message-off");
            }
            return status;
        }
        return null;
    }

}
