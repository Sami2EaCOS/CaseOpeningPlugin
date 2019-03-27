package fr.smourad.Test.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.smourad.MyPlugin.MyPlugin;
import fr.smourad.Test.ressources.InvPermission;

public class PlayerInteract implements Listener {
	
	private MyPlugin plugin;
	
	public PlayerInteract(MyPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent e) {
		if (plugin.chestList.contains(e.getClickedBlock())) {
			Player p = e.getPlayer();
			
			if (p.getItemInHand().getType().equals(Material.DIAMOND_BLOCK) && p.getItemInHand().getAmount() >= 10) {
				p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 10);
				plugin.caseOpening.newCaseOpening(p);
			} else {
				p.openInventory(plugin.caseOpening.getActualRewards("Récompenses Possibles", InvPermission.READ));
			}
			
			e.setCancelled(true);
		}
	}
	
	
}
