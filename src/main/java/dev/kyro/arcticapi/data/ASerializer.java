package dev.kyro.arcticapi.data;

import dev.kyro.arcticapi.builders.AItemStackBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedHashMap;
import java.util.List;

public class ASerializer {

    public static Location deserializeLocation(String locString) {

        String[] locParts = locString.split(",");

        System.out.println(locParts);

        if(locParts.length != 4) return null;

        try {

            World world = Bukkit.getWorld(locParts[0].trim());

            System.out.println(world.toString());

            double x = Double.parseDouble(locParts[1].trim());
            double y = Double.parseDouble(locParts[2].trim());
            double z = Double.parseDouble(locParts[3].trim());

            System.out.println(x + " " + y + " " + z);

            return new Location(world, x, y, z);

        } catch(Exception exception) {

            exception.printStackTrace();
            return null;
        }
    }

    public static ItemStack deserializeItem(ConfigurationSection configSection) {

        try {

            Material material = Material.getMaterial(configSection.getString("material"));
            int amount = configSection.getInt("amount");
            int data = configSection.getInt("data");
            String name = configSection.getString("name");
            List<String> lore = configSection.getStringList("lore");

            AItemStackBuilder itemStackBuilder = new AItemStackBuilder(material, amount, data)
                    .setName(name)
                    .setLore(lore);

            return itemStackBuilder.itemStack;
        } catch(Exception exception) {

            exception.printStackTrace();
            return null;
        }
    }

    public static String serializeLocation(Location location) {

        return location.getWorld().getName() + ", " + location.getX() + ", " + location.getY() + ", " + location.getZ();
    }

    public static LinkedHashMap<String, Object> serializeItem(ItemStack itemStack) {

        LinkedHashMap<String, Object> itemMap = new LinkedHashMap<>();

        if(!itemStack.hasItemMeta()) itemStack.setItemMeta(Bukkit.getItemFactory().getItemMeta(itemStack.getType()));
        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMap.put("material", itemStack.getType().toString());
        itemMap.put("amount", itemStack.getAmount());
        itemMap.put("data", itemStack.getDurability());
        itemMap.put("name", itemMeta.getDisplayName() == null ? "" : itemMeta.getDisplayName());
        itemMap.put("lore", itemMeta.getLore() == null ? "" : itemMeta.getLore());

        return itemMap;
    }
}