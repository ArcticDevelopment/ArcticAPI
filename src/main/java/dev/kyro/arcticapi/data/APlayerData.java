package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class APlayerData implements Listener {
	public static List<UUID> storedPlayers = new ArrayList<>();

	public static void init() {

		new BukkitRunnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					if(!storedPlayers.contains(player.getUniqueId())) continue;
					APlayer aPlayer = getPlayerData(player);
					updateDefaultFields(player, aPlayer);
				}
			}
		}.runTaskTimer(ArcticAPI.INSTANCE, 20L, 20L);

		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
		if(!folder.exists()) return;
		File[] files = folder.listFiles();
		for(File file : files) {
			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;
			try {
				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
				storedPlayers.add(pUUID);
				APlayer aPlayer = new APlayer(pUUID, file);
			} catch(Exception ignored) {}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onJoin(PlayerJoinEvent event) {
		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
		if(!folder.exists()) return;

		Player player = event.getPlayer();
		APlayer aPlayer = getPlayerData(player);

		new BukkitRunnable() {
			@Override
			public void run() {
				updateDefaultFields(player, aPlayer);
			}
		}.runTaskLater(ArcticAPI.INSTANCE, 10L);
	}

	public static APlayer getPlayerData(OfflinePlayer player) {
		return getPlayerData(player.getUniqueId());
	}

	public static APlayer getPlayerData(Player player) {
		return getPlayerData(player.getUniqueId());
	}

	@NotNull
	public static APlayer getPlayerData(UUID uuid) {
		if(!storedPlayers.contains(uuid)) return createPlayerData(uuid);

		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
		if(!folder.exists()) folder.mkdirs();
		File[] files = folder.listFiles();
		for(File file : files) {
			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;
			try {
				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
				if(pUUID != uuid) continue;
				return new APlayer(pUUID, file);
			} catch(Exception ignored) {}
		}
		assert false;
		return null;
	}

	public static Map<UUID, APlayer> getAllData() {
		Map<UUID, APlayer> playerMap = new HashMap<>();

		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
		if(!folder.exists()) return playerMap;
		File[] files = folder.listFiles();
		for(File file : files) {
			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;
			try {
				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
				playerMap.put(pUUID, new APlayer(pUUID, file));
			} catch(Exception ignored) {}
		}
		return playerMap;
	}

	private static APlayer createPlayerData(UUID uuid) {
		File playerFile = new File(ArcticAPI.INSTANCE.getDataFolder() + "/playerdata/", uuid + ".yml");
		if(!playerFile.exists()) {
			try {
				playerFile.getParentFile().mkdirs();
				playerFile.createNewFile();
			} catch(IOException exception) {
				exception.printStackTrace();
			}
		}
		storedPlayers.add(uuid);
		return new APlayer(uuid, playerFile);
	}

	@Deprecated
	public static void savePlayerData(OfflinePlayer player) {
		savePlayerData(player.getUniqueId());
	}

	@Deprecated
	public static void savePlayerData(Player player) {
		savePlayerData(player.getUniqueId());
	}

	@Deprecated
	public static void savePlayerData(UUID uuid) {
		getPlayerData(uuid).save();
	}

    private static void updateDefaultFields(Player player, APlayer aPlayer) {
		aPlayer.playerData.set("name", player.getName());
		aPlayer.playerData.set("displayname", player.getDisplayName());
		aPlayer.save();
	}
}
