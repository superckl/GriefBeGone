package me.superckl.griefbegone.events.world;

import java.util.List;

import me.superckl.griefbegone.MiscActionHandler;

import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;

public class BlockTnTExplodeEvent extends BlockEntityExplodeEvent{

	private TNTPrimed tnt;
	
	public BlockTnTExplodeEvent(List<Block> blocklist, TNTPrimed tnt) {
		super(MiscActionHandler.TNT, blocklist);
		this.tnt = tnt;
	}

	public TNTPrimed getTNT(){
		return this.tnt;
	}
}
