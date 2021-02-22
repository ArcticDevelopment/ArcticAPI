package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ABaseCommand;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Plugin INSTANCE;
	public static Plugin WORLDGUARD;

	@Override
	public void onLoad() {

		WORLDGUARD = getServer().getPluginManager().getPlugin("WorldGuard");
		if(WORLDGUARD == null) {

			System.out.println("WorldGuard not found");
		} else {

			System.out.println("WorldGuard loaded");
			WorldGuardHook.registerFlag("arctic-test", false);
		}
	}

	@Override
	public void onEnable() {

		INSTANCE = this;

		loadConfig();

		ArcticAPI.init(this, "", "");

//		getCommand("api").setExecutor(new TestCommand());

		ABaseCommand apiCommand = new TestBase("api");
		apiCommand.registerCommand(new TestCommand("test"));

		ArcticAPI.logAPI("&6asdfasdfasd-------------------");
		ArcticAPI.logAPI("&aasdfasdfasdfasdfasdf-------------------------------");
		ArcticAPI.logAPI("&lasdfasdfasdfasdfasdf-------------------------------");
		ArcticAPI.logAPI("&masdfasdfasdfasdfasdf-------------------------------");
		ArcticAPI.logAPI("&oasdfasdfasdfasdfasdf-------------------------------");
		ArcticAPI.logAPI("&uasdfasdfasdfasdfasdf-------------------------------");
		ArcticAPI.logAPI("&kasdfasdfasdfasdfasdf-------------------------------");
	}

	@Override
	public void onDisable() {

	}

	private void loadConfig() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
