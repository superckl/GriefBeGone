package me.superckl.griefbegone.events;

import lombok.Setter;
import me.superckl.griefbegone.ActionHandler;

public abstract class DeletableEvent extends BlockableEvent{

	@Setter
	private boolean willDelete;

	public DeletableEvent(final ActionHandler action, final boolean delete){
		super(action);
		this.willDelete = delete;
	}

	public boolean willDelete() {
		return this.willDelete;
	}

}
