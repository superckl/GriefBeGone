package me.superckl.griefbegone;

import java.util.List;

import lombok.Getter;

import org.bukkit.World;
import org.bukkit.entity.Player;

public enum MiscActionHandler {

	CREEPER("creeper"),
	TNT("tnt"),
	BREW("brew"),
	ITEM_SPAWN("itemspawn"),
	ENTITY_SPAWN("entityspawn"),
	FIREBALL("fireball"),
	FIRE_IGNITE("fireignite"),
	FIRE_SPREAD("firespread");

	@Getter
	private final String key;

	private MiscActionHandler(final String key){
		this.key = key;
	}

	public List<String> getDisabled(final String worldName){
		return GriefBeGone.getInstance().getMiscActionMap(this).get(worldName);
	}

	public String getMessage(){
		return GriefBeGone.getInstance().getMessage(this);
	}
	public static boolean shouldBlockFirePlace(final Player player, final World world){
		return !(player.hasPermission("disabler.bypass.all."+world.getName()) || player.hasPermission("player.bypass.fireplace."+world.getName()));
	}
}
