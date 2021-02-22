package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TestCommand extends ASubCommand {

	public TestCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;

//		player.sendMessage("asdf");
		player.sendMessage(WorldGuardHook.hasFlag(player.getLocation(), "arctic-test") + "");

//		Hologram hologram = HologramsAPI.createHologram(Main.INSTANCE, player.getLocation());
//		hologram.appendTextLine("lol");

//		player.openInventory(new ExampleGUI().getFirstPage());
//
//		return;
	}
}
