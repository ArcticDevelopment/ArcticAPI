package dev.kyro.arcticapi.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class ASubCommand implements ACommand {

	private String executor;
	private List<String> aliases = new ArrayList<>();

	public ASubCommand(String executor) {

		this.executor = executor;
	}

	public abstract void execute(CommandSender sender, List<String> args);

	@Override
	public String getExecutor() {
		return executor;
	}

	@Override
	public List<String> getAliases() {
		return aliases;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
