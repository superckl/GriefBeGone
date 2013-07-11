package me.superckl.griefbegone.events.world;

import org.bukkit.entity.LivingEntity;

import me.superckl.griefbegone.MiscActionHandler;
import me.superckl.griefbegone.events.BlockableMiscActionEvent;

public class BlockCreatureSpawnEvent extends BlockableMiscActionEvent{

	private LivingEntity entity;
	
	public BlockCreatureSpawnEvent(LivingEntity entity) {
		super(MiscActionHandler.ENTITY_SPAWN);
		this.entity = entity;
	}
	
	public LivingEntity getSpawnedCreature(){
		return this.entity;
	}

}
