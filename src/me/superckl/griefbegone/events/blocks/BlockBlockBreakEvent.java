package me.superckl.griefbegone.events.blocks;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockBlockBreakEvent extends DeletableEvent{

	private final Player player;
	private final Block blockBroken;
	private final int exp;

	public BlockBlockBreakEvent(final boolean delete, final Block blockBroken, final Player player, final int exp){
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
