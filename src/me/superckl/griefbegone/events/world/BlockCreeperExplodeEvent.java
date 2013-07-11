package me.superckl.griefbegone.events.world;

import java.util.List;

import me.superckl.griefbegone.MiscActionHandler;

import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;

public class BlockCreeperExplodeEvent extends BlockEntityExplodeEvent{

	private final Creeper creeper;

	public BlockCreeperExplodeEvent(final List<Block> blocklist, final Creeper creeper) {
		super(MiscActionHandler.CREEPER, blocklist);
		this.creeper = creeper;
	}

	public Creeper getCreeper(){
		return this.creeper;
	}

}
