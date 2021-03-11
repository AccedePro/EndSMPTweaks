package me.AccedePro;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

public class PlayerConfigManager {

    private EndSMPTweaks plugin;
    private FileConfiguration playerConfig = null;
    private File playerConfigFile = null;

    public PlayerConfigManager(EndSMPTweaks plugin) {
        this.plugin = plugin;
        saveDefaultConfig();
    }

    public void reloadPlayerConfig() {
        if (this.playerConfigFile == null) {
            this.playerConfigFile = new File(this.plugin.getDataFolder(), "playerdata.yml");
        }

        this.playerConfig = YamlConfiguration.loadConfiguration(this.playerConfigFile);

        InputStream defaultStream = this.plugin.getResource("playerdata.yml");
        if (defaultStream != null) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.playerConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getPlayerConfig() {
        if (this.playerConfig == null) {
            reloadPlayerConfig();
        }
        return this.playerConfig;
    }

    public void savePlayerConfig() {
        if (this.playerConfig == null || this.playerConfigFile == null) {
            return;
        }
        try {
            this.getPlayerConfig().save(this.playerConfigFile);
        } catch (IOException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not save config to " + this.playerConfigFile, e);
        }
    }

    public void saveDefaultConfig() {
        if (this.playerConfigFile == null) {
            this.playerConfigFile = new File(this.plugin.getDataFolder(), "playerdata.yml");
        }
        if (!this.playerConfigFile.exists()) {
            this.plugin.saveResource("playerdata.yml", false);
        }
    }
}