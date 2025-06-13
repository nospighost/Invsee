package de.Main.invsee;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.concurrent.CompletableFuture;

public class InvseeToggleCommand implements CommandExecutor {
    private final LuckPerms luckPerms;

    public InvseeToggleCommand(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Dieser Befehl kann nur von Spielern ausgeführt werden.");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("be.invsee.toggle")) {
            player.sendMessage("§cDu hast keine Berechtigung, diesen Befehl zu verwenden.");
            return true;
        }

        User user = luckPerms.getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            player.sendMessage("§cFehler beim Abrufen deiner LuckPerms-Daten.");
            return true;
        }

        String permission = "be.invsee.denyview";
        Node node = Node.builder(permission).build();

        if (user.getCachedData().getPermissionData().checkPermission(permission).asBoolean()) {
            user.data().remove(node);
            luckPerms.getUserManager().saveUser(user);
            player.sendMessage("§a§lInventar Sichtbarkeit §7| §c§lDeaktiviert");
        } else {
            user.data().add(node);
            luckPerms.getUserManager().saveUser(user);
            player.sendMessage("§a§lInventar Sichtbarkeit §7| §a§lAktiviert");
        }

        return true;
    }

}
