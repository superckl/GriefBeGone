package me.superckl.griefbegone.events.blocks;

import lombok.Getter;
import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.block.Block;

public class BlockFireSpreadEvent extends BlockableMiscActionEvent{

	@Getter
	private final Block ignited;

	public BlockFireSpreadEvent(final Block ignited) {
		super(MiscActionHandler.FIRE_SPREAD);
		this.ignited = ignited;
	}

}
