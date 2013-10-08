package me.superckl.griefbegone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.superckl.griefbegone.listeners.BlockListeners;
import me.superckl.griefbegone.listeners.PlayerListeners;
import me.superckl.griefbegone.listeners.WorldListeners;

import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefBeGone extends JavaPlugin{

	static GriefBeGone instance;
	public static final ItemStack emptyItemStack = new ItemStack(0,0);
	private final Map<String, Map<String, Map<String, Boolean>>> disables = new HashMap<String, Map<String, Map<String, Boolean>>>();
	private final Map<String, Map<String, List<String>>> miscDisables = new HashMap<String, Map<String, List<String>>>();
	private final Map<String, String> messages = new HashMap<String, String>();
	private BlockListeners blockListeners;
	private PlayerListeners playerListeners;
	private WorldListeners worldListeners;

	@Override
	public void onEnable(){
		GriefBeGone.instance = this;
		this.saveDefaultConfig();
		this.getLogger().info("Registering listeners...");
		boolean events = this.getConfig().getBoolean("Fire Events");
		this.blockListeners = new BlockListeners(events);
		this.getServer().getPluginManager().registerEvents(this.blockListeners, this);
		this.playerListeners = new PlayerListeners(events);
		this.getServer().getPluginManager().registerEvents(this.playerListeners, this);
		this.worldListeners = new WorldListeners(events);
		this.getServer().getPluginManager().registerEvents(this.worldListeners, this);
	}

	public Map<String, Map<String, Boolean>> getActionMap(final ActionHandler action){
		return this.disables.get(action.getPerm());
	}
	public Map<String, List<String>> getMiscActionMap(final MiscActionHandler action){
		return this.miscDisables.get(action.getKey());
	}
	public String getMessage(final ActionHandler action){
		return this.messages.get(action.getPerm());
	}
	public String getMessage(final MiscActionHandler action){
		return this.messages.get(action.getKey());
	}
	public static GriefBeGone getInstance(){
		return GriefBeGone.instance;
	}
	public Map<String, Map<String, Map<String, Boolean>>> getDisables(){
		return this.disables;
	}
	public Map<String, Map<String, List<String>>> getMiscDisables(){
		return this.miscDisables;
	}
	public Map<String, String> getMessages(){
		return this.messages;
	}
}
