package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ABaseCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	public static Plugin INSTANCE;
	public static Plugin WORLDGUARD;

	@Override
	public void onEnable() {

		INSTANCE = this;

		loadConfig();

		ArcticAPI.init(this, "", "");

		getServer().getPluginManager().registerEvents(this, this);

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
