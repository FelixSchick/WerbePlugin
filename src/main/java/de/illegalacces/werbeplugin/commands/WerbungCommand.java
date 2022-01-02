package de.illegalacces.werbeplugin.commands;

import de.illegalacces.werbeplugin.WerbePlugin;
import de.illegalacces.werbeplugin.utils.CooldownManager;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.w3c.dom.Text;


public class WerbungCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("werbung.vip")){
                int timeLeft = CooldownManager.INSTANCE.getCooldown(player.getUniqueId());
                if (timeLeft == 0) {
                    if (args.length >= 1){
                        String message = "";
                        for (int i = 0; i < args.length; i++){
                            message = message + " " + args[i];
                        }
                        WerbePlugin.werbung.put(message, player);
                        TextComponent text = new TextComponent("§6§l"+ message);
                        text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpw "+ message));
                        text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clicke um teleportiert zu werden.").create()));
                        Bukkit.getServer().broadcastMessage("§8---------- §4§lExample Server Advertise §8§l----------");
                        Bukkit.getServer().spigot().broadcast(text);
                        Bukkit.getServer().broadcastMessage("§8---------------------------------------------");
                    } else {
                        player.sendMessage("§4§lExample Server Advertise §8§l |  Dein Text muss größe als nix sein.");
                    }
                    CooldownManager.INSTANCE.setCooldown(player.getUniqueId(), 300);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            int timeLeft = CooldownManager.INSTANCE.getCooldown(player.getUniqueId());
                            if (timeLeft == 0) {
                                CooldownManager.INSTANCE.setCooldown(player.getUniqueId(), null);
                                this.cancel();
                                return;
                            }
                            CooldownManager.INSTANCE.setCooldown(player.getUniqueId(), timeLeft - 1);
                        }
                    }.runTaskTimer(WerbePlugin.plugin, 0, 20);

                } else {
                    player.sendMessage(timeLeft + " sekunden bevor du wieder werbung machen kannst.");
                }
            }
        }
        return false;
    }
}