package dev.kyro.arcticapi.ui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class AInventoryUI implements InventoryHolder {

    public Inventory inventory;

    public AInventoryUI(String name, int rows) {

        inventory = Bukkit.createInventory(this, getSlots(rows), name);
    }

    public abstract boolean onClick(InventoryClickEvent event);

    public abstract void onOpen(InventoryOpenEvent event);

    public abstract void onClose(InventoryCloseEvent event);

    public static int getSlots(int rows) {

        return Math.max(Math.min(rows, 6), 1) * 9;
    }

    @Override
    public Inventory getInventory() {

        return inventory;
    }
}
