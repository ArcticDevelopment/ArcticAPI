package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {

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
