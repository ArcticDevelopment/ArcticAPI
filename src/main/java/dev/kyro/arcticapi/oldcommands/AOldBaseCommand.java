package dev.kyro.arcticapi.oldcommands;

import dev.kyro.arcticapi.ArcticAPI;
import dev.kyro.arcticapi.builders.AMessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public abstract class AOldBaseCommand implements CommandExecutor, TabCompleter, AOldCommand {
	private final List<AOldCommand> subCommands = new ArrayList<>();

	private String executor;
	private List<String> aliases = new ArrayList<>();

	public AOldBaseCommand(String executor) {

		this.executor = executor;
		ArcticAPI.INSTANCE.getCommand(executor).setExecutor(this);
		ArcticAPI.INSTANCE.getCommand(executor).setTabCompleter(this);
	}

	public AOldBaseCommand(AOldBaseCommand baseCommand, String executor) {

		this.executor = executor;
		baseCommand.registerCommand(this);
	}

	public abstract void executeBase(CommandSender sender, String label, List<String> args);

	public abstract void executeFail(CommandSender sender, String label, List<String> args);

	@Override
	public String getExecutor() {
		return executor;
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	public void registerCommand(AOldCommand subCommand) {

		subCommands.add(subCommand);
	}

	public void registerCommands(AOldCommand... subCommands) {

		for(AOldCommand subCommand : subCommands) {

			registerCommand(subCommand);
		}
	}

	protected AMessageBuilder createHelp() {

		AMessageBuilder helpMessage = new AMessageBuilder();
		helpMessage.addLine("&b&l/&8&m---------------&7[ &3&lHELP &7]&8&m---------------&b&l\\").colorize();

		for(AOldCommand subCommand : subCommands) {

			String command = "&7 - /&f" + executor + " &3&l" + subCommand.getExecutor();
			helpMessage.addLine(command);
		}

		helpMessage.addLine("&b&l\\&8&m--------------------------------------&b&l/").colorize();

		return helpMessage;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<String> argsList = new ArrayList<>(Arrays.asList(args));

		if(args.length == 0) {
			executeBase(sender, label, argsList);
			return false;
		}

		for(AOldCommand subCommand : subCommands) {
			if(!subCommand.getExecutor().equals(args[0]) && !subCommand.getAliases().contains(args[0])) continue;
			argsList.remove(0);

			if(subCommand instanceof AOldBaseCommand) {
				args = new String[argsList.size()];
				args = argsList.toArray(args);
				((AOldBaseCommand) subCommand).onCommand(sender, command, label, args);
			} else if(subCommand instanceof AOldSubCommand) {
				((AOldSubCommand) subCommand).execute(sender, label, argsList);
			}
			return false;
		}

		executeFail(sender, label, argsList);
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		if(!(sender instanceof Player)) return null;

		List<String> argsList = new ArrayList<>(Arrays.asList(args));

		Player player = (Player) sender;
		return getAutocomplete(player);
	}

	@Override
	public List<String> getAutocomplete(Player player) {
		List<String> autocomplete = new ArrayList<>();
		for(AOldCommand subCommand : subCommands) autocomplete.add(subCommand.getExecutor());
		return autocomplete;
	}
}
