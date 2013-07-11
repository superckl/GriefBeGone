package me.superckl.griefbegone.events.player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class BlockItemDropEvent extends DeletableEvent{

	private final Player player;
	private final Item dropped;

	public BlockItemDropEvent(final boolean delete, final Player player, final Item dropped) {
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
