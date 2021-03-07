package dev.kyro.arcticapi.misc;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collections;
import java.util.TreeMap;

@SuppressWarnings("unused")
public class AUtil {

	private final static TreeMap<Integer, String> map = new TreeMap<>();

	static {

		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "IX");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");

	}

	public static String toRoman(int number) {
		int l =  map.floorKey(number);
		if ( number == l ) {
			return map.get(number);
		}
		return map.get(l) + toRoman(number-l);
	}

	public static String createProgressBar(String tickMark, ChatColor fullColor, ChatColor emptyColor, int length, double percentFull) {

		int full = (int) (length * percentFull);
		return fullColor.toString() + String.join("", Collections.nCopies(full, tickMark))
				+ emptyColor.toString() + String.join("", Collections.nCopies(length - full, tickMark));
	}

	public static void giveItemSafely(Player player, ItemStack itemStack, boolean secondChance) {

		Inventory inventory = player.getInventory();

		if(inventory.firstEmpty() != -1) {

			inventory.addItem(itemStack);
			return;
		}

		ASound.play(player, Sound.NOTE_PLING);

		if(!secondChance) {

			AOutput.send(player, "&4&lFULL INVENTORY! Item dropped at your current location");
			player.getWorld().dropItem(player.getLocation(), itemStack);
			return;
		}

		AOutput.error(player, "&4&lFULL INVENTORY!" +
				" Please make space for an item to be added or it will be dropped on the ground in 5 seconds.");

		new BukkitRunnable() {
			@Override
			public void run() {
				Inventory inventory = player.getInventory();

				if(inventory.firstEmpty() != -1) {

					inventory.addItem(itemStack);
					AOutput.send(player, "The item has been safely added to your inventory");
				} else {

					AOutput.send(player, "The item has been dropped on the ground at your location");
					player.getWorld().dropItem(player.getLocation(), itemStack);
				}
			}
		}.runTaskLater(ArcticAPI.PLUGIN, 100L);
	}
}

