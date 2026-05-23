package com.matt5262.mcemanager.commands;

import com.matt5262.mcemanager.gui.CTSManagerGUI;
import com.matt5262.mcemanager.storage.SpawnStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CTSCommand implements CommandExecutor {

    private static int spawnCounter = 0;

    CTSManagerGUI gui = new CTSManagerGUI();

    public void sendUsage(Player player) {
        player.sendMessage(Component.text("Usage: /cts c <optionalName>").color(NamedTextColor.RED));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("Only players can use this.");
            return true;
        }

        // /cts
        if (args.length == 0) {
            gui.open(player, 0);
            player.sendMessage(Component.text("Opening CTS Manager...").color(NamedTextColor.YELLOW));
            return true;
        }

        if (args[0].equalsIgnoreCase("c")) {

            String name;

            if (args.length == 1) {
                spawnCounter++;
                name = "Spawn #" + spawnCounter;
            } else if (args.length == 2) {
                name = args[1];
            } else {
                sendUsage(player);
                return true;
            }

            Location loc = player.getLocation();

            SpawnStorage.saveSpawn(name, loc);

            player.sendMessage(
                    Component.text("Created spawn: " + name).color(NamedTextColor.GREEN)
            );

            return true;
        }

        sendUsage(player);
        return true;
    }
}