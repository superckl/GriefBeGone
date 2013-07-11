package me.superckl.griefbegone.events;

import me.superckl.griefbegone.MiscActionHandler;

public class DeletableMiscActionEvent extends BlockableMiscActionEvent{

	private boolean delete;
	
	public DeletableMiscActionEvent(MiscActionHandler action, boolean delete) {
		super(action);
		
	}

	public boolean willDelete() {
		return delete;
	}

	public void setWillDelete(boolean delete) {
		this.delete = delete;
	}

}
