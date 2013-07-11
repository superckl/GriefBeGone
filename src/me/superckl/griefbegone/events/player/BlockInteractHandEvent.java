package me.superckl.griefbegone.events.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

public class BlockInteractHandEvent extends DeletableEvent{

	private Player player;
	private ItemStack item;
	
	public BlockInteractHandEvent(boolean delete, Player player, ItemStack item) {
		super(ActionHandler.INTERACT_HAND, delete);
		this.player = player;
		this.item = item;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public ItemStack getItemStackInHand(){
		return this.item;
	}

}
