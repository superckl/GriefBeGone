package me.superckl.griefbegone.events.player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockInteractWorldEvent extends DeletableEvent{

	private final Player player;
	private final Block block;

	public BlockInteractWorldEvent(final boolean delete, final Player player, final Block block) {
		super(ActionHandler.INTERACT_WORLD, delete);
		this.player = player;
		this.block = block;
	}

	public Player getPlayer(){
		return this.player;
	}

	public Block getClickedBlock(){
		return this.block;
	}

}
