package dev.kyro.arcticapi.commands;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public abstract class ASubCommand {

	private String name;
	private String description;
	private List<String> aliases = new ArrayList<>();

	public ASubCommand(String name) {

		this(name, null);
	}

	public ASubCommand(String name, String description) {

		this.name = name;
		this.description = description;
	}

	public abstract void execute(CommandSender sender, List<String> args);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
