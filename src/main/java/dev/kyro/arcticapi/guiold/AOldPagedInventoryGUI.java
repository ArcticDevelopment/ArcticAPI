package dev.kyro.arcticapi.guiold;

import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Deprecated
public abstract class AOldPagedInventoryGUI extends AOldInventoryGUI {

	public int previousSlot = 0;
	public ItemStack noPreviousButton = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
	public ItemStack hasPreviousButton = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);

	public int nextSlot = 8;
	public ItemStack noNextButton = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
	public ItemStack hasNextButton = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);

	public int[] contentSlots = new int[]{ 2, 3, 4, 5, 6 };
	public List<ItemStack> inventoryContents = new ArrayList<>();

	public AOldPagedInventoryGUI(String name, int rows) {

		super(name, rows);

		for(int i = 0; i < 70; i++) {

			try {

				ItemStack itemStack = new ItemStack(Material.getMaterial(i));
				ItemMeta itemMeta = itemStack.getItemMeta();
				itemMeta.setDisplayName(i + "");
				itemStack.setItemMeta(itemMeta);

				inventoryContents.add(itemStack);
			} catch(Exception ignored) {

			}
		}
	}

	public void previousPage(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();
		int page = Integer.parseInt(event.getInventory().getName());

		if(page == 0) {

			AOutput.error(player, "Cannot go back any further");
			return;
		}

		player.openInventory(getPage(page - 1));
	}

	public void nextPage(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();
		int page = Integer.parseInt(event.getInventory().getName());

		if(page == getPages() - 1) {

			AOutput.error(player, "Cannot go any further forward");
			return;
		}

		player.openInventory(getPage(page + 1));
	}

	public Inventory getFirstPage() {

		return getPage(0);
	}

	private Inventory getPage(int page) {

		Inventory inventory = getInventoryWithName(page + "");

		inventory.setItem(previousSlot, page == 0 ? noPreviousButton : hasNextButton);
		inventory.setItem(nextSlot, page == getPages() - 1 ? noNextButton : hasNextButton);

		for(int i = 0; i < contentSlots.length; i++) {

			inventory.setItem(contentSlots[i], getItem(page, i));
		}

		return inventory;
	}

	private ItemStack getItem(int page, int slotNum) {

		int itemNum = page * contentSlots.length + slotNum;

		if(itemNum >= inventoryContents.size()) return new ItemStack(Material.AIR);

		return inventoryContents.get(itemNum);
	}

	private int getPages() {

		return (int) Math.ceil((double) inventoryContents.size() / (double) contentSlots.length);
	}
}
