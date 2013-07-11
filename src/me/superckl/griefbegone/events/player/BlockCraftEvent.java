package me.superckl.griefbegone.events.player;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.BlockableEvent;

public class BlockCraftEvent extends BlockableEvent{

	private HumanEntity viewer;
	private ItemStack result;
	
	public BlockCraftEvent(HumanEntity viewer, ItemStack result) {
		super(ActionHandler.CRAFT);
		this.viewer = viewer;
		this.result = result;
	}
	
	public HumanEntity getViewer(){
		return this.viewer;
	}

	public ItemStack getResult(){
		return this.result;
	}
}
