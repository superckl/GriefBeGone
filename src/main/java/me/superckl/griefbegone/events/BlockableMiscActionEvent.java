package me.superckl.griefbegone.events;

import me.superckl.griefbegone.MiscActionHandler;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class BlockableMiscActionEvent extends Event{

	private static final HandlerList handlers = new HandlerList();
	private final MiscActionHandler action;
	private boolean willBlock;

	public BlockableMiscActionEvent(final MiscActionHandler action){
		this.action = action;
		this.willBlock = true;
	}

	public boolean willBlock() {
		return this.willBlock;
	}

	public void setWillBlock(final boolean willBlock) {
		this.willBlock = willBlock;
	}


	public MiscActionHandler getAction(){
		return this.action;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public static HandlerList getHandlerList(){
		return handlers;
	}

}
