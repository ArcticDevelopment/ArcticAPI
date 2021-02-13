package dev.kyro.arcticapi;

import dev.kyro.arcticapi.ui.AInventoryUI;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ExampleUI extends AInventoryUI {

    public ExampleUI() {

        inventory = Bukkit.createInventory(this, 27, "Example inventory");
    }

    @Override
    public boolean onClick(InventoryClickEvent event) {

        event.getWhoClicked().sendMessage("click");
        return false;
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {

        event.getPlayer().sendMessage("open");
    }

    @Override
    public void onClose(InventoryCloseEvent event) {

        event.getPlayer().sendMessage("close");
    }
}
