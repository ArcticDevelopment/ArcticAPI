package dev.kyro.arcticapi.ui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public class AInventoryUIManager implements Listener {

    @EventHandler
    public static void onClick(InventoryClickEvent event) {

        InventoryHolder holder = event.getInventory().getHolder();
        if(!(holder instanceof AInventoryUI)) return;
        event.setCancelled(((AInventoryUI) holder).onClick(event));
    }

    @EventHandler
    public static void onOpen(InventoryOpenEvent event) {

        InventoryHolder holder = event.getInventory().getHolder();
        if(!(holder instanceof AInventoryUI)) return;
        ((AInventoryUI) holder).onOpen(event);
    }

    @EventHandler
    public static void onClose(InventoryCloseEvent event) {

        InventoryHolder holder = event.getInventory().getHolder();
        if(!(holder instanceof AInventoryUI)) return;
        ((AInventoryUI) holder).onClose(event);
    }
}
