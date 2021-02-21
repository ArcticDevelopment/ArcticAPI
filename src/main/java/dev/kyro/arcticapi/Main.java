package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Plugin INSTANCE;

	@Override
	public void onEnable() {

		INSTANCE = this;

		loadConfig();

		ArcticAPI.init(this, "", "");

//		getCommand("api").setExecutor(new TestCommand());

		ABaseCommand apiCommand = new TestBase("api");
		apiCommand.registerCommand(new TestCommand("test"));
	}

	@Override
	public void onDisable() {

	}

	private void loadConfig() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
