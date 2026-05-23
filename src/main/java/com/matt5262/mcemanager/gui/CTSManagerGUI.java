package com.matt5262.mcemanager.gui;

import com.matt5262.mcemanager.storage.SpawnStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.profile.PlayerTextures;

import java.net.URL;
import java.util.List;
import java.util.UUID;

public class CTSManagerGUI {

    private final int PAGE_SIZE = 45;

    public void open(Player player, int page) {

        List<String> spawns = SpawnStorage.getAllSpawns();

        Inventory gui = Bukkit.createInventory(
                new CTSManagerHolder(page),
                54,
                Component.text("CTS Manager - Page " + (page + 1))
        );

        int start = page * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, spawns.size());

        int slot = 0;

        for (int i = start; i < end; i++) {
            gui.setItem(slot++, createSpawnHead(spawns.get(i)));
        }

        // Buttons (glass panes)
        gui.setItem(45, button(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Previous Page"));
        gui.setItem(46, button(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "Next Page"));

        gui.setItem(48, button(Material.RED_STAINED_GLASS_PANE, "Unmark"));
        gui.setItem(49, button(Material.LIME_STAINED_GLASS_PANE, "Confirm"));

        gui.setItem(53, button(Material.BLACK_STAINED_GLASS_PANE, "Close"));

        player.openInventory(gui);
    }

    private ItemStack createSpawnHead(String name) {

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) item.getItemMeta();

        meta.displayName(Component.text(name, NamedTextColor.GREEN));

        PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());
        PlayerTextures textures = profile.getTextures();

        try {
            textures.setSkin(new URL(
                    "http://textures.minecraft.net/texture/81fb8ce6408a5851384e1c2ef753851eac18ba4018266cdd669dc944873d42"
            ));
        } catch (Exception ignored) {}

        profile.setTextures(textures);
        meta.setOwnerProfile(profile);

        item.setItemMeta(meta);
        return item;
    }

    private ItemStack button(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(Component.text(name, NamedTextColor.YELLOW));

        item.setItemMeta(meta);
        return item;
    }
}