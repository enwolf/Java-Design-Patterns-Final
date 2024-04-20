package org.cst8288.finalproject.manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dao.InventoryManagerDAO;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.validator.FoodItemValidator;

/**
 * Manages inventory-related operations by interfacing with the InventoryManagerDAO and validating item data using FoodItemValidator.
 * This class consolidates business logic associated with inventory management such as adding, removing, and retrieving items
 * from the inventory, including handling validation to ensure data integrity before database operations are performed.
 *
 * The class uses methods in InventoryManagerDAO for direct database interaction and FoodItemValidator for pre-operation validation,
 * ensuring that only valid data is processed for inventory operations. This approach encapsulates inventory management logic,
 * making the system easier to maintain and modify.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see org.cst8288.finalproject.dao.InventoryManagerDAO
 * @see org.cst8288.finalproject.dto.Item
 * @see org.cst8288.finalproject.validator.FoodItemValidator
 */
public class InventoryManager {
	
	private InventoryManagerDAO inventoryDAO;
	private FoodItemValidator foodItemValidator;
			
	
    /**
     * Constructs an InventoryManager with the specified InventoryManagerDAO and FoodItemValidator.
     *
     * @param inventoryDAO      The InventoryManagerDAO to use for database interactions.
     * @param foodItemValidator The FoodItemValidator to use for validating food items.
     */
	public InventoryManager(InventoryManagerDAO inventoryDAO, FoodItemValidator foodItemValidator ) 
	{
		this.inventoryDAO = inventoryDAO;
		this.foodItemValidator = foodItemValidator;
	}	
	
	/**
     * Validates and adds an inventory item.
     *
     * @param inventoryItem The inventory item to validate and add.
     */
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
	
    /**
     * Adds an inventory item.
     *
     * @param inventoryItem The inventory item to add.
     */
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
	
    /**
     * Removes an inventory item by ID.
     *
     * @param itemID The ID of the inventory item to remove.
     */
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
	
    /**
     * Retrieves an inventory item by ID.
     *
     * @param itemID The ID of the inventory item to retrieve.
     */
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
	
    /**
     * Retrieves all inventory items.
     *
     * @return A list of all inventory items.
     */
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
    /**
     * Retrieves all surplus inventory items.
     *
     * @return A list of all surplus inventory items.
     */
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
