package me.superckl.griefbegone.events;

import me.superckl.griefbegone.ActionHandler;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class BlockableEvent extends Event{

	private final ActionHandler action;
	private boolean willBlock;

	public BlockableEvent(final ActionHandler action){
		this.action = action;
		this.willBlock = true;
	}

	public boolean willBlock() {
		return this.willBlock;
	}

	public void setWillBlock(final boolean willBlock) {
		this.willBlock = willBlock;
	}


	public ActionHandler getAction(){
		return this.action;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
}
