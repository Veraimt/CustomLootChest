package de.veraimt.customlootchest;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomLootChest extends JavaPlugin {

    private static CustomLootChest instance;

    @Override
    public void onEnable() {
        instance = this;

        //bStats
        Metrics metrics = new Metrics(this, 10538);
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static CustomLootChest getInstance() {
        return instance;
    }
}
