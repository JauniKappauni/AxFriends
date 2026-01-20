package de.jauni.axfriends;

import de.jauni.axfriends.manager.DatabaseManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class AxFriends extends JavaPlugin {
    DatabaseManager databaseManager;
    public DatabaseManager getDatabaseManager(){
        return databaseManager;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        try{
            databaseManager = new DatabaseManager(this);
            if(databaseManager.initDatabaseTable1() == false){
                getLogger().severe("Error creating friends table!");
                Bukkit.getServer().shutdown();
            }
            if(databaseManager.initDatabaseTable2() == false){
                getLogger().severe("Error creating friends_requests table!");
                Bukkit.getServer().shutdown();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
