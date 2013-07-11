package me.superckl.griefbegone;

import java.util.List;

import org.bukkit.World;
import org.bukkit.entity.Player;

public enum MiscActionHandler {

	CREEPER("creeper"),
	TNT("tnt"),//TODO
	BREW("brew"),
	ITEM_SPAWN("itemspawn"),
	ENTITY_SPAWN("entityspawn"),
	FIREBALL("fireball"),
	FIRE_IGNITE("fireignite"),
	FIRE_SPREAD("firespread");
	
	private String key;
	
	private MiscActionHandler(String key){
		this.key = key;
	}
	
	public List<String> getDisabled(String worldName){
		return GriefBeGone.getInstance().getMiscActionMap(this).get(worldName);
	}
	
	public String getKey(){
		return this.key;
	}
	public String getMessage(){
		return GriefBeGone.getInstance().getMessage(this);
	}
	public static boolean shouldBlockFirePlace(Player player, World world){
		return !(player.hasPermission("disabler.bypass.all."+world.getName()) || player.hasPermission("player.bypass.fireplace."+world.getName()));
	}
}
