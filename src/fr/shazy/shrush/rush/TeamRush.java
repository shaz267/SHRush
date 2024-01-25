package fr.shazy.shrush.rush;

import net.minecraft.server.v1_9_R2.Position;
import org.bukkit.*;
import org.bukkit.entity.Player;

import java.awt.*;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class TeamRush {
    private String name;
    private Color color;
    private final int maxPlayers = 2;
    private ArrayList<Player> playersList;
    private boolean bedDestroyed;
    Material bed = Material.BED_BLOCK;

    public TeamRush(String name) {
        this.name = name;
        this.playersList = new ArrayList<Player>();
        this.bedDestroyed = false;
        //récupere le monde de l'end et place un lit
        World end = Bukkit.getWorld("world_the_end");
        if (name.equals("bleu"))
            end.getBlockAt(new Location(end, 215,69,-8)).setType(this.bed);
        if (name.equals("rouge"))
            end.getBlockAt(new Location(end, 408,69,-8)).setType(this.bed);
    }
    public void ajouterPlayer(Player player) {
        if (this.playersList.size()<this.maxPlayers) {
            this.playersList.add(player);
        }
        else
            player.sendMessage(ChatColor.RED + "La team est pleine !");
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

    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
    public boolean isBedDestroyed() {
        return bedDestroyed;
    }

    public void setBedDestroyed(boolean b) {
        this.bedDestroyed = b;
    }
    public Material getBed() {
        return bed;
    }
}
