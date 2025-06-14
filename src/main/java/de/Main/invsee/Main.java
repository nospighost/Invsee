    package de.Main.invsee;

    import de.Main.invsee.Commands.InvseeCommand;
    import de.Main.invsee.Commands.InvseeToggleCommand;
    import de.Main.invsee.Listener.InventoryTakeListener;
    import net.luckperms.api.LuckPerms;
    import org.bukkit.Bukkit;
    import org.bukkit.plugin.java.JavaPlugin;
    public final class Main extends JavaPlugin {
    private LuckPerms luckPerms;
        @Override
        public void onEnable() {
            luckPerms = getServer().getServicesManager().load(LuckPerms.class);
            if (luckPerms == null) {
                getLogger().severe("LuckPerms-API konnte nicht geladen werden. Bitte stelle sicher, dass LuckPerms installiert ist.");
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
            getCommand("invsee").setExecutor(new InvseeCommand());
            getCommand("invseetoggle").setExecutor(new InvseeToggleCommand(luckPerms));
            Bukkit.getPluginManager().registerEvents(new InventoryTakeListener(), this);
            getLogger().info("invsee Plugin aktiv");
        }

        @Override
        public void onDisable() {
        }
    }
