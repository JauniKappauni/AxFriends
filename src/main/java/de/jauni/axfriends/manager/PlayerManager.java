package de.jauni.axfriends.manager;

import de.jauni.axfriends.AxFriends;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerManager {
    AxFriends reference;

    public PlayerManager(AxFriends reference) {
        this.reference = reference;
    }

    public boolean addFriend(Player sourcePlayer, Player targetPlayer) {
        try (Connection conn = reference.getDatabaseManager().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO friends(player_one, player_two) VALUES (?, ?)")) {
                ps.setString(1, sourcePlayer.getUniqueId().toString());
                ps.setString(2, targetPlayer.getUniqueId().toString());
                ps.executeUpdate();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public List<String> listFriends(Player sourcePlayer) {
        List<String> friends = new ArrayList<>();
        try (Connection conn = reference.getDatabaseManager().getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT player_two FROM friends WHERE player_one = ?")) {
                ps.setString(1, sourcePlayer.getUniqueId().toString());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        friends.add(rs.getString("player_two"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return friends;
    }
}
