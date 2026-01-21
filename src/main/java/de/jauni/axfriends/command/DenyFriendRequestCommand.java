package de.jauni.axfriends.command;

import de.jauni.axfriends.AxFriends;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DenyFriendRequestCommand implements CommandExecutor {
    AxFriends reference;

    public DenyFriendRequestCommand(AxFriends reference) {
        this.reference = reference;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
        Player sourcePlayer = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        boolean state = reference.getPlayerManager().denyFriendRequest(sourcePlayer, targetPlayer);
        if (state) {
            targetPlayer.sendMessage(sourcePlayer.getName() + " " + "hat deine Freundschaftsanfrage abgelehnt.");
            sourcePlayer.sendMessage("Du hast die Freundschaftsanfrage von" + " " + targetPlayer.getName() + " " + "abgelehnt.");
        }
        return true;
    }
}
