package uhcrun.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import uhcrun.GameManager;

public class UHCPlayerDeathEvent implements Listener {

    private GameManager gameManager;

    public UHCPlayerDeathEvent(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        gameManager.playerDied(player);
    }

}