package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ABaseCommand;
import dev.kyro.arcticapi.misc.AOutput;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class TestBase extends ABaseCommand {

	public TestBase(String baseCommand) {
		super(baseCommand);
	}

	@Override
	public void executeBase(CommandSender sender, List<String> args) {

		if(sender instanceof Player) {

			AOutput.send((Player) sender, "Base command");
		} else {

			System.out.println("Base command");
		}
	}

	@Override
	public void executeFail(CommandSender sender, List<String> args) {

		if(sender instanceof Player) {

			AOutput.send((Player) sender, "fail");
		} else {

			System.out.println("fail");
		}
	}
}
