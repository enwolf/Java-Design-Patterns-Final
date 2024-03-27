package org.cst8288.finalproject.manager;

import java.util.List;

import org.cst8288.dto.Item;
import org.cst8288.finalproject.dao.InventoryManagerDAO;
import org.cst8288.finalproject.validator.FoodItemValidator;

public class InventoryManager {
	
	private InventoryManagerDAO inventoryDAO;
	private FoodItemValidator foodItemValidator;
	private List<Item> inventoryItems;
		
	
	public InventoryManager(InventoryManagerDAO inventoryDAO, FoodItemValidator foodItemValidator ) 
	{
		this.inventoryDAO = inventoryDAO;
		this.foodItemValidator = foodItemValidator;
	}	
	
	public void validateAndAddInventoryItem(Item inventoryItem) 
	{
		//TO DO create the logic once you have finished creating the FoodItemValidator class and methods.
	}
	
	public void addInventoryItem(Item inventoryItem) 
	{
		inventoryDAO.addInventoryItem(inventoryItem);
	}
	
	public void removeInventoryItem(int itemID) 
	{
		inventoryDAO.removeInventoryitem(itemID);		
	}
	
	public void getInventoryItem(int itemID) 
	{
		inventoryDAO.getSingleInventoryItemByID(itemID);
	}
	
	public List<Item> getAllInventoryItems()
	{
		return inventoryDAO.getALLInventoryItems();		
	}
	
	public List<Item> getAllSurplusnventoryItems()
	{
		return inventoryDAO.getALLSurplusInventoryItems();
	}
	

}
