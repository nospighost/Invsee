package de.Main.invsee;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Syntax;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("invsee") // Der Hauptalias des Befehls
@Description("Erlaubt es, das Inventar eines anderen Spielers einzusehen.")
public class InvseeCommand extends BaseCommand {

    @CommandPermission("be.invsee") // Erfordert die Berechtigung "be.invsee"
    @Syntax("<Spieler>") // Syntax-Hinweis für Spieler
    public void onInvsee(Player player, @Optional String targetName) {
        // Überprüfen, ob der Spieler einen Namen eingegeben hat
        if (targetName == null) {
            player.sendMessage("§cVerwendung: /invsee <Spieler>");
            return;
        }

        // Zielspieler abrufen
        Player targetPlayer = Bukkit.getPlayer(targetName);
        if (targetPlayer == null || !targetPlayer.isOnline()) {
            player.sendMessage("§cDieser Spieler ist nicht online!");
            return;
        }

        // Inventar öffnen
        player.sendMessage("§aDu siehst nun das Inventar von §e" + targetPlayer.getName() + "§a.");
        player.openInventory(targetPlayer.getInventory());
    }

}
