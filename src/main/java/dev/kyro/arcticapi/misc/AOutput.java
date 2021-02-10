package dev.kyro.arcticapi.misc;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AOutput {

    public static void send(Player player, String message) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ArcticAPI.getPrefix() + message));
    }

    public static void error(Player player, String message) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ArcticAPI.getErrorPrefix() + message));
    }

    public static void color(Player player, String message) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public static void raw(Player player, String message) {

        player.sendMessage(message);
    }
}
