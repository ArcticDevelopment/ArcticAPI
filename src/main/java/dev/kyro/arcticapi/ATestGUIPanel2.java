package dev.kyro.arcticapi;

import dev.kyro.arcticapi.gui.AGUI;
import dev.kyro.arcticapi.gui.AGUIPanel;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class ATestGUIPanel2 extends AGUIPanel {

	public ATestGUIPanel2(AGUI gui) {
		super(gui);

		inventoryBuilder.createBorder(Material.STAINED_GLASS_PANE, 2);
	}

	@Override
	public String getName() {
		return "2nd test panel";
	}

	@Override
	public int getRows() {
		return 3;
	}

	@Override
	public void onClick(InventoryClickEvent event) {

		getInventory().setItem((int) (Math.random() * getInventory().getSize()), new ItemStack(Material.EMERALD));

		if(event.getSlot() == 0) {

			openPreviousGUI();
		}
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {

	}

	@Override
	public void onClose(InventoryCloseEvent event) {

	}
}
