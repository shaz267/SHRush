package fr.shazy.shrush.rush;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PartieRush {
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
}
