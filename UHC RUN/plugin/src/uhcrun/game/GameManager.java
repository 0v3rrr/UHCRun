package uhcrun.game;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameManager {

    private final Plugin plugin;
    private final List<Player> players;
    private final Map<Player, Location> playerLocations;
    private BukkitTask gameTask;
    private GameState gameState;

    public GameManager(Plugin plugin) {
        this.plugin = plugin;
        this.players = new ArrayList<>();
        this.playerLocations = new HashMap<>();
        this.gameState = GameState.WAITING;
    }

    public void addPlayer(Player player) {
        players.add(player);
        playerLocations.put(player, player.getLocation());
        player.setGameMode(GameMode.ADVENTURE);
        player.getInventory().clear();
        player.sendMessage("§aYou have been added to the UHC Run game.");
        Bukkit.broadcastMessage("§a" + player.getName() + " has joined the game. (" + players.size() + "/8)");
        if (gameState == GameState.WAITING && players.size() >= 2) {
            startCountdown();
        }
    }

    public void removePlayer(Player player) {
        players.remove(player);
        player.teleport(playerLocations.get(player));
        playerLocations.remove(player);
        player.setGameMode(GameMode.SURVIVAL);
        player.sendMessage("§cYou have been removed from the UHC Run game.");
        Bukkit.broadcastMessage("§c" + player.getName() + " has left the game. (" + players.size() + "/8)");
        if (gameState == GameState.COUNTDOWN && players.size() < 2) {
            gameTask.cancel();
            gameState = GameState.WAITING;
            Bukkit.broadcastMessage("§cThe countdown has been cancelled.");
        }
    }

    private void startCountdown() {
        gameState = GameState.COUNTDOWN;
        Bukkit.broadcastMessage("§eThe UHC Run game will start in 30 seconds.");
        gameTask = new BukkitRunnable() {
            int countdown = 30;

            @Override
            public void run() {
                if (countdown == 0) {
                    startGame();
                    cancel();
                    return;
                }

                if (countdown <= 10 || countdown == 20 || countdown == 25) {
                    Bukkit.broadcastMessage("§eThe UHC Run game will start in " + countdown + " second(s).");
                }

                countdown--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    private void startGame() {
        gameState = GameState.RUNNING;
        Bukkit.broadcastMessage("§aThe UHC Run game has started!");
        for (Player player : players) {
            player.sendMessage("§aThe game has started! Good luck!");
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public List<Player> getPlayers() {
        return players;
    }
}