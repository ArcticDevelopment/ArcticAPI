package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class AData {

	File dataFile;
	private YamlConfiguration data;

	public AData(String fileName) {

		this("", fileName);
	}

	public AData(String filePath, String fileName) {

		dataFile = new File(ArcticAPI.PLUGIN.getDataFolder() + "/" + filePath, fileName);

		try {

			if(!dataFile.exists()) dataFile.createNewFile();
		} catch(Exception ignored) { }

		data = YamlConfiguration.loadConfiguration(dataFile);
	}

	public void set(String path, LinkedHashMap<String, Object> map) {

		for(Map.Entry<String, Object> entry : map.entrySet()) {

			set(path + "." + entry.getKey(), entry.getValue());
		}
	}

	public void set(String path, Object object) {

		data.set(path, object);
	}

	public void addToList(String path, Object object) {

		List<Object> list = getList(path);
		list.add(object);

		set(path, list);
	}

	public void removeFromList(String path, Object object) {

		List<Object> list = getList(path);
		list.remove(object);

		set(path, list);
	}

	public Object get(String path) {

		return data.get(path);
	}

	@SuppressWarnings("unchecked")
	public List<Object> getList(String path) {

		return (List<Object>) data.getList(path);
	}

	public String getString(String path) {

		return data.getString(path);
	}

	public boolean getBoolean(String path) {

		return data.getBoolean(path);
	}

	public int getInt(String path) {

		return data.getInt(path);
	}

	public double getDouble(String path) {

		return data.getDouble(path);
	}

	public List<String> getStringList(String path) {

		return data.getStringList(path);
	}

	public void saveData() {

		if(dataFile == null || data == null) return;

		try {
			data.save(dataFile);
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}