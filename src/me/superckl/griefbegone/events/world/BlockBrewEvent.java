package me.superckl.griefbegone.events.world;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.inventory.BrewerInventory;

public class BlockBrewEvent extends BlockableMiscActionEvent{

	private final BrewerInventory inv;

	public BlockBrewEvent(final BrewerInventory inv) {
		super(MiscActionHandler.BREW);
		this.inv = inv;
	}

	public BrewerInventory getBrewerInventory(){
		return this.inv;
	}

}
