package de.jauni.axfriends.command;

import de.jauni.axfriends.AxFriends;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ListFriendsCommand implements CommandExecutor {
    AxFriends reference;

    public ListFriendsCommand(AxFriends reference) {
        this.reference = reference;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
        Player sourcePlayer = (Player) sender;
        sourcePlayer.sendMessage("Deine Freundesliste:");
        for (String uuid : reference.getPlayerManager().listFriends(sourcePlayer)) {
            sourcePlayer.sendMessage("-" + " " + Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName());
        }
        return true;
    }
}
