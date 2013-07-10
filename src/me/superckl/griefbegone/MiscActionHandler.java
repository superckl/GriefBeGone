package me.superckl.griefbegone;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

public enum MiscActionHandler {

	CREEPER("creeper"),
	TNT("tnt"),
	FIREBALL("fireball"),
	FIRE_IGNITE("fireignite"),
	FIRE_SPREAD("firespread");
	
	private String key;
	
	private MiscActionHandler(String key){
		this.key = key;
	}
	
	public List<String> getWorlds(){
		return GriefBeGone.getInstance().getWorldList(this);
	}
	
	public String getKey(){
		return this.key;
	}
	public static boolean shouldBlockFirePlace(Player player, World world){
		return !(player.hasPermission("disabler.bypass.all."+world.getName()) || player.hasPermission("player.bypass.fireplace."+world.getName()));
	}
}
