package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class AConfig {

    public static FileConfiguration config = ArcticAPI.PLUGIN.getConfig();

    public static void set(String path, LinkedHashMap<String, Object> map) {

        for(Map.Entry<String, Object> entry : map.entrySet()) {

            set(path + "." + entry.getKey(), entry.getValue());
        }
    }

    public static void set(String path, Object object) {

        config.set(path, object);
    }

    public static void addToList(String path, Object object) {

        List<Object> list = getList(path);
        list.add(object);

        set(path, list);
    }

    public static void removeFromList(String path, Object object) {

        List<Object> list = getList(path);
        list.remove(object);

        set(path, list);
    }

    public static Object get(String path) {

        return config.get(path);
    }

    @SuppressWarnings("unchecked")
    public static List<Object> getList(String path) {

        return (List<Object>) config.getList(path);
    }

    public static String getString(String path) {

        return config.getString(path);
    }

    public static boolean getBoolean(String path) {

        return config.getBoolean(path);
    }

    public static int getInt(String path) {

        return config.getInt(path);
    }

    public static double getDouble(String path) {

        return config.getDouble(path);
    }

    public static List<String> getStringList(String path) {

        return config.getStringList(path);
    }
}
