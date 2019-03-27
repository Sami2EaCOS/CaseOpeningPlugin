package fr.smourad.Test.ressources;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Wool;

public class CaseOpening {
		
	private List<LuckyItem> rewardsList = new ArrayList<LuckyItem>();
	private Integer allChancePoint = 0;
	
	public HashMap<Inventory, InvPermission> invList = new HashMap<Inventory, InvPermission>();
	
	public CaseOpening() {
		setupRewards();
	}
	
	private HashMap<Integer, LuckyItem> createPointTable() {
		HashMap<Integer, LuckyItem> chanceForAllItem = new HashMap<Integer, LuckyItem>();
		allChancePoint = 0;
		
		for (int i=0;i<rewardsList.size();++i) {
			allChancePoint += rewardsList.get(i).getItemPointChance();
			Integer itemLimit = allChancePoint.intValue();
			chanceForAllItem.put(itemLimit, rewardsList.get(i));
		}
		
		return chanceForAllItem;
	}
	
	public void newCaseOpening(Player p) {
		Random rand = new Random();
		HashMap<Integer, LuckyItem> pointTable = createPointTable();
		
		int randomNumber = rand.nextInt(allChancePoint + 1);
		
		for (Integer key : pointTable.keySet()) {
			if (randomNumber <= key.intValue()) {
				ItemStack item = pointTable.get(key).getItem();
				p.getInventory().addItem(item);
				p.sendMessage(ChatColor.AQUA + "Vous avez gagné l'objet: " + item.getType().name() + " x" + item.getAmount());
				break;
			}
		}
		
	}
	
	public void setNewReward(List<LuckyItem> newReward) {
		rewardsList.clear();
		for (LuckyItem item : newReward) {
			if (!(item.getItem().getType().equals(Material.AIR))) {
				rewardsList.add(item);
			}
		}
	}
	
	private void setupRewards() {
		rewardsList.add(new LuckyItem(new ItemStack(Material.GOLDEN_APPLE, 2), Rarity.RARE));
		rewardsList.add(new LuckyItem(new ItemStack(Material.COOKED_BEEF, 64), Rarity.COMMON));
		
		ItemStack enchantedBow = new ItemStack(Material.BOW);
		ItemMeta enchantedBow_meta = enchantedBow.getItemMeta();
		enchantedBow_meta.addEnchant(Enchantment.ARROW_INFINITE, 1, false);
		enchantedBow_meta.addEnchant(Enchantment.ARROW_DAMAGE, 4, false);
		enchantedBow_meta.addEnchant(Enchantment.ARROW_FIRE, 1, false);
		enchantedBow_meta.addEnchant(Enchantment.DURABILITY, 5, false);
		enchantedBow.setItemMeta(enchantedBow_meta);
		
		rewardsList.add(new LuckyItem(enchantedBow, Rarity.LEGENDARY));
	}
	
	public Inventory getActualRewards(String invName, InvPermission perm) {
		Inventory rewardsGUI = Bukkit.createInventory(null, 9*6, invName);
	
		for (int i=0;i<9*3;++i) {
			rewardsGUI.setItem(18*((int)(i/9))+(i%9)+9, noneItem());
		}	
		
		for (int i=0;i<rewardsList.size();++i) {
			rewardsGUI.setItem(18*((int)(i/9))+(i%9), rewardsList.get(i).getItem());
			rewardsGUI.setItem(18*((int)(i/9))+(i%9)+9, woolRarityColor(rewardsList.get(i).getRarity()));
		}		
		
		invList.put(rewardsGUI, perm);
		
		return rewardsGUI;
	}
	
	public ItemStack woolRarityColor(Rarity rarity) {
		Wool wool = new Wool();
		ItemMeta meta = wool.toItemStack().getItemMeta();
		createPointTable();
		List<String> lore = new ArrayList<String>();
		
		DecimalFormat df = new DecimalFormat("#.##");
		
		switch (rarity) {
		case LEGENDARY:
			wool.setColor(DyeColor.ORANGE);
			meta.setDisplayName(ChatColor.GOLD + "Légendaire");
			lore.add(df.format((Rarity.LEGENDARY.getChancePoint()*100.0)/allChancePoint) + "% de chance");
			break;
		case EPIC:
			wool.setColor(DyeColor.PURPLE);
			meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Epic");
			lore.add(df.format((Rarity.EPIC.getChancePoint()*100.0)/allChancePoint) + "% de chance");
			break;
		case RARE:
			wool.setColor(DyeColor.BLUE);
			meta.setDisplayName(ChatColor.BLUE + "Rare");
			lore.add(df.format((Rarity.RARE.getChancePoint()*100.0)/allChancePoint) + "% de chance");
			break;
		case COMMON:
			wool.setColor(DyeColor.WHITE);
			meta.setDisplayName(ChatColor.WHITE + "Commun");
			lore.add(df.format((Rarity.COMMON.getChancePoint()*100.0)/allChancePoint) + "% de chance");
			break;
		default:
			break;
		}
		
		ItemStack woolStack = wool.toItemStack(1);
		meta.setLore(lore);
		woolStack.setItemMeta(meta);
		
		return woolStack;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack noneItem() {
		ItemStack noItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
		ItemMeta meta = noItem.getItemMeta();
		meta.setDisplayName(ChatColor.GRAY + "Vide");
		noItem.setItemMeta(meta);
		return noItem;
	}
}
