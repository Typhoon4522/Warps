package me.warps;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.parser.ParseException;

import me.warps.core.CustomConfig;

public class Warps extends JavaPlugin{
	public void onEnable(){
		new CustomConfig().defaultCreateConfig();
	}
	
	public void onDisable(){
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		Player player = (Player)sender;
		if(alias.equalsIgnoreCase("warps")){
			if(args[0].equalsIgnoreCase("set")){
				if(!(args[1].isEmpty())){
					CustomConfig config = new CustomConfig();
					
					config.addPlayerInCofing(player, player.getLocation(), (String)args[1]);
					player.sendMessage("succsess your command! :)");
					return true;
				}
			}
			
			if(args[0].equalsIgnoreCase("list")){
			
			}
			
			if(args[0].equalsIgnoreCase("warp")){
				if(!(args[1].isEmpty())){
					Location getLoc = null;
					CustomConfig config = new CustomConfig();
					
					try {
						getLoc = config.getConfigData(player, args[0]);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					
					player.teleport(getLoc);
					return true;
				}
			}
		}
		return true;
	}
	
	public void listWarps(Player player){
		
	}
}
