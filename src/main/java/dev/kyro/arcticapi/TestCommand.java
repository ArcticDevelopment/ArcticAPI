package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ASubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class TestCommand extends ASubCommand {

	public TestCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {


	}
}
