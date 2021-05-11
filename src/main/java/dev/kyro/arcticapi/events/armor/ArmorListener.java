package dev.kyro.arcticapi.events.armor;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ArmorListener implements Listener {

	public static Map<UUID, ItemStack[]> previousArmorMap = new HashMap<>();

	static {

		new BukkitRunnable() {
			@Override
			public void run() {

				for(Player player : Bukkit.getOnlinePlayers()) {

					boolean armorChanged = false;

					ItemStack[] currentArmor = new ItemStack[5];
					for(int i = 0; i < 4; i++) {
						currentArmor[i] = player.getInventory().getArmorContents()[i];
					}
					currentArmor[4] = player.getItemInHand();

					previousArmorMap.putIfAbsent(player.getUniqueId(), getEmptyArmor());

					ItemStack[] previousArmor = previousArmorMap.get(player.getUniqueId());

					for(int i = 0; i < 5; i++) {

						if(previousArmor[i].equals(currentArmor[i])) continue;
						if(previousArmor[i].getType() == Material.AIR && currentArmor[i].getType() == Material.AIR) continue;

						Bukkit.getServer().getPluginManager().callEvent(new AChangeEquipment(player, previousArmor[i], currentArmor[i]));
						armorChanged = true;
					}

					if(armorChanged) previousArmorMap.put(player.getUniqueId(), currentArmor);
				}
			}
		}.runTaskTimer(ArcticAPI.INSTANCE, 0L, 1L);
	}

	@EventHandler
	public static void onLeave(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		boolean armorChanged = false;

		ItemStack[] currentArmor = new ItemStack[5];
		for(int i = 0; i < 4; i++) {
			currentArmor[i] = player.getInventory().getArmorContents()[i];
		}
		currentArmor[4] = player.getItemInHand();
		for(int i = 0; i < 5; i++) {

			if(currentArmor[i].getType() == Material.AIR) continue;

			Bukkit.getServer().getPluginManager().callEvent(new AChangeEquipment(player, currentArmor[i], new ItemStack(Material.AIR)));
			armorChanged = true;
		}

		previousArmorMap.remove(player.getUniqueId());
	}

	public static ItemStack[] getEmptyArmor() {

		return new ItemStack[] { new ItemStack(Material.AIR), new ItemStack(Material.AIR), new ItemStack(Material.AIR),
				new ItemStack(Material.AIR), new ItemStack(Material.AIR) };
	}
}
