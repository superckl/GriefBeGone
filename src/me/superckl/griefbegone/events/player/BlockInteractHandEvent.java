package me.superckl.griefbegone.events.player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class BlockInteractHandEvent extends DeletableEvent{

	private final Player player;
	private final ItemStack item;

	public BlockInteractHandEvent(final boolean delete, final Player player, final ItemStack item) {
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
