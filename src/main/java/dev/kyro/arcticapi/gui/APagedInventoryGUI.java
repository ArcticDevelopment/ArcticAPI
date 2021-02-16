package dev.kyro.arcticapi.gui;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class APagedInventoryGUI extends AInventoryGUI {

    public int forwardSlot = 0;
    public ItemStack forwardButton = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);

    public int backwardSlot = 8;
    public ItemStack backwardButton = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);

    public int[] contentSlots = new int[]{ 2, 3, 4, 5, 6 };
    public List<ItemStack> content = new ArrayList<>();

    public APagedInventoryGUI(String name, int rows) {

        super(name, rows);
    }

    public void previousPage() {


    }

    public void nextPage() {


    }
}
