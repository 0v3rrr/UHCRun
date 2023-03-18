package uhcrun.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

public class ScoreBoardManager {
    
    private ScoreboardManager scoreboardManager;
    private Scoreboard scoreboard;
    private Objective objective;
    private Team team;

    public ScoreBoardManager() {
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getNewScoreboard();
        objective = scoreboard.registerNewObjective("uhcRun", "dummy", ChatColor.BOLD + "UHC Run");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        team = scoreboard.registerNewTeam("players");
    }

    public void updateScoreBoard(List<Player> players) {
        objective.getScore(ChatColor.GREEN + "Players:").setScore(players.size());
        objective.getScore("").setScore(0);
        int i = 1;
        for (Player player : players) {
            if (!team.hasEntry(player.getName())) {
                team.addEntry(player.getName());
            }
            objective.getScore(team.getPrefix() + player.getName()).setScore(i);
            i++;
        }
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}