package dev.kyro.arcticapi.commands;

import java.util.List;

@SuppressWarnings("unused")
public interface ACommand {

	String getExecutor();
	List<String> getAliases();
}
