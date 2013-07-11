package me.superckl.griefbegone.events.blocks;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class BlockFireIgniteEvent extends BlockableMiscActionEvent{

	private final Block ignited;
	private final Entity igniterEntity;
	private final Block igniterBlock;

	public BlockFireIgniteEvent(final Block ignited, final Entity igniterEntity, final Block igniterBlock) {
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
