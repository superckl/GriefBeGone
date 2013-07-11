package me.superckl.griefbegone.events.blocks;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockBlockPlaceEvent extends DeletableEvent{

	private final Player player;
	private final Block blockPlaced;
	private final Block placedAgainst;

	public BlockBlockPlaceEvent(final boolean delete, final Block blockPlaced, final Block placedAgainst, final Player player){
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
