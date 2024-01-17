package fr.shazy.shrush.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CommandPinte implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // Si la commande est envoyée par un joueur
        if(commandSender instanceof Player){
            // On récupère le joueur
            Player player = (Player) commandSender;
            // On lui ajoute un effet de nausée
            player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 500, 50));
            // On lui envoie un message
            player.sendMessage(ChatColor.GREEN + "Petit coquin vaj ! Tu vas finir avec la même panse que Seb.");
        }
        return false;
    }
}
