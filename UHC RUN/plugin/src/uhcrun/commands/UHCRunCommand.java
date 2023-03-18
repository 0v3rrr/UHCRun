package uhcrun.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import uhcrun.GameManager;

public class UHCRunCommand implements CommandExecutor {

    private GameManager gameManager;

    public UHCRunCommand(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                gameManager.joinGame(player);
                return true;
            } else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
                gameManager.leaveGame(player);
                return true;
            }
        }
        return false;
    }

}