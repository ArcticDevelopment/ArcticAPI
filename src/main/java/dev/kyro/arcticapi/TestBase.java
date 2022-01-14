package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.AMultiCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TestBase extends AMultiCommand {

	public TestBase(String baseCommand) {
		super(baseCommand);
	}

	public TestBase(AMultiCommand base, String executor) {
		super(base, executor);
	}

	@Override
	public void execute(CommandSender sender, Command command, String alias, List<String> args) {
		createHelp().send((Player) sender);
	}
}
