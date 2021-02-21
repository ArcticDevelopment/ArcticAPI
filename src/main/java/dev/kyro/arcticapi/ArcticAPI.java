package dev.kyro.arcticapi;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.hooks.enums.SupportedPlugins;
import dev.kyro.arcticapi.gui.AInventoryGUIManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class ArcticAPI {

	private static Logger LOGGER = Logger.getLogger("Minecraft");

	public static JavaPlugin PLUGIN;

	public static String prefix = "";
	public static String errorPrefix = "";

	public static void init(JavaPlugin plugin, String prefix, String errorPrefix) {

		ArcticAPI.PLUGIN = plugin;

		ArcticAPI.prefix = prefix;
		ArcticAPI.errorPrefix = errorPrefix;

		setup();
	}

	public static void configInit(JavaPlugin plugin, String prefix, String errorPrefix) {

		ArcticAPI.PLUGIN = plugin;

		ArcticAPI.prefix = AConfig.getString(prefix);
		ArcticAPI.errorPrefix = AConfig.getString(errorPrefix);

		setup();
	}

	public static void setup() {

		APlayerData.init();
		SupportedPlugins.getHooks();

		Bukkit.getPluginManager().registerEvents(new AInventoryGUIManager(), PLUGIN);
	}
}
