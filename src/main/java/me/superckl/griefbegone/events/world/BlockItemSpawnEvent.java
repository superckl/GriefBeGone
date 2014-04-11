package me.superckl.griefbegone.events.world;

import lombok.Getter;
import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.entity.Item;

public class BlockItemSpawnEvent extends BlockableMiscActionEvent{

	@Getter
	private final Item item;
	
	public BlockItemSpawnEvent(final Item item) {
		super(MiscActionHandler.ITEM_SPAWN);
		this.item = item;
	}
	
}
