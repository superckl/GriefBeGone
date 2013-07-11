package me.superckl.griefbegone.events.world;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;

import me.superckl.griefbegone.MiscActionHandler;

public class BlockCreeperExplodeEvent extends BlockEntityExplodeEvent{

	private Creeper creeper;
	
	public BlockCreeperExplodeEvent(List<Block> blocklist, Creeper creeper) {
		super(MiscActionHandler.CREEPER, blocklist);
		this.creeper = creeper;
	}

	public Creeper getCreeper(){
		return this.creeper;
	}
	
}
