package dev.kyro.arcticapi.guiold;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

@Deprecated
public class AOldInventoryGUIManager implements Listener {

	@EventHandler
	private static void onClick(InventoryClickEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AOldInventoryGUI)) return;
		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();

		if(event.getClickedInventory() == null || event.getCurrentItem() == null) return;

		if(holder instanceof AOldPagedInventoryGUI) {

			AOldPagedInventoryGUI pagedInventory = (AOldPagedInventoryGUI) holder;

			if(event.getRawSlot() == pagedInventory.previousSlot) {

				pagedInventory.previousPage(event);
				return;
			} else if (event.getRawSlot() == pagedInventory.nextSlot) {

				pagedInventory.nextPage(event);
				return;
			}
		}

//		event.setCancelled(((AInventoryGUI) holder).onClick(event));
		((AOldInventoryGUI) holder).onClick(event);
		((Player) event.getWhoClicked()).updateInventory();
	}

	@EventHandler
	private static void onOpen(InventoryOpenEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AOldInventoryGUI)) return;
		((AOldInventoryGUI) holder).onOpen(event);
		((Player) event.getPlayer()).updateInventory();
	}

	@EventHandler
	private static void onClose(InventoryCloseEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AOldInventoryGUI)) return;
		((AOldInventoryGUI) holder).onClose(event);
		((Player) event.getPlayer()).updateInventory();
	}
}
