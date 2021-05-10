package me.linckode.adminsnitcher;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AdminSnitcherCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (!commandSender.hasPermission("adminsnitcher.command")) {
            commandSender.sendMessage(ChatColor.RED + "You lack the permission to use this command.");
            return false;
        }

        if (args.length == 0){
            commandSender.sendMessage(ChatColor.GREEN + AdminSnitcher.getInstance().getDescription().getName() + ChatColor.YELLOW + " version " + ChatColor.DARK_PURPLE + AdminSnitcher.getInstance().getDescription().getVersion());
            commandSender.sendMessage(ChatColor.GREEN + "By " + ChatColor.GOLD +  AdminSnitcher.getInstance().getDescription().getAuthors().get(0));
            return true;
        }
        if (args.length == 1 && args[0].equals("reload")){
            commandSender.sendMessage(ChatColor.YELLOW + "Reloading " + AdminSnitcher.getInstance().getDescription().getName() + "...");
            if (!AdminSnitcher.loadConfigCheck()) {
                commandSender.sendMessage(ChatColor.RED + "Encountered errors while reloading! Check console!");
                return true;
            }
            commandSender.sendMessage(ChatColor.GREEN + "Successfully reloaded.");
            return true;
        }

        return true;
    }
}
