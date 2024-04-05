package org.cst8288.finalproject.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dao.InventoryManagerDAO;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.validator.FoodItemValidator;

public class InventoryManager {
	
	private InventoryManagerDAO inventoryDAO;
	private FoodItemValidator foodItemValidator;
			
	
	public InventoryManager(InventoryManagerDAO inventoryDAO, FoodItemValidator foodItemValidator ) 
	{
		this.inventoryDAO = inventoryDAO;
		this.foodItemValidator = foodItemValidator;
	}	
	
	public void validateAndAddInventoryItem(Item inventoryItem) 
	{
		if(foodItemValidator.validateFoodItem(inventoryItem)) 
		{
			try 
			{
				inventoryDAO.addInventoryItem(inventoryItem);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void addInventoryItem(Item inventoryItem) 
	{
		try 
		{
			inventoryDAO.addInventoryItem(inventoryItem);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void removeInventoryItem(int itemID) 
	{
		try 
		{
			inventoryDAO.removeInventoryitem(itemID);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}		
	}
	
	public void getInventoryItem(int itemID) 
	{
		try 
		{
			inventoryDAO.getSingleInventoryItemByID(itemID);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Item> getAllInventoryItems()
	{
		try 
		{
			return inventoryDAO.getALLInventoryItems();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<>();			
		}		
	}
	
	public List<Item> getAllSurplusnventoryItems()
	{
		try 
		{
			return inventoryDAO.getALLSurplusInventoryItems();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return new ArrayList<>();			
		}
	}
}
