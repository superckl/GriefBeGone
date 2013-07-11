package me.superckl.griefbegone.events.world;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

public class BlockItemDispenseEvent extends DeletableEvent{

	private Block disp;
	private ItemStack item;
	
	public BlockItemDispenseEvent(boolean delete, Block disp, ItemStack item) {
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
