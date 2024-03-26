package org.cst8288.finalproject.dao;

import java.util.List;

import org.cst8288.dto.Item;

public interface InventoryManagerDAOInterface {
	

	void addInventoryItem(Item inventoryItem);
	void updateInventoryItem(Item inventoryItem);
	void removeInventoryitem(int itemID);
	Item getSingleInventoryItemByID(int itemID);
	List<Item> getALLInventoryItems();
	List<Item> getALLSurplusInventoryItems();

}
