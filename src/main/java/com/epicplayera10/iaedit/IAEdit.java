package com.epicplayera10.iaedit;

import com.epicplayera10.iaedit.hook.CustomBlocksFactory;
import com.epicplayera10.iaedit.hook.listener.WorldEditListener;
import com.epicplayera10.iaedit.listeners.ItemsAdderListener;
import com.sk89q.worldedit.WorldEdit;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class IAEdit extends JavaPlugin implements CommandExecutor, Listener {
    private static IAEdit instance;

    private boolean isFaweEnabled = false;

    public void onEnable() {
        instance = this;

        setupMetrics();

        isFaweEnabled = Bukkit.getPluginManager().isPluginEnabled("FastAsyncWorldEdit");
        if (isFaweEnabled) {
            getLogger().info("FastAsyncWorldEdit is enabled, enabling performance optimizations.");
        }

        Bukkit.getPluginManager().registerEvents(new ItemsAdderListener(), this);

        WorldEdit.getInstance().getEventBus().register(new WorldEditListener());
        WorldEdit.getInstance().getBlockFactory().register(new CustomBlocksFactory());
    }

    public static IAEdit instance() {
        return instance;
    }

    public boolean isFaweEnabled() {
        return isFaweEnabled;
    }

    private void setupMetrics() {
        int pluginId = 25717;
        Metrics metrics = new Metrics(this, pluginId);
    }
}
