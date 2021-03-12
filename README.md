# CustomLootChest
Minecraft Plugin to easily generate Chests with custom Loot

This is my first plugin and my first use of GitHub, so don't expect top level code. :D

I want to learn from this, so it would be nice if you leave some feedback!

## bStats
This plugin uses bStats which collects metrics about the server its running on.

To opt out, edit the `/plugins/bstats/config.yml` in your server folder.

[Dashboard](https://bstats.org/plugin/bukkit/CustomLootChest/10538)

## Usage
### Commands
- `/lootchest create [name]`  Saves the chest you look at as LootChest with the given name
- `/lootchest load [name]`  Loads the LootChest with the given name into the chest you look at
- `/lootchest delete [name]`  Deletes the LootChest with the given name

### Saved LootChests
LootChests are saved as `.json` files in this directory (`/plugins/CustomLootChest/savedChests`)  in your server folder.

You can edit these to change various parameters such as the probability that an item will appear in a chest or
whether the positions of the items are shuffled.

A saved LootChest may look like this (obviously) without the comments on the right:

```
{
    "name": "example",              The name of the LootChest, don't change this
    "shuffle": false,               enables / disables shuffling of items - default: false
    "lootItems": [
        {
            "type": "INK_SACK",     Name of the item as in org.bukkit.material
            "data": 4,              Data of the item, used for item variants like wool colors
                                    INK_SACK with a data of 4 equals dye:4 in Minecraft
                                    which is the ID for Lapis Lazuli
            "probability": 20       The probability with which the item can generate in percent, default: 100
        },
        {
            "type": "GOLD_INGOT",   Another item for example
            "data": 0,              Gold Ingots have no variants so data is 0
            "probability": 50       Probability of 50%
        }
    ]    
}
```
## To-Do
- [x] Adding Code Structure and committing the Project to GitHub
- [x] Get the Plugin running!
- [x] Save Loot-Sets
- [x] Fill Chests with Loot-Sets
- [ ] Make a configurable Randomizer
- [ ] Maybe add a GUI
