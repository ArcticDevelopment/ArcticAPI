package dev.kyro.arcticapi.commands;

import dev.kyro.arcticapi.builders.AMessageBuilder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class AMultiCommand extends ACommandBase {
	private final List<ACommandBase> subCommands = new ArrayList<>();

	public AMultiCommand(String executor) {
		super(executor);
	}

	public AMultiCommand(AMultiCommand base, String executor) {
		super(base, executor);
	}

	protected void registerCommand(ACommandBase subCommand) {

		subCommands.add(subCommand);
	}

	public List<ACommandBase> getSubCommands() {
		return subCommands;
	}

	@Override
	public void execute(CommandSender sender, Command command, String alias, List<String> args) {

		if(args.isEmpty()) {
			if(sender instanceof Player) createHelp().send((Player) sender);
			return;
		}

		for(ACommandBase subCommand : subCommands) {
			if(!subCommand.getExecutor().equals(args.get(0))) continue;
			args.remove(0);

			subCommand.execute(sender, command, alias, args);
			return;
		}

		if(sender instanceof Player) createHelp().send((Player) sender);
	}

	public List<String> getTabComplete(Player player, String current, List<String> args) {
		List<String> tabComplete = new ArrayList<>();

		if(current.isEmpty() || args.isEmpty()) {
			for(ACommandBase subCommand : subCommands) {
				if(!current.isEmpty() && !subCommand.getExecutor().startsWith(current)) continue;
				tabComplete.add(subCommand.getExecutor());
			}
			return tabComplete;
		}

		for(ACommandBase subCommand : subCommands) {
			if(!subCommand.getExecutor().equalsIgnoreCase(current)) continue;
			current = args.remove(0);
			return subCommand.getTabComplete(player, current, args);
		}
		return tabComplete;
	}

	protected AMessageBuilder createHelp() {

		AMessageBuilder helpMessage = new AMessageBuilder();
		helpMessage.addLine("&b&l/&8&m---------------&7[ &3&lHELP &7]&8&m---------------&b&l\\").colorize();

		for(ACommandBase subCommand : subCommands) {

			String command = "&7 - /&f" + getExecutor() + " &3&l" + subCommand.getExecutor();
			helpMessage.addLine(command);
		}

		helpMessage.addLine("&b&l\\&8&m--------------------------------------&b&l/").colorize();

		return helpMessage;
	}
}
