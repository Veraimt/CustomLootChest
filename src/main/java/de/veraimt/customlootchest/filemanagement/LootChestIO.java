package de.veraimt.customlootchest.filemanagement;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.veraimt.customlootchest.CustomLootChest;
import de.veraimt.customlootchest.lootchest.LootChest;

import java.io.*;
import java.util.logging.Level;

public class LootChestIO {
    private static final File savedChestsDir = new File(CustomLootChest.getInstance().getDataFolder().getAbsolutePath()
            + File.separator + "savedChests" + File.separator);

    /**
     * Saves the given LootChest and returns if successful
     *
     * @param lootChest LootChest to be saved
     * @return The success of the saving process
     */
    public static boolean saveChest(LootChest lootChest) {
        //Checks if directory exists and creates it if not existent
        if (!savedChestsDir.exists())
            if (!savedChestsDir.mkdirs()) {
                //return the method when the directory couldn't be created
                CustomLootChest.getInstance().getLogger().log(Level.SEVERE, "Could not create the folder for saving the LootChests");
                return false;
            }

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        try(Writer writer = new FileWriter(savedChestsDir.getAbsolutePath() + File.separator + lootChest.getName() + ".json")) {
            gson.toJson(lootChest, writer);
            CustomLootChest.getInstance().getLogger().log(Level.INFO, "LootChest: '" + lootChest.getName() + "' was successfully saved!");
            return true;

        } catch (IOException e) {
            CustomLootChest.getInstance().getLogger().log(Level.SEVERE, "Couldn't save the LootChest: '" + lootChest.getName() + "'!");
            CustomLootChest.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
            return false;
        }
    }

    /**
     * Loads the LootChes with the given name and returning it
     *
     * @param name  Name of the LootChest to be loaded
     * @return The LootChest with the given name, null if it doesn't exist
     */
    public static LootChest loadChest(String name) {
        Gson gson = new Gson();
        LootChest lootChest = null;

        try {
            Reader reader = new BufferedReader(new FileReader(savedChestsDir.getAbsolutePath() + File.separator + name + ".json"));
            lootChest = gson.fromJson(reader,LootChest.class);
            reader.close();
        } catch (IOException e) {
            CustomLootChest.getInstance().getLogger().log(Level.SEVERE, "Couldn't load the LootChest: '" + name + "'!");
            CustomLootChest.getInstance().getLogger().log(Level.SEVERE, e.getMessage());
        }

        return lootChest;
    }

    /**
     * Deletes the LootChes with the given name, returning its success
     *
     * @param name  Name of the LootChest to be deleted
     * @return true if deletion was successful, otherwise false
     */
    public static boolean deleteChest(String name) {
        File file = new File(savedChestsDir.getAbsolutePath() + File.separator + name + ".json");
        return file.delete();
    }
}
