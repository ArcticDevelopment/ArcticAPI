package dev.kyro.arcticapi;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.gui.AInventoryGUIManager;
import dev.kyro.arcticapi.hooks.enums.SupportedPlugins;
import dev.kyro.arcticapi.hooks.APAPIExpansion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ArcticAPI {

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
		Bukkit.getPluginManager().registerEvents(new APlayerData(), PLUGIN);
	}

	public static void setupPlaceholderAPI(@NotNull String identifier) {

		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) return;

		new APAPIExpansion(identifier).register();
	}

	public static void logAPI(String message) {

		ArcticAPI.PLUGIN.getServer().getConsoleSender().sendMessage(
				"[" + ArcticAPI.PLUGIN.getName() + ":ArcticAPI] " + ChatColor.translateAlternateColorCodes('&', message));
	}
}
