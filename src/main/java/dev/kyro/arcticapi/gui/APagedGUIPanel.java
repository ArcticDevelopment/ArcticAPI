package dev.kyro.arcticapi.gui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public abstract class APagedGUIPanel extends AGUIPanel {
	private final static List<Integer> allowedSlots = new ArrayList<>();
	private final List<TaggedItem> items = new ArrayList<>();
	private int page = 1;
	private TaggedItem previousPage;
	private TaggedItem nextPage;

	static {
		for(int i = 9; i < 45; i++) {
			if(i % 9 == 0 || (i + 1) % 9 == 0) continue;
			allowedSlots.add(i);
		}
	}

	public APagedGUIPanel(AGUI gui) {
		super(gui, true);
		addItems();

		previousPage = addTaggedItem(getRows() * 9 - 9, AGUIManager::getPreviousPageItemStack, event -> {
			if(page == 1) return;
			page--;
			setInventory();
		});
		nextPage = addTaggedItem(getRows() * 9 - 1, AGUIManager::getNextPageItemStack, event -> {
			if(page == getMaxPages()) return;
			page++;
			setInventory();
		});
	}

	public abstract void addItems();

	@Override
	public int getRows() {
		if(getMaxPages() != 1) return 6;
		return Math.min((items.size() - 1) / 7 + 3, 6);
	}

	@Override
	public void onClick(InventoryClickEvent event) {
		if(event.getClickedInventory().getHolder() != this) return;

		ItemStack clickedItem = event.getCurrentItem();
		String tag = getTagFromItem(clickedItem);
		if(tag == null) return;
		for(TaggedItem item : items) {
			if(item.getTag() == null || !item.getTag().equals(tag)) continue;
			item.getCallback().accept(event);
		}
	}

	@Override
	public void buildInventory() {
		super.buildInventory();
		setInventory();
	}

	public int getMaxPages() {
		return (items.size() - 1) / allowedSlots.size() + 1;
	}

	public void addItem(Supplier<ItemStack> itemStack) {
		addItem(itemStack, null);
	}

	public void addItem(Supplier<ItemStack> itemStack, Consumer<InventoryClickEvent> callback) {
		items.add(new TaggedItem(UUID.randomUUID().toString(), itemStack, callback));
	}

	@Override
	public void setInventory() {
		super.setInventory();
		for(int i = 0; i < allowedSlots.size(); i++) {
			if((i - 1) / 7 + 3 > getRows()) continue;
			int slot = allowedSlots.get(i);
			getInventory().setItem(slot, new ItemStack(Material.AIR));
		}
		for(TaggedItem item : items) {
			if(getPage(item) != page) continue;
			getInventory().setItem(getSlot(item), item.getTaggedItemStack());
		}
		if(page != 1) {
			previousPage.setItem();
		} else {
			previousPage.removeItem();
		}
		if(page != getMaxPages()) {
			nextPage.setItem();
		} else {
			nextPage.removeItem();
		}
	}

	public int getPage(TaggedItem taggedItem) {
		int index = items.indexOf(taggedItem);
		return index / allowedSlots.size() + 1;
	}

	public int getSlot(TaggedItem taggedItem) {
		int index = items.indexOf(taggedItem);
		return allowedSlots.get(index % allowedSlots.size());
	}
}