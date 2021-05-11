package me.linckode.adminsnitcher;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if(event.getPlayer().hasPermission("adminsnitcher.admin") && !AdminSnitcher.getConfiguration().getIgnoredCommands().contains(event.getMessage().split(" ")[0].replace("/",""))){
           Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', AdminSnitcher.getConfiguration().getChatMessage().replace("%player%", event.getPlayer().getName())
                   .replace("%command%", event.getMessage())));
        }
    }
}
