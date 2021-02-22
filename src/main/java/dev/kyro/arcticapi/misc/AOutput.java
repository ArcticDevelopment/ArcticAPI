package dev.kyro.arcticapi.misc;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class AOutput {

	public static void send(Player player, String message) {

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', ArcticAPI.prefix + message));
	}

	public static void error(Player player, String message) {

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', ArcticAPI.errorPrefix + message));
	}

	public static void color(Player player, String message) {

		player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	public static void raw(Player player, String message) {

		player.sendMessage(message);
	}

	public static void broadcast(String message) {

		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
	}

	public static void log(String message) {

		ArcticAPI.PLUGIN.getServer().getConsoleSender().sendMessage(
				"[" + ArcticAPI.PLUGIN.getName() + "] " + ChatColor.translateAlternateColorCodes('&', message));
	}
}
