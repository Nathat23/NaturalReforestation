package me.ma.naturalreforestation;

import me.ma.naturalreforestation.listeners.OnDespawn;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new OnDespawn(), this);
    }

    private void registerCommands() {}
}
