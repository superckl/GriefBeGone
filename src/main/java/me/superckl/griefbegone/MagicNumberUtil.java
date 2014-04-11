package me.superckl.griefbegone;

import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class MagicNumberUtil {

	@SuppressWarnings("deprecation")
	public static int getTypeId(ItemStack i){
		return i.getTypeId();
	}
	
	@SuppressWarnings("deprecation")
	public static byte getData(MaterialData m){
		return m.getData();
	}
	
}
