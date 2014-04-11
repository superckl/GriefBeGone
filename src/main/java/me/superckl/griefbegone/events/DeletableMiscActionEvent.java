package me.superckl.griefbegone.events;

import me.superckl.griefbegone.MiscActionHandler;

public class DeletableMiscActionEvent extends BlockableMiscActionEvent{

	private boolean delete;

	public DeletableMiscActionEvent(final MiscActionHandler action, final boolean delete) {
		super(action);

	}

	public boolean willDelete() {
		return this.delete;
	}

	public void setWillDelete(final boolean delete) {
		this.delete = delete;
	}

}
