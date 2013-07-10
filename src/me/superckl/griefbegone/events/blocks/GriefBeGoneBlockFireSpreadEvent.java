package me.superckl.griefbegone.events.blocks;

import org.bukkit.block.Block;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.GriefBeGoneBlockableMiscActionEvent;

public class GriefBeGoneBlockFireSpreadEvent extends GriefBeGoneBlockableMiscActionEvent{

	private Block ignited;
	
	public GriefBeGoneBlockFireSpreadEvent(Block ignited) {
		super(MiscActionHandler.FIRE_SPREAD);
		this.ignited = ignited;
	}

	public Block getIgnited(){
		return this.ignited;
	}

}
