package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ABaseCommand;
import dev.kyro.arcticapi.events.armor.AChangeEquipmentEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

	public static Plugin INSTANCE;
	public static Plugin WORLDGUARD;

	@EventHandler
	public static void onArmorChange(AChangeEquipmentEvent event) {

		System.out.println(event.getArmorType() + "");
		System.out.println("old: " + event.getOldArmorPiece().getType());
		System.out.println("new: " + event.getNewArmorPiece().getType());
	}

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
