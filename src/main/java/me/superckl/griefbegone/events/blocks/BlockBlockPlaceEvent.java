package me.superckl.griefbegone.events.blocks;

import lombok.Getter;
import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.DeletableEvent;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BlockBlockPlaceEvent extends DeletableEvent{

	@Getter
	private final Player player;
	@Getter
	private final Block blockPlaced;
	@Getter
	private final Block placedAgainst;

	public BlockBlockPlaceEvent(final boolean delete, final Block blockPlaced, final Block placedAgainst, final Player player){
		super(ActionHandler.PLACE, delete);
		this.placedAgainst = placedAgainst;
		this.player = player;
		this.blockPlaced = blockPlaced;
	}

}
