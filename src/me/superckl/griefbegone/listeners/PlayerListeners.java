package me.superckl.griefbegone.listeners;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.GriefBeGone;
import me.superckl.griefbegone.events.player.BlockItemDropEvent;
import me.superckl.griefbegone.events.player.BlockItemPickupEvent;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerListeners implements Listener{

	private boolean events;
	
	public PlayerListeners(boolean events){
		this.events = events;
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerDrop(PlayerDropItemEvent e){
		boolean[] boolArray = ActionHandler.DROP.shouldBlockAndDelete(e.getPlayer(), e.getItemDrop().getItemStack());
		if(boolArray[0]){
			if(this.events){
				BlockItemDropEvent event = new BlockItemDropEvent(boolArray[1], e.getPlayer(), e.getItemDrop());
				Bukkit.getPluginManager().callEvent(event);
				if(event.willBlock()){
					if(event.willDelete())
						e.getItemDrop().setItemStack(GriefBeGone.emptyItemStack);
					else
						e.setCancelled(true);
				}
			}else{
				if(boolArray[1])
					e.getItemDrop().setItemStack(GriefBeGone.emptyItemStack);
				else
					e.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemPickup(PlayerPickupItemEvent e){
		boolean[] boolArray = ActionHandler.PICK_UP.shouldBlockAndDelete(e.getPlayer(), e.getItem().getItemStack());
		if(boolArray[0]){
			if(this.events){
				BlockItemPickupEvent event = new BlockItemPickupEvent(boolArray[1], e.getPlayer(), e.getItem(), e.getRemaining());
				Bukkit.getPluginManager().callEvent(event);
				if(event.willBlock()){
					if(event.willDelete())
						e.getItem().setItemStack(GriefBeGone.emptyItemStack);
					else
						e.setCancelled(true);
				}
			}else{
				if(boolArray[1])
					e.getItem().setItemStack(GriefBeGone.emptyItemStack);
				else
					e.setCancelled(true);
			}
		}
	}
}
