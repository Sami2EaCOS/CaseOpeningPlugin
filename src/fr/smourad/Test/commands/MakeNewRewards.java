package fr.smourad.Test.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.smourad.MyPlugin.MyPlugin;
import fr.smourad.Test.ressources.InvPermission;
import net.md_5.bungee.api.ChatColor;

public class MakeNewRewards implements CommandExecutor {

	private MyPlugin plugin;
	
	public MakeNewRewards(MyPlugin plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) {
			System.out.println("Pas avec la console sorry.");
			return true;
		}
		
		Player p = (Player) sender;
		p.openInventory(plugin.caseOpening.getActualRewards(ChatColor.BLUE + "Tableaux des récompenses", InvPermission.WRITE));
		
		return true;
	}

}
