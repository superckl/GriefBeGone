package me.superckl.griefbegone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtil {

	private static final String[] configDisableEntries = new String[] {"Disable Crafting.", "craft", "Disable Placing", "place", "Disable Breaking", "break",
		"Disable Interacting With Item In Hand", "interacthand", "Disable Interacting With Block", "interactworld", "Disable Dropping", "drop", "Disable Dispensing",
		"dispense", "Disable Picking Up", "pickup", "Disable Clicking Item In Inventories", "inventory"};
	private static final String[] configMiscDisableEntries = new String[] {"Disable Creeper Explosions", "creeper"};//TODO finish
	static void loadDisables(){
		FileConfiguration c = GriefBeGone.getInstance().getConfig();
		Map<String, Map<String, Map<String, Boolean>>> d = GriefBeGone.getInstance().getDisables();
		Map<String, Map<String, Boolean>> di = new HashMap<String, Map<String, Boolean>>();
		for(int i = 0; i < configDisableEntries.length; i++){
			for(World world:Bukkit.getWorlds()){
				String key = configDisableEntries[i].concat(world.getName());
				if(!c.contains(key))
					continue;
				List<String> entries = c.getStringList(key);
				if(entries == null || entries.isEmpty())
					continue;
				Map<String, Boolean> dii = new HashMap<String, Boolean>();
				for(String entry:entries){
					int count = count(entry, ':');
					if(count > 2){
						GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
						continue;
					}
					switch(count){
					case 0:
						if(!entry.matches("[0-9]*")){
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
						dii.put(entry, false);
						break;
					case 1:
						if(!entry.matches("[0-9]*:[0-9]*")){
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
						dii.put(entry, false);
						break;
					case 2:
						if(!entry.matches("[0-9]*:[0-9]*:(true|false)")){
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
						String[] split = entry.split(":");
						dii.put(split[0].concat(split[1]), Boolean.valueOf(split[2]));
						break;
					}
				}
				di.put(world.getName(), dii);
			}
			d.put(configDisableEntries[++i], di);
		}
	}
	
	static void loadMiscDisables(){
		FileConfiguration c = GriefBeGone.getInstance().getConfig();
		Map<String, Map<String, List<String>>> md = GriefBeGone.getInstance().getMiscDisables();
	}
	
	static void loadMessages(){
		FileConfiguration c = GriefBeGone.getInstance().getConfig();
		Map<String, String> m = GriefBeGone.getInstance().getMessages();
	}
	
	public static int count(String phrase, char character){
		int count = 0;
		for(int i = 0; i<phrase.length(); i++)
			if(phrase.charAt(i) == character)
				count++;
		return count;
	}
}
