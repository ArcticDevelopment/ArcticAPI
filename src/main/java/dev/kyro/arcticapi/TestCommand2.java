package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ACommand;
import dev.kyro.arcticapi.commands.AMultiCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestCommand2 extends ACommand {
	public TestCommand2(AMultiCommand base, String executor) {
		super(base, executor);
	}

	@Override
	public void execute(CommandSender sender, Command command, String alias, List<String> args) {

	}

	@Override
	public List<String> getTabComplete(Player player, String current, List<String> args) {
		ArrayList<String> tabComplete = new ArrayList<>();
		tabComplete.add("test2");
		tabComplete.add("command2");
		return tabComplete;
	}
}