package com.matt5262.mcemanager.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CTSCommand implements CommandExecutor {

    public void sendUsage(Player player) {
        player.sendMessage(Component.text("Invalid usage. Use: /cts c <optionalName>").color(NamedTextColor.RED));

    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Component.text("This command can only be run by a player."));
            return true;
        }

        // If no arguments are provided, open the management menu
        if (args.length == 0) {
            // menu logic would go here
            player.sendMessage(Component.text("Opening the management menu...").color(NamedTextColor.YELLOW));
            return true;
        }

        String subCommand = args[0].toLowerCase();

        if (subCommand.equals("c")) {
            switch (args.length) {
                case 1:
                    player.sendMessage(Component.text("Creating spawnpoint at your location...").color(NamedTextColor.GREEN));
                    break;

                case 2:
                    player.sendMessage(Component.text("Creating spawnpoint '" + args[1] + "' at your location...").color(NamedTextColor.GREEN));
                    break;

                default:
                    sendUsage(player);
            }
            return true;
        }

        sendUsage(player);
        return true;
    }
}
