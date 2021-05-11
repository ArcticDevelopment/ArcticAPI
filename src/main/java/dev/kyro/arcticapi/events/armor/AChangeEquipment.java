package dev.kyro.arcticapi.events.armor;

import dev.kyro.arcticapi.enums.ArmorType;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @author Arnah
 * @since Jul 30, 2015
 */
public class AChangeEquipment extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancel = false;
	private final ArmorType armorType;
	private final ItemStack oldArmorPiece, newArmorPiece;

	public AChangeEquipment(Player player, ItemStack oldArmorPiece, ItemStack newArmorPiece) {
		super(player);
//		TODO: Fix armor type (now including held item so this doesn't work really
		this.armorType = ArmorType.getType(oldArmorPiece != null ? oldArmorPiece : newArmorPiece);
		this.oldArmorPiece = oldArmorPiece;
		this.newArmorPiece = newArmorPiece;
	}

	@Override
	public boolean isCancelled() {
		return cancel;
	}

	@Override
	public void setCancelled(boolean cancel) {
		this.cancel = cancel;
	}

	public static HandlerList getHandlerList(){
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public ArmorType getArmorType() {
		return armorType;
	}

	public ItemStack getOldArmorPiece() {
		return oldArmorPiece;
	}

	public ItemStack getNewArmorPiece() {
		return newArmorPiece;
	}
}
