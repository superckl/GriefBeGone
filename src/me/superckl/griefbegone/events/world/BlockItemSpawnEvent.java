package me.superckl.griefbegone.events.world;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.entity.Item;

public class BlockItemSpawnEvent extends BlockableMiscActionEvent{

	public BlockItemSpawnEvent(final Item item) {
		super(MiscActionHandler.ITEM_SPAWN);
	}

}
