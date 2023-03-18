package uhcrun.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import uhcrun.GameManager;

public class UHCPlayerJoinEvent implements Listener {

    private GameManager gameManager;

    public UHCPlayerJoinEvent(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        gameManager.checkPlayer(player);
    }

}