package uhcrun;

import org.bukkit.plugin.java.JavaPlugin;

public class UHCRun extends JavaPlugin {
    
    private static UHCRun instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        // Enregistrement des événements
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitEvent(gameManager), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(gameManager), this);
        getServer().getPluginManager().registerEvents(new GameStartEvent(gameManager), this);
        getServer().getPluginManager().registerEvents(new GameEndEvent(gameManager), this);
        
        // Initialisation des managers
        ConfigManager configManager = new ConfigManager(this);
        ItemManager itemManager = new ItemManager(this);
        ScoreboardManager scoreboardManager = new ScoreboardManager(this);
        
        // Chargement de la configuration
        configManager.loadConfig();
        
        // Création du gestionnaire de parties
        this.gameManager = new GameManager(configManager, itemManager, scoreboardManager);
        
        // Enregistrement de la commande /uhcrun
        getCommand("uhcrun").setExecutor(new UHCRunCommand(gameManager));
        
        getLogger().info("UHC Run plugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("UHC Run plugin disabled!");
    }
    
    public static UHCRun getInstance() {
        return instance;
    }
    
}