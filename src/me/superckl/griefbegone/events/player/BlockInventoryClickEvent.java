package me.superckl.griefbegone.events.player;

import me.superckl.griefbegone.ActionHandler;
import me.superckl.griefbegone.events.BlockableEvent;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

public class BlockInventoryClickEvent extends BlockableEvent{

	private boolean deleteClicked;
	private boolean deleteCursor;
	private final HumanEntity player;
	private final ItemStack clicked;
	private final ItemStack cursor;
	private final int slot;

	public BlockInventoryClickEvent(final boolean deleteCursor, final boolean deleteClicked, final HumanEntity player, final ItemStack clickedItem, final ItemStack itemOnCursor, final int rawSlot) {
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
		return this.deleteCursor;
	}

	public void setDeleteCursor(final boolean deleteCursor) {
		this.deleteCursor = deleteCursor;
	}

	public boolean willDeleteClicked() {
		return this.deleteClicked;
	}

	public void setDeleteClicked(final boolean deleteClicked) {
		this.deleteClicked = deleteClicked;
	}
}
