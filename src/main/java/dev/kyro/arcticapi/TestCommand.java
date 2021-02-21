package dev.kyro.arcticapi;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import dev.kyro.arcticapi.commands.ASubCommand;
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

		player.sendMessage("asdfasdf");

		Hologram hologram = HologramsAPI.createHologram(Main.INSTANCE, player.getLocation());
		hologram.appendTextLine("lol");

//		player.openInventory(new ExampleGUI().getFirstPage());
//
//		return;
	}
}
