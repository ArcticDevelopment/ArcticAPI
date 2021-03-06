package dev.kyro.arcticapi.commands;

import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.builders.AMessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public abstract class ABaseCommand implements CommandExecutor, ACommand {

	private final List<ACommand> subCommands = new ArrayList<>();

	private String executor;
	private List<String> aliases = new ArrayList<>();

	public ABaseCommand(String executor) {

		this.executor = executor;
		ArcticAPI.PLUGIN.getCommand(executor).setExecutor(this);
	}

	public ABaseCommand(ABaseCommand baseCommand, String executor) {

		this.executor = executor;
		baseCommand.registerCommand(this);
	}

	public abstract void executeBase(CommandSender sender, List<String> args);

	public abstract void executeFail(CommandSender sender, List<String> args);

	@Override
	public String getExecutor() {
		return executor;
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	public void registerCommand(ACommand subCommand) {

		subCommands.add(subCommand);
	}

	public void registerCommands(ACommand... subCommands) {

		for(ACommand subCommand : subCommands) {

			registerCommand(subCommand);
		}
	}

	protected AMessageBuilder createHelp() {

		AMessageBuilder helpMessage = new AMessageBuilder();
		helpMessage.addLine("&b&l/&8&m---------------&7[ &3&lHelp &7]&8&m---------------&b&l\\").colorize();

		for(ACommand subCommand : subCommands) {

			String command = "&7 - /&f" + executor + " &3&l" + subCommand.getExecutor();
			helpMessage.addLine(command);
		}

		helpMessage.addLine("&b&l\\&8&m------------------------------------&b&l/").colorize();

		return helpMessage;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> argsList = new ArrayList<>(Arrays.asList(args));

		if(args.length == 0) {

			executeBase(sender, argsList);
			System.out.println(executor + " why");
			return false;
		}

		for(ACommand subCommand : subCommands) {

			if(!subCommand.getExecutor().equals(args[0]) && !subCommand.getAliases().contains(args[0])) continue;
			argsList.remove(0);

			if(subCommand instanceof ABaseCommand) {
				args = argsList.toArray(args);
				((ABaseCommand) subCommand).onCommand(sender, cmd, label, args);
				System.out.println(executor  + " executing base: " + subCommand.getExecutor());
			} else if(subCommand instanceof ASubCommand) {
				((ASubCommand) subCommand).execute(sender, argsList);
				System.out.println(executor  + " executing: " + subCommand.getExecutor());
			}
			return false;
		}

		executeFail(sender, argsList);
		System.out.println(executor + " fail");
		return false;
	}
}
