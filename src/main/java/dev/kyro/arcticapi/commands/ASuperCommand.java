package dev.kyro.arcticapi.commands;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ASuperCommand implements CommandExecutor {

	private String baseCommand;
	private List<ARootCommand> subCommands = new ArrayList<>();

	public ASuperCommand(String baseCommand) {

		this.baseCommand = baseCommand;

		ArcticAPI.PLUGIN.getCommand(baseCommand).setExecutor(this);
	}

	public void registerCommand(ARootCommand subCommand) {

		subCommands.add(subCommand);
	}

	public void registerCommands(ARootCommand... subCommands) {

		for(ARootCommand subCommand : subCommands) {

			registerCommand(subCommand);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		for(ARootCommand subCommand : subCommands) {

			if(!subCommand.getName().equals(args[0]) && !subCommand.getAliases().contains(args[0])) continue;

			List<String> argsList = Arrays.asList(args);
			argsList.remove(0);

			return subCommand.execute(sender, argsList);
		}

		return false;
	}
}
