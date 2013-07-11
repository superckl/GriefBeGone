package me.superckl.griefbegone.events;

import me.superckl.griefbegone.ActionHandler;

public abstract class DeletableEvent extends BlockableEvent{

	private boolean willDelete;

	public DeletableEvent(final ActionHandler action, final boolean delete){
		super(action);
		this.willDelete = delete;
	}

	public boolean willDelete() {
		return this.willDelete;
	}

	public void setWillDelete(final boolean willDelete) {
		this.willDelete = willDelete;
	}

}
