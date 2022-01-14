package dev.kyro.arcticapi.oldcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class AOldSubCommand implements AOldCommand {
	private String executor;
	private List<String> aliases = new ArrayList<>();

	public AOldSubCommand(String executor) {

		this.executor = executor;
	}

	public abstract void execute(CommandSender sender, String label, List<String> args);

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

	@Override
	public List<String> getAutocomplete(Player player) {
		List<String> autocomplete = new ArrayList<>();
		return autocomplete;
	}
}
