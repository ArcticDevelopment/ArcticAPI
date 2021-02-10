package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class AConfig {

    public static FileConfiguration config = ArcticAPI.plugin.getConfig();

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
