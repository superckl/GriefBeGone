package me.superckl.griefbegone.events.world;

import java.util.List;

import lombok.Getter;
import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.block.Block;

public class BlockEntityExplodeEvent extends BlockableMiscActionEvent{

	@Getter
	private final List<Block> blocklist;

	public BlockEntityExplodeEvent(final MiscActionHandler action, final List<Block> blocklist) {
		super(action);
		this.blocklist = blocklist;
	}

}
