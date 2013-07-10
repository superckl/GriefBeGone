package me.superckl.griefbegone.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import me.superckl.griefbegone.MiscActionHandler;

public abstract class BlockableMiscActionEvent extends Event{

	private MiscActionHandler action;
	private boolean willBlock;
	
	public BlockableMiscActionEvent(MiscActionHandler action){
		this.action = action;
		this.willBlock = true;
	}
	
	public boolean willBlock() {
		return willBlock;
	}

	public void setWillBlock(boolean willBlock) {
		this.willBlock = willBlock;
	}
	
	
	public MiscActionHandler getAction(){
		return this.action;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
