package dev.kyro.arcticapi;

import dev.kyro.arcticapi.gui.APagedInventoryGUI;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

public class ExampleGUI extends APagedInventoryGUI {

	public ExampleGUI() {

		super("0", 1);
	}

	@Override
	public void onClick(InventoryClickEvent event) {

//		event.getWhoClicked().sendMessage("click");
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

//		event.getPlayer().sendMessage("open");
	}

	@Override
	public void onClose(InventoryCloseEvent event) {

//		event.getPlayer().sendMessage("close");
	}
}
