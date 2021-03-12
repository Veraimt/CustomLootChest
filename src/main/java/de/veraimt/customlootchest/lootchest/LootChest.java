package de.veraimt.customlootchest.lootchest;

import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import java.util.Random;

public class LootChest {

    private final String name;
    private boolean shuffle = false;
    private final ArrayList<LootItem> lootItems;

    public LootChest(String name) {
        this.name = name;
        lootItems = new ArrayList<>();

    }

    public void addLootItem(LootItem lootItem) {
        lootItems.add(lootItem);
    }

    public void addLootItem(ItemStack itemStack) {
        addLootItem(new LootItem(itemStack));
    }

    public String getName() {
        return name;
    }

    public ItemStack[] generate() {
        ItemStack[] itemStacks = new ItemStack[3*9];
        Random random = new Random();

        for (int i = 0; i < itemStacks.length; i++) {
            if (lootItems.get(i).getProbability() >= random.nextInt(100))
                itemStacks[i] = lootItems.get(i).getItemStack();
            else
                itemStacks[i] = null;
        }

        if (shuffle) {
            ItemStack[] temp = new ItemStack[itemStacks.length];
            for (ItemStack itemStack : itemStacks) {
                int randomInt = random.nextInt(itemStacks.length);

                while (temp[randomInt] != null) {
                    randomInt = random.nextInt(itemStacks.length);
                }
                temp[randomInt] = itemStack;
            }
            itemStacks = temp;
        }
        return itemStacks;
    }
}
