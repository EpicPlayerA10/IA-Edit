<a href="https://modrinth.com/plugin/ia-edit"><img alt="modrinth" height="40" src="https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/compact/available/modrinth_vector.svg"></a>

# <img src="https://minecraft.wiki/images/Wooden_Axe_JE2_BE2.png" height=32> IA-Edit <img src="https://minecraft.wiki/images/Crafting_Table_JE4_BE3.png" height=32>
A minecraft plugin that integrates [WorldEdit](https://dev.bukkit.org/projects/worldedit) and [FAWE](https://www.spigotmc.org/resources/fastasyncworldedit.13932/) with custom blocks from [ItemsAdder](https://www.spigotmc.org/resources/%E2%9C%A8itemsadder%E2%AD%90emotes-mobs-items-armors-hud-gui-emojis-blocks-wings-hats-liquids.73355/). Works on versions 1.16 - 1.21.5+

There is already a plugin called [ItemsAdderWorldEdit](https://www.spigotmc.org/resources/addon-itemsadder-worldedit.79012/) but it has some bugs. For example it does not work with the latest version of FAWE. That inspired me to create my own plugin for worldedit integration.

## Installation
- [Modrinth](https://modrinth.com/plugin/ia-edit)

## What this plugin fixes from [ItemsAdderWorldEdit](https://www.spigotmc.org/resources/addon-itemsadder-worldedit.79012/)
- Adds compatibility with the latest version of FAWE
- Now it can place and remove custom blocks everywhere in WorldEdit for example in commands, clipboard and schematics. Just everywhere you can imagine in WorldEdit

## Troubleshooting

### When I `//copy` and `//paste`, then furniture won't be copied
You need to copy with `-e` flag like so `//copy -e` and paste with this flag `//paste -e`. This is because furniture are entities and using `-e` flag we are telling worldedit to also copy entities.

