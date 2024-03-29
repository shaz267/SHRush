package fr.shazy.shrush.commands;

import fr.shazy.shrush.rush.PartieRush;
import fr.shazy.shrush.rush.TeamRush;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.awt.*;

public class CommandRushJoin implements CommandExecutor {
    private static final PartieRush partie = new PartieRush();

    public CommandRushJoin() {
        TeamRush teamRouge = new TeamRush("Rouge");
        partie.ajouterTeam(teamRouge);
        TeamRush teamBleu = new TeamRush("Bleu");
        partie.ajouterTeam(teamBleu);
    }


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        // On récupère le joueur
        Player player = (Player) commandSender;
        // On vérifie si la partie est démarrée et si il y a de la place
        if (!partie.isStarted()&&partie.getPlayers()<partie.getMaxPlayers()) {
            // On ajoute le joueur à la partie
            partie.ajouterPlayer(player);

        } else {
            // On envoie un message d'erreur
            player.sendMessage(ChatColor.RED + "La partie est pleine !");
            return false;
        }
        // On ferme l'inventaire
        player.closeInventory();
        // On clear le joueur
        player.getInventory().clear();

        //on créer une laine blanche
        ItemStack laine = new ItemStack(Material.WOOL);
        //on custom la laine
        ItemMeta customlaine = laine.getItemMeta();
        customlaine.setDisplayName("§lChoix de l'équipe");
        laine.setItemMeta(customlaine);
        //on donne la laine au joueur
        player.getInventory().setItem(0, laine);

        // On crée une porte
        ItemStack door = new ItemStack(Material.WOOD_DOOR);
        ItemMeta customdoor = door.getItemMeta();
        customdoor.setDisplayName(ChatColor.RED + "§lQuitter");
        door.setItemMeta(customdoor);
        // On donne l'item au joueur
        player.getInventory().setItem(8, door);
        player.closeInventory();
        // On téléporte le joueur au lobby du rush
        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 311, 69, -8));
        // On affiche un message
        Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a rejoint la partie !");
        return false;
    }
    public static PartieRush getPartie() {
        return partie;
    }
}
