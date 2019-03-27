package fr.smourad.Test.ressources;

import org.bukkit.inventory.ItemStack;

public class LuckyItem {

	private ItemStack item;
	private Rarity rarity;
	
	public LuckyItem(ItemStack item, Rarity rarity) {
		this.item = item;
		this.rarity = rarity;
	}
	
	public int getItemPointChance() {
		return rarity.getChancePoint(); 
	}
	
	public Rarity getRarity() {
		return rarity;
	}
	
	public ItemStack getItem() {
		return item;
	}
}
