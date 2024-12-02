package me.indian.enderchest.util;

import java.io.File;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class ConfigUtil {
    private final File file;
    private final FileConfiguration config;

    public ConfigUtil(final Plugin plugin, final String patch) {
        this(plugin.getDataFolder().getAbsolutePath() + "/" + patch);

    }

    public ConfigUtil(final String patch) {
        this.file = new File(patch);
        this.config = YamlConfiguration.loadConfiguration(this.file);
    }

    public boolean save() {
        try {
            this.config.save(this.file);
            return true;
        } catch (final Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getFile() {
        return this.file;
    }

    public FileConfiguration getConfig() {
        return this.config;
    }
}
