package org.cst8288.finalproject.validator;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.cst8288.finalproject.dao.InventoryManagerDAO;
import org.cst8288.finalproject.dto.Item;

/**
 * Validator class for FoodItem objects, providing methods to validate various attributes of a food item.
 * This class includes methods to validate the food item's ID, name, quantity, expiration date, price, and retailer name. 
 * Additionally, it interfaces with the InventoryManagerDAO to validate whether a food item is listed as surplus in the database.
 * The class offers a comprehensive validation method that validates all attributes of a FoodItem object, 
 * ensuring data integrity and the processing or persistence of only valid data.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 04-05-2024
 * @see org.cst8288.finalproject.dto.Item
 * @see org.cst8288.finalproject.dto.FoodItem
 * @see org.cst8288.finalproject.dao.InventoryManagerDAO
 */
public class FoodItemValidator {
	
	private InventoryManagerDAO inventoryDAO;

    /**
     * Constructs a new FoodItemValidator.
     */
    public FoodItemValidator(InventoryManagerDAO inventoryDAO) 
    {
       this.inventoryDAO = inventoryDAO;
    }

    /**
     * Validates the ID of a food item.
     *
     * @param foodItemId The food item's ID as a String.
     * @return true if the ID is a numeric value, false otherwise.
     */
    public boolean validateFoodItemId(String foodItemId) 
    {
        String regex = "\\d+"; 
        return foodItemId.matches(regex);
    }

    /**
     * Validates the name of a food item.
     *
     * @param itemName The name of the food item.
     * @return true if the name contains only alphanumeric characters, spaces, or special characters (! and &), false otherwise.
     */
    public boolean validateItemName(String itemName) 
    {
        String regex = "^[a-zA-Z0-9!&]*$";
        return itemName.matches(regex);
    }

    /**
     * Validates the quantity of a food item.
     *
     * @param quantity The quantity of the food item.
     * @return true if the quantity is greater than 0, false otherwise.
     */
    public boolean validateQuantity(int quantity) 
    {
        return quantity > 0;
    }

    /**
     * Validates the expiration date of a food item.
     *
     * @param date The expiration date in MM-dd-yyyy format.
     * @return true if the date is a valid date in the specified format, false otherwise.
     */
    public boolean validateExperationDate(String date) 
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        dateFormat.setLenient(false); // This ensures strict adherence to the pattern.
        try 
        {
            dateFormat.parse(date);
            return true;
        }
        catch (ParseException e) 
        {
            return false;
        }
    }

    /**
     * Validates the price of a food item.
     *
     * @param price The price of the food item.
     * @return true if the price is non-negative, false otherwise.
     */
    public boolean validatePrice(double price) 
    {
        return price >= 0;
    }

    /**
     * Validates the surplus status of a food item by checking if it's listed as surplus in the database.
     *
     * @param itemID The ID of the food item to be validated for surplus status.
     * @return true if the item is listed as surplus in the database, false otherwise.
     */
    public boolean validateSurplus(int itemID) 
    {
        try 
        {
            return inventoryDAO.isItemSurplus(itemID);
        }
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        return false;
    }

    /**
     * Validates the retailer name.
     *
     * @param retailerName The name of the retailer.
     * @return true if the name contains only alphanumeric characters, spaces, or special characters (! and &), false otherwise.
     */
    public boolean validateRetailer(String retailerName) 
    {
        String regex = "^[a-zA-Z0-9!&]+$"; 
        return retailerName.matches(regex);
    }

    /**
     * Validates all attributes of a FoodItem object.
     *
     * @param foodItem The FoodItem object to be validated.
     * @return true if all attributes of the food item are valid, false otherwise.
     */
    public boolean validateFoodItem(Item foodItem) 
    {
        if (foodItem == null) 
            return false; // Return false if the FoodItem object is null

        if (!validateItemName(foodItem.getItemName())) 
            return false; // Invalid Item Name

        if (!validateQuantity(foodItem.getQuantity())) 
            return false; // Invalid Quantity

        if (!validateExperationDate(foodItem.getExpirationDate().toString())) 
            return false; // Invalid Expiration Date

        if (!validatePrice(foodItem.getPrice())) 
            return false; // Invalid Price

        if (!validateSurplus(foodItem.getItemID())) 
            return false; // Invalid Surplus

        if (!validateRetailer(foodItem.getRetailerID())) 
            return false; // Invalid Retailer Name

        return true; // All validations passed
    }
}