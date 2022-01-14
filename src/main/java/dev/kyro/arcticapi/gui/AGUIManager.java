package dev.kyro.arcticapi.gui;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("unused")
public class AGUIManager implements Listener {

	@EventHandler
	private static void onClick(InventoryClickEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AGUIPanel)) return;
		event.setCancelled(true);

		AGUIPanel guiPanel = (AGUIPanel) holder;
		Player player = (Player) event.getWhoClicked();
		if(event.getClickedInventory() == null || event.getCurrentItem() == null) return;

		guiPanel.onClick(event);
	}

	@EventHandler
	private static void onOpen(InventoryOpenEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AGUIPanel)) return;
		((AGUIPanel) holder).onOpen(event);
		((Player) event.getPlayer()).updateInventory();
	}

	@EventHandler
	private static void onClose(InventoryCloseEvent event) {

		InventoryHolder holder = event.getInventory().getHolder();
		if(!(holder instanceof AGUIPanel)) return;
		((AGUIPanel) holder).onClose(event);
		((Player) event.getPlayer()).updateInventory();

		new BukkitRunnable() {
			@Override
			public void run() {
				((Player) event.getPlayer()).updateInventory();
			}
		}.runTaskLater(ArcticAPI.INSTANCE, 1L);
	}
}
