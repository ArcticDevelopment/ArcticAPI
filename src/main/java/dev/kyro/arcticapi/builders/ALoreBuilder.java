package dev.kyro.arcticapi.builders;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class ALoreBuilder {

	private List<String> lore = new ArrayList<>();

	/**
	 * Builds lore from the ground.
	 */
	public ALoreBuilder() { }

	/**
	 * Build around a pre-existing item.
	 */
	public ALoreBuilder(ItemStack itemStack) {

		if(itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) lore = itemStack.getItemMeta().getLore();
	}

	/**
	 * Adds a single line of lore.
	 */
	public ALoreBuilder addLore(List<String> lines) {

		lore.addAll(lines);
		return this;
	}

	/**
	 * Add any amount of lines in order to the lore.
	 */
	public ALoreBuilder addLore(String... lines) {

		lore.addAll(Arrays.asList(lines));
		return this;
	}

	/**
	 * Applies one string to the top and bottom of the lore.
	 */
	public ALoreBuilder border(String border) {

		lore.add(0, border);
		lore.add(border);

		return this;
	}

	/**
	 * Translates color codes through the whole lore.
	 */
	public ALoreBuilder colorize() {

		for(int i = 0; i < lore.size(); i++) {

			String line = lore.get(i);

			lore.set(i, ChatColor.translateAlternateColorCodes('&', line));
		}

		return this;
	}

	public List<String> getLore() {
		return lore;
	}
}
