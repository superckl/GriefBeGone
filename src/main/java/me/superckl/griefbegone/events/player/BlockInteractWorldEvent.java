package me.superckl.griefbegone.events.player;

import lombok.Getter;
import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class BlockInteractWorldEvent extends DeletableEvent{

	@Getter
	private final Player player;
	private final Block block;
	@Getter
	private final Action actionType;

	public BlockInteractWorldEvent(final boolean delete, final Player player, final Block block, Action action) {
		super(ActionHandler.INTERACT_WORLD, delete);
		this.player = player;
		this.block = block;
		this.actionType = action;
	}

	public Block getClickedBlock(){
		return this.block;
	}

}
