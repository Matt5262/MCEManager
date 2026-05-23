package com.matt5262.mcemanager.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CTSManagerHolder implements InventoryHolder {

    private final int page;

    public CTSManagerHolder(int page) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    @Override
    public Inventory getInventory() {
        return null; // required by interface, not used
    }
}