package me.superckl.griefbegone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigUtil {

	private static final String[] configDisableEntries = new String[] {"Disable Crafting.", "craft", "Disable Placing.", "place", "Disable Breaking.", "break",
		"Disable Interacting With Item In Hand.", "interacthand", "Disable Interacting With Block.", "interactworld", "Disable Dropping.", "drop", "Disable Dispensing.",
		"dispense", "Disable Picking Up.", "pickup", "Disable Clicking Item In Inventories.", "inventory"};
	private static final String[] configMiscDisableEntries = new String[] {"Disable Creeper Explosions", "creeper", "Disable TNT Explosions", "tnt",
		"Disable Fireball Explosions", "fireball", "Disable Fire Ignition", "fireignite", "Disable Fire Spread", "firespread"};
	private static final String[] messageEntries = new String[] {"Crafting Message", "craft", "Placing Message", "place", "Breaking Message", "break",
		"Interacting With Item In Hand Message", "interacthand", "Interacting With Block Message", "interactworld", "Dropping Message", "drop", "Dispensing Message",
		"dispense", "Picking Up Message", "pickup", "Clicking Item In Inventories Message", "inventory"};

	static void loadDisables(){
		final FileConfiguration c = GriefBeGone.getInstance().getConfig();
		final Map<String, Map<String, Map<String, Boolean>>> d = GriefBeGone.getInstance().getDisables();
		d.clear();
		Map<String, Map<String, Boolean>> di = new HashMap<String, Map<String, Boolean>>();
		for (int i = 0; i<ConfigUtil.configDisableEntries.length;i++) {
			if(i >= ConfigUtil.configDisableEntries.length)
				break;
			di.clear();
			for(final World world:Bukkit.getWorlds()){
				final String key = ConfigUtil.configDisableEntries[i].concat(world.getName());
				if(!c.contains(key))
					continue;
				final List<String> entries = c.getStringList(key);
				if((entries == null) || entries.isEmpty())
					continue;
				final Map<String, Boolean> dii = new HashMap<String, Boolean>();
				for(final String entry:entries){
					entry.trim();
					entry.toLowerCase();
					String[] split = entry.split(":");
					if(split.length > 2){
						GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
						continue;
					}
					switch(split.length){
					case 1:
						if(!entry.matches("[0-9]*")){
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
						dii.put(entry, false);
						break;
					case 2:
						if(!split[0].matches("[0-9]*")){
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
						if(split[1].matches("[0-9]*")){
							dii.put(entry, false);
							break;
						}else if(split[1].matches("true|false")){
							dii.put(split[0], Boolean.valueOf(split[1]));
							break;
						}else{
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
					case 3:
						if(!(split[0].matches("[0-9]*") && split[1].matches("[0-9]*") && split[2].matches("true|false"))){
							GriefBeGone.getInstance().getLogger().warning("Failed to read "+key+"."+entry);
							break;
						}
						dii.put(split[0].concat(":").concat(split[1]), Boolean.valueOf(split[2]));
						break;
					}
				}
				di.put(world.getName(), new HashMap<String, Boolean>(dii));
			}
			d.put(ConfigUtil.configDisableEntries[++i], new HashMap<>(di));
		}
	}

	static void loadMiscDisables(){
		final FileConfiguration c = GriefBeGone.getInstance().getConfig();
		final Map<String, Map<String, List<String>>> md = GriefBeGone.getInstance().getMiscDisables();
		md.clear();
		Map<String, List<String>> mdi = new HashMap<String, List<String>>();
		for(int i = 0; i < ConfigUtil.configMiscDisableEntries.length; i++){
			mdi = new HashMap<String, List<String>>();
			final List<String> list = new ArrayList<String>(c.getStringList(ConfigUtil.configMiscDisableEntries[i++]));
			if((list == null) || list.isEmpty())
				continue;
			for(final World world:Bukkit.getWorlds())
				if(list.contains(world.getName())){
					mdi.put(world.getName(), new ArrayList<String>(0));
					list.remove(world.getName());
				}
			md.put(ConfigUtil.configMiscDisableEntries[i], mdi);
			for(final String string:list)
				GriefBeGone.getInstance().getLogger().warning(string+" was listed for "+ConfigUtil.configMiscDisableEntries[i-1]+", but no corresponding world was found.");
		}
		mdi = new HashMap<String, List<String>>();
		for(final World world:Bukkit.getWorlds()){
			final List<String> items = c.getStringList("Disable Item Spawning."+world.getName());
			if((items != null) && !items.isEmpty())
				mdi.put(world.getName(), items);
		}
		md.put("itemspawn", mdi);
		mdi = new HashMap<String, List<String>>();
		for(final World world:Bukkit.getWorlds()){
			final List<String> entities = c.getStringList("Disable Entity Spawning."+world.getName());
			if((entities != null) && !entities.isEmpty())
				mdi.put(world.getName(), entities);
		}
		md.put("entityspawn", mdi);
		mdi = new HashMap<String, List<String>>();
		for(final World world:Bukkit.getWorlds()){
			final List<String> items = c.getStringList("Disable Brewing."+world.getName());
			if((items != null) && !items.isEmpty())
				mdi.put(world.getName(), items);
		}
		md.put("brew", mdi);
	}

	static void loadMessages(){
		final FileConfiguration c = GriefBeGone.getInstance().getConfig();
		final Map<String, String> m = GriefBeGone.getInstance().getMessages();
		m.clear();
		for(int i = 0; i < ConfigUtil.messageEntries.length; i++)
			m.put(ConfigUtil.messageEntries[i+1], c.getString(ConfigUtil.messageEntries[i++]));
	}

	public static int count(final String phrase, final char character){
		int count = 0;
		for(int i = 0; i<phrase.length(); i++)
			if(phrase.charAt(i) == character)
				count++;
		return count;
	}
}
