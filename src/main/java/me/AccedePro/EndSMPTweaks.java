package me.AccedePro;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class EndSMPTweaks extends JavaPlugin implements Listener {

    public static PlayerConfigManager playerConfigManager;

    @Override
    public void onEnable() {
        this.playerConfigManager = new PlayerConfigManager(this);

        final PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new EnderDragonDeathListener(), this);
        pluginManager.registerEvents(new ExperienceSpawnListener(), this);

        //this.getCommand("edtplayers").setExecutor(new DebugCommandListener());
        //this.getCommand("edtworld").setExecutor(new DebugCommandListener());
        //this.getCommand("edtlist").setExecutor(new DebugCommandListener());

    }

    @Override
    public void onDisable() {

    }
}
