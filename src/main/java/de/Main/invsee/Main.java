package de.Main.invsee;

import co.aikar.commands.BukkitCommandManager;
import de.Main.invsee.Manager.InventoryTakeListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private BukkitCommandManager commandManager;

    @Override
    public void onEnable() {
        // Initialisierung von ACF
        this.commandManager = new BukkitCommandManager(this);

        // ACF für Bukkit konfigurieren
        commandManager.enableUnstableAPI("help");

        // Registriere den Befehl
        commandManager.registerCommand(new InvseeCommand());

        // Registriere den Event-Listener
        Bukkit.getPluginManager().registerEvents(new InventoryTakeListener(), this);

        getLogger().info("Invsee Plugin wurde erfolgreich aktiviert!");
    }

    @Override
    public void onDisable() {
        // Deaktivierungslog
        getLogger().info("Invsee Plugin wurde deaktiviert.");
    }
}
