package me.superckl.griefbegone.events.world;

import org.bukkit.inventory.BrewerInventory;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

public class BlockBrewEvent extends BlockableMiscActionEvent{
	
	private BrewerInventory inv;
	
	public BlockBrewEvent(BrewerInventory inv) {
		super(MiscActionHandler.BREW);
		this.inv = inv;
	}

	public BrewerInventory getBrewerInventory(){
		return this.inv;
	}
	
}
