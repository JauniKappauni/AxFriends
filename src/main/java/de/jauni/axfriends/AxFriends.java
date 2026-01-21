package de.jauni.axfriends;

import de.jauni.axfriends.manager.DatabaseManager;
import de.jauni.axfriends.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class AxFriends extends JavaPlugin {
    DatabaseManager databaseManager;

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    PlayerManager playerManager;

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        try {
            databaseManager = new DatabaseManager(this);
            playerManager = new PlayerManager(this);
            if (databaseManager.initDatabaseTable1() == false) {
                getLogger().severe("Error creating friends table!");
                Bukkit.getServer().shutdown();
            }
            if (databaseManager.initDatabaseTable2() == false) {
                getLogger().severe("Error creating friends_requests table!");
                Bukkit.getServer().shutdown();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        getCommand("addfriend").setExecutor(new de.jauni.axfriends.command.AddFriendCommand(this));
        getCommand("listfriends").setExecutor(new de.jauni.axfriends.command.ListFriendsCommand(this));
        getCommand("removefriend").setExecutor(new de.jauni.axfriends.command.RemoveFriendCommand(this));
        getCommand("acceptfriendrequest").setExecutor(new de.jauni.axfriends.command.AcceptFriendRequestCommand(this));
        getCommand("denyfriendrequest").setExecutor(new de.jauni.axfriends.command.DenyFriendRequestCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
