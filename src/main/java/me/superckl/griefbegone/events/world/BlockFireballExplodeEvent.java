package me.superckl.griefbegone.events.world;

import java.util.List;

import lombok.Getter;
import me.superckl.griefbegone.MiscActionHandler;

import org.bukkit.block.Block;
import org.bukkit.entity.Fireball;

public class BlockFireballExplodeEvent extends BlockEntityExplodeEvent{

	@Getter
	private final Fireball fireball;

	public BlockFireballExplodeEvent(final List<Block> blocklist, final Fireball fireball) {
		super(MiscActionHandler.FIREBALL, blocklist);
		this.fireball = fireball;
	}

}
