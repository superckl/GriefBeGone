package me.superckl.griefbegone.events.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

public class BlockInteractWorldEvent extends DeletableEvent{

	private Player player;
	private Block block;
	
	public BlockInteractWorldEvent(boolean delete, Player player, Block block) {
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
