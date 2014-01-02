package co.mccn.mccnsql;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class MCCNSQL extends JavaPlugin {
    private String databaseName;
    private Database database;

    public final void onLoad() {
        if (!new File(this.getDataFolder(), "config.yml").exists()) {
            this.saveDefaultConfig();
            this.getLogger().severe("THIS IS NOT A PLUGIN. THIS IS AN API FOR OTHER PLUGINS TO USE. DO NOT EXPECT THIS PLUGIN TO DO ANYTHING.");
        }
    }

    public final void onEnable() {
        try {
            this.database = new Database(this);
        } catch (SQLException ex) {
            this.getLogger().severe("Error connecting to database! Disabling...");
            ex.printStackTrace();
            this.getPluginLoader().disablePlugin(this);
            return;
        }

    }

    public final void onDisable() {
        this.database.disconnect();
    }

    public void executeUpdate(String query, Object... parameters) {
        this.database.executeUpdate(query, parameters);

    }

    public ResultSet executeQuery(String query, Object... parameters) {
        return this.database.executeQuery(query, parameters);
    }

    public void reconnect() {
        try {
            this.database.reconnect();
        } catch (SQLException ex) {
            getLogger().log(Level.SEVERE, ex.getMessage());
        }
    }

    public void disconnect() {
        this.database.disconnect();
    }

    public void buildAndFetchColumn(String query, String result, Object... parameters) {
        this.buildAndFetchColumn(query, result, parameters);
    }

    public void setDatabase(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return this.databaseName;
    }

    public void connect() {
        try {
            this.database.connect();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, e.getMessage());
        }
    }

}
