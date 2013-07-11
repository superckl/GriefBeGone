package me.superckl.griefbegone.events.player;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.BlockableEvent;

public class BlockInventoryClickEvent extends BlockableEvent{

	private boolean deleteClicked;
	private boolean deleteCursor;
	private HumanEntity player;
	private ItemStack clicked;
	private ItemStack cursor;
	private int slot;
	
	public BlockInventoryClickEvent(boolean deleteCursor, boolean deleteClicked, HumanEntity player, ItemStack clickedItem, ItemStack itemOnCursor, int rawSlot) {
		super(ActionHandler.INVENTORY);
		this.setDeleteClicked(deleteClicked);
		this.deleteCursor = deleteCursor;
		this.player = player;
		this.clicked = clickedItem;
		this.cursor = itemOnCursor;
		this.slot = rawSlot;
	}

	public HumanEntity getWhoClicked(){
		return this.player;
	}
	
	public ItemStack getClickedItemStack(){
		return this.clicked;
	}
	
	public ItemStack getItemStackOnCursor(){
		return this.cursor;
	}
	
	public int getRawSlot(){
		return this.slot;
	}

	public boolean willDeleteCursor() {
		return deleteCursor;
	}

	public void setDeleteCursor(boolean deleteCursor) {
		this.deleteCursor = deleteCursor;
	}

	public boolean willDeleteClicked() {
		return deleteClicked;
	}

	public void setDeleteClicked(boolean deleteClicked) {
		this.deleteClicked = deleteClicked;
	}
}
