package dev.kyro.arcticapi.builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

@SuppressWarnings("unused")
public class AItemStackBuilder {

    public ItemStack itemStack;
    public ItemMeta itemMeta;

    public AItemStackBuilder(ItemStack itemStack) {

        this.itemStack = itemStack;
        buildItemMeta();
    }

    public AItemStackBuilder(Material material) {

        this(material, 0);
    }

    public AItemStackBuilder(Material material, int amount) {

        this(material, amount, 0);
    }

    public AItemStackBuilder(Material material, int amount, int data) {

        itemStack = new ItemStack(material, amount, (short) data);
        buildItemMeta();
    }

    public AItemStackBuilder setName(String name) {

        itemMeta.setDisplayName(name);
        updateItemMeta();

        return this;
    }

    public AItemStackBuilder setLore(List<String> lore) {

        itemMeta.setLore(lore);
        updateItemMeta();

        return this;
    }

    public AItemStackBuilder setLore(ALoreBuilder loreBuilder) {

        itemMeta.setLore(loreBuilder.lore);
        updateItemMeta();

        return this;
    }

    public AItemStackBuilder addEnchantGlint(boolean hideFlag) {

        itemStack.addUnsafeEnchantment(Enchantment.LUCK, 0);

        if(!hideFlag) return this;

        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        updateItemMeta();

        return this;
    }

    private void buildItemMeta() {

        itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
    }

    private void updateItemMeta() {

        itemStack.setItemMeta(itemMeta);
    }
}
