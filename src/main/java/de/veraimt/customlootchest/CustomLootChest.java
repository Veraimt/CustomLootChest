package de.veraimt.customlootchest;

import de.veraimt.customlootchest.commands.LootChestCommand;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomLootChest extends JavaPlugin {

    private static CustomLootChest instance;

    @Override
    public void onEnable() {
        instance = this;

        //bStats
        Metrics metrics = new Metrics(this, 10538);

        getCommand("lootchest").setExecutor(new LootChestCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CustomLootChest getInstance() {
        return instance;
    }
}
