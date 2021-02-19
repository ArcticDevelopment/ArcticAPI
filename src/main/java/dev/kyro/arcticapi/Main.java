package dev.kyro.arcticapi;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

		loadConfig();

		ArcticAPI.init(this, "", "");

		getCommand("api").setExecutor(new TestCommand());
	}

	@Override
	public void onDisable() {

	}

	private void loadConfig() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
