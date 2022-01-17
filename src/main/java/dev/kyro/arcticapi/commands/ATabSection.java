package dev.kyro.arcticapi.commands;

import java.util.ArrayList;
import java.util.List;

public class ATabSection {
//	Only add ATabSection or String objects
	public String identifier;
	public List<Object> children = new ArrayList<>();

	public ATabSection(String identifier) {
		this.identifier = identifier;
	}

	public ATabSection createChild(String identifier) {
		ATabSection tabSection = new ATabSection(identifier);
		children.add(tabSection);
		return tabSection;
	}
}
