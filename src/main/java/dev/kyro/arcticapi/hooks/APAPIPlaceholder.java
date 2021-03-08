package dev.kyro.arcticapi.hooks;

import org.bukkit.entity.Player;

@SuppressWarnings("unused")
public interface APAPIPlaceholder {

	String getIdentifier();

	String getValue(Player player);
}
