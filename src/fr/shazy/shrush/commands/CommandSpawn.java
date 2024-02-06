package fr.shazy.shrush.commands;

import fr.shazy.shrush.Main;
import fr.shazy.shrush.rush.PartieRush;
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

import java.util.Arrays;

public class CommandSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String msg, String[] args) {
        // Si la commande est envoyée par un joueur
        if(commandSender instanceof Player){
            // On récupère le joueur
            Player player = (Player) commandSender;

            // Si le joueur était présent dans une partie de duel
            if(Main.partieDuel.getPlayersList().contains(player)){
                // On le supprime de la partie
                Main.partieDuel.getPlayersList().remove(player);
                // On envoie un message aux joueurs
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a quitté la partie de duel !");
                // Si la partie était lancée
                if(Main.partieDuel.getStarted()){
                    // On arrête la partie
                    Main.partieDuel.setStarted(false);
                    // On téléporte l'autre joueur au spawn
                    for(Player p : Main.partieDuel.getPlayersList()){
                        if(!p.equals(player)){
                            p.chat("/spawn");
                        }
                    }
                    // On clear les inventaires des joueurs
                    for(Player p : Main.partieDuel.getPlayersList()){
                        p.getInventory().clear();
                    }
                    // On supprime la partie
                    Main.partieDuel = null;
                }
            }
            PartieRush partieRush = CommandRushJoin.getPartie();
            //si le joueur était présent dans une partie de rush
            if(partieRush.getPlayersList().contains(player)){
                // On le supprime de la partie
                partieRush.getPlayersList().remove(player);
                // On envoie un message aux joueurs
                Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a quitté la partie de rush !");
                // Si la partie était lancée
                if(partieRush.isStarted()){
                    // On arrête la partie
                    partieRush.setStarted(false);
                    // On téléporte l'autre joueur au spawn
                    for(Player p : partieRush.getPlayersList()){
                        if(!p.equals(player)){
                            p.chat("/spawn");
                        }
                    }
                    // On clear les inventaires des joueurs
                    for(Player p : partieRush.getPlayersList()){
                        p.getInventory().clear();
                    }
                    // On supprime la partie
                    partieRush.setStarted(false);
                }
            }

            // Si le joueur n'a pas déja la netherstar
            if(!player.getInventory().contains(Material.NETHER_STAR)){
                // On regive le menu au joueur
                ItemStack netherstar = new ItemStack(Material.NETHER_STAR);

                // On custom la netherstar
                ItemMeta customns = netherstar.getItemMeta();
                customns.addEnchant(Enchantment.DURABILITY, 1, true);
                customns.setDisplayName("§c-§f-§c-§f-§c-§f-§c» §4§lMe§c§lnu §c«§f-§c-§f-§c-§f-§c-");
                customns.setLore(Arrays.asList("§6-", "§6Clique droit pour ouvrir le menu"));
                netherstar.setItemMeta(customns);

                // On donne la netherstar au joueur
                player.getInventory().addItem(netherstar);
            }
            // On crée la zone d'apparition
            Location spawn = new Location(Bukkit.getWorld("world"), -251, 96, -202);
            // On téléporte le joueur
            player.teleport(spawn);
            // On envoie un message de confirmation au joueur
            player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté au spawn.");
        }
        return false;
    }
}