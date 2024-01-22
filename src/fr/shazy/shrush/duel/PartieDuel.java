package fr.shazy.shrush.duel;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;

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

        this.started = true;
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
}