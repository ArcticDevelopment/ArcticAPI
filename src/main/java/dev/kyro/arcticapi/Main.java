package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.AMultiCommand;
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

		AMultiCommand apiCommand = new TestBase("api");
		new TestCommand(apiCommand, "test");
		new TestCommand2(apiCommand, "test2");
		AMultiCommand nest = new TestBase(apiCommand, "nest");
		new TestCommand(nest, "test");
		new TestCommand2(nest, "test2");
		new TestCommand(nest, "test3");
		new TestCommand2(nest, "test4");

		ATestCommand aTestCommand = new ATestCommand();
		getCommand("atest").setExecutor(aTestCommand);
	}

	@Override
	public void onDisable() {

	}

	private void loadConfig() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
