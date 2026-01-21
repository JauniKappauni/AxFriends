package de.jauni.axfriends.command;

import de.jauni.axfriends.AxFriends;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AddFriendCommand implements CommandExecutor {
    AxFriends reference;

    public AddFriendCommand(AxFriends reference) {
        this.reference = reference;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cnd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getServer().broadcast(Component.text("Nur Spieler können diesen Befehl ausführen."));
            return true;
        }
        Player sourcePlayer = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        boolean state = reference.getPlayerManager().addFriend(sourcePlayer, targetPlayer);
        if (state) {
            sourcePlayer.sendMessage("Du hast" + " " + targetPlayer.getName() + " " + "eine Freundschaftsanfrage geschickt.");
            targetPlayer.sendMessage(sourcePlayer.getName() + " " + "hat dir eine Freundschaftsanfrage geschickt.");
            return true;
        }
        sourcePlayer.sendMessage("Du bist bereits mit" + " " + targetPlayer.getName() + " " + "befreundet.");
        return true;
    }
}
