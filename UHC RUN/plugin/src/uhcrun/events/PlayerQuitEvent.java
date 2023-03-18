package uhcrun.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import uhcrun.GameManager;

public class UHCPlayerQuitEvent implements Listener {

    private GameManager gameManager;

    public UHCPlayerQuitEvent(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        gameManager.removePlayer(player);
    }

}