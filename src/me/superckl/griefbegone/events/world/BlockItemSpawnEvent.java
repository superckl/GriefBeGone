package me.superckl.griefbegone.events.world;

import org.bukkit.entity.Item;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

public class BlockItemSpawnEvent extends BlockableMiscActionEvent{

	public BlockItemSpawnEvent(Item item) {
		super(MiscActionHandler.ITEM_SPAWN);
	}

}
