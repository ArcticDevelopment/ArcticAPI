package dev.kyro.arcticapi;

import dev.kyro.arcticapi.builders.AItemStackBuilder;
import dev.kyro.arcticapi.builders.ALoreBuilder;
import dev.kyro.arcticapi.gui.AGUIManager;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
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

		ItemStack back = new AItemStackBuilder(Material.ARROW)
				.setName("&cBack")
				.setLore(new ALoreBuilder(
						"&7Click to go back to the previous page."
				)).getItemStack();
		ItemStack previousPage = new AItemStackBuilder(Material.ARROW)
				.setName("&cPrevious Page")
				.setLore(new ALoreBuilder(
						"&7Click to go to the previous page."
				)).getItemStack();
		ItemStack nextPage = new AItemStackBuilder(Material.ARROW)
				.setName("&cNext Page")
				.setLore(new ALoreBuilder(
						"&7Click to go to the next page."
				)).getItemStack();
		AGUIManager.setDefaultItemStacks(back, previousPage, nextPage);

//		AMultiCommand apiCommand = new TestBase("api");
//		new TestCommand(apiCommand, "test");
//		new TestCommand2(apiCommand, "test2");
//		AMultiCommand nest = new TestBase(apiCommand, "nest");
//		new TestCommand(nest, "test");
//		new TestCommand2(nest, "test2");
//		new TestCommand(nest, "test3");
//		new TestCommand2(nest, "test4");

		ATestCommand aTestCommand = new ATestCommand();
		getCommand("aapi").setExecutor(aTestCommand);
	}

	@Override
	public void onDisable() {

	}

	private void loadConfig() {

		getConfig().options().copyDefaults(true);
		saveConfig();
	}
}
