package dev.kyro.arcticapi.builders;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class AMessageBuilder {
	private List<String> message = new ArrayList<>();

	/**
	 * Builds a message from the ground.
	 */
	public AMessageBuilder() { }

	/**
	 * Adds a single line to the message.
	 */
	public AMessageBuilder addLines(List<String> lines) {

		message.addAll(lines);
		return this;
	}

	/**
	 * Add any amount of lines in order to the message.
	 */
	public AMessageBuilder addLine(String... lines) {

		message.addAll(Arrays.asList(lines));
		return this;
	}

	/**
	 * Applies one string to the top and bottom of the message.
	 */
	public AMessageBuilder border(String border) {

		message.add(0, border);
		message.add(border);

		return this;
	}

	/**
	 * Translates color codes through the whole message.
	 * @Deprecated Now automatically called when getting lore
	 */
	public AMessageBuilder colorize() {

		for(int i = 0; i < message.size(); i++) {

			String line = message.get(i);

			message.set(i, ChatColor.translateAlternateColorCodes('&', line));
		}

		return this;
	}

	public List<String> getMessage() {
		return colorize().message;
	}

	public void send(Player player) {

		for(String line : message) {

			player.sendMessage(line);
		}
	}
}
