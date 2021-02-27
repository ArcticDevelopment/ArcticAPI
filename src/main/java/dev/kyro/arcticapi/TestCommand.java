package dev.kyro.arcticapi;

import dev.kyro.arcticapi.commands.ASubCommand;
import dev.kyro.arcticapi.libs.discord.DiscordWebhook;
import dev.kyro.arcticapi.hooks.pluginhooks.WorldGuardHook;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.awt.*;
import java.util.List;

public class TestCommand extends ASubCommand {

	public TestCommand(String name) {
		super(name);
	}

	@Override
	public void execute(CommandSender sender, List<String> args) {

		if(!(sender instanceof Player)) return;
		Player player = (Player) sender;

//		player.sendMessage("asdf");
		player.sendMessage(WorldGuardHook.hasFlag(player.getLocation(), "arctic-test") + "");

		DiscordWebhook webhook = new DiscordWebhook(
				"https://discord.com/api/webhooks/813971879219429430/VkkAd6KlbXQiy-QdFcLmb1sZqEsDLU3FK5VjyZ_yTe0f4XFMYr2FOQKwB_53s0ge5_5j");
		webhook.setContent("Any message!");
		webhook.setAvatarUrl("https://your.awesome/image.png");
		webhook.setUsername("Custom Usernames!");
		webhook.setTts(true);
		webhook.addEmbed(new DiscordWebhook.EmbedObject()
				.setTitle("Title")
				.setDescription("This is a description")
				.setColor(Color.RED)
				.addField("1st Field", "Inline", true)
				.addField("2nd Field", "Inline", true)
				.addField("3rd Field", "No-Inline", false)
				.setThumbnail("https://kryptongta.com/images/kryptonlogo.png")
				.setFooter("Footer text", "https://kryptongta.com/images/kryptonlogodark.png")
				.setImage("https://kryptongta.com/images/kryptontitle2.png")
				.setAuthor("Author Name", "https://kryptongta.com", "https://kryptongta.com/images/kryptonlogowide.png")
				.setUrl("https://kryptongta.com"));
		webhook.addEmbed(new DiscordWebhook.EmbedObject()
				.setDescription("Just another added embed object!"));
		try {

			webhook.execute(); //Handle exception
		} catch(Exception e) {

			e.printStackTrace();
		}


//		Hologram hologram = HologramsAPI.createHologram(Main.INSTANCE, player.getLocation());
//		hologram.appendTextLine("lol");

//		player.openInventory(new ExampleGUI().getFirstPage());
//
//		return;
	}
}
