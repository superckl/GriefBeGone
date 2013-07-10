package me.superckl.griefbegone.events.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

public class BlockItemPickupEvent extends DeletableEvent{

	private Player player;
	private Item pickup;
	private int remaining;
	
	public BlockItemPickupEvent(boolean delete, Player player, Item pickup, int remaining) {
		super(ActionHandler.PICK_UP, delete);
		this.pickup = pickup;
		this.player = player;
		this.remaining = remaining;
	}

	public Player getPlayer(){
		return this.player;
	}
	
	public Item getPickupItem(){
		return this.pickup;
	}

	public int getRemainingItemsOnGround(){
		return this.remaining;
	}
}
