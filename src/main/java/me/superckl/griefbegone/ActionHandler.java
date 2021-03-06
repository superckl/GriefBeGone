package me.superckl.griefbegone;

import java.util.Map;

import lombok.Getter;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

public enum ActionHandler {

	CRAFT("craft"),
	PLACE("place"),
	BREAK("break"),
	INTERACT_HAND("interacthand"),
	INTERACT_WORLD("interactworld"),
	DROP("drop"),
	DISPENSE("dispense"),
	PICK_UP("pickup"),
	INVENTORY("inventory");

	@Getter
	private final String perm;

	private ActionHandler(final String perm){
		this.perm = perm;
	}

	public boolean[] shouldBlockAndDelete(final HumanEntity player, final ItemStack i){
		final boolean[] boolArray = new boolean[] {false, false};
		if (i == null)
			return boolArray;
		if(i.getType() == Material.AIR)
			return boolArray;
		if(player == null) return boolArray;
		final String worldname = player.getWorld().getName();
		final String id = Integer.toString(MagicNumberUtil.getTypeId(i));
		final String data = new StringBuilder(MagicNumberUtil.getTypeId(i)).append(":")
				.append(MagicNumberUtil.getData(i.getData())).toString();
		if(this.hasBypassPerm(player, id, data, worldname))
			return boolArray;
		Map<String, Boolean> disables = GriefBeGone.getInstance().getActionMap(this).get(worldname);
		boolean containsID = disables == null ? false:disables.containsKey(id) || disables.containsKey("*"); 
		boolean containsData = disables == null ? false:disables.containsKey(data) || disables.containsKey("*");
		if(containsID == false && containsData == false){
			disables = GriefBeGone.getInstance().getActionMap(this).get("Global");
			containsID = disables == null ? false:disables.containsKey(id) || disables.containsKey("*"); 
			containsData = disables == null ? false:disables.containsKey(data) || disables.containsKey("*");
		}
		if(!containsID && !containsData)
			return boolArray;
		boolArray[0] = true;
		if(this.hasDeleteBypassPerm(player, id, data, worldname))
			return boolArray;
		if(containsID)
			boolArray[1] = disables.get(id);
		else
			boolArray[1] = disables.get(data);
		return boolArray;
	}
	private boolean hasBypassPerm(final HumanEntity player, final String id, final String data, final String worldname){
		if (player.hasPermission("disabler.bypass.all." + worldname + "." + id)
				|| player.hasPermission("disabler.bypass.all." + worldname
						+ "." + data) || player.hasPermission("disabler.bypass.all." + worldname + ".*"))
			return true;
		String perm = "disabler.bypass.".concat(this.perm).concat(".");
		perm = perm.concat(worldname).concat(".");
		final String permid = perm.concat(id);
		final String permdata = perm.concat(data);
		final String permevery = perm.concat("*");
		if (player.hasPermission(permid) || player.hasPermission(permdata) || player.hasPermission(permevery))
			return true;
		return false;
	}
	private boolean hasDeleteBypassPerm(final HumanEntity player, final String id, final String data, final String worldname){
		final String permbase = "disabler.bypass.delete."+worldname+".";
		final String permid = permbase.concat(id);
		final String permdata = permbase.concat(data);
		final String permevery = permbase.concat("*");
		if (player.hasPermission(permid) || player.hasPermission(permdata) || player.hasPermission(permevery))
			return true;
		return false;
	}
	public static boolean[] shouldBlockAndDeleteDispense(final ItemStack i, final String worldname){
		final boolean[] boolArray = new boolean[] {false, false};
		if (i == null)
			return boolArray;
		if(i.getType() == Material.AIR)
			return boolArray;
		final String id = Integer.toString(MagicNumberUtil.getTypeId(i));
		final String data = new StringBuilder(MagicNumberUtil.getTypeId(i)).append(":")
				.append(MagicNumberUtil.getData(i.getData())).toString();
		final Map<String, Boolean> disables = GriefBeGone.getInstance().getActionMap(ActionHandler.DISPENSE).get(worldname);
		if(disables == null)
			return boolArray;
		final boolean containsID = disables.containsKey(id) || disables.containsKey("*"); final boolean containsData = disables.containsKey(data) || disables.containsKey("*");
		if(!containsID || !containsData)
			return boolArray;
		boolArray[0] = true;
		if(containsID)
			boolArray[1] = disables.get(id);
		else
			boolArray[1] = disables.get(data);
		return boolArray;
	}

	public String getMessage(){
		return GriefBeGone.getInstance().getMessage(this);
	}
}
