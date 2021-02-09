package dev.kyro.arcticapi.misc;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AOutput {

    public String prefix;
    public String errorPrefix;

    public AOutput(String prefix, String errorPrefix) {

        this.prefix = prefix;
        this.errorPrefix = errorPrefix;
    }

    public void send(Player player, String message) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
    }

    public void error(Player player, String message) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', errorPrefix + message));
    }

    public void color(Player player, String message) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

    public void raw(Player player, String message) {

        player.sendMessage(message);
    }

    public void playSound(Player player, String sound, float vol, float pitch) {

        playSound(player, player.getLocation(), sound, vol, pitch);
    }

    public void playSound(Player player, Location loc, String sound, float vol, float pitch) {

        player.playSound(loc, sound, vol, pitch);
    }
}
