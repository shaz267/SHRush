package fr.shazy.shrush.listeners;

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
import java.util.Arrays;

public class SHRushListener implements Listener {

    /**
     * Méthode qui gère tout ce qui se passe lors de la connexion d'un joueur
     * @param event
     */
    @EventHandler
    public void onJoin(PlayerJoinEvent event){

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

        // Si l'objet est une étoile du nether
        if(event.getItem().getType() == Material.NETHER_STAR && event.getItem().getItemMeta().getDisplayName().equals("§c-§f-§c-§f-§c-§f-§c» §4§lMe§c§lnu §c«§f-§c-§f-§c-§f-§c-")){
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
            // Si l'item cliqué est un lit
            if(current.getType() == Material.BED){
                player.chat("/rush");
            }
        }
    }
}
