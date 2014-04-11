package me.superckl.griefbegone.events;

import lombok.Getter;
import lombok.Setter;
import me.superckl.griefbegone.ActionHandler;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class BlockableEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	@Getter
	private final ActionHandler action;
	@Setter
	private boolean willBlock;

	public BlockableEvent(final ActionHandler action){
		this.action = action;
		this.willBlock = true;
	}

	public boolean willBlock() {
		return this.willBlock;
	}

	@Override
	public HandlerList getHandlers() {
		return BlockableEvent.handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}
}
