package co.mccn.mccnsql;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class MCCNSQL extends JavaPlugin {
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
            this.getLogger().log(Level.INFO,"MCCNSQL connected to the database.");
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

    /**
     * @param query
     * @param parameters
     */
    public void executeUpdate(String query, Object... parameters) {
        this.database.executeUpdate(query, parameters);

    }

    /**
     * @param query
     * @param parameters
     * @return ResultSet
     */
    public ResultSet executeQuery(String query, Object... parameters) {
        return this.database.executeQuery(query, parameters);
    }

    /**
     * Reconnect to Database;
     */
    public void reconnect() {
        try {
            this.database.reconnect();
        } catch (SQLException ex) {
            getLogger().log(Level.SEVERE, ex.getMessage());
        }
    }

    /**
     * Disconnect from Database;
     */
    public void disconnect() {
        this.database.disconnect();
    }

    /**
     * @param query
     * @param result
     * @param parameters
     */
    public void buildAndFetchColumn(String query, String result, Object... parameters) {
        this.buildAndFetchColumn(query, result, parameters);
    }



}
