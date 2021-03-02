package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
public class AData {

	private File dataFile;
	private FileConfiguration configuration;

	public AData(String fileName) {

		this(fileName, "");
	}

	public AData(String fileName, String path) {

		File dataFile = new File(ArcticAPI.PLUGIN.getDataFolder() + "/" + path, fileName);
		if(!dataFile.exists()) createDataFile(fileName, path);
		configuration = YamlConfiguration.loadConfiguration(dataFile);
	}

	public FileConfiguration getConfiguration() {
		return configuration;
	}

	public void saveDataFile() {

		try {
			configuration.save(dataFile);
		} catch(Exception ignored) {}
	}

	private static FileConfiguration createDataFile(String fileName, String path) {

		File dataFile = new File(ArcticAPI.PLUGIN.getDataFolder() + path, fileName + ".yml");
		if(!dataFile.exists()) {
			try {
				boolean ignored = dataFile.getParentFile().mkdirs();
				boolean ignored2 = dataFile.createNewFile();
			} catch(IOException exception) {

				exception.printStackTrace();
			}
		}

		return YamlConfiguration.loadConfiguration(dataFile);
	}
}