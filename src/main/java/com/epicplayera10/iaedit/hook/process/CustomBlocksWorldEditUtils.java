package com.epicplayera10.iaedit.hook.process;

import com.epicplayera10.iaedit.hook.CustomBlocksFactory;
import com.epicplayera10.iaedit.utils.IACache;
import com.sk89q.jnbt.CompoundTag;
import com.sk89q.jnbt.Tag;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.world.block.BlockStateHolder;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import dev.lone.itemsadder.api.CustomBlock;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class CustomBlocksWorldEditUtils {

    /**
     * The core method that processes the custom block placement or removal.
     */
    public static void processBlock(Location location, BlockStateHolder<?> stateToSet, BlockStateHolder<?> stateBefore, CompoundTag nbtToSet, CompoundTag nbtBefore) {
        if (CustomBlocksFactory.isCustomBlockType(stateBefore.getBlockType())) {
            // Remove existing custom block
            CustomBlock.Advanced.removeFromCustomRegion(location);
        }

        // Match custom block with block state and raw place it
        BlockType stateToSetType = stateToSet.getBlockType();
        if (CustomBlocksFactory.isCustomBlockType(stateToSetType)) {
            CustomBlock customBlock = getCustomBlockByBlockState(stateToSet, nbtToSet);
            if (customBlock == null) return;

            // Recover custom block
            CustomBlock.Advanced.placeInCustomRegion(customBlock, location);
        }
    }

    /**
     * Get the custom block by the block state.
     */
    @Nullable
    private static CustomBlock getCustomBlockByBlockState(@NotNull BlockStateHolder<?> blockState, @Nullable CompoundTag nbt) {
        if (blockState.getBlockType() == BlockTypes.SPAWNER && nbt != null) {
            // Tile block (spawner)

            CompoundTag spawnData = (CompoundTag) nbt.getValue().get("SpawnData");
            CompoundTag entity = (CompoundTag) spawnData.getValue().get("entity");
            List<CompoundTag> armorItems = entity.getList("ArmorItems", CompoundTag.class);
            CompoundTag itemNbt = armorItems.get(3);
            Map<String, Tag<?, ?>> itemNbtMap = itemNbt.getValue();

            if (!itemNbtMap.containsKey("id") || !itemNbtMap.containsKey("tag")) return null;

            String itemId = itemNbt.getString("id");
            CompoundTag itemTag = (CompoundTag) itemNbtMap.get("tag");

            if (!itemTag.getValue().containsKey("CustomModelData")) return null;
            int customModelData = itemTag.getInt("CustomModelData");

            return IACache.getCustomBlockByItemTypeAndCustomModelData(itemId, customModelData);
        } else {
            return IACache.getCustomBlockByBlockData(BukkitAdapter.adapt(blockState.toImmutableState()));
        }
    }
}
