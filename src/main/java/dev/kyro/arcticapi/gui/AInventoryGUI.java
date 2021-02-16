package dev.kyro.arcticapi.gui;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public abstract class AInventoryGUI implements InventoryHolder {

    /**
     * The default to a gui. Changing this changes
     * the base inventory used when generating the final gui.
     */
    public Inventory baseGUI;

    public AInventoryGUI(String name, int rows) {

        baseGUI = Bukkit.createInventory(this, getSlots(rows), name);
    }

    /**
     * Called when an item is clicked.
     * This method is not ran if the gui is a paged
     * gui and the clicked item is a page selector.
     *
     * @return  Returning true cancels the event
     */
    public abstract boolean onClick(InventoryClickEvent event);

    /**
     * Called when the GUI is opened.
     */
    public abstract void onOpen(InventoryOpenEvent event);

    /**
     * Called when the GUI is closed.
     */
    public abstract void onClose(InventoryCloseEvent event);

    /**
     * The inventory is cloned so modified versions of
     * an inventory can be easily displayed.
     *
     * @return a clone of {@link AInventoryGUI#baseGUI}
     */
    @Override
    public Inventory getInventory() {

        Inventory inventory = Bukkit.createInventory(this, baseGUI.getSize(), baseGUI.getName());

        for(int i = 0; i < baseGUI.getSize(); i++) {

            ItemStack toClone = baseGUI.getItem(i);

            inventory.setItem(i, toClone);
        }

        return baseGUI;
    }

    private static int getSlots(int rows) {

        return Math.max(Math.min(rows, 6), 1) * 9;
    }
}
