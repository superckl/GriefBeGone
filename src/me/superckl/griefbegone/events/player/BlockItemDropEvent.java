package me.superckl.griefbegone.events.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

public class BlockItemDropEvent extends DeletableEvent{

	private Player player;
	private Item dropped;
	
	public BlockItemDropEvent(boolean delete, Player player, Item dropped) {
		super(ActionHandler.DROP, delete);
		this.player = player;
		this.dropped = dropped;
	}

	public Player getPlayer(){
		return this.player;
	}

	public Item getDroppedItem(){
		return this.dropped;
	}
}
