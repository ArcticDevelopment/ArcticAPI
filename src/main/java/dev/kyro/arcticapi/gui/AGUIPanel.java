package dev.kyro.arcticapi.gui;

import dev.kyro.arcticapi.builders.AInventoryBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

@SuppressWarnings("unused")
public abstract class AGUIPanel implements InventoryHolder {
	public Player player;
	public AGUI gui;
	public AGUIPanel previousGUI;

	private final Inventory inventory;
	public AInventoryBuilder inventoryBuilder;

	public boolean cancelClicks = true;

	public AGUIPanel(AGUI gui) {
		this.player = gui.player;
		this.gui = gui;

		inventory = Bukkit.createInventory(this, getSlots(getRows()), getName());
		inventoryBuilder = new AInventoryBuilder(inventory);
	}

	public abstract String getName();
	public abstract int getRows();

	/**
	 * Called when an item is clicked.
	 * This method is not ran if the gui is a paged
	 * gui and the clicked item is a page selector.
	 *
	 */
	public abstract void onClick(InventoryClickEvent event);

	/**
	 * Called when the GUI is opened.
	 */
	public abstract void onOpen(InventoryOpenEvent event);

	/**
	 * Called when the GUI is closed.
	 */
	public abstract void onClose(InventoryCloseEvent event);

	public void openPanel(AGUIPanel guiPanel) {

		guiPanel.previousGUI = this;
		guiPanel.player.openInventory(guiPanel.getInventory());
	}

	public void openPreviousGUI() {
		if(previousGUI == null) return;

		previousGUI.player.openInventory(previousGUI.getInventory());
		previousGUI = null;
	}

	public void updateInventory() {

		player.updateInventory();
	}

	public Inventory getInventory() {
		return inventory;
	}

	private static int getSlots(int rows) {

		return Math.max(Math.min(rows, 6), 1) * 9;
	}
}