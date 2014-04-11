package me.superckl.griefbegone.events.player;

import lombok.Getter;
import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class BlockItemPickupEvent extends DeletableEvent{

	@Getter
	private final Player player;
	private final Item pickup;
	private final int remaining;

	public BlockItemPickupEvent(final boolean delete, final Player player, final Item pickup, final int remaining) {
		super(ActionHandler.PICK_UP, delete);
		this.pickup = pickup;
		this.player = player;
		this.remaining = remaining;
	}

	public Item getPickupItem(){
		return this.pickup;
	}

	public int getRemainingItemsOnGround(){
		return this.remaining;
	}
}
