package de.Main.invsee;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;

        if (!(commandSender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage("Du musst ein Spieler sein um diesen Befehl auszuführen");
        }

        if (!(player.hasPermission("be.invsee"))) {
            player.sendMessage("Fehlende Rechte: be.invsee");
            return false;
        }
        if (args.length == 1) {
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            if (targetPlayer == null || !targetPlayer.isOnline()) {
                player.sendMessage("§cDieser Spieler nicht online!");
                return false;
            }

            player.openInventory(targetPlayer.getInventory());

        }
        return true;
    }


}
