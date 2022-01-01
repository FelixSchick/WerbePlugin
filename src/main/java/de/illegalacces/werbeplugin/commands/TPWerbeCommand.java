package de.illegalacces.werbeplugin.commands;

import de.illegalacces.werbeplugin.WerbePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

import javax.swing.text.PlainDocument;

public class TPWerbeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length >= 1){
                String message = "";
                for (int i = 0; i < args.length; i++){
                    message = message + " " + args[i];
                }
                if (WerbePlugin.werbung.containsKey(message)){
                    Player target = WerbePlugin.werbung.get(message);
                    player.teleport(target.getLocation());
                }
            }
        }
        return false;
    }
}