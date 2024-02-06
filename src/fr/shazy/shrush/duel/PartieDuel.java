package fr.shazy.shrush.duel;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

public class PartieDuel {
    private boolean started;
    private ArrayList<Player> playersList;

    public PartieDuel() {
        this.started = false;
        this.playersList = new ArrayList<Player>();
    }

    /**
     * Méthode qui démarre la partie
     */
    public void start() {
        // On fait un décompte
        for (int i = 5; i > 0; i--) {
            // On envoie un message à tous les joueurs
            for (Player player : playersList) {
                player.sendMessage(ChatColor.YELLOW + "Début dans " + ChatColor.RED + i + ChatColor.YELLOW + " secondes !");
            }
            // On attend 1 seconde
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // On envoie un message à tous les joueurs
        for (Player player : playersList) {
            player.sendMessage(ChatColor.GREEN + "Battez-vous !");
        }
        // On téléporte les 2 joueurs dans l'arène
        Location arene1 = new Location(Bukkit.getWorld("world"), -497, 119, -300);
        Location arene2 = new Location(Bukkit.getWorld("world"), -439, 119, -300);
        playersList.get(0).teleport(arene1);
        playersList.get(1).teleport(arene2);
        // On clear les inventaires des joueurs
        playersList.get(0).getInventory().clear();
        playersList.get(1).getInventory().clear();
        // On crée une épée en pierre
        ItemStack sword = new ItemStack(Material.STONE_SWORD);
        // Un arc
        ItemStack bow = new ItemStack(Material.BOW);
        // Des pommes en or
        ItemStack apple = new ItemStack(Material.GOLDEN_APPLE, 10);
        // Une rod
        ItemStack rod = new ItemStack(Material.FISHING_ROD);
        // Des flèches
        ItemStack arrow = new ItemStack(Material.ARROW, 15);
        // Une armure en fer
        ItemStack helmet = new ItemStack(Material.IRON_HELMET);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS);
        // On donne tout ça aux joueurs
        playersList.get(0).getInventory().setItem(0, sword);
        playersList.get(1).getInventory().setItem(0, sword);
        playersList.get(0).getInventory().setItem(1, bow);
        playersList.get(1).getInventory().setItem(1, bow);
        playersList.get(0).getInventory().setItem(2, apple);
        playersList.get(1).getInventory().setItem(2, apple);
        playersList.get(0).getInventory().setItem(3, rod);
        playersList.get(1).getInventory().setItem(3, rod);
        playersList.get(0).getInventory().setItem(4, arrow);
        playersList.get(1).getInventory().setItem(4, arrow);
        playersList.get(0).getInventory().setHelmet(helmet);
        playersList.get(1).getInventory().setHelmet(helmet);
        playersList.get(0).getInventory().setChestplate(chestplate);
        playersList.get(1).getInventory().setChestplate(chestplate);
        playersList.get(0).getInventory().setLeggings(leggings);
        playersList.get(1).getInventory().setLeggings(leggings);
        playersList.get(0).getInventory().setBoots(boots);
        playersList.get(1).getInventory().setBoots(boots);

        // On vérifie que les joueurs soient en survival
        if (playersList.get(0).getGameMode() != org.bukkit.GameMode.SURVIVAL)
            playersList.get(0).setGameMode(org.bukkit.GameMode.SURVIVAL);
        if (playersList.get(1).getGameMode() != org.bukkit.GameMode.SURVIVAL)
            playersList.get(1).setGameMode(org.bukkit.GameMode.SURVIVAL);

        this.started = true;
    }

    /**
     * Méthode qui arrête la partie
     */
    public void stop(){
        this.started = false;
    }

    /**
     * Méthode qui ajoute un joueur à la partie
     * @param player joueur à ajouter
     *
     */
    public void ajouterPlayer(Player player) {
        // Si il y a moins de 2 personnes dans la partie
        if (this.playersList.size()<2) {
            // On ajoute le joueur à la partie
            this.playersList.add(player);
        }
        // Sinon
        else
            // On envoie un message d'erreur
            player.sendMessage(ChatColor.GREEN + "La partie est pleine !");
    }

    public boolean getStarted(){
        return this.started;
    }

    public ArrayList<Player> getPlayersList() {
        return this.playersList;
    }

    public void setStarted(boolean b) {
        this.started = b;
    }
}