package fr.smourad.MyPlugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import fr.smourad.Test.commands.CreateNewBox;
import fr.smourad.Test.commands.MakeNewRewards;
import fr.smourad.Test.listeners.ClickInventory;
import fr.smourad.Test.listeners.PlayerInteract;
import fr.smourad.Test.ressources.CaseOpening;

public class MyPlugin extends JavaPlugin {
	
	public List<Block> chestList = new ArrayList<Block>();
	public CaseOpening caseOpening = new CaseOpening();
	
	@Override
	public void onEnable() {
		setupCommands();
		setupListeners();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void setupCommands() {
		this.getCommand("newBox").setExecutor(new CreateNewBox(this));
		this.getCommand("newRewards").setExecutor(new MakeNewRewards(this));
	}
	
	private void setupListeners() {
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(this), this);
		Bukkit.getPluginManager().registerEvents(new ClickInventory(this), this);
	}
}
