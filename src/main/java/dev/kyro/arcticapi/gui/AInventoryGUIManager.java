package dev.kyro.arcticapi.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

@SuppressWarnings("unused")
public class AInventoryGUIManager implements Listener {

	@EventHandler
	private static void onClick(InventoryClickEvent event) {

		if(event.getClickedInventory() == null || event.getCurrentItem() == null) return;

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AInventoryGUI)) return;

		if(holder instanceof APagedInventoryGUI) {

			APagedInventoryGUI pagedInventory = (APagedInventoryGUI) holder;

			if(event.getRawSlot() == pagedInventory.backwardSlot) {

				pagedInventory.previousPage();
			} else if (event.getRawSlot() == pagedInventory.backwardSlot) {

				pagedInventory.nextPage();
			}

			return;
		}

		event.setCancelled(((AInventoryGUI) holder).onClick(event));
		((Player) event.getWhoClicked()).updateInventory();
	}

	@EventHandler
	private static void onOpen(InventoryOpenEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AInventoryGUI)) return;
		((AInventoryGUI) holder).onOpen(event);
		((Player) event.getPlayer()).updateInventory();
	}

	@EventHandler
	private static void onClose(InventoryCloseEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AInventoryGUI)) return;
		((AInventoryGUI) holder).onClose(event);
		((Player) event.getPlayer()).updateInventory();
	}
}
