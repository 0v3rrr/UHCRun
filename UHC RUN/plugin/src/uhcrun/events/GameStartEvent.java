package uhcrun.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import uhcrun.GameManager;

public class GameStartEvent implements Listener {

    private GameManager gameManager;

    public GameStartEvent(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        gameManager.checkStartGame();
    }

}