package me.superckl.griefbegone.events.world;

import java.util.List;

import org.bukkit.block.Block;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

public class BlockEntityExplodeEvent extends BlockableMiscActionEvent{

	private List<Block> blocklist;
	
	public BlockEntityExplodeEvent(MiscActionHandler action, List<Block> blocklist) {
		super(action);
		this.blocklist = blocklist;
	}

	public List<Block> getBlockList(){
		return this.blocklist;
	}
}
