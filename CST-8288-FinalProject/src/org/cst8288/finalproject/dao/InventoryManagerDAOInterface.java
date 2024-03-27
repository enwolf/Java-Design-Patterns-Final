package org.cst8288.finalproject.dao;

import java.sql.SQLException;
import java.util.List;

import org.cst8288.dto.Item;

public interface InventoryManagerDAOInterface {
	

	void addInventoryItem(Item inventoryItem) throws SQLException;
	void updateInventoryItem(Item inventoryItem) throws SQLException;
	void removeInventoryitem(int itemID) throws SQLException;
	Item getSingleInventoryItemByID(int itemID) throws SQLException;
	List<Item> getALLInventoryItems() throws SQLException;
	List<Item> getALLSurplusInventoryItems() throws SQLException;

}
