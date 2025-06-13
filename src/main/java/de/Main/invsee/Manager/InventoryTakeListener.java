package de.Main.invsee.Manager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryTakeListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getInventory();
        InventoryHolder holder = clickedInventory.getHolder();
            Player targetPlayer = (Player) holder;
            String playerRole = getRole(player);
            String targetRole = getRole(targetPlayer);

            switch (playerRole) {
                case "view":
                    event.setCancelled(true);
                    player.sendMessage("§cDu darfst dieses Inventar nur ansehen.");
                    break;

                case "supporter":
                    if (!targetRole.equals("none")) {
                        event.setCancelled(true);
                        player.sendMessage("§cDu darfst nur Inventare von normalen Spielern ansehen.");
                    }
                    break;
                case "mod":
                    if (targetRole.equals("mod") || targetRole.equals("teamlead") || targetRole.equals("admin")) {
                        event.setCancelled(true);
                        player.sendMessage("§cDu darfst keine Inventare von gleich- oder höherrangigen Teammitgliedern bearbeiten.");
                    }
                    break;
                case "teamlead":
                    if (targetRole.equals("admin")) {
                        event.setCancelled(true);
                        player.sendMessage("§cDu darfst keine Inventare von Admins bearbeiten.");
                    }
                    break;

                case "admin":
                    break;

                default:
                  event.setCancelled(true);
                    player.sendMessage("§cDu hast keine Berechtigung, mit diesem Inventar zu interagieren.");
                    break;
            }
        }


    public static String getRole(Player player) {
        if (player.hasPermission("be.invsee.admin")) return "admin";
        if (player.hasPermission("be.invsee.teamlead")) return "teamlead";
        if (player.hasPermission("be.invsee.mod")) return "mod";
        if (player.hasPermission("be.invsee.supporter")) return "supporter";
        if (player.hasPermission("be.invsee.view")) return "view";
        return "none";
    }
}
