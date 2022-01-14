package dev.kyro.arcticapi.hooks.papi;

import dev.kyro.arcticapi.ArcticAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class APAPIExpansion extends PlaceholderExpansion {
	public static List<APAPIPlaceholder> registeredPlaceholders = new ArrayList<>();

	private final String identifier;

	public APAPIExpansion(@NotNull String identifier) {
		this.identifier = identifier;
	}

	@Override
	public boolean persist(){
		return true;
	}

	@Override
	public boolean canRegister(){
		return true;
	}

	@Override
	public @NotNull String getAuthor(){
		return "KyroKrypt";
	}

	@Override
	public @NotNull String getIdentifier(){
		return identifier;
	}

	@Override
	public @NotNull String getVersion(){
		return ArcticAPI.INSTANCE.getDescription().getVersion();
	}

	@Override
	public String onPlaceholderRequest(Player player, @NotNull String identifier){

		if(player == null) return "";

		for(APAPIPlaceholder registeredPlaceholder : registeredPlaceholders) {

			if(!registeredPlaceholder.getIdentifier().equalsIgnoreCase(identifier)) continue;

			return registeredPlaceholder.getValue(player);
		}

		return null;
	}
}
