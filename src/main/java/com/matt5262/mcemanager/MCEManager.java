package com.matt5262.mcemanager;

import com.matt5262.mcemanager.commands.CTSCommand;
import com.matt5262.mcemanager.gui.CTSManagerGUI;
import com.matt5262.mcemanager.storage.SpawnStorage;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;

public final class MCEManager extends JavaPlugin {

    @Override
    public void onEnable() {
        String version = getPluginMeta().getVersion();

        // Register the command
        Objects.requireNonNull(getCommand("cts")).setExecutor(new CTSCommand());

        SpawnStorage.init(this);

        // Using the modern logging format for the 26.x API
        getComponentLogger().info("MCEManager v{} initialized for Minecraft 26.1.2", version);
    }

    @Override
    public void onDisable() {
        getLogger().info("MCEManager has been safely shut down.");
    }
}