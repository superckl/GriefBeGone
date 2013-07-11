package me.superckl.griefbegone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.superckl.griefbegone.listeners.BlockListeners;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefBeGone extends JavaPlugin{

	static GriefBeGone instance;
	public static final ItemStack emptyItemStack = new ItemStack(0,0);
	private Map<String, Map<String, Map<String, Boolean>>> disables = new HashMap<String, Map<String, Map<String, Boolean>>>();
	private Map<String, Map<String, List<String>>> miscDisables = new HashMap<String, Map<String, List<String>>>();
	private Map<String, String> messages = new HashMap<String, String>();
	
	@Override
	public void onEnable(){
		instance = this;
		this.saveDefaultConfig();
		this.getServer().getPluginManager().registerEvents(new BlockListeners(false), this);
	}

	public Map<String, Map<String, Boolean>> getActionMap(ActionHandler action){
		return this.disables.get(action.getPerm());
	}
	public Map<String, List<String>> getMiscActionMap(MiscActionHandler action){
		return this.miscDisables.get(action.getKey());//TODO
	}
	public String getMessage(ActionHandler action){
		return this.messages.get(action.getPerm());
	}
	public String getMessage(MiscActionHandler action){
		return this.messages.get(action.getKey());
	}
	public static GriefBeGone getInstance(){
		return instance;
	}
	//TODO Block messages
}
