package fr.shazy.shrush.rush;

import fr.shazy.shrush.commands.CommandRushJoin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class PartieRush{
    private final int maxPlayers = 4;
    private int players;
    private boolean started;
    private ArrayList<TeamRush> teams;
    private ArrayList<Player> playersList;

    public PartieRush() {
        this.players = 0;
        this.started = false;
        this.teams = new ArrayList<TeamRush>();
        this.playersList = new ArrayList<Player>();
    }
    public void start() { //méthode pas très opti je pense
        TeamRush teamRouge = teams.get(0);
        TeamRush teamBleu = teams.get(1);
        for (Player player : teamRouge.getPlayersList()) {
            player.teleport(new Location(Bukkit.getWorld("world_the_end"), 10000, 50, 10000));
            player.sendTitle("§c§lVa me les saigner !", "(c'est sur ta droite)");
        }
        for (Player player : teamBleu.getPlayersList()) {
            player.teleport(new Location(Bukkit.getWorld("world_the_end"), 10000, 50, 10000));
            player.sendTitle("§r§lVa me les saigner !", "(c'est sur ta gauche)");
        }
        while (this.started) {
            for (Player player : playersList) {
                if (player.isDead()){
                    player.spigot().respawn();
                    if (CommandRushJoin.getPartie().getTeam(player).isBedDestroyed()){
                        player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                        player.sendTitle("§c§lT'es mort mec ! et tu va pas réaparaitre", "la honte..");
                    }

                    if (teamRouge.getPlayersList().contains(player)) {
                        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 10000, 50, 10000));
                    }
                    if (teamBleu.getPlayersList().contains(player)) {
                        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 10000, 50, 10000));
                    }
                    player.sendTitle("§c§lC'est pas fini lache rien !", "");
                }
            }
        }
        for (Player player : playersList) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendTitle("§c§lLa partie est finie !", "");
            player.chat("/spawn");
        }
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public int getPlayers() {
        return players;
    }
    public boolean isStarted() {
        return started;
    }
    public void setStarted(boolean started) {
        this.started = started;
    }
    public ArrayList<TeamRush> getTeams() {
        return teams;
    }
    public void ajouterTeam(TeamRush team) {
        this.teams.add(team);
    }
    public void ajouterPlayer(Player player) {
        this.players++;
        this.playersList.add(player);
    }
    public void retirerPlayer() {
        this.players--;
    }
    public TeamRush getTeam(String nomTeam){
        for (TeamRush team : teams) {
            if (team.getName().equals(nomTeam)) {
                return team;
            }
        }
        return null;
    }
    public TeamRush getTeam(Player player){
        for (TeamRush team : teams) {
            if (team.getPlayersList().contains(player)) {
                return team;
            }
        }
        return null;
    }
}
