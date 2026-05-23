package com.matt5262.mcemanager.listeners;

import com.matt5262.mcemanager.gui.CTSManagerHolder;
import com.matt5262.mcemanager.storage.SpawnStorage;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (!(event.getInventory().getHolder() instanceof CTSManagerHolder)) return;

        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null || !item.hasItemMeta()) return;

        String name = PlainTextComponentSerializer.plainText()
                .serialize(item.getItemMeta().displayName());

        // remove colors safely
        name = name.replace("CTS Manager - ", "");

        Location loc = SpawnStorage.getSpawn(name);

        if (loc != null) {
            player.teleport(loc);
            player.sendMessage("Teleported to " + name);
        }
    }
}