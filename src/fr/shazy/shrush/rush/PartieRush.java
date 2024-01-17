package fr.shazy.shrush.rush;

import java.util.ArrayList;

public class PartieRush {
    private final int maxPlayers = 4;
    private int players;
    private boolean started;
    private ArrayList<TeamRush> teams;

    public PartieRush() {
        this.players = 0;
        this.started = false;
        this.teams = new ArrayList<TeamRush>();
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
    public ArrayList<TeamRush> getTeams() {
        return teams;
    }
    public void ajouterTeam(TeamRush team) {
        this.teams.add(team);
    }
    public void ajouterPlayer() {
        if (this.players < this.maxPlayers)
            this.players++;
    }
    public void retirerPlayer() {
        this.players--;
    }
}
