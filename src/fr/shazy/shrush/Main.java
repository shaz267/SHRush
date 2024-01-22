package fr.shazy.shrush;
import fr.shazy.shrush.commands.CommandDuel;
import fr.shazy.shrush.commands.CommandPinte;
import fr.shazy.shrush.commands.CommandRushJoin;
import fr.shazy.shrush.commands.CommandSpawn;
import fr.shazy.shrush.duel.PartieDuel;
import fr.shazy.shrush.listeners.SHRushListener;
import fr.shazy.shrush.rush.PartieRush;
import org.bukkit.plugin.java.*;

public class Main extends JavaPlugin{

    // attribut permettant de savoir si une partieDuel est déja en cours
    public static PartieDuel partieDuel = null;

    @Override
    public void onEnable() {
        System.out.println("Le plugin est op!");

        // On associe les commandes aux executeurs
        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("rush").setExecutor(new CommandRushJoin());
        getCommand("pinte").setExecutor(new CommandPinte());
        getCommand("duel").setExecutor(new CommandDuel());

        // On crée un listener
        getServer().getPluginManager().registerEvents(new SHRushListener(), this);
    }

    @Override
    public void onDisable() {
        System.out.println("Le plugin s'est correctement arreté.");
    }
}