package dev.kyro.arcticapi.nms;

import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ANBTItemStack extends ItemStack {
	public ANBTItemStack(ItemStack stack) {

		super(stack);
	}

	public ANBTItemStack(Material type) {

		super(type);
	}

	public ANBTItemStack(Material type, int amount) {

		super(type,amount);
	}

	public ANBTItemStack(Material type, int amount, int durability) {

		super(type, amount, (short) durability);
	}

	public void setNBTTag(String key, NBTBase value) {

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
		NBTTagCompound comp = nmsItem.getTag();

		comp.set(key,value);

		nmsItem.setTag(comp);

		ItemMeta meta = CraftItemStack.getItemMeta(nmsItem);
		this.setItemMeta(meta);
	}

	public void setNBTTag(String key, String value) {

		setNBTTag(key, new NBTTagString(value));
	}

	public void removeNBTTag(String key) {

		net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
		NBTTagCompound comp = nmsItem.getTag();

		comp.remove(key);

		nmsItem.setTag(comp);

		ItemMeta meta = CraftItemStack.getItemMeta(nmsItem);
		this.setItemMeta(meta);
	}

	public NBTBase getNBTTag(String key) {

		try {

			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			NBTTagCompound comp = nmsItem.getTag();

			return comp.get(key);

		} catch(Exception ex) {
			return null;
		}
	}

	public String getNBTTagString(String key) {

		try {

			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			NBTTagCompound comp = nmsItem.getTag();
			String value = comp.getString(key);

			return value.isEmpty() ? null : value;

		} catch(Exception ex) {

			return null;
		}
	}

	public int getNBTTagInt(String key, int defaultValue) {

		try {

			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			NBTTagCompound comp = nmsItem.getTag();

			return comp.getInt(key);

		} catch(Exception ex) {

			return defaultValue;
		}
	}

	/**
	 *
	 * @param key
	 * @param type BYTE = 1, SHORT = 2, INT = 3, LONG = 4, FLOAT = 5, DOUBLE = 6, BYTE ARRAY = 7, STRING = 8, LIST = 9, COMPOUND = 10, INT ARRAY = 11
	 * @return
	 */

	public NBTTagList getNBTTagList(String key, int type) {

		try {

			net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(this);
			NBTTagCompound comp = nmsItem.getTag();

			return comp.getList(key,type);

		} catch(Exception ex) {

			return null;
		}
	}

	public NBTTagList getNBTTagList(String key) {

		return getNBTTagList(key,10);
	}
}


