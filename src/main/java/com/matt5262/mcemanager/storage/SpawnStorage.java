package com.matt5262.mcemanager.storage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class SpawnStorage {

    private static File file;
    private static FileConfiguration config;

    public static void init(JavaPlugin plugin) {
        file = new File(plugin.getDataFolder(), "spawns.yml");

        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        config = YamlConfiguration.loadConfiguration(file);
    }

    public static void saveSpawn(String name, Location loc) {
        config.set("spawns." + name + ".world", loc.getWorld().getName());
        config.set("spawns." + name + ".x", loc.getX());
        config.set("spawns." + name + ".y", loc.getY());
        config.set("spawns." + name + ".z", loc.getZ());
        config.set("spawns." + name + ".yaw", loc.getYaw());
        config.set("spawns." + name + ".pitch", loc.getPitch());

        saveFile();
    }

    public static Location getSpawn(String name) {
        if (!config.contains("spawns." + name)) return null;

        return new Location(
                Bukkit.getWorld(config.getString("spawns." + name + ".world")),
                config.getDouble("spawns." + name + ".x"),
                config.getDouble("spawns." + name + ".y"),
                config.getDouble("spawns." + name + ".z"),
                (float) config.getDouble("spawns." + name + ".yaw"),
                (float) config.getDouble("spawns." + name + ".pitch")
        );
    }

    public static List<String> getAllSpawns() {
        if (config.getConfigurationSection("spawns") == null) return new ArrayList<>();
        return new ArrayList<>(config.getConfigurationSection("spawns").getKeys(false));
    }

    private static void saveFile() {
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}