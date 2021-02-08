package dev.kyro.arcticapi.builders;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AInventoryBuilder {

    public Inventory inventory;

    public AInventoryBuilder(Player owner, int size, String name) {

        inventory = Bukkit.createInventory(owner, size, name);
    }

    public AInventoryBuilder(Inventory inventory) {

        this.inventory = inventory;
    }

    public AInventoryBuilder createBorder(Material material, int toData) {

        byte data = (byte) toData;

        for(int i = 0; i < inventory.getSize(); i++) {

            if(i < 9 || i > inventory.getSize() - 10 || i % 9 == 8 || i % 9 == 0) {

                inventory.setItem(i, new ItemStack(material, 1, data));
            }
        }

        return this;
    }

    public AInventoryBuilder setSlots(Material material, int toData, int... slots) {

        for(int slot : slots) {

            setSlot(material, toData, slot, null, null);
        }

        return this;
    }

    public AInventoryBuilder setSlot(Material material, int toData, int slot, String name, ArrayList<String> lore) {

        byte data = (byte) toData;

        ItemStack item = new ItemStack(material, 1, data);
        ItemMeta meta = item.getItemMeta();
        if(name != null) meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        if(lore != null) meta.setLore(lore);
        item.setItemMeta(meta);

        inventory.setItem(slot, item);

        return this;
    }

    public AInventoryBuilder addEnchantGlint(boolean hideFlag, int... slots) {

        for(int slot : slots) {

            if(inventory.getItem(slot).getType() == Material.AIR) return this;

            ItemStack item = inventory.getItem(slot);

            item.addUnsafeEnchantment(Enchantment.DURABILITY, 0);

            if(!hideFlag) continue;

            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(itemMeta);
        }

        return this;
    }
}
