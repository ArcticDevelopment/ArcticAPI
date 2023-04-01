package dev.kyro.arcticapi.gui;

import de.tr7zw.nbtapi.NBTItem;
import dev.kyro.arcticapi.builders.AInventoryBuilder;
import dev.kyro.arcticapi.misc.AUtil;
import dev.kyro.arcticapi.misc.NBTTag;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public abstract class AGUIPanel implements InventoryHolder {
	public Player player;
	public AGUI gui;
	public AGUIPanel previousGUI;

	private Inventory inventory;
	public AInventoryBuilder inventoryBuilder;

	public boolean cancelClicks = true;
	
	public final Map<String, TaggedItem> taggedItemMap = new HashMap<>();

	public AGUIPanel(AGUI gui) {
		this(gui, false);
	}

	/**
	 * @param lateBuild used to prevent the constructor from creating the inventory.
	 *                  The method must be called manually in the subclass
	 *                  construction with the method
	 */
	public AGUIPanel(AGUI gui, boolean lateBuild) {
		this.player = gui.player;
		this.gui = gui;

		if(!lateBuild) buildInventory();
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

	public void buildInventory() {
		inventory = Bukkit.createInventory(this, getSlots(getRows()),
				ChatColor.translateAlternateColorCodes('&', getName()));
		inventoryBuilder = new AInventoryBuilder(inventory);
	}

	public TaggedItem getGUIItem(String tag) {
		return taggedItemMap.get(tag);
	}
	
	public TaggedItem addTaggedItem(int slot, ItemStack itemStack, Consumer<InventoryClickEvent> callback) {
		UUID uuid = UUID.randomUUID();
		TaggedItem taggedItem = new TaggedItem(slot, uuid.toString(), itemStack, callback);
		taggedItemMap.put(uuid.toString(), taggedItem);
		return taggedItem;
	}
	
	public String getTagFromItem(ItemStack itemStack) {
		if(AUtil.isAirOrNull(itemStack)) return null;
		NBTItem nbtItem = new NBTItem(itemStack, true);
		if(!nbtItem.hasKey(NBTTag.ITEM_TAG.getRef())) return null;
		return nbtItem.getString(NBTTag.ITEM_TAG.getRef());
	}

	public TaggedItem setBackButton(int slot) {
		return addTaggedItem(slot, AGUIManager.getBackItemStack(), event -> openPreviousGUI());
	}

	public class TaggedItem {
		private int slot;
		private final String tag;
		private final ItemStack itemStack;
		private final Consumer<InventoryClickEvent> callback;

		public TaggedItem(String tag, ItemStack itemStack, Consumer<InventoryClickEvent> callback) {
			this(-1, tag, itemStack, callback);
		}

		public TaggedItem(int slot, String tag, ItemStack itemStack, Consumer<InventoryClickEvent> callback) {
			this.slot = slot;
			this.tag = tag;
			this.itemStack = itemStack.clone();
			this.callback = callback;
		}

		public void setItem() {
			if(slot == -1) return;
			getInventory().setItem(slot, getTaggedItemStack());
		}

		public void removeItem() {
			if(slot == -1) return;
			getInventory().setItem(slot, new ItemStack(Material.AIR));
		}

		public String getTag() {
			return tag;
		}

		public ItemStack getNormalItemStack() {
			return itemStack;
		}

		public ItemStack getTaggedItemStack() {
			ItemStack itemStack = getNormalItemStack();
			NBTItem nbtItem = new NBTItem(itemStack, true);
			nbtItem.setString(NBTTag.ITEM_TAG.getRef(), tag);
			return itemStack;
		}

		public Consumer<InventoryClickEvent> getCallback() {
			return callback;
		}
	}
}