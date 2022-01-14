package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
public class APlayerData implements Listener {
	public static Map<UUID, File> storedPlayers = new HashMap<>();

	public static void init() {
		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
		if(!folder.exists()) return;
		File[] files = folder.listFiles();
		for(File file : files) {
			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;
			try {
				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
				storedPlayers.put(pUUID, file);
				APlayer aPlayer = new APlayer(pUUID, file);
			} catch(Exception ignored) {}
		}
	}

//	@EventHandler(priority = EventPriority.MONITOR)
//	public static void onJoin(PlayerJoinEvent event) {
//		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
//		if(!folder.exists()) return;
//
//		Player player = event.getPlayer();
//
//		new BukkitRunnable() {
//			@Override
//			public void run() {
//				APlayer aPlayer = getPlayerData(player);
//				aPlayer.playerData.set("name", player.getName());
//			}
//		}.runTaskLater(ArcticAPI.INSTANCE, 1L);
//	}

	public static APlayer getPlayerData(OfflinePlayer player) {
		return getPlayerData(player.getUniqueId());
	}

	public static APlayer getPlayerData(Player player) {
		return getPlayerData(player.getUniqueId());
	}

	@NotNull
	public static APlayer getPlayerData(UUID uuid) {
		if(!storedPlayers.containsKey(uuid)) return createPlayerData(uuid);
		return new APlayer(uuid, storedPlayers.get(uuid));

//		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
//		if(!folder.exists()) folder.mkdirs();
//		File[] files = folder.listFiles();
//		for(File file : files) {
//			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;
//			try {
//				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
//				if(!pUUID.equals(uuid)) continue;
//				return new APlayer(pUUID, file);
//			} catch(Exception ignored) {}
//		}
//		assert false;
//		return null;
	}

	public static Map<UUID, APlayer> getAllData() {
		Map<UUID, APlayer> playerMap = new HashMap<>();
		for(Map.Entry<UUID, File> entry : storedPlayers.entrySet()) {
			playerMap.put(entry.getKey(), new APlayer(entry.getKey(), entry.getValue()));
		}
		return playerMap;

//		File folder = new File(ArcticAPI.INSTANCE.getDataFolder(), "playerdata/");
//		if(!folder.exists()) return playerMap;
//		File[] files = folder.listFiles();
//		for(File file : files) {
//			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;
//			try {
//				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
//				playerMap.put(pUUID, new APlayer(pUUID, file));
//			} catch(Exception ignored) {}
//		}
//		return playerMap;
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
		storedPlayers.put(uuid, playerFile);
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
}
