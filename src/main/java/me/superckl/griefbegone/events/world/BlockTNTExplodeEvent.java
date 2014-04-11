package me.superckl.griefbegone.events.world;

import java.util.List;

import lombok.Getter;
import me.superckl.griefbegone.MiscActionHandler;

import org.bukkit.block.Block;
import org.bukkit.entity.TNTPrimed;

public class BlockTNTExplodeEvent extends BlockEntityExplodeEvent{

	@Getter
	private final TNTPrimed TNT;

	public BlockTNTExplodeEvent(final List<Block> blocklist, final TNTPrimed tnt) {
		super(MiscActionHandler.TNT, blocklist);
		this.TNT = tnt;
	}

}
