package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class AData {

	private final File dataFile;
	private FileConfiguration configuration;

	private final String fileName;
	private final String path;

	public AData(String fileName) {

		this(fileName, "");
	}

	public AData(String fileName, String path) {

		this(fileName, path, true);
	}

	public AData(String fileName, String path, boolean saveResource) {

		this.fileName = fileName;
		this.path = path;

		dataFile = new File(ArcticAPI.PLUGIN.getDataFolder() + "/" + path, fileName + ".yml");
		if(!dataFile.exists()) createDataFile(fileName, path);
		configuration = YamlConfiguration.loadConfiguration(dataFile);
		if(saveResource) ArcticAPI.PLUGIN.saveResource(fileName, true);
	}

	public FileConfiguration getConfiguration() {
		return configuration;
	}

	public void reloadDataFile() {

		if(!dataFile.exists()) createDataFile(fileName, path);
		configuration = YamlConfiguration.loadConfiguration(dataFile);
	}

	public void saveDataFile() {

		if(!dataFile.exists()) createDataFile(fileName, path);

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

	public void set(String path, Object object) {

		configuration.set(path, object);
	}

	public void addToList(String path, Object object) {

		List<Object> list = getList(path) != null ? getList(path) : new ArrayList<>();
		list.add(object);

		set(path, list);
	}

	public void removeFromList(String path, Object object) {

		List<Object> list = getList(path);
		list.remove(object);

		set(path, list);
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