package fr.smourad.Test.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import fr.smourad.MyPlugin.MyPlugin;
import fr.smourad.Test.ressources.InvPermission;
import fr.smourad.Test.ressources.Rarity;

public class ClickInventory implements Listener {

	private MyPlugin plugin;
	
	public ClickInventory(MyPlugin plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		
		Inventory inv = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		
		if (plugin.caseOpening.invList.containsKey(inv)) {
			if (plugin.caseOpening.invList.get(inv).equals(InvPermission.WRITE)) {
				int slot = e.getSlot();
				
				if ((9 <= slot && slot <= 17) || (27 <= slot && slot <= 35) || (54 <= slot && slot <= 63)) {
					e.setCancelled(true);
					if (inv.getItem(slot).getType().equals(Material.WOOL)) {
						
						switch (inv.getItem(slot).getDurability()) {
						case 1:
							inv.setItem(slot+9, plugin.caseOpening.woolRarityColor(Rarity.COMMON));
							break;
						case 10:
							inv.setItem(slot+9, plugin.caseOpening.woolRarityColor(Rarity.LEGENDARY));
							break;
						case 11:
							inv.setItem(slot+9, plugin.caseOpening.woolRarityColor(Rarity.EPIC));
							break;
						case 0:
							inv.setItem(slot+9, plugin.caseOpening.woolRarityColor(Rarity.RARE));
							break;
						default:
							break;
						}
					}
				} else {
					if (inv.getItem(slot) != null) {
						inv.setItem(slot+9, plugin.caseOpening.noneItem());
					} else {
						inv.setItem(slot+9, plugin.caseOpening.woolRarityColor(Rarity.COMMON));
					}
				}
				
				p.updateInventory();
			} else if (plugin.caseOpening.invList.get(inv).equals(InvPermission.READ)) {
				e.setCancelled(true);
			}
		}
	}
}
