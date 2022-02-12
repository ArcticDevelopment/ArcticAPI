package dev.kyro.arcticapi.hooks;

import dev.kyro.arcticapi.hooks.papi.APAPIExpansion;
import dev.kyro.arcticapi.hooks.papi.APAPIPlaceholder;

public class AHook {

	public static void registerPlaceholder(APAPIPlaceholder placeholder) {
		APAPIExpansion.registeredPlaceholders.add(placeholder);
	}
}
