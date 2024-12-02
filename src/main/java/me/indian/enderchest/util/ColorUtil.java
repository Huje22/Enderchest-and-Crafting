package me.indian.enderchest.util;

import org.bukkit.ChatColor;

public class ColorUtil {

    private ColorUtil() {

    }

    public static String colorize(final String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
