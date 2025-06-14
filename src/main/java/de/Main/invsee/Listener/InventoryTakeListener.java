package de.Main.invsee.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class InventoryTakeListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        Inventory clickedInventory = event.getInventory();

        if (clickedInventory.getHolder() instanceof Player) {
            Player targetPlayer = (Player) clickedInventory.getHolder();

            if (player.getUniqueId().equals(targetPlayer.getUniqueId())) {
                return;
            }

            // Rollenlogik anwenden
            String playerRole = getRole(player);
            String targetRole = getRole(targetPlayer);

            switch (playerRole) {
                case "view":
                    event.setCancelled(true);
                    break;

                case "supporter":
                    if (!targetRole.equals("none")) {
                        event.setCancelled(true);
                    }
                    break;

                case "mod":
                    if (targetRole.equals("mod") || targetRole.equals("teamlead") || targetRole.equals("admin")) {
                        event.setCancelled(true);
                    }
                    break;

                case "teamlead":
                    if (targetRole.equals("admin") || targetRole.equals("teamlead")) {
                        event.setCancelled(true);
                    }
                    break;

                case "admin":
                    break;

                default:
                    event.setCancelled(true);
                    break;
            }
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
