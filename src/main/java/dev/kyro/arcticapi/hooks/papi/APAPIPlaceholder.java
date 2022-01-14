package dev.kyro.arcticapi.hooks.papi;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public interface APAPIPlaceholder {

	String getIdentifier();
	String getValue(Player player);
}
