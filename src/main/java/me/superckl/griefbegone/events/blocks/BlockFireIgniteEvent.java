package me.superckl.griefbegone.events.blocks;

import lombok.Getter;
import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

public class BlockFireIgniteEvent extends BlockableMiscActionEvent{

	@Getter
	private final Block ignited;
	@Getter
	private final Entity igniterEntity;
	@Getter
	private final Block igniterBlock;

	public BlockFireIgniteEvent(final Block ignited, final Entity igniterEntity, final Block igniterBlock) {
		super(MiscActionHandler.FIRE_IGNITE);
		this.ignited = ignited;
		this.igniterEntity = igniterEntity;
		this.igniterBlock = igniterBlock;
	}

}
