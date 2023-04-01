package dev.kyro.arcticapi.builders;

import dev.kyro.arcticapi.misc.AUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings("unused")
public class AInventoryBuilder {
	private final Inventory inventory;

	/**
	 * Builds an inventory from the ground.
	 */
	public AInventoryBuilder(InventoryHolder owner, int size, String name) {

		inventory = Bukkit.createInventory(owner, size, name);
	}

	/**
	 * Build around a pre-existing inventory.
	 */
	public AInventoryBuilder(Inventory inventory) {

		this.inventory = inventory;
	}

	public AInventoryBuilder createBorder(Material material, int data) {
		return createBorder(material, data, true);
	}

	/**
	 * Creates a border going around the outside of the GUI.
	 */
	public AInventoryBuilder createBorder(Material material, int toData, boolean overwrite) {

		byte data = (byte) toData;

		for(int i = 0; i < inventory.getSize(); i++) {

			if(i < 9 || i > inventory.getSize() - 10 || i % 9 == 8 || i % 9 == 0) {
				ItemStack itemStack = inventory.getItem(i);
				if(!overwrite && !AUtil.isAirOrNull(itemStack)) continue;
				inventory.setItem(i, new ItemStack(material, 1, data));
			}
		}

		return this;
	}

	/**
	 * Sets any amount of slots in the inventory.
	 */
	public AInventoryBuilder setSlots(Material material, int toData, int... slots) {

		for(int slot : slots) {

			setSlot(material, toData, slot, null, null);
		}

		return this;
	}

	/**
	 * Sets a single slot in the inventory.
	 *
	 * @param name the name of the item; accepts null for no name
	 * @param lore the lore of the item; accepts null for no lore
	 */
	public AInventoryBuilder setSlot(Material material, int toData, int slot, String name, List<String> lore) {

		byte data = (byte) toData;

		ItemStack item = new ItemStack(material, 1, data);
		ItemMeta meta = item.getItemMeta();
		if(name != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
		if(lore != null) meta.setLore(lore);
		item.setItemMeta(meta);

		inventory.setItem(slot, item);

		return this;
	}

	/**
	 * Adds an enchant glint to the items in given inventory slots.
	 * @param hideFlag whether to add the item flag that hides enchants
	 */
	public AInventoryBuilder addEnchantGlint(boolean hideFlag, int... slots) {

		for(int slot : slots) {

			ItemStack item = inventory.getItem(slot);
			AItemStackBuilder itemStackBuilder = new AItemStackBuilder(item).addEnchantGlint(hideFlag);
			inventory.setItem(slot, itemStackBuilder.getItemStack());
	    }

        return this;
    }

    public Inventory getInventory() {

        return inventory;
    }
}
