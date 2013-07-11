package me.superckl.griefbegone.events.player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.BlockableEvent;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

public class BlockCraftEvent extends BlockableEvent{

	private final HumanEntity viewer;
	private final ItemStack result;

	public BlockCraftEvent(final HumanEntity viewer, final ItemStack result) {
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
