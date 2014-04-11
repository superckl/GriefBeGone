package me.superckl.griefbegone.events.world;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class BlockItemDispenseEvent extends DeletableEvent{

	private final Block disp;
	private final ItemStack item;

	public BlockItemDispenseEvent(final boolean delete, final Block disp, final ItemStack item) {
		super(ActionHandler.DISPENSE, delete);
		this.disp = disp;
		this.item = item;
	}

	public Block getDispenser(){
		return this.disp;
	}

	public ItemStack getDispensedItemStack(){
		return this.item;
	}

}
