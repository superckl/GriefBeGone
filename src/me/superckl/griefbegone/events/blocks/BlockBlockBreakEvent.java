package me.superckl.griefbegone.events.blocks;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockBlockBreakEvent extends DeletableEvent{

	private Player player;
	private Block blockBroken;
	private int exp;
	
	public BlockBlockBreakEvent(boolean delete, Block blockBroken, Player player, int exp){
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
