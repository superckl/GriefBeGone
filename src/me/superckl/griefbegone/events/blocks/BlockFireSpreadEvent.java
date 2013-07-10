package me.superckl.griefbegone.events.blocks;

import org.bukkit.block.Block;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

public class BlockFireSpreadEvent extends BlockableMiscActionEvent{

	private Block ignited;
	
	public BlockFireSpreadEvent(Block ignited) {
		super(MiscActionHandler.FIRE_SPREAD);
		this.ignited = ignited;
	}

	public Block getIgnited(){
		return this.ignited;
	}

}
