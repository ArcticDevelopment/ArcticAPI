package dev.kyro.arcticapi.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class AGUI {
	public Player player;

	private AGUIPanel homePanel;
	private final Map<String, AGUIPanel> panelMap = new HashMap<>();

	public AGUI(Player player) {
		this.player = player;
	}

	public void setHomePanel(AGUIPanel homePanel) {

		this.homePanel = homePanel;
	}

	public void addPanel(String refName, AGUIPanel panel) {

		panelMap.put(refName, panel);
	}

	public void open() {

		if(homePanel != null) player.openInventory(homePanel.getInventory());
	}

	public AGUIPanel getHomePanel() {

		return homePanel;
	}

	public AGUIPanel getPanel(String refName) {

		for(Map.Entry<String, AGUIPanel> entry : panelMap.entrySet()) {
			if(entry.getKey().equalsIgnoreCase(refName)) return entry.getValue();
		}
		return homePanel;
	}
}
