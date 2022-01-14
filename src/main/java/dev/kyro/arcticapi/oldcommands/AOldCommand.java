package dev.kyro.arcticapi.oldcommands;

import org.bukkit.entity.Player;

import java.util.List;

@SuppressWarnings("unused")
public interface AOldCommand {

	String getExecutor();
	List<String> getAliases();
	List<String> getAutocomplete(Player player);
}
