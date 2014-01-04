package co.mccn.mccnsql;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

public class MCCNSQL extends JavaPlugin {
    private String databaseName;
    private Database database;
    private String userName;
    private String passWord;

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

    /**
     * Set databaseName;
     *
     * @param databaseName
     */
    public void setDatabase(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Set database userName;
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Set database passWord;
     *
     * @param passWord
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * @return userName String;
     */
    public String getUserName() {
        return this.getUserName();
    }

    /**
     * @return passWord String;
     */
    public String getPassWord() {
        return this.passWord;
    }

    /**
     * @return database Name;
     */
    public String getDatabaseName() {
        return this.databaseName;
    }

    /**
     * Initialize connection to database;
     */
    public void connect() {
        try {
            this.database.connect();
        } catch (SQLException e) {
            getLogger().log(Level.SEVERE, e.getMessage());
        }
    }

}
