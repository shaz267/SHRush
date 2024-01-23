package fr.shazy.shrush.listeners;

import fr.shazy.shrush.Main;
import fr.shazy.shrush.commands.CommandRushJoin;
import fr.shazy.shrush.rush.TeamRush;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Team;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class SHRushListener implements Listener {
    /**
     * Méthode qui gère tout ce qui se passe lors de la connexion d'un joueur
     * @param event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event){

        // On active le pvp 1.8 pour le joueur
        Bukkit.getServer().broadcastMessage("ocm mode old " + event.getPlayer().getName());

        // On téléporte le joueur au spawn
        event.getPlayer().chat("/spawn");

        // On désactive le message de bienvenue par défaut
        event.setJoinMessage(null);

        // On affiche un title de bienvenue
        event.getPlayer().sendTitle(ChatColor.RED + "Bienvenue sur le serveur !", ChatColor.GRAY + "Viens te battre mtn");

        // On affiche un message de bienvenue
        event.getPlayer().sendMessage(ChatColor.YELLOW + "Bienvenue sur le serveur !");

        // On clear son inventaire
        event.getPlayer().getInventory().clear();

        // On lui donne une netherstar
        ItemStack netherstar = new ItemStack(Material.NETHER_STAR);

        // On custom la netherstar
        ItemMeta customns = netherstar.getItemMeta();
        customns.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
        customns.setDisplayName("§c-§f-§c-§f-§c-§f-§c» §4§lMe§c§lnu §c«§f-§c-§f-§c-§f-§c-");
        customns.setLore(Arrays.asList("§6-", "§6Clique droit pour ouvrir le menu"));
        netherstar.setItemMeta(customns);

        // On donne la netherstar au joueur
        event.getPlayer().getInventory().addItem(netherstar);
    }

    /**
     * Méthode qui gère tout ce qui se passe lors de la déconnexion d'un joueur
     * @param event
     */
    @EventHandler
    public void onQuit(PlayerQuitEvent event){

        // On désactive le message de bienvenue par défaut
        event.setQuitMessage(null);
    }

    /**
     * Méthode qui gère tout ce qui se passe lorsqu'un joueur interagit avec un objet
     * @param event
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        // On récupère le joueur qui a interagit avec l'objet
        Player player = event.getPlayer();

        // Switch en fonction de l'item
        if(event.getItem() == null){return;} // Si l'item est null (si le joueur n'a rien dans la main) on ne fait rien
        switch(event.getItem().getType()){
            case NETHER_STAR:
                // On crée le GUI et les items à ajouter dedans
                Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "§lMine" + "§8§lstoners");
                ItemStack rush = new ItemStack(Material.BED);
                ItemMeta customrush = rush.getItemMeta();

                // On custom l'item
                customrush.setDisplayName(ChatColor.WHITE + "✦ " + ChatColor.RED + "Rush");
                customrush.setLore(Arrays.asList("§6-", "Cliques pour jouer en rush"));
                customrush.addEnchant(org.bukkit.enchantments.Enchantment.DURABILITY, 1, true);
                rush.setItemMeta(customrush);

                // On place les items dans le GUI
                ItemStack redglass = new ItemStack(Material.STAINED_GLASS_PANE, 1);

                inv.setItem(0, redglass);
                inv.setItem(1, redglass);
                inv.setItem(2, redglass);
                inv.setItem(3, redglass);
                inv.setItem(4, rush);
                inv.setItem(5, redglass);
                inv.setItem(6, redglass);
                inv.setItem(7, redglass);
                inv.setItem(8, redglass);

                player.openInventory(inv);
                break;
            case WOOD_DOOR:
                // On retire la porte de l'inventaire du joueur
                player.getInventory().setItem(8, null);

                // On retire la laine de l'inventaire du joueur
                player.getInventory().setItem(0, null);

                // On téléporte le joueur au spawn
                player.chat("/spawn");
                break;
            // Si l'item est la laine blanche qui permet de choisir son équipe
            case WOOL:
                Inventory menuRush = Bukkit.createInventory(null, 9,"§lChoix de l'équipe");
                //crée un Item qui est un block de laine rouge
                ItemStack laineRouge = new ItemStack(Material.WOOL, 1, (short) 14);
                //crée un Item qui est un block de laine bleu
                ItemStack laineBleu = new ItemStack(Material.WOOL, 1, (short) 11);
                ItemMeta customlaineRouge = laineRouge.getItemMeta();
                ItemMeta customlaineBleu = laineBleu.getItemMeta();
                //on custom les blocks de laine
                String lore = "";
                customlaineRouge.setDisplayName("§cEquipe Rouge");
                for (Player p : CommandRushJoin.getPartie().getTeam("Rouge").getPlayersList()) {
                    lore += p.getName() + "\n";
                }
                customlaineRouge.setLore(Collections.singletonList(lore));
                customlaineBleu.setDisplayName("§9Equipe Bleu");
                lore = "";
                for (Player p : CommandRushJoin.getPartie().getTeam("Bleu").getPlayersList()) {
                    lore += p.getName() + "\n";
                }
                customlaineBleu.setLore(Collections.singletonList(lore));
                laineRouge.setItemMeta(customlaineRouge);
                laineBleu.setItemMeta(customlaineBleu);
                //on place les blocks de laine dans le menu
                menuRush.setItem(0, laineRouge);
                menuRush.setItem(1, laineBleu);
                player.openInventory(menuRush);
                break;
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        // On récupère l'inventaire, le joueur et l'item cliqué
        Inventory inv = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        // Si l'item cliqué est null
        if(current == null){return;}

        // Si l'inventaire est celui du menu
        if(inv.getName().equals(ChatColor.RED + "§lMine" + "§8§lstoners")){
            // switch en fonction de l'item cliqué
            switch(current.getType()){
                case BED:
                    // On téléporte le joueur au lobby du rush
                    player.chat("/rush");
                    break;
                case STAINED_GLASS_PANE:
                    // On annule l'évènement pour que le joueur ne puisse pas prendre l'item
                    event.setCancelled(true);
                    break;
            }
        }
        //si l'inventaire est celui du menu de choix d'équipe
        if (inv.getName().equals("§lChoix de l'équipe")){
            TeamRush teamRouge = CommandRushJoin.getPartie().getTeam("Rouge");
            TeamRush teamBleu = CommandRushJoin.getPartie().getTeam("Bleu");
            //si l'item cliqué est un block de laine rouge
            if (current.getType() == Material.WOOL && current.getItemMeta().getDisplayName().equals("§cEquipe Rouge")){
                player.closeInventory();
                //on l'ajoute dans l'équipe rouge s'il n'y est pas déjà
                if (!teamRouge.getPlayersList().contains(player)) {
                    teamBleu.getPlayersList().remove(player);
                    teamRouge.ajouterPlayer(player);
                    //on lui envoie un message
                    player.sendMessage(ChatColor.RED + "Tu as rejoint l'équipe rouge");
                }
                else
                    player.sendMessage(ChatColor.RED + "Tu es déjà dans l'équipe rouge");
            }
            //si l'item cliqué est un block de laine bleu
            if (current.getType() == Material.WOOL && current.getItemMeta().getDisplayName().equals("§9Equipe Bleu")){
                player.closeInventory();
                //on l'ajoute dans l'équipe bleu s'il n'y est pas déjà
                if (!teamBleu.getPlayersList().contains(player)) {
                    teamRouge.getPlayersList().remove(player);
                    teamBleu.ajouterPlayer(player);
                    //on lui envoie un message
                    player.sendMessage(ChatColor.BLUE + "Tu as rejoint l'équipe bleu");
                }
                else
                    player.sendMessage(ChatColor.BLUE + "Tu es déjà dans l'équipe bleu");
            }
            //si les deux équipes sont pleines on démarre la partie
            if (teamRouge.getPlayersList().size()==teamRouge.getMaxPlayers()&&teamBleu.getPlayersList().size()==teamBleu.getMaxPlayers()) {
                // On préviens que le paetie va commencer
                Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "Que la partie commence !");
                // On démarre la partie
                CommandRushJoin.getPartie().setStarted(true);
                CommandRushJoin.getPartie().start();
            }
        }
    }
    @EventHandler
    public void onBreak(BlockBreakEvent event){
        // On récupère le joueur
        Player player = event.getPlayer();
        // Si le joueur n'est pas dans le monde du rush
        if(!player.getWorld().getName().equals("world_the_end")){
            // On annule l'évènement
            event.setCancelled(true);
        }
        //on récupère le block cassé
        Material block = event.getBlock().getType();
        //si le block cassé est un lit
        if (block == Material.BED_BLOCK){
            //on récupère l'équipe du joueur
            TeamRush team = CommandRushJoin.getPartie().getTeam(player);
            //affiche un message a tout les joueurs
            Bukkit.getServer().broadcastMessage(ChatColor.RED + player.getName() + " a détruit le lit de ses adversaires !");
            //on détruit le lit
            event.getBlock().setType(Material.AIR);
            //on met le lit comme détruit
            team.setBedDestroyed(true);
        }
    }

    /**
     * Méthode qui gère tout ce qui se passe lors de la mort d'un joueur
     */
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        // On récupère le joueur
        Player player = e.getEntity();


        // Si le joueur est dans la partie de duel
        if(Main.partieDuel.getPlayersList().contains(player)){
            Player otherPlayer;
            // On arrête la partie
            Main.partieDuel.stop();
            // On récupère l'autre joueur de la partie
            if(Main.partieDuel.getPlayersList().get(0) == player)
                otherPlayer = Main.partieDuel.getPlayersList().get(1);
            else
                otherPlayer = Main.partieDuel.getPlayersList().get(0);
            // On affiche un message
            Bukkit.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a perdu en duel face à " + ChatColor.YELLOW + otherPlayer.getName() + ChatColor.GRAY + " !");
        }
    }

}
