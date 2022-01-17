package dev.kyro.arcticapi.commands;

import java.util.List;

public abstract class ACommand extends ACommandBase {
	private List<ATabSection> staticComplete;

	public ACommand(String executor) {
		super(executor);
	}

	public ACommand(AMultiCommand base, String executor) {
		super(base, executor);
	}

	protected List<ATabSection> getStaticComplete() {
		return staticComplete;
	}

	public ATabSection createChild(String identifier) {
		ATabSection tabSection = new ATabSection(identifier);
		staticComplete.add(tabSection);
		return tabSection;
	}
}
