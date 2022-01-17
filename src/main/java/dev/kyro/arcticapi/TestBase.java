package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.AMultiCommand;

public class TestBase extends AMultiCommand {

	public TestBase(String baseCommand) {
		super(baseCommand);
	}

	public TestBase(AMultiCommand base, String executor) {
		super(base, executor);
	}
}
