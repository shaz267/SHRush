package fr.shazy.shrush;
import fr.shazy.shrush.commands.CommandPinte;
import fr.shazy.shrush.commands.CommandRushJoin;
import fr.shazy.shrush.commands.CommandSpawn;
import fr.shazy.shrush.listeners.SHRushListener;
import org.bukkit.plugin.java.*;

public class Main extends JavaPlugin{



    @Override
    public void onEnable() {
        System.out.println("Le plugin est op!");

        // On associe les commandes aux executeurs
        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("rush").setExecutor(new CommandRushJoin());
        getCommand("pinte").setExecutor(new CommandPinte());

        // On crée un listener
        getServer().getPluginManager().registerEvents(new SHRushListener(), this);

        // On crée une partie
    }

    @Override
    public void onDisable() {
        System.out.println("Le plugin s'est correctement arreté.");
    }
}