package fr.shazy.shrush.listeners;

import com.avaje.ebeaninternal.server.persist.Constant;
import net.minecraft.server.v1_9_R2.ChatTypeAdapterFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.swing.text.StyledEditorKit;
import java.io.Console;
import java.util.Arrays;

public class SHRushListener implements Listener {

    /**
     * Méthode qui gère tout ce qui se passe lors de la connexion d'un joueur
     * @param event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event){

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

                // On téléporte le joueur au spawn
                player.chat("/spawn");
                break;
        }
        //si l'objet est la laine blanche qui permet de choisir son équipe
        if (event.getItem().getType() == Material.WOOL && event.getItem().getItemMeta().getDisplayName().equals("§lChoix de l'équipe")){
            Inventory menuRush = Bukkit.createInventory(null, 2,"§lChoix de l'équipe");
            //crée un Item qui est un block de laine rouge
            ItemStack laineRouge = new ItemStack(Material.WOOL, 1, (short) 14);
            //crée un Item qui est un block de laine bleu
            ItemStack laineBleu = new ItemStack(Material.WOOL, 1, (short) 11);
            ItemMeta customlaineRouge = laineRouge.getItemMeta();
            ItemMeta customlaineBleu = laineBleu.getItemMeta();
            //on custom les blocks de laine
            customlaineRouge.setDisplayName("§cEquipe Rouge");
            customlaineBleu.setDisplayName("§9Equipe Bleu");
            laineRouge.setItemMeta(customlaineRouge);
            laineBleu.setItemMeta(customlaineBleu);
            //on place les blocks de laine dans le menu
            menuRush.setItem(0, laineRouge);
            menuRush.setItem(1, laineBleu);
            player.openInventory(menuRush);
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
                    // On crée une porte
                    ItemStack door = new ItemStack(Material.WOOD_DOOR);
                    ItemMeta customdoor = door.getItemMeta();
                    customdoor.setDisplayName(ChatColor.RED + "§lQuitter");
                    door.setItemMeta(customdoor);
                    // On donne l'item au joueur
                    player.getInventory().setItem(8, door);
                    break;
                case STAINED_GLASS_PANE:
                    // On annule l'évènement pour que le joueur ne puisse pas prendre l'item
                    event.setCancelled(true);
                    break;
            }
        }
        //si l'inventaire est celui du menu de choix d'équipe
        if (inv.getName().equals("§lChoix de l'équipe")){
            //si l'item cliqué est un block de laine rouge
            if (current.getType() == Material.WOOL && current.getItemMeta().getDisplayName().equals("§cEquipe Rouge")){
                //on l'ajoute dans l'équipe rouge

                //on téléporte le joueur dans l'équipe rouge
                player.teleport(new Location(Bukkit.getWorld("world_the_end"), 10000, 50, 10000));
                //on lui envoie un message
                player.sendMessage(ChatColor.RED + "Tu as rejoint l'équipe rouge");
            }
        }
    }
}
