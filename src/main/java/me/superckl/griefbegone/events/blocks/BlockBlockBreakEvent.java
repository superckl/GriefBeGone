package me.superckl.griefbegone.events.blocks;

import lombok.Getter;
import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockBlockBreakEvent extends DeletableEvent{

	@Getter
	private final Player player;
	@Getter
	private final Block blockBroken;
	@Getter
	private final int exp;

	public BlockBlockBreakEvent(final boolean delete, final Block blockBroken, final Player player, final int exp){
		super(ActionHandler.BREAK, delete);
		this.player = player;
		this.blockBroken = blockBroken;
		this.exp = exp;
	}

}
