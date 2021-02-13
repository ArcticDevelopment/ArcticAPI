package dev.kyro.arcticapi;

import dev.kyro.arcticapi.ui.AInventoryUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

public class ExampleUI extends AInventoryUI {

    public ExampleUI(Inventory inventory) {
        super(inventory);
    }

    public ExampleUI(String name, int rows) {
        super(name, rows);
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
