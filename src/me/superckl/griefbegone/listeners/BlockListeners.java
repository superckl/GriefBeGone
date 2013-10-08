package me.superckl.griefbegone.listeners;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.GriefBeGone;
import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.blocks.BlockBlockBreakEvent;
import me.superckl.griefbegone.events.blocks.BlockBlockPlaceEvent;
import me.superckl.griefbegone.events.blocks.BlockFireIgniteEvent;
import me.superckl.griefbegone.events.blocks.BlockFireSpreadEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockIgniteEvent.IgniteCause;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockListeners implements Listener{

	private boolean events;

	public BlockListeners(final boolean events){
		this.events = events;
	}

	public void setEvents(boolean events){
		this.events = events;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockPlace(final BlockPlaceEvent e){
		final boolean[] boolArray = ActionHandler.PLACE.shouldBlockAndDelete(e.getPlayer(), e.getItemInHand());
		if(boolArray[0]){
			if(this.events){
				final BlockBlockPlaceEvent event = new BlockBlockPlaceEvent(boolArray[1], e.getBlockPlaced(), e.getBlockAgainst(), e.getPlayer());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
				boolArray[1] = event.willDelete();
			}
			e.setCancelled(true);
			String message = ActionHandler.PLACE.getMessage();
			if(message != null)
				e.getPlayer().sendMessage(message);
			message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" tried to place ").append(e.getBlockPlaced().getType().toString()).toString();
			GriefBeGone.getInstance().getLogger().info(message);
			for(final Player player:Bukkit.getOnlinePlayers())
				if(player.hasPermission("disabler.broadcast."+ActionHandler.PLACE.getPerm()))
					player.sendMessage(message);
			if(boolArray[1])
				e.getPlayer().setItemInHand(GriefBeGone.emptyItemStack);

		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockBreak(final BlockBreakEvent e){
		final boolean[] boolArray = ActionHandler.BREAK.shouldBlockAndDelete(e.getPlayer(), new ItemStack(e.getBlock().getTypeId()));
		if(boolArray[0]){
			if(this.events){
				final BlockBlockBreakEvent event = new BlockBlockBreakEvent(boolArray[1], e.getBlock(), e.getPlayer(), e.getExpToDrop());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
				boolArray[1] = event.willDelete();
			}
			e.setCancelled(true);
			String message = ActionHandler.BREAK.getMessage();
			if(message != null)
				e.getPlayer().sendMessage(message);
			message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" tried to break ").append(e.getBlock().getType().toString()).toString();
			GriefBeGone.getInstance().getLogger().info(message);
			for(final Player player:Bukkit.getOnlinePlayers())
				if(player.hasPermission("disabler.broadcast."+ActionHandler.BREAK.getPerm()))
					player.sendMessage(message);
			if(boolArray[1])
				e.getBlock().setTypeId(0);

		}
	}
	@EventHandler(priority = EventPriority.LOWEST)
	public void onBlockIgnite(final BlockIgniteEvent e){
		if(e.getCause() == IgniteCause.SPREAD){
			if(MiscActionHandler.FIRE_SPREAD.getDisabled(e.getBlock().getWorld().getName()) != null)
				if(this.events){
					final BlockFireSpreadEvent event = new BlockFireSpreadEvent(e.getBlock());
					Bukkit.getPluginManager().callEvent(event);
					if(event.willBlock())
						e.setCancelled(true);
				}else
					e.setCancelled(true);
		}else{
			boolean block = MiscActionHandler.FIRE_IGNITE.getDisabled(e.getBlock().getWorld().getName()) != null;
			if(e.getPlayer() != null && block)
				block = MiscActionHandler.shouldBlockFirePlace(e.getPlayer(), e.getPlayer().getWorld());
			if(block){
				if(this.events){
					final BlockFireIgniteEvent event = new BlockFireIgniteEvent(e.getBlock(), e.getIgnitingEntity(), e.getIgnitingBlock());
					Bukkit.getPluginManager().callEvent(event);
					if(!event.willBlock())
						return;
				}
				e.setCancelled(true);
				if(e.getPlayer() == null) return;
				String message = MiscActionHandler.FIRE_IGNITE.getMessage();
				if(message != null)
					e.getPlayer().sendMessage(message);
				message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" tried to light fire to ").append(e.getBlock().getType().toString()).toString();
				GriefBeGone.getInstance().getLogger().info(message);
				for(final Player player:Bukkit.getOnlinePlayers())
					if(player.hasPermission("disabler.broadcast."+MiscActionHandler.FIRE_IGNITE.getKey()))
						player.sendMessage(message);
			}
		}
	}

}
