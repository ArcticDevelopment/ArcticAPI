package dev.kyro.arcticapi;

import dev.kyro.arcticapi.data.AConfig;
import org.bukkit.plugin.java.JavaPlugin;

public class ArcticAPI {

    public static JavaPlugin plugin;

    public static String prefix = "";
    public static String errorPrefix = "";

    public static void init(JavaPlugin plugin, String prefix, String errorPrefix) {

        ArcticAPI.plugin = plugin;

        ArcticAPI.prefix = prefix;
        ArcticAPI.errorPrefix = errorPrefix;
    }

    public static void configInit(JavaPlugin plugin, String prefix, String errorPrefix) {

        ArcticAPI.plugin = plugin;

        ArcticAPI.prefix = AConfig.getString(prefix);
        ArcticAPI.errorPrefix = AConfig.getString(errorPrefix);
    }
}
