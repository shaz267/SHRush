package fr.shazy.shrush.commands;

import fr.shazy.shrush.Main;
import fr.shazy.shrush.duel.PartieDuel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandDuel implements CommandExecutor {

    public CommandDuel(){
        if(Main.partieDuel == null)
            Main.partieDuel = new PartieDuel();
    }
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // On récupère le joueur
        Player player = (Player) commandSender;

        // Si une partie duel est déja en cours
        if(Main.partieDuel.getStarted()){
            // On envoie un message d'erreur
            player.sendMessage(ChatColor.GREEN + "Une partie de duel est déja en cours !");
            return false;
        }
        // Sinon
        else{
            // On crée une zone d'apparition
            Location duel = new Location(Bukkit.getWorld("world"),-499,119,-253);
            // On téléporte le joueur
            player.teleport(duel);
            // On le clear
            player.getInventory().clear();
            // On affiche un message
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a rejoint la partie de duel !");
            // On ajoute le joueur à la partie
            Main.partieDuel.ajouterPlayer(player);
            // Si il y a 2 joueurs dans la partie
            if(Main.partieDuel.getPlayersList().size() == 2){
                // On démarre la partie
                Main.partieDuel.start();
            }
        }
        return false;
    }
}
