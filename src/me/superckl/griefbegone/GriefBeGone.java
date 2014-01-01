package me.superckl.griefbegone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.superckl.griefbegone.commands.AbstractCommand;
import me.superckl.griefbegone.commands.CommandReload;
import me.superckl.griefbegone.listeners.BlockListeners;
import me.superckl.griefbegone.listeners.PlayerListeners;
import me.superckl.griefbegone.listeners.WorldListeners;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class GriefBeGone extends JavaPlugin{

	static GriefBeGone instance;
	public static final ItemStack emptyItemStack = new ItemStack(Material.AIR, 0);
	private final Map<String, Map<String, Map<String, Boolean>>> disables = new HashMap<String, Map<String, Map<String, Boolean>>>();
	private final Map<String, Map<String, List<String>>> miscDisables = new HashMap<String, Map<String, List<String>>>();
	private final Map<String, String> messages = new HashMap<String, String>();
	private final Map<String, AbstractCommand> commands = new HashMap<String, AbstractCommand>();
	private BlockListeners blockListeners;
	private PlayerListeners playerListeners;
	private WorldListeners worldListeners;

	@Override
	public void onEnable(){
		GriefBeGone.instance = this;
		this.getLogger().warning("You are running a beta version of GriefBeGone! Be wary of bugs!");
		this.getLogger().info("Reading configuration...");
		this.saveDefaultConfig();
		ConfigUtil.loadDisables();
		ConfigUtil.loadMiscDisables();
		ConfigUtil.loadMessages();
		this.getLogger().info("Registering listeners...");
		final boolean events = this.getConfig().getBoolean("Fire Events");
		this.blockListeners = new BlockListeners(events);
		this.getServer().getPluginManager().registerEvents(this.blockListeners, this);
		this.playerListeners = new PlayerListeners(events);
		this.getServer().getPluginManager().registerEvents(this.playerListeners, this);
		this.worldListeners = new WorldListeners(events);
		this.getServer().getPluginManager().registerEvents(this.worldListeners, this);
		this.getLogger().info("Instantiating commands...");
		this.fillCommandMap();
		this.getLogger().info("GriefBeGone is protecting you...");
	}

	private void fillCommandMap(){
		this.commands.clear();
		this.commands.put("reload", new CommandReload());
	}

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String name, final String[] args){
		if(args.length == 0)
			return false;
		final AbstractCommand aCommand = this.commands.get(args[0].toLowerCase());
		return aCommand == null ? false:aCommand.execute(sender, Arrays.copyOfRange(args, 1, args.length));
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
	public void reload(){
		this.reloadConfig();
		ConfigUtil.loadDisables();
		ConfigUtil.loadMiscDisables();
		ConfigUtil.loadMessages();
		final boolean events = this.getConfig().getBoolean("Fire Events");
		this.blockListeners.setEvents(events);
		this.playerListeners.setEvents(events);
		this.worldListeners.setEvents(events);
	}
}
