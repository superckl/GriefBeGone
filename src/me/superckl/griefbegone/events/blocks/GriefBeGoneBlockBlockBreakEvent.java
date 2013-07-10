package me.superckl.griefbegone.events.blocks;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.GriefBeGoneDeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class GriefBeGoneBlockBlockBreakEvent extends GriefBeGoneDeletableEvent{

	private Player player;
	private Block blockBroken;
	private int exp;
	
	public GriefBeGoneBlockBlockBreakEvent(boolean delete, Block blockBroken, Player player, int exp){
		super(ActionHandler.BREAK, delete);
		this.player = player;
		this.blockBroken = blockBroken;
		this.exp = exp;
	}
	
	public Player getPlayer(){
		return this.player;
	}

	public Block getBlockBroken(){
		return this.blockBroken;
	}
	
	public int getExpDropped(){
		return this.exp;
	}
}
