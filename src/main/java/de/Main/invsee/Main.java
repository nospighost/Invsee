package de.Main.invsee;

import de.Main.invsee.Manager.InventoryTakeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("invsee").setExecutor(new InvseeCommand());
        Bukkit.getPluginManager().registerEvents(new InventoryTakeListener(), this);
        getLogger().info("invsee Plugin aktiv");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
