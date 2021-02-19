package dev.kyro.arcticapi.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class ASubCommand {

	private String executor;
	private String description;
	private List<String> aliases = new ArrayList<>();

	public ASubCommand(String executor) {

		this(executor, null);
	}

	public ASubCommand(String executor, String description) {

		this.executor = executor;
		this.description = description;
	}

	public abstract void execute(CommandSender sender, List<String> args);

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getAliases() {
		return aliases;
	}

	public void setAliases(List<String> aliases) {
		this.aliases = aliases;
	}
}
