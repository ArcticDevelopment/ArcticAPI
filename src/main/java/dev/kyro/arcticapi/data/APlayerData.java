package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SuppressWarnings("unused")
public class APlayerData implements Listener {

	private static HashMap<UUID, APlayer> playerData = new HashMap<>();

	public static void init() {

		new BukkitRunnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {

					if(!playerData.containsKey(player.getUniqueId())) return;
					APlayer aPlayer = playerData.get(player.getUniqueId());

					APlayerData.updateDefaultFields(player, aPlayer);
				}
			}
		}.runTaskTimer(ArcticAPI.PLUGIN, 20L, 20L);

		File folder = new File(ArcticAPI.PLUGIN.getDataFolder(), "playerdata/");
		if(!folder.exists()) return;
		File[] files = folder.listFiles();

		for(File file : files) {

			if(!file.isFile() || !file.getName().endsWith(".yml")) continue;

			try {

				UUID pUUID = UUID.fromString(file.getName().replaceFirst("[.][^.]+$", ""));
				APlayer aPlayer = new APlayer(pUUID, file);
				playerData.put(pUUID, aPlayer);
			} catch(Exception ignored) {}
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		if(!playerData.containsKey(player.getUniqueId())) return;
		APlayer aPlayer = playerData.get(player.getUniqueId());

		new BukkitRunnable() {
			@Override
			public void run() {
				updateDefaultFields(player, aPlayer);
			}
		}.runTaskLater(ArcticAPI.PLUGIN, 10L);
	}

	public static FileConfiguration getPlayerData(Player player) {

		if(!playerData.containsKey(player.getUniqueId())) return createPlayerData(player).playerdata;
		APlayer aPlayer = playerData.get(player.getUniqueId());

		return aPlayer.playerdata;
	}

	public static Map<UUID, FileConfiguration> getAllData() {

		Map<UUID, FileConfiguration> playerMap = new HashMap<>();

		for(Map.Entry<UUID, APlayer> entry : playerData.entrySet()) {

			playerMap.put(entry.getKey(), entry.getValue().playerdata);
		}

		return playerMap;
	}

	public static void savePlayerData(Player player) {

		if(!playerData.containsKey(player.getUniqueId())) return;
		APlayer aPlayer = playerData.get(player.getUniqueId());

		try {
			aPlayer.playerdata.save(aPlayer.playerFile);
		} catch (IOException ignored) {}
	}

	private static APlayer createPlayerData(Player player) {

		File playerFile = new File(ArcticAPI.PLUGIN.getDataFolder() + "/playerdata/", player.getUniqueId() + ".yml");

		if(!playerFile.exists()) {
			try {
				boolean ignored = playerFile.getParentFile().mkdirs();
				boolean ignored2 = playerFile.createNewFile();
			} catch(IOException exception) {

                exception.printStackTrace();
            }
        }

        APlayer aPlayer = new APlayer(player.getUniqueId(), playerFile);
        playerData.put(player.getUniqueId(), aPlayer);

        return aPlayer;
    }

    private static void updateDefaultFields(Player player, APlayer aPlayer) {

		aPlayer.playerdata.set("name", player.getName());
		aPlayer.playerdata.set("displayname", player.getDisplayName());

		try {
			aPlayer.playerdata.save(aPlayer.playerFile);
		} catch (IOException ignored) {}
	}
}
