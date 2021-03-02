package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

	public Object get(String path) {

		return configuration.get(path);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getList(String path) {

		return (List<Object>) configuration.getList(path);
	}

	public String getString(String path) {

		return configuration.getString(path);
	}

	public boolean getBoolean(String path) {

		return configuration.getBoolean(path);
	}

	public int getInt(String path) {

		return configuration.getInt(path);
	}

	public double getDouble(String path) {

		return configuration.getDouble(path);
	}

	public List<String> getStringList(String path) {

		return configuration.getStringList(path);
	}
}