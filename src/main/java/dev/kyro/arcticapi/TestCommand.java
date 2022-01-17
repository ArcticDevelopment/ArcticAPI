package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ACommand;
import dev.kyro.arcticapi.commands.AMultiCommand;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TestCommand extends ACommand {
	public TestCommand(AMultiCommand base, String executor) {
		super(base, executor);
	}

	@Override
	public void execute(CommandSender sender, Command command, String alias, List<String> args) {
		AOutput.broadcast("test");
	}

	@Override
	public List<String> getTabComplete(Player player, String current, List<String> args) {
		ArrayList<String> tabComplete = new ArrayList<>();
		tabComplete.add("test1");
		tabComplete.add("command1");
		return tabComplete;
	}
}