package dev.kyro.arcticapi.hooks;

import dev.kyro.arcticapi.hooks.interfaces.FactionsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public enum SupportedPlugins {

        SABER_FACTIONS("Factions");

        private String name;
        private static Map<SupportedPlugins, Boolean> cachedPluginState = new HashMap<>();

        SupportedPlugins(String name) {
            this.name = name;
        }

        public boolean isPluginLoaded() {
            return cachedPluginState.get(this);
        }

        public Plugin getPlugin() {
            return Bukkit.getServer().getPluginManager().getPlugin(name);
        }
        public static void updatePluginStates() {
            cachedPluginState.clear();
            for (SupportedPlugins supportedPlugin : values()) {
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(supportedPlugin.name);
                if (plugin != null && plugin.isEnabled()) {
                    List<String> authors = plugin.getDescription().getAuthors();
                    String version = plugin.getDescription().getVersion();
                    String website = plugin.getDescription().getWebsite() != null ? plugin.getDescription().getWebsite() : "";
                    switch (supportedPlugin) {
                        case SABER_FACTIONS:
                            cachedPluginState.put(supportedPlugin, authors.contains("Driftay"));
                            break;
                    }
                } else {
                    cachedPluginState.put(supportedPlugin, false);
                }
            }
            updateFactionPlugin();
        }

        public static void printHooks() {
            if (cachedPluginState.isEmpty()) updatePluginStates();
            System.out.println("&4&lCrazy Enchantment Hooks");
            for (SupportedPlugins plugin : cachedPluginState.keySet()) {
                if (plugin.isPluginLoaded()) {
                    System.out.println("&6&l" + plugin.name() + ": &a&lEnabled");
                }
            }
        }

        private static void updateFactionPlugin() {
            for (SupportedPlugins supportedPlugin : values()) {
                if (supportedPlugin.isPluginLoaded()) {
                    switch (supportedPlugin) {
                        case SABER_FACTIONS:
                            factionsPlugin = new SaberFactionsHook();
                            return;
                    }
                }
            }
        }
    }
}
