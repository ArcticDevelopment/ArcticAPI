package dev.kyro.arcticapi;

import dev.kyro.arcticapi.gui.AGUI;
import dev.kyro.arcticapi.gui.APagedGUIPanel;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;

public class ATestGUIPanel extends APagedGUIPanel {

	public ATestGUIPanel(AGUI gui) {
		super(gui);
		buildInventory();
	}

	@Override
	public void addItems() {
		for(int i = 0; i < 64; i++) {
			int finalI = i;
			addItem(new ItemStack(Material.BAKED_POTATO, i + 1), event -> {
				System.out.println("You clicked potato: " + (finalI + 1));
			});
		}
	}

	@Override
	public void setInventory() {
		super.setInventory();
		inventoryBuilder.createBorder(Material.STAINED_GLASS_PANE, 3, false);
	}

	@Override
	public String getName() {
		return "Test GUI";
	}

	@Override
	public void onOpen(InventoryOpenEvent event) {}

	@Override
	public void onClose(InventoryCloseEvent event) {}
}
