package dev.kyro.arcticapi;

import org.bukkit.event.Listener;

/**
 * @author Arnah
 * @since Jul 30, 2015
 */
public class TrashArmorListener implements Listener {

//	public static List<String> blockedMaterials = new ArrayList<>();
//
//	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//	public void onClick(InventoryClickEvent event) {
//
//		if(!(event.getWhoClicked() instanceof Player)) return;
//
//		boolean shift = false, numberKey = false;
//		if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) shift = true;
//		if(event.getClick() == ClickType.NUMBER_KEY) numberKey = true;
//
//		if(event.getSlotType() != InventoryType.SlotType.ARMOR && event.getSlotType() !=
//				InventoryType.SlotType.QUICKBAR && event.getSlotType() != InventoryType.SlotType.CONTAINER) return;
//		if(event.getClickedInventory() != null && !event.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
//		if (!event.getInventory().getType().equals(InventoryType.CRAFTING) && !event.getInventory().getType().equals(InventoryType.PLAYER)) return;
//
//		ArmorType newArmorType = ArmorType.getType(shift ? event.getCurrentItem() : event.getCursor());
//		if(shift){
//			newArmorType = ArmorType.getType(event.getCurrentItem());
//			if(newArmorType != null){
//				boolean equipping = event.getRawSlot() != newArmorType.getSlot();
//				if(newArmorType.equals(ArmorType.HELMET) && (equipping == isAirOrNull(event.getWhoClicked().getInventory().getHelmet())) ||
//						newArmorType.equals(ArmorType.CHESTPLATE) && (equipping == isAirOrNull(event.getWhoClicked().getInventory().getChestplate())) ||
//						newArmorType.equals(ArmorType.LEGGINGS) && (equipping == isAirOrNull(event.getWhoClicked().getInventory().getLeggings())) ||
//						newArmorType.equals(ArmorType.BOOTS) && (equipping == isAirOrNull(event.getWhoClicked().getInventory().getBoots()))){
//					AArmorModifyEvent AArmorModifyEvent = new AArmorModifyEvent((Player) event.getWhoClicked(), EquipMethod.SHIFT_CLICK,
//							newArmorType, equipping ? null : event.getCurrentItem(), equipping ? event.getCurrentItem() : null);
//					Bukkit.getServer().getPluginManager().callEvent(AArmorModifyEvent);
//					if(AArmorModifyEvent.isCancelled()){
//						event.setCancelled(true);
//					}
//				}
//			}
//		}else{
//			ItemStack newArmorPiece = event.getCursor();
//			ItemStack oldArmorPiece = event.getCurrentItem();
//			if(numberKey){
//				if(event.getClickedInventory().getType().equals(InventoryType.PLAYER)){// Prevents shit in the 2by2 crafting
//					// event.getClickedInventory() == The players inventory
//					// event.getHotBarButton() == key people are pressing to equip or unequip the item to or from.
//					// event.getRawSlot() == The slot the item is going to.
//					// event.getSlot() == Armor slot, can't use event.getRawSlot() as that gives a hotbar slot ;-;
//					ItemStack hotbarItem = event.getClickedInventory().getItem(event.getHotbarButton());
//					if(!isAirOrNull(hotbarItem)){// Equipping
//						newArmorType = ArmorType.getType(hotbarItem);
//						newArmorPiece = hotbarItem;
//						oldArmorPiece = event.getClickedInventory().getItem(event.getSlot());
//					}else{// Unequipping
//						newArmorType = ArmorType.getType(!isAirOrNull(event.getCurrentItem()) ? event.getCurrentItem() : event.getCursor());
//					}
//				}
//			}else{
//				if(isAirOrNull(event.getCursor()) && !isAirOrNull(event.getCurrentItem())){// unequip with no new item going into the slot.
//					newArmorType = ArmorType.getType(event.getCurrentItem());
//				}
//				// event.getCurrentItem() == Unequip
//				// event.getCursor() == Equip
//				// newArmorType = ArmorType.matchType(!isAirOrNull(event.getCurrentItem()) ? event.getCurrentItem() : event.getCursor());
//			}
//			if(newArmorType != null && event.getRawSlot() == newArmorType.getSlot()){
//				EquipMethod method = EquipMethod.PICK_DROP;
//				if(event.getAction().equals(InventoryAction.HOTBAR_SWAP) || numberKey) method = EquipMethod.HOTKEY;
//				AArmorModifyEvent AArmorModifyEvent = new AArmorModifyEvent((Player) event.getWhoClicked(), method, newArmorType, oldArmorPiece, newArmorPiece);
//				Bukkit.getServer().getPluginManager().callEvent(AArmorModifyEvent);
//				if(AArmorModifyEvent.isCancelled()){
//					event.setCancelled(true);
//				}
//			}
//		}
//	}
//
//	@EventHandler
//	public void test(InventoryMoveItemEvent event) {
//
//		System.out.println("event");
//	}
//
//	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//	public void onInteract(PlayerInteractEvent event) {
//		System.out.println("e");
//		if(event.useItemInHand().equals(Event.Result.DENY))return;
//		if(event.getAction() == Action.PHYSICAL) return;
//		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
//			System.out.println("e1");
//			Player player = event.getPlayer();
//			if(!event.useInteractedBlock().equals(Event.Result.DENY)){
//				if(event.getClickedBlock() != null && event.getAction() == Action.RIGHT_CLICK_BLOCK && !player.isSneaking()){
//					// Having both of these checks is useless, might as well do it though.
//					// Some blocks have actions when you right click them which stops the client from equipping the armor in hand.
//					Material mat = event.getClickedBlock().getType();
//					for(String s : blockedMaterials){
//						if(mat.name().equalsIgnoreCase(s)) return;
//					}
//				}
//			}
//			System.out.println("e2");
//			ArmorType newArmorType = ArmorType.getType(event.getItem());
//			if(newArmorType != null){
//				System.out.println("e3");
//				if(newArmorType.equals(ArmorType.HELMET) && isAirOrNull(event.getPlayer().getInventory().getHelmet()) ||
//						newArmorType.equals(ArmorType.CHESTPLATE) && isAirOrNull(event.getPlayer().getInventory().getChestplate()) ||
//						newArmorType.equals(ArmorType.LEGGINGS) && isAirOrNull(event.getPlayer().getInventory().getLeggings()) ||
//						newArmorType.equals(ArmorType.BOOTS) && isAirOrNull(event.getPlayer().getInventory().getBoots())){
//					System.out.println("e4");
//					AArmorModifyEvent AArmorModifyEvent = new AArmorModifyEvent(event.getPlayer(), EquipMethod.HOTBAR,
//							ArmorType.getType(event.getItem()), null, event.getItem());
//					Bukkit.getServer().getPluginManager().callEvent(AArmorModifyEvent);
//					if(AArmorModifyEvent.isCancelled()){
//						event.setCancelled(true);
//						player.updateInventory();
//					}
//				}
//			}
//		}
//	}
//
//	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//	public void onInventoryDrag(InventoryDragEvent event) {
//		// getType() seems to always be even.
//		// Old Cursor gives the item you are equipping
//		// Raw slot is the ArmorType slot
//		// Can't replace armor using this method making getCursor() useless.
//		ArmorType type = ArmorType.getType(event.getOldCursor());
//		if(event.getRawSlots().isEmpty()) return;// Idk if this will ever happen
//		if(type != null && type.getSlot() == event.getRawSlots().stream().findFirst().orElse(0)){
//			AArmorModifyEvent AArmorModifyEvent = new AArmorModifyEvent((Player) event.getWhoClicked(),
//					EquipMethod.DRAG, type, null, event.getOldCursor());
//			Bukkit.getServer().getPluginManager().callEvent(AArmorModifyEvent);
//			if(AArmorModifyEvent.isCancelled()){
//				event.setResult(Event.Result.DENY);
//				event.setCancelled(true);
//			}
//		}
//	}
//
//	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
//	public void onItemBreak(PlayerItemBreakEvent event) {
//		ArmorType type = ArmorType.getType(event.getBrokenItem());
//		if(type != null){
//			Player p = event.getPlayer();
//			AArmorModifyEvent AArmorModifyEvent = new AArmorModifyEvent(p, EquipMethod.BREAK, type, event.getBrokenItem(), null);
//			Bukkit.getServer().getPluginManager().callEvent(AArmorModifyEvent);
//			if(AArmorModifyEvent.isCancelled()){
//				ItemStack i = event.getBrokenItem().clone();
//				i.setAmount(1);
//				i.setDurability((short) (i.getDurability() - 1));
//				if(type.equals(ArmorType.HELMET)){
//					p.getInventory().setHelmet(i);
//				}else if(type.equals(ArmorType.CHESTPLATE)){
//					p.getInventory().setChestplate(i);
//				}else if(type.equals(ArmorType.LEGGINGS)){
//					p.getInventory().setLeggings(i);
//				}else if(type.equals(ArmorType.BOOTS)){
//					p.getInventory().setBoots(i);
//				}
//			}
//		}
//	}
//
//	@EventHandler(priority = EventPriority.MONITOR)
//	public void onDeath(PlayerDeathEvent event) {
//		Player p = event.getEntity();
//		if(event.getKeepInventory()) return;
//		for(ItemStack i : p.getInventory().getArmorContents()){
//			if(!isAirOrNull(i)){
//				Bukkit.getServer().getPluginManager().callEvent(
//						new AArmorModifyEvent(p, EquipMethod.DEATH, ArmorType.getType(i), i, null));
//				// No way to cancel a death event.
//			}
//		}
//	}
//
//	public static boolean isAirOrNull(ItemStack item){
//		return item == null || item.getType().equals(Material.AIR);
//	}
//
//	static {
//
//		blockedMaterials.add("FURNACE");
//		blockedMaterials.add("CHEST");
//		blockedMaterials.add("TRAPPED_CHEST");
//		blockedMaterials.add("BEACON");
//		blockedMaterials.add("DISPENSER");
//		blockedMaterials.add("DROPPER");
//		blockedMaterials.add("HOPPER");
//		blockedMaterials.add("WORKBENCH");
//		blockedMaterials.add("ENCHANTMENT_TABLE");
//		blockedMaterials.add("ENDER_CHEST");
//		blockedMaterials.add("ANVIL");
//		blockedMaterials.add("BED_BLOCK");
//		blockedMaterials.add("FENCE_GATE");
//		blockedMaterials.add("SPRUCE_FENCE_GATE");
//		blockedMaterials.add("BIRCH_FENCE_GATE");
//		blockedMaterials.add("ACACIA_FENCE_GATE");
//		blockedMaterials.add("JUNGLE_FENCE_GATE");
//		blockedMaterials.add("DARK_OAK_FENCE_GATE");
//		blockedMaterials.add("IRON_DOOR_BLOCK");
//		blockedMaterials.add("WOODEN_DOOR");
//		blockedMaterials.add("SPRUCE_DOOR");
//		blockedMaterials.add("BIRCH_DOOR");
//		blockedMaterials.add("JUNGLE_DOOR");
//		blockedMaterials.add("ACACIA_DOOR");
//		blockedMaterials.add("DARK_OAK_DOOR");
//		blockedMaterials.add("WOOD_BUTTON");
//		blockedMaterials.add("STONE_BUTTON");
//		blockedMaterials.add("TRAP_DOOR");
//		blockedMaterials.add("IRON_TRAPDOOR");
//		blockedMaterials.add("DIODE_BLOCK_OFF");
//		blockedMaterials.add("DIODE_BLOCK_ON");
//		blockedMaterials.add("REDSTONE_COMPARATOR_OFF");
//		blockedMaterials.add("REDSTONE_COMPARATOR_ON");
//		blockedMaterials.add("FENCE");
//		blockedMaterials.add("SPRUCE_FENCE");
//		blockedMaterials.add("BIRCH_FENCE");
//		blockedMaterials.add("JUNGLE_FENCE");
//		blockedMaterials.add("DARK_OAK_FENCE");
//		blockedMaterials.add("ACACIA_FENCE");
//		blockedMaterials.add("NETHER_FENCE");
//		blockedMaterials.add("BREWING_STAND");
//		blockedMaterials.add("CAULDRON");
//		blockedMaterials.add("LEGACY_SIGN_POST");
//		blockedMaterials.add("LEGACY_WALL_SIGN");
//		blockedMaterials.add("LEGACY_SIGN");
//		blockedMaterials.add("ACACIA_SIGN");
//		blockedMaterials.add("ACACIA_WALL_SIGN");
//		blockedMaterials.add("BIRCH_SIGN");
//		blockedMaterials.add("BIRCH_WALL_SIGN");
//		blockedMaterials.add("DARK_OAK_SIGN");
//		blockedMaterials.add("DARK_OAK_WALL_SIGN");
//		blockedMaterials.add("JUNGLE_SIGN");
//		blockedMaterials.add("JUNGLE_WALL_SIGN");
//		blockedMaterials.add("OAK_SIGN");
//		blockedMaterials.add("OAK_WALL_SIGN");
//		blockedMaterials.add("SPRUCE_SIGN");
//		blockedMaterials.add("SPRUCE_WALL_SIGN");
//		blockedMaterials.add("LEVER");
//		blockedMaterials.add("BLACK_SHULKER_BOX");
//		blockedMaterials.add("BLUE_SHULKER_BOX");
//		blockedMaterials.add("BROWN_SHULKER_BOX");
//		blockedMaterials.add("CYAN_SHULKER_BOX");
//		blockedMaterials.add("GRAY_SHULKER_BOX");
//		blockedMaterials.add("GREEN_SHULKER_BOX");
//		blockedMaterials.add("LIGHT_BLUE_SHULKER_BOX");
//		blockedMaterials.add("LIME_SHULKER_BOX");
//		blockedMaterials.add("MAGENTA_SHULKER_BOX");
//		blockedMaterials.add("ORANGE_SHULKER_BOX");
//		blockedMaterials.add("PINK_SHULKER_BOX");
//		blockedMaterials.add("PURPLE_SHULKER_BOX");
//		blockedMaterials.add("RED_SHULKER_BOX");
//		blockedMaterials.add("SILVER_SHULKER_BOX");
//		blockedMaterials.add("WHITE_SHULKER_BOX");
//		blockedMaterials.add("YELLOW_SHULKER_BOX");
//		blockedMaterials.add("DAYLIGHT_DETECTOR_INVERTED");
//		blockedMaterials.add("DAYLIGHT_DETECTOR");
//		blockedMaterials.add("BARREL");
//		blockedMaterials.add("BLAST_FURNACE");
//		blockedMaterials.add("SMOKER");
//		blockedMaterials.add("CARTOGRAPHY_TABLE");
//		blockedMaterials.add("COMPOSTER");
//		blockedMaterials.add("GRINDSTONE");
//		blockedMaterials.add("LECTERN");
//		blockedMaterials.add("LOOM");
//		blockedMaterials.add("STONECUTTER");
//		blockedMaterials.add("BELL");
//	}
}
