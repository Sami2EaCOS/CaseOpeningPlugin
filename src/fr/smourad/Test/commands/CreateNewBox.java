package fr.smourad.Test.commands;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.smourad.MyPlugin.MyPlugin;

public class CreateNewBox implements CommandExecutor {

	private MyPlugin plugin;
	
	public CreateNewBox(MyPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			System.out.println("Pas avec la console sorry.");
			return true;
		}
		
		Player p = (Player) sender;
		Block targetBlock = p.getTargetBlock((Set<Material>) null, 5); 
		
		if (targetBlock.getType() == Material.CHEST) {
			plugin.chestList.add(targetBlock);
			p.sendMessage(ChatColor.GREEN + "Ce coffre est prêt pour sa mission.");
		} else {
			p.sendMessage(ChatColor.YELLOW + "Veuillez cibler un coffre.");
		}
		
		return true;
	}

}
