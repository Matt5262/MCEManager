package com.matt5262.mcemanager;

import org.bukkit.plugin.java.JavaPlugin;

public final class MCEManager extends JavaPlugin {

    @Override
    public void onEnable() {
        String version = getPluginMeta().getVersion();
        // Using the modern logging format for the 26.x API
        getComponentLogger().info("MCEManager v{} initialized for Minecraft 26.1.2", version);
    }

    @Override
    public void onDisable() {
        getLogger().info("MCEManager has been safely shut down.");
    }
}