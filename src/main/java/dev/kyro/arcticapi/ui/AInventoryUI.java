package dev.kyro.arcticapi.ui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class AInventoryUI implements InventoryHolder {

    public Inventory inventory;

    public abstract boolean onClick(InventoryClickEvent event);

    public abstract void onOpen(InventoryOpenEvent event);

    public abstract void onClose(InventoryCloseEvent event);

    @Override
    public Inventory getInventory() {

        return inventory;
    }
}
