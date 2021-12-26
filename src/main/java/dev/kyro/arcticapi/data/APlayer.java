package dev.kyro.arcticapi.data;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@SuppressWarnings("unused")
public class APlayer {

	public UUID pUUID;
	public OfflinePlayer offlinePlayer;
	public File playerFile;
	public FileConfiguration playerData;

	public APlayer(UUID pUUID, File playerFile) {
		this.pUUID = pUUID;
		this.offlinePlayer = Bukkit.getOfflinePlayer(pUUID);
		this.playerFile = playerFile;

		playerData = YamlConfiguration.loadConfiguration(playerFile);
	}

	public void save() {
		try {
			playerData.save(playerFile);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
