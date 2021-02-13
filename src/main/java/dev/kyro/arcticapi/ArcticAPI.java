package dev.kyro.arcticapi;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.hooks.AHookManager;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class ArcticAPI {

    public static JavaPlugin plugin;

    public static String prefix = "";
    public static String errorPrefix = "";

    public static void init(JavaPlugin plugin, String prefix, String errorPrefix) {

        ArcticAPI.plugin = plugin;

        ArcticAPI.prefix = prefix;
        ArcticAPI.errorPrefix = errorPrefix;

        setup();
    }

    public static void configInit(JavaPlugin plugin, String prefix, String errorPrefix) {

        ArcticAPI.plugin = plugin;

        ArcticAPI.prefix = AConfig.getString(prefix);
        ArcticAPI.errorPrefix = AConfig.getString(errorPrefix);

        setup();
    }

    public static void setup() {

        APlayerData.init();
        AHookManager.SupportedPlugins.printHooks();
    }
}
