package me.superckl.griefbegone.events.world;

import java.util.List;

import me.superckl.griefbegone.MiscActionHandler;

import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;

public class BlockTnTExplodeEvent extends BlockEntityExplodeEvent{

	private final TNTPrimed tnt;

	public BlockTnTExplodeEvent(final List<Block> blocklist, final TNTPrimed tnt) {
		super(MiscActionHandler.TNT, blocklist);
		this.tnt = tnt;
	}

	public TNTPrimed getTNT(){
		return this.tnt;
	}
}
