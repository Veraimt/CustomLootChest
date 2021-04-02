package de.veraimt.customlootchest.commands;

import de.veraimt.customlootchest.CustomLootChest;
import de.veraimt.customlootchest.filemanagement.LootChestIO;
import de.veraimt.customlootchest.lootchest.LootChest;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Set;
import java.util.logging.Level;

public class LootChestCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     * determines if the usage specified in the plugin.yml is sent to the player
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //Termination condition: Player only
        if (!(sender instanceof Player)) {
            CustomLootChest.getInstance().getLogger().log(Level.INFO, "This command is player only");
            return true;
        }

        //Variables
        Player player = (Player) sender;
        Block targetBlock = player.getTargetBlock((Set<Material>) null, 5);

        //Termination condition: targeted Block not a Chest
        if (!(targetBlock.getState() instanceof Chest)) {
            player.sendMessage("§cPlease look at a chest while performing this command!");
            return true;
        }
        Chest chest = (Chest) targetBlock.getState();

        //Termination condition: too few args
        if (args.length != 2)
            return false;
        LootChest lootChest;

        //Format:   /lootchest  args[0]     args[1]
        //          /lootchest  subcommand  chest name

        switch (args[0]) {
            case "create":
                lootChest = new LootChest(args[1]);

                //Iterating through the chests inventory and adding the items to the LootChest
                for (ItemStack itemStack : chest.getBlockInventory().getContents()) {
                    lootChest.addLootItem(itemStack);
                }

                //Saving the LootChest
                //Player Feedback
                if (LootChestIO.saveChest(lootChest))
                    player.sendMessage("The LootChest: '§5" + lootChest.getName() + "§r' was §2successfully§r saved!");
                else
                    player.sendMessage("Couldn't save the Chest!");

                return true;

            case "load":
                //Load LootChest with given name (args[1])
                lootChest = LootChestIO.loadChest(args[1]);
                //if null the LootChest doesn't exist, cancelling execution
                if (lootChest == null) {
                    player.sendMessage("The LootChest: '§5" + args[1] + "§r' doesn't exist!");
                    return true;
                }

                //Generate the Loot for the LootChest and fill the chest that the player is looking at
                chest.getBlockInventory().setContents(lootChest.generate());

                //Player Feedback
                player.sendMessage("The LootChest: '§5" + lootChest.getName() + "§r' was loaded into the chest you looked at!");

                return true;

            case "delete":
                if (LootChestIO.deleteChest(args[1]))
                    player.sendMessage("The LootChest: '§5" + args[1] + "§r' was deleted!");

                return true;
            default:
                return false;
        }
    }
}
