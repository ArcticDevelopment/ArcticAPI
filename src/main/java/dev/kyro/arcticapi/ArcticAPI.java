package dev.kyro.arcticapi;

import org.bukkit.plugin.java.JavaPlugin;

public class ArcticAPI {

    public static JavaPlugin plugin;

    public static String prefix = "";
    public static String errorPrefix = "";

    public static void setup(JavaPlugin plugin, String prefix, String errorPrefix) {

        ArcticAPI.plugin = plugin;

        ArcticAPI.prefix = prefix;
        ArcticAPI.errorPrefix = errorPrefix;
    }
}
