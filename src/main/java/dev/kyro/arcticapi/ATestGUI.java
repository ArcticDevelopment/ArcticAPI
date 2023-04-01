package dev.kyro.arcticapi;

import dev.kyro.arcticapi.gui.AGUI;
import org.bukkit.entity.Player;

public class ATestGUI extends AGUI {

	public ATestGUI(Player player) {
		super(player);

		ATestGUIPanel testGUIPanel = new ATestGUIPanel(this);
		setHomePanel(testGUIPanel);
	}
}
