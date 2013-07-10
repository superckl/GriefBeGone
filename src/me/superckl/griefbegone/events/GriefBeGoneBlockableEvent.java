package me.superckl.griefbegone.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.superckl.griefbegone.ActionHandler;

public abstract class GriefBeGoneBlockableEvent extends Event{

	private ActionHandler action;
	private boolean willBlock;

	public GriefBeGoneBlockableEvent(ActionHandler action){
		this.action = action;
		this.willBlock = true;
	}
	
	public boolean willBlock() {
		return willBlock;
	}

	public void setWillBlock(boolean willBlock) {
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
