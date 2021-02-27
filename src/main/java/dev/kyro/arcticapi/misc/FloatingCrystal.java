package dev.kyro.arcticapi.misc;

import dev.kyro.arcticapi.ArcticAPI;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityTeleport;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FloatingCrystal {

	private static final List<FloatingCrystal> crystalList = new ArrayList<>();

	private Location initialLocation;
	private Location currentLocation;
	private ArmorStand armorStand;

	private double degrees = 0;

//	private boolean movingUp = true;
//	private double velocity = 0;

	public FloatingCrystal(Location initialLocation, ItemStack itemStack, boolean isSmall, String message) {

		this.initialLocation = initialLocation.clone();
		currentLocation = initialLocation.clone();

		armorStand = (ArmorStand) currentLocation.getWorld().spawnEntity(currentLocation, EntityType.ARMOR_STAND);
		armorStand.setGravity(false);
//		armorStand.setVisible(false);
//		armorStand.setSmall(isSmall);
		armorStand.setHelmet(itemStack);

//		armorStand.setCustomNameVisible(true);
//		armorStand.setCustomName(message);

		crystalList.add(this);
	}

	public static void init() {

		new BukkitRunnable() {
			@Override
			public void run() {

				for(FloatingCrystal floatingCrystal : crystalList) {

					floatingCrystal.update();
				}
			}
		}.runTaskTimerAsynchronously(ArcticAPI.PLUGIN, 0L, 1L);
	}

	public void update() {

//		Location tempLocation = initialLocation.clone();
//		tempLocation.setY(initialLocation.getY() - 10);
//		armorStand.teleport(tempLocation, PlayerTeleportEvent.TeleportCause.COMMAND);

//		if(armorStand.getLocation().getY() > initialLocation.getY()) return;
//		armorStand.setVelocity(armorStand.getVelocity().setY(0.5));

		armorStand.teleport(currentLocation, PlayerTeleportEvent.TeleportCause.COMMAND);

		double multiplier = 1;
		double yChange = multiplier * (Math.cos(Math.toRadians(degrees)) / 2 + multiplier / 2);
		currentLocation.setY(initialLocation.getY() + yChange);
		currentLocation.setYaw(currentLocation.getYaw() + 5);
		degrees += 5;

		Collection<Entity> nearbyEntities = armorStand.getNearbyEntities(10, 10, 10);
		for(Entity nearbyEntity : nearbyEntities) {

			if(!(nearbyEntity instanceof Player)) continue;

			Player player = (Player) nearbyEntity;
			CraftPlayer craftPlayer = (CraftPlayer) player;

			player.sendMessage("Y: " + currentLocation.getY());

			PacketPlayOutEntityTeleport update = new PacketPlayOutEntityTeleport(
					armorStand.getEntityId(),
					(int) (currentLocation.getX() * 32.0D),
					(int) (currentLocation.getY() * 32.0D),
					(int) (currentLocation.getZ() * 32.0D),
					(byte)((int)(currentLocation.getYaw() * 256.0F / 360.0F)),
					(byte)((int)(currentLocation.getPitch() * 256.0F / 360.0F)),
					false
			);
			craftPlayer.getHandle().playerConnection.sendPacket(update);
		}
	}
}
