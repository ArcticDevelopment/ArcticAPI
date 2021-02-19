package dev.kyro.arcticapi.commands;

import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.builders.AMessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ABaseCommand implements CommandExecutor {

	private final List<ASubCommand> subCommands = new ArrayList<>();

	private String baseExecutor;

	public ABaseCommand(String baseExecutor) {

		this.baseExecutor = baseExecutor;
		ArcticAPI.PLUGIN.getCommand(baseExecutor).setExecutor(this);
	}

	public abstract void executeBase(CommandSender sender, List<String> args);

	public abstract void executeFail(CommandSender sender, List<String> args);

	public void registerCommand(ASubCommand subCommand) {

		subCommands.add(subCommand);
	}

	public void registerCommands(ASubCommand... subCommands) {

		for(ASubCommand subCommand : subCommands) {

			registerCommand(subCommand);
		}
	}

	private AMessageBuilder createHelp() {

		AMessageBuilder helpMessage = new AMessageBuilder();

		for(ASubCommand subCommand : subCommands) {

			String command = "&f/" + baseExecutor + " " + subCommand.getExecutor()
					+ (subCommand.getDescription() != null ? " - " + subCommand.getDescription() : "");
			helpMessage.addLine(command);
		}

		helpMessage.border("&f&m--------------------&f< HELP >&m--------------------");

		return helpMessage;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> argsList = new ArrayList<>(Arrays.asList(args));

		if(args.length == 0) {

			executeBase(sender, argsList);
			return false;
		}

		for(ASubCommand subCommand : subCommands) {

			if(!subCommand.getExecutor().equals(args[0]) && !subCommand.getAliases().contains(args[0])) continue;

			argsList.remove(0);

			subCommand.execute(sender, argsList);
			return false;
		}

		executeFail(sender, argsList);
		return false;
	}
}
