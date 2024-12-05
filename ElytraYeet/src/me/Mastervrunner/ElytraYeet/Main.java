package me.Mastervrunner.ElytraYeet;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import me.Mastervrunner.ElytraYeet.Main;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	public static Main instance;

	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		instance = this;
	}
	
	// /hello <name> <amount>
	// <-- Hey how are you!

	ArrayList<Player> Players = new ArrayList<Player>();
	ArrayList<Double> Speeds = new ArrayList<Double>();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(player.getInventory().getItemInMainHand().getType() == Material.GLISTERING_MELON_SLICE) {

			double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
	        double yaw = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;
	        double x = Math.sin(pitch) * Math.cos(yaw);
	        double y = Math.sin(pitch) * Math.sin(yaw);
	        double z = Math.cos(pitch);
	        Vector vector = new Vector(x, z, y);
	        
	        boolean unsetspeed = true;
	        
	        Vector SetSpeed;
	        
	        double pitch2 = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
	        double yaw2 = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;
	        double x2 = Math.sin(pitch2) * Math.cos(yaw2);
	        double y2 = Math.sin(pitch2) * Math.sin(yaw2);
	        double z2 = Math.cos(pitch2);
	        Vector vector2 = new Vector(x2, z2, y2);
	        
	        SetSpeed = vector2.multiply(2);
	    
	        for(int i = 0; i < Players.size(); i++) {
	        	
	        	if(Players.get(i).getDisplayName() == player.getDisplayName()) {
			        
			        player.setVelocity(vector2.multiply(Speeds.get(i)));
			    
	        	}
	        	
	        }
		}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		//hello
		if (label.equalsIgnoreCase("yeet")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				
				double pitch = ((player.getLocation().getPitch() + 90) * Math.PI) / 180;
		        double yaw = ((player.getLocation().getYaw() + 90) * Math.PI) / 180;
		        double x = Math.sin(pitch) * Math.cos(yaw);
		        double y = Math.sin(pitch) * Math.sin(yaw);
		        double z = Math.cos(pitch);
		        Vector vector = new Vector(x, z, y);
		        
		        if(args.length >= 1) {
		        	player.setVelocity(vector.multiply(Double.valueOf(args[0])));
		        } else {
		        	player.setVelocity(vector.multiply(Double.valueOf(2)));
		        }
		        
				
				return true;
			} else {
				sender.sendMessage(ChatColor.RED + "You have to be a player to run this, lol");
				return true;
			}
			
		} else if (label.equalsIgnoreCase("yeetspeed") && args.length >= 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				int index;
				if(Players.contains(player)) {
					index = Players.indexOf(player);
					Players.remove(index);//Forgot to add ;
					Speeds.remove(index);
					
				}

				Players.add(player);
				Speeds.add(Double.valueOf(args[0]));
				player.sendMessage("Added");
			}
		}
		
		
		return false;
		
	}
	
}
