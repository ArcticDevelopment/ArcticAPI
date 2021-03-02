package dev.kyro.arcticapi.builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings("unused")
public class AItemStackBuilder {

	private final ItemStack itemStack;
	private ItemMeta itemMeta;

	/**
	 * Builds an item from the ground.
	 */
	public AItemStackBuilder(Material material) {

		this(material, 0);
	}

	/**
	 * Builds an item from the ground.
	 */
	public AItemStackBuilder(Material material, int amount) {

		this(material, amount, 0);
	}

	/**
	 * Builds an item from the ground.
	 */
	public AItemStackBuilder(Material material, int amount, int data) {

		itemStack = new ItemStack(material, amount, (short) data);
		buildItemMeta();
	}

	/**
	 * Build around a pre-existing item.
	 */
	public AItemStackBuilder(ItemStack itemStack) {

		this.itemStack = itemStack;
		buildItemMeta();
	}

	public AItemStackBuilder setName(String name) {

		itemMeta.setDisplayName(name);
		updateItemMeta();

		return this;
	}

	public AItemStackBuilder setLore(List<String> lore) {

		itemMeta.setLore(lore);
		updateItemMeta();

		return this;
	}

	public AItemStackBuilder setLore(ALoreBuilder loreBuilder) {

		itemMeta.setLore(loreBuilder.getLore());
		updateItemMeta();

		return this;
	}

	/**
	 * Adds an enchant glint to the item.
	 * @param hideFlag whether to add the item flag that hides enchants
	 */
	public AItemStackBuilder addEnchantGlint(boolean hideFlag) {

		if(itemStack.getType() == Material.AIR) return this;

		itemStack.addUnsafeEnchantment(Enchantment.DURABILITY, 1);

		if(!hideFlag) return this;

		itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		updateItemMeta();

		return this;
	}

    public ItemStack getItemStack() {
        return itemStack;
    }

    private void buildItemMeta() {

        itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
        updateItemMeta();
    }

    private void updateItemMeta() {

        itemStack.setItemMeta(itemMeta);
    }
}
