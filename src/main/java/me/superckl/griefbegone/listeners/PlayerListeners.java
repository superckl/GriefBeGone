package me.superckl.griefbegone.listeners;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.GriefBeGone;
import me.superckl.griefbegone.events.player.BlockCraftEvent;
import me.superckl.griefbegone.events.player.BlockInteractHandEvent;
import me.superckl.griefbegone.events.player.BlockInteractWorldEvent;
import me.superckl.griefbegone.events.player.BlockInventoryClickEvent;
import me.superckl.griefbegone.events.player.BlockItemDropEvent;
import me.superckl.griefbegone.events.player.BlockItemPickupEvent;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListeners implements Listener{

	private boolean events;

	public PlayerListeners(final boolean events){
		this.events = events;
	}

	public void setEvents(final boolean events){
		this.events = events;
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerDrop(final PlayerDropItemEvent e){
		final boolean[] boolArray = ActionHandler.DROP.shouldBlockAndDelete(e.getPlayer(), e.getItemDrop().getItemStack());
		if(boolArray[0]){
			if(this.events){
				final BlockItemDropEvent event = new BlockItemDropEvent(boolArray[1], e.getPlayer(), e.getItemDrop());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
				boolArray[1] = event.willDelete();
			}
			String message = ActionHandler.DROP.getMessage();
			if(message != null)
				e.getPlayer().sendMessage(message);
			message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" tried to drop ").append(e.getItemDrop().getItemStack().getType().toString()).toString();
			GriefBeGone.getInstance().getLogger().info(message);
			for(final Player player:Bukkit.getOnlinePlayers())
				if(player.hasPermission("disabler.broadcast."+ActionHandler.DROP.getPerm()))
					player.sendMessage(message);
			if(boolArray[1])
				e.getItemDrop().setItemStack(GriefBeGone.emptyItemStack);
			else
				e.setCancelled(true);

		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onItemPickup(final PlayerPickupItemEvent e){
		final boolean[] boolArray = ActionHandler.PICK_UP.shouldBlockAndDelete(e.getPlayer(), e.getItem().getItemStack());
		if(boolArray[0]){
			if(this.events){
				final BlockItemPickupEvent event = new BlockItemPickupEvent(boolArray[1], e.getPlayer(), e.getItem(), e.getRemaining());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
				boolArray[1] = event.willDelete();
			}
			String message = ActionHandler.PICK_UP.getMessage();
			if(message != null)
				e.getPlayer().sendMessage(message);
			message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" tried to pick-up ").append(e.getItem().getType().toString()).toString();
			GriefBeGone.getInstance().getLogger().info(message);
			for(final Player player:Bukkit.getOnlinePlayers())
				if(player.hasPermission("disabler.broadcast."+ActionHandler.PICK_UP.getPerm()))
					player.sendMessage(message);
			if(boolArray[1])
				e.getItem().setItemStack(GriefBeGone.emptyItemStack);
			else
				e.setCancelled(true);

		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInteract(final PlayerInteractEvent e){
		if((e.getClickedBlock() != null) && (e.getClickedBlock().getType() != Material.AIR)){
			final boolean[] boolArray = ActionHandler.INTERACT_WORLD.shouldBlockAndDelete(e.getPlayer(), new ItemStack(e.getClickedBlock().getType()));
			if(boolArray[0])
				if(this.events){
					final BlockInteractWorldEvent event = new BlockInteractWorldEvent(boolArray[1], e.getPlayer(), e.getClickedBlock(), e.getAction());
					Bukkit.getPluginManager().callEvent(event);
					if(event.willBlock()){
						e.setCancelled(true);
						String message = ActionHandler.INTERACT_WORLD.getMessage();
						if(message != null)
							e.getPlayer().sendMessage(message);
						message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" tried to interact with ").append(e.getClickedBlock().getType().toString()).toString();
						GriefBeGone.getInstance().getLogger().info(message);
						for(final Player player:Bukkit.getOnlinePlayers())
							if(player.hasPermission("disabler.broadcast."+ActionHandler.INTERACT_WORLD.getPerm()))
								player.sendMessage(message);
						if(event.willDelete())
							e.getClickedBlock().setType(Material.AIR);
					}
				}else{
					e.setCancelled(true);
					String message = ActionHandler.INTERACT_WORLD.getMessage();
					if(message != null)
						e.getPlayer().sendMessage(message);
					message = new StringBuilder(ChatColor.RED+e.getPlayer().getName()).append(" tried to interact with ").append(e.getClickedBlock().getType().toString()).toString();
					GriefBeGone.getInstance().getLogger().info(message);
					for(final Player player:Bukkit.getOnlinePlayers())
						if(player.hasPermission("disabler.broadcast."+ActionHandler.INTERACT_WORLD.getPerm()))
							player.sendMessage(message);
					if(boolArray[1])
						e.getClickedBlock().setType(Material.AIR);
				}
		}
		if((e.getItem() != null) && (e.getItem().getType() != Material.AIR)){
			final boolean[] boolArray = ActionHandler.INTERACT_HAND.shouldBlockAndDelete(e.getPlayer(), e.getItem());
			if(boolArray[0])
				if(this.events){
					final BlockInteractHandEvent event = new BlockInteractHandEvent(boolArray[1], e.getPlayer(), e.getItem());
					Bukkit.getPluginManager().callEvent(event);
					if(!event.willBlock())
						return;
					boolArray[1] = event.willDelete();
				}else{
					e.setCancelled(true);
					String message = ActionHandler.INTERACT_HAND.getMessage();
					if(message != null)
						e.getPlayer().sendMessage(message);
					message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(e.getPlayer().getName()).append(" had an illegal item in their hand: ").append(e.getItem().getType().toString()).toString();
					GriefBeGone.getInstance().getLogger().info(message);
					for(final Player player:Bukkit.getOnlinePlayers())
						if(player.hasPermission("disabler.broadcast."+ActionHandler.INTERACT_HAND.getPerm()))
							player.sendMessage(message);
					if(boolArray[1])
						e.getItem().setType(Material.AIR);
				}
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerInventoryClick(final InventoryClickEvent e){
		if((e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) && (e.getCursor() == null || e.getCursor().getType() == Material.AIR))
			return;
		final boolean[] cursorArray = ActionHandler.INVENTORY.shouldBlockAndDelete(e.getWhoClicked(), e.getCursor());
		final boolean[] clickedArray = ActionHandler.INVENTORY.shouldBlockAndDelete(e.getWhoClicked(), e.getCurrentItem());
		if(cursorArray[0] || clickedArray[0]){
			if(this.events){
				final BlockInventoryClickEvent event = new BlockInventoryClickEvent(cursorArray[1], clickedArray[1], e.getWhoClicked(), e.getCurrentItem(), e.getCursor(), e.getRawSlot());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;
				cursorArray[1] = event.willDeleteCursor();
				clickedArray[1] = event.willDeleteClicked();
			}
			e.setCancelled(true);
			String message = ActionHandler.INVENTORY.getMessage();
			if(e.getWhoClicked() instanceof Player){
				if(message != null)
					((Player)e.getWhoClicked()).sendMessage(message);
				final StringBuilder sb = new StringBuilder();
				if(cursorArray[0])
					sb.append(ChatColor.RED).append("[GriefBeGone] ").append(((Player)e.getWhoClicked()).getName()).append(" had an illegal item on their cursor: ").append(e.getCursor().getType().toString()).append("\n");
				if(clickedArray[0])
					sb.append(ChatColor.RED).append("[GriefBeGone] ").append(((Player)e.getWhoClicked()).getName()).append(" clicked on an illegal item in their inventory: ").append(e.getCurrentItem().getType().toString()).append("\n");
				sb.substring(0, sb.length()-2);
				message = sb.toString();
				GriefBeGone.getInstance().getLogger().info(message);
				for(final Player player:Bukkit.getOnlinePlayers())
					if(player.hasPermission("disabler.broadcast."+ActionHandler.INVENTORY.getPerm()))
						player.sendMessage(message);
			}
			if(cursorArray[1])
				e.getView().setCursor(GriefBeGone.emptyItemStack);
			if(clickedArray[1])
				e.setCurrentItem(GriefBeGone.emptyItemStack);

		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCraft(final CraftItemEvent e){
		final boolean block = ActionHandler.CRAFT.shouldBlockAndDelete(e.getWhoClicked(), e.getInventory().getResult())[0];
		if(block){
			if(this.events){
				final BlockCraftEvent event = new BlockCraftEvent(e.getWhoClicked(), e.getInventory().getResult());
				Bukkit.getPluginManager().callEvent(event);
				if(!event.willBlock())
					return;

			}
			e.setResult(Result.DENY);
			String message = ActionHandler.CRAFT.getMessage();
			if(e.getWhoClicked() instanceof Player){
				if(message != null)
					((Player)e.getWhoClicked()).sendMessage(message);
				message = new StringBuilder().append(ChatColor.RED).append("[GriefBeGone] ").append(((Player)e.getWhoClicked()).getName()).append(" tried to craft an illegal item: ").append(e.getInventory().getResult().getType().toString()).toString();
				GriefBeGone.getInstance().getLogger().info(message);
				for(final Player player:Bukkit.getOnlinePlayers())
					if(player.hasPermission("disabler.broadcast."+ActionHandler.CRAFT.getPerm()))
						player.sendMessage(message);
			}

		}
	}
}
