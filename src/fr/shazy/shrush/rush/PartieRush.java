package fr.shazy.shrush.rush;

import fr.shazy.shrush.commands.CommandRushJoin;
import fr.shazy.shrush.listeners.SHRushListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        SHRushListener.setInGame(true);
        TeamRush teamRouge = teams.get(0);
        TeamRush teamBleu = teams.get(1);
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
        for (Player player : teamRouge.getPlayersList()) {
            player.teleport(new Location(Bukkit.getWorld("world_the_end"), 214, 69, -8));
            player.sendTitle("§c§lVa me les saigner !", "(c'est sur ta droite)");
        }
        for (Player player : teamBleu.getPlayersList()) {
            player.teleport(new Location(Bukkit.getWorld("world_the_end"), 410, 69, -8));
            player.sendTitle("§r§lVa me les saigner !", "(c'est sur ta gauche)");
        }
        while (this.started) {
            for (Player player : playersList) {
                if (player.isDead()){
                    player.spigot().respawn();
                    player.setGameMode(org.bukkit.GameMode.SPECTATOR);
                    player.teleport(new Location(Bukkit.getWorld("world_the_end"), 311, 90, -8));
                    if (CommandRushJoin.getPartie().getTeam(player).isBedDestroyed()){
                        player.sendTitle("§c§lT'es mort mec ! et tu va pas réaparaitre", "la honte..");
                    }

                    for (int y = 5; y>0; y--){
                        player.sendTitle("§c§lT'es mort mec !", "respawn dans " + ChatColor.RED + y);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (teamRouge.getPlayersList().contains(player)&&!teamRouge.isBedDestroyed()) {
                        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 214, 69, -8));
                    }
                    if (teamBleu.getPlayersList().contains(player)&&!teamBleu.isBedDestroyed()) {
                        player.teleport(new Location(Bukkit.getWorld("world_the_end"), 410, 69, -8));
                    }
                }
            }
        }
        for (Player player : playersList) {
            SHRushListener.setInGame(false);
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
    public ArrayList<Player> getPlayersList() {
        return playersList;
    }
    public void removePlayer(Player player) {
        this.playersList.remove(player);
        this.players--;
    }
}
