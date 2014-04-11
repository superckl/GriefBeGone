package me.superckl.griefbegone.events.world;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

import org.bukkit.entity.LivingEntity;

public class BlockCreatureSpawnEvent extends BlockableMiscActionEvent{

	private final LivingEntity entity;

	public BlockCreatureSpawnEvent(final LivingEntity entity) {
		super(MiscActionHandler.ENTITY_SPAWN);
		this.entity = entity;
	}

	public LivingEntity getSpawnedCreature(){
		return this.entity;
	}

}
