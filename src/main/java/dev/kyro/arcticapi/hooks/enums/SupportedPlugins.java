package dev.kyro.arcticapi.hooks.enums;

import dev.kyro.arcticapi.hooks.AHook;
import dev.kyro.arcticapi.hooks.SaberFactionsHook;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public enum SupportedPlugins {

	SABER_FACTIONS("Factions");

	private final String name;
	private static final Map<SupportedPlugins, Boolean> supportedPlugins = new HashMap<>();

	SupportedPlugins(String name) {

		this.name = name;
	}

	public boolean isPluginLoaded() {

		return supportedPlugins.get(this);
	}

	public Plugin getPlugin() {

		return Bukkit.getServer().getPluginManager().getPlugin(name);
	}

	public static void getSupportedPlugins() {

		supportedPlugins.clear();

		for (SupportedPlugins supportedPlugin : values()) {

			Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(supportedPlugin.name);

			if (plugin != null && plugin.isEnabled()) {

				PluginDescriptionFile description = plugin.getDescription();

				List<String> authors = description.getAuthors();
				String version = description.getVersion();
				String website = description.getWebsite() != null ? description.getWebsite() : "";

				switch (supportedPlugin) {

					case SABER_FACTIONS:
						supportedPlugins.put(supportedPlugin, authors.contains("Driftay"));
						break;

				}
			} else {
				supportedPlugins.put(supportedPlugin, false);
			}
		}
		loadFactionsHook();
	}

	public static void getHooks() {

		if (supportedPlugins.isEmpty()) getSupportedPlugins();

	    AOutput.log("Plugin Hooks");

        for (SupportedPlugins plugin : supportedPlugins.keySet()) {
            if (plugin.isPluginLoaded()) {

                AOutput.log("Hooked into: " + plugin.name());
            }
        }
    }

    private static void loadFactionsHook() {
        for (SupportedPlugins plugin : values()) {
            if (plugin.isPluginLoaded()) {
                switch (plugin) {
                    case SABER_FACTIONS:
                        AHook.factionsPlugin = new SaberFactionsHook();
                        return;
                }
            }
        }
    }
}