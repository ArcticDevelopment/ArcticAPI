package dev.kyro.arcticapi.commands;

import dev.kyro.arcticapi.ArcticAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class ACommandBase implements CommandExecutor, TabCompleter {
	private String executor;
	private boolean isBase;

	public ACommandBase(String executor) {
		this.executor = executor;
		this.isBase = true;

		ArcticAPI.INSTANCE.getCommand(executor).setExecutor(this);
		ArcticAPI.INSTANCE.getCommand(executor).setTabCompleter(this);
	}

	public ACommandBase(AMultiCommand base, String executor) {
		this.executor = executor;
		this.isBase = false;

		base.registerCommand(this);
	}

//	Command execution
	public abstract void execute(CommandSender sender, Command command, String alias, List<String> args);
//	Tab completion (not needed for base commands)
	public abstract List<String> getTabComplete(Player player, String current, List<String> args);

	public String getExecutor() {
		return executor;
	}

	public boolean isBase() {
		return isBase;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] argsArray) {
		List<String> args = Arrays.asList(argsArray);

		execute(sender, command, alias, args);
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] argsArray) {
		if(!(sender instanceof Player)) return null;
		Player player = (Player) sender;
		List<String> args = new ArrayList<>(Arrays.asList(argsArray));

		String current = args.remove(0);
		return getTabComplete(player, current, args);
	}
 }
