package dev.kyro.arcticapi.builders;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
        itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(material);
        buildItemMeta();
    }

    public AItemStackBuilder setName(String name) {

        itemMeta.setDisplayName(name);
        updateItem();

        return this;
    }

    public AItemStackBuilder setLore(List<String> lore) {

        itemMeta.setLore(lore);
        updateItem();

        return this;
    }

    public AItemStackBuilder setLore(ALoreBuilder loreBuilder) {

        itemMeta.setLore(loreBuilder.lore);
        updateItem();

        return this;
    }

    private void buildItemMeta() {

        itemMeta = itemStack.hasItemMeta() ? itemStack.getItemMeta() : Bukkit.getItemFactory().getItemMeta(itemStack.getType());
    }

    private void updateItem() {

        itemStack.setItemMeta(itemMeta);
    }
}
