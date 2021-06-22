package dev.kyro.arcticapi;

import dev.kyro.arcticapi.data.AConfig;
import dev.kyro.arcticapi.data.APlayerData;
import dev.kyro.arcticapi.events.armor.ArmorListener;
import dev.kyro.arcticapi.gui.AGUIManager;
import dev.kyro.arcticapi.guiold.AOldInventoryGUIManager;
import dev.kyro.arcticapi.hooks.APAPIExpansion;
import dev.kyro.arcticapi.hooks.enums.SupportedPlugins;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class ArcticAPI {

	public static JavaPlugin INSTANCE;

	public static String prefix = "";
	public static String errorPrefix = "";

	public static void init(JavaPlugin plugin, String prefix, String errorPrefix) {

		ArcticAPI.INSTANCE = plugin;

		ArcticAPI.prefix = prefix;
		ArcticAPI.errorPrefix = errorPrefix;

		setup();
	}

	public static void configInit(JavaPlugin plugin, String prefix, String errorPrefix) {

		ArcticAPI.INSTANCE = plugin;

		ArcticAPI.prefix = AConfig.getString(prefix);
		ArcticAPI.errorPrefix = AConfig.getString(errorPrefix);

		setup();
	}

	public static void setup() {

		APlayerData.init();
		SupportedPlugins.getHooks();

		Bukkit.getPluginManager().registerEvents(new AGUIManager(), INSTANCE);
		Bukkit.getPluginManager().registerEvents(new AOldInventoryGUIManager(), INSTANCE);
		Bukkit.getPluginManager().registerEvents(new APlayerData(), INSTANCE);
		Bukkit.getPluginManager().registerEvents(new ArmorListener(), INSTANCE);
	}

	public static void setupPlaceholderAPI(@NotNull String identifier) {

		if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") == null) return;

		new APAPIExpansion(identifier).register();
	}

	public static void logAPI(String message) {

		ArcticAPI.INSTANCE.getServer().getConsoleSender().sendMessage(
				"[" + ArcticAPI.INSTANCE.getName() + ":ArcticAPI] " + ChatColor.translateAlternateColorCodes('&', message));
	}
}
