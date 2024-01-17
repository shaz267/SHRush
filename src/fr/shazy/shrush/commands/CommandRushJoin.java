package fr.shazy.shrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandRushJoin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // On récupère le joueur
        Player player = (Player) commandSender;

        // On ferme l'inventaire
        player.closeInventory();
        // On clear le joueur
        player.getInventory().clear();
        // On téléporte le joueur au lobby du rush
        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 10000, 50, 10000));
        // On affiche un message
        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a rejoint la partie !");
        return false;
    }
}
