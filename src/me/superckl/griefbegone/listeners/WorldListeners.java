package me.superckl.griefbegone.listeners;

import java.util.List;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.GriefBeGone;
import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.world.BlockBrewEvent;
import me.superckl.griefbegone.events.world.BlockCreatureSpawnEvent;
import me.superckl.griefbegone.events.world.BlockCreeperExplodeEvent;
import me.superckl.griefbegone.events.world.BlockFireballExplodeEvent;
import me.superckl.griefbegone.events.world.BlockItemDispenseEvent;
import me.superckl.griefbegone.events.world.BlockItemSpawnEvent;
import me.superckl.griefbegone.events.world.BlockTnTExplodeEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.BrewEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldListeners implements Listener{

	private boolean events;

	public WorldListeners(final boolean events){
		this.events = events;
	}
	
	public void setEvents(boolean events){
		this.events = events;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemSpawn(final ItemSpawnEvent e){
		final List<String> disabled = MiscActionHandler.ITEM_SPAWN.getDisabled(e.getEntity().getWorld().getName());
		final String id = Integer.toString(e.getEntity().getItemStack().getTypeId());
		final String data = new StringBuilder(id).append(":").append(e.getEntity().getItemStack().getData().getData()).toString();
		final boolean block = disabled.contains(id) || disabled.contains(data) || disabled.contains("*");
		if(block){
			if(this.events){
				final BlockItemSpawnEvent event = new BlockItemSpawnEvent(e.getEntity());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
			}
			e.setCancelled(true);
			final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" An illegal item spawned: ").append(e.getEntity().getType().toString())
					.append(" at x:").append(e.getLocation().getBlockX()).append(" y:").append(e.getLocation().getBlockY()).append(" z:").append(e.getLocation().getBlockZ())
					.toString();
			GriefBeGone.getInstance().getLogger().info(message);
			for(final Player player:Bukkit.getOnlinePlayers())
				if(player.hasPermission("disabler.broadcast."+MiscActionHandler.ITEM_SPAWN.getKey()))
					player.sendMessage(message);
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntitySpawn(final CreatureSpawnEvent e){
		String name = e.getEntity().getClass().getSimpleName();
		if(name.startsWith("Craft")) name = name.substring(5);
		final boolean block = MiscActionHandler.ENTITY_SPAWN.getDisabled(e.getLocation().getWorld().getName()).contains(name);
		if(block){
			if(this.events){
				final BlockCreatureSpawnEvent event = new BlockCreatureSpawnEvent(e.getEntity());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
			}
			e.setCancelled(true);
			final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" An illegal creature spawned: ").append(e.getEntity().getType().toString())
					.append(" at x:").append(e.getLocation().getBlockX()).append(" y:").append(e.getLocation().getBlockY()).append(" z:").append(e.getLocation().getBlockZ())
					.toString();
			GriefBeGone.getInstance().getLogger().info(message);
			for(final Player player:Bukkit.getOnlinePlayers())
				if(player.hasPermission("disabler.broadcast."+MiscActionHandler.ENTITY_SPAWN.getKey()))
					player.sendMessage(message);
		}
	}
	//TODO probably won't work
	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemDispense(final BlockDispenseEvent e){
		final boolean[] boolArray = ActionHandler.shouldBlockAndDeleteDispense(e.getItem(), e.getBlock().getWorld().getName());
		if(boolArray[0]){
			if(this.events){
				final BlockItemDispenseEvent event = new BlockItemDispenseEvent(boolArray[1], e.getBlock(), e.getItem());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
				boolArray[1] = event.willDelete();
			}
			e.setCancelled(true);
			if(boolArray[1] && e.getBlock() instanceof InventoryHolder)
				new BukkitRunnable(){
				@Override
				public void run() {
					final InventoryHolder block = (InventoryHolder) e.getBlock();
					block.getInventory().remove(e.getItem().getTypeId());
					final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" An illegal item was dispensed: ")
							.append(e.getItem().getType().toString()).append(" at x:").append(e.getBlock().getLocation().getBlockX()).append(" y:")
							.append(e.getBlock().getLocation().getBlockY()).append(" z:").append(e.getBlock().getLocation().getBlockZ()).toString();
					GriefBeGone.getInstance().getLogger().info(message);
					for(final Player player:Bukkit.getOnlinePlayers())
						if(player.hasPermission("disabler.broadcast."+ActionHandler.DISPENSE.getPerm()))
							player.sendMessage(message);
				}
			}.runTaskLater(GriefBeGone.getInstance(), 1L);

		}
	}

	//TODO Probably doesn't work
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBrew(final BrewEvent e){
		final ItemStack[] preInv = e.getContents().getContents();
		final BrewingStand stand = e.getContents().getHolder();
		final String worldName = e.getBlock().getWorld().getName();
		final List<String> disabled = MiscActionHandler.BREW.getDisabled(worldName);
		new BukkitRunnable(){
			@Override
			public void run() {
				if(e.getBlock().getTypeId() != stand.getTypeId())
					return;
				for(final ItemStack item:stand.getInventory().getContents()){
					final String id = Integer.toString(item.getTypeId());
					final String data = new StringBuilder(id).append(":").append(item.getData().getData()).toString();
					if(disabled.contains(id) || disabled.contains(data) || disabled.contains("*")){
						if(WorldListeners.this.events){
							final BlockBrewEvent event = new BlockBrewEvent(stand.getInventory());
							Bukkit.getPluginManager().callEvent(event);
							if(!event.willBlock())
								return;
						}
						stand.getInventory().setContents(preInv);
						final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" An illegal item was brewed: ").append(item.getType()
								.toString()).append(" at x:").append(e.getBlock().getLocation().getBlockX()).append(" y:").append(e.getBlock().getLocation().getBlockY())
								.append(" z:").append(e.getBlock().getLocation().getBlockZ()).toString();
						GriefBeGone.getInstance().getLogger().info(message);
						for(final Player player:Bukkit.getOnlinePlayers())
							if(player.hasPermission("disabler.broadcast."+MiscActionHandler.BREW.getKey()))
								player.sendMessage(message);
						return;
					}
				}
			}
		}.runTaskLater(GriefBeGone.getInstance(), 1L);
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onEntityExplode(final EntityExplodeEvent e){
		switch(e.getEntityType()){
		case CREEPER:
			if(MiscActionHandler.CREEPER.getDisabled(e.getLocation().getWorld().getName()) != null){
				if(this.events){
					final BlockCreeperExplodeEvent event = new BlockCreeperExplodeEvent(e.blockList(), (Creeper) e.getEntity());
					Bukkit.getPluginManager().callEvent(event);
					if(!event.willBlock())
						return;
				}
				e.blockList().clear();
				final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" A creeper exploded at x:").append(" at x:")
						.append(e.getLocation().getBlockX()).append(" y:").append(e.getLocation().getBlockY()).append(" z:").append(e.getLocation().getBlockZ())
						.toString();
				GriefBeGone.getInstance().getLogger().info(message);
				for(final Player player:Bukkit.getOnlinePlayers())
					if(player.hasPermission("disabler.broadcast."+MiscActionHandler.CREEPER.getKey()))
						player.sendMessage(message);
			}
			break;
		case FIREBALL:
			if(MiscActionHandler.FIREBALL.getDisabled(e.getLocation().getWorld().getName()) != null){
				if(this.events){
					final BlockFireballExplodeEvent event = new BlockFireballExplodeEvent(e.blockList(), (Fireball) e.getEntity());
					Bukkit.getPluginManager().callEvent(event);
					if(!event.willBlock())
						return;
				}
				e.blockList().clear();
				final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" A fireball exploded at x:").append(" at x:")
						.append(e.getLocation().getBlockX()).append(" y:").append(e.getLocation().getBlockY()).append(" z:").append(e.getLocation().getBlockZ())
						.toString();
				GriefBeGone.getInstance().getLogger().info(message);
				for(final Player player:Bukkit.getOnlinePlayers())
					if(player.hasPermission("disabler.broadcast."+MiscActionHandler.FIREBALL.getKey()))
						player.sendMessage(message);
			}
			break;
		case PRIMED_TNT:
			if(MiscActionHandler.TNT.getDisabled(e.getLocation().getWorld().getName()) != null){
				if(this.events){
					final BlockTnTExplodeEvent event = new BlockTnTExplodeEvent(e.blockList(), (TNTPrimed) e.getEntity());
					Bukkit.getPluginManager().callEvent(event);
					if(!event.willBlock())
						return;
				}
				e.blockList().clear();
				final String message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone]").append(" TnT exploded at x:").append(" at x:")
						.append(e.getLocation().getBlockX()).append(" y:").append(e.getLocation().getBlockY()).append(" z:").append(e.getLocation().getBlockZ())
						.toString();
				GriefBeGone.getInstance().getLogger().info(message);
				for(final Player player:Bukkit.getOnlinePlayers())
					if(player.hasPermission("disabler.broadcast."+MiscActionHandler.TNT.getKey()))
						player.sendMessage(message);
			}
			break;
		default:
		}
	}
}
