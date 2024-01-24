package fr.shazy.shrush.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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

            // Si le joueur n'a pas déja la netherstar
            if(!player.getInventory().contains(Material.NETHER_STAR)){
                // On regive le menu au joueur
                ItemStack netherstar = new ItemStack(Material.NETHER_STAR);

                // On custom la netherstar
                ItemMeta customns = netherstar.getItemMeta();
                customns.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
                customns.setDisplayName("§c-§f-§c-§f-§c-§f-§c» §4§lMe§c§lnu §c«§f-§c-§f-§c-§f-§c-");
                customns.setLore(Arrays.asList("§6-", "§6Clique droit pour ouvrir le menu"));
                netherstar.setItemMeta(customns);

                // On donne la netherstar au joueur
                player.getInventory().addItem(netherstar);
            }
            // On crée la zone d'apparition
            Location spawn = new Location(Bukkit.getWorld("world"), -250, 96, -201);
            // On téléporte le joueur
            player.teleport(spawn);
            // On envoie un message de confirmation au joueur
            player.sendMessage(ChatColor.GREEN + "Vous avez été téléporté au spawn.");
        }
        return false;
    }
}
