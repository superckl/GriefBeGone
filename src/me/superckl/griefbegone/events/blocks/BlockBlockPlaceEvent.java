package me.superckl.griefbegone.events.blocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

public class BlockBlockPlaceEvent extends DeletableEvent{

	private Player player;
	private Block blockPlaced;
	private Block placedAgainst;
	
	public BlockBlockPlaceEvent(boolean delete, Block blockPlaced, Block placedAgainst, Player player){
		super(ActionHandler.PLACE, delete);
		this.placedAgainst = placedAgainst;
		this.player = player;
		this.blockPlaced = blockPlaced;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public Block getBlockPlaced(){
		return this.blockPlaced;
	}

	public Block getBlockPlacedAgainst(){
		return this.placedAgainst;
	}
}