package de.jauni.axfriends.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    HikariDataSource hikari;

    public DatabaseManager(JavaPlugin plugin) {
        FileConfiguration pluginConfig = plugin.getConfig();

        String url = pluginConfig.getString("database.url");
        String username = pluginConfig.getString("database.username");
        String password = pluginConfig.getString("database.password");

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);

        hikari = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return hikari.getConnection();
    }

    public boolean initDatabaseTable1() throws SQLException {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS friends (player_one VARCHAR(255), player_two VARCHAR(255), PRIMARY KEY (player_one, player_two))")) {
                ps.executeUpdate();
                return true;
            }
        }
    }

    public boolean initDatabaseTable2() throws SQLException {
        try (Connection conn = getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("CREATE TABLE IF NOT EXISTS friend_requests (sender VARCHAR(255), receiver VARCHAR(255), PRIMARY KEY (sender, receiver))")) {
                ps.executeUpdate();
                return true;
            }
        }
    }
}
