package me.superckl.griefbegone.events.blocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

public class BlockFireIgniteEvent extends BlockableMiscActionEvent{

	private Block ignited;
	private Entity igniterEntity;
	private Block igniterBlock;
	
	public BlockFireIgniteEvent(Block ignited, Entity igniterEntity, Block igniterBlock) {
		super(MiscActionHandler.FIRE_IGNITE);
		this.ignited = ignited;
		this.igniterEntity = igniterEntity;
		this.igniterBlock = igniterBlock;
	}

	public Block getIgnited(){
		return this.ignited;
	}

	public Entity getIgniterEntity(){
		return this.igniterEntity;
	}
	
	public Block getIgniterBlock(){
		return this.igniterBlock;
	}
}
