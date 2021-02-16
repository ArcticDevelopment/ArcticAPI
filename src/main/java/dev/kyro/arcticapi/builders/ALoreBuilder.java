package dev.kyro.arcticapi.builders;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class ALoreBuilder {

    public List<String> lore = new ArrayList<>();

    public ALoreBuilder() {

    }

    public ALoreBuilder(ItemStack itemStack) {

        if(itemStack.hasItemMeta() && itemStack.getItemMeta().hasLore()) lore = itemStack.getItemMeta().getLore();
    }

    public ALoreBuilder addLore(String... lines) {

        lore.addAll(Arrays.asList(lines));

        return this;
    }

    public ALoreBuilder border(String border) {

        lore.add(0, border);
        lore.add(border);

        return this;
    }

    public ALoreBuilder colorize() {

        for(int i = 0; i < lore.size(); i++) {

            String line = lore.get(i);

            lore.set(i, ChatColor.translateAlternateColorCodes('&', line));
        }

        return this;
    }
}
