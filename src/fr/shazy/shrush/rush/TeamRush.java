package fr.shazy.shrush.rush;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TeamRush {
    private String name;
    private Color color;
    private final int maxPlayers = 2;
    private int players;
    private ArrayList<Player> playersList;

    public TeamRush(String name, Color color) {
        this.name = name;
        this.color = color;
        this.players = 0;
        this.playersList = new ArrayList<Player>();
    }

    public String getName() {
        return name;
    }
    public Color getColor() {
        return color;
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public int getPlayers() {
        return players;
    }
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
    public void ajouterPlayer(Player player) {
        if (this.players<this.maxPlayers) {
            this.players++;
            this.playersList.add(player);
        }
        else
            player.sendMessage(ChatColor.RED + "La team est pleine !");
    }
}
