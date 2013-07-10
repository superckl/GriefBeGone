package me.superckl.griefbegone.events;

import me.superckl.griefbegone.ActionHandler;

public abstract class GriefBeGoneDeletableEvent extends GriefBeGoneBlockableEvent{

	private boolean willDelete;
	
	public GriefBeGoneDeletableEvent(ActionHandler action, boolean delete){
		super(action);
		this.willDelete = delete;
	}

	public boolean willDelete() {
		return willDelete;
	}

	public void setWillDelete(boolean willDelete) {
		this.willDelete = willDelete;
	}

}
