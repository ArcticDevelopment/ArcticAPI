package dev.kyro.arcticapi.hooks;

import dev.kyro.arcticapi.hooks.interfaces.FactionsPlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public class AHookManager {

    public static FactionsPlugin factionsPlugin;

    public static boolean isFriendly(Player player, Player otherPlayer) {

        return factionsPlugin != null && factionsPlugin.isFriendly(player, otherPlayer);
    }

    public static boolean inUnclaimed(Player player) {

        return factionsPlugin != null && factionsPlugin.inUnclaimed(player);
    }

    public static boolean inOwnTerritory(Player player) {

        return factionsPlugin != null && factionsPlugin.inOwnTerritory(player);
    }

    public static boolean inUnclaimed(Location location) {

        return factionsPlugin != null && factionsPlugin.inUnclaimed(location);
    }
}
