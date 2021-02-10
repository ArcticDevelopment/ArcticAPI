package dev.kyro.arcticapi.misc;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

public class ASound {

    public static void playNearby(Sound sound, Location location, double range) {

        playNearby(sound, location, range, true);
    }

    public static void playNearby(Sound sound, Location location, double range, boolean cuboidRange) {

        playNearby(sound, 1, location, range, cuboidRange);
    }

    public static void playNearby(Sound sound, float pitch, Location location, double range, boolean cuboidRange) {

        playNearby(sound, 1000, pitch, location, range, cuboidRange);
    }

    public static void playNearby(Sound sound, float volume, float pitch, Location location, double range, boolean cuboidRange) {

        Collection<Entity> nearbyEntities = location.getWorld().getNearbyEntities(location, range, range, range);

        for(Entity entity : nearbyEntities) {

            if(!(entity instanceof Player)) continue;

            Player player = (Player) entity;

            if(!cuboidRange) {

                Location pLoc = player.getLocation();
                if(location.distance(pLoc) <= range) continue;
            }

            play(player, sound, volume, pitch);
        }
    }

    public static void play(Player player, Sound sound) {

        play(player, sound, 1);
    }

    public static void play(Player player, Sound sound, float pitch) {

        play(player, sound, 1000, pitch);
    }

    public static void play(Player player, Sound sound, float vol, float pitch) {

        player.playSound(player.getLocation(), sound, vol, pitch);
    }
}
