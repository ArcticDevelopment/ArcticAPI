package dev.kyro.arcticapi.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class ARootCommand {

	private String name;
	private String description;
	private List<String> aliases;

	public ARootCommand(String name) {

		this(name, null);
	}

	public ARootCommand(String name, String description) {

		this.name = name;
		this.description = description;
	}

	public abstract boolean execute(CommandSender sender, List<String> args);

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
