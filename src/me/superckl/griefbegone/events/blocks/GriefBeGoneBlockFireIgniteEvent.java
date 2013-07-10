package me.superckl.griefbegone.events.blocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.GriefBeGoneBlockableMiscActionEvent;

public class GriefBeGoneBlockFireIgniteEvent extends GriefBeGoneBlockableMiscActionEvent{

	private Block ignited;
	private Entity igniterEntity;
	private Block igniterBlock;
	
	public GriefBeGoneBlockFireIgniteEvent(Block ignited, Entity igniterEntity, Block igniterBlock) {
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
