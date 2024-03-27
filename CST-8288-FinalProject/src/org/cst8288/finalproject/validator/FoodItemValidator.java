package org.cst8288.finalproject.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.cst8288.finalporject.dto.Item;

public class FoodItemValidator 
{
	
	public FoodItemValidator() 
	{
		
	}

	public boolean validateFoodItemId(String foodItemId) 
	{
	    String regex = "\\d+"; // Regex pattern to match one or more digits
	    return foodItemId.matches(regex);
	}
	
	public boolean validateItemName(String itemName) 
	{
	    String regex = "^[a-zA-Z0-9!&]*$"; 
	    return itemName.matches(regex);
	}
	
	public boolean validateQuantity(int quantity) 
	{
        return quantity > 0;
    }

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
    
    public boolean validatePrice(double price) 
    {
        return price >= 0;
    }
    
    public boolean validateSurplus(boolean isSurplus) 
    {
        return true; 
    }
    
    public boolean validateRetailer(String retailerName) 
    {
        String regex = "^[a-zA-Z0-9!&]+$"; 
        return retailerName.matches(regex);
    }
        
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
                
        if (!validateSurplus(foodItem.isSurplus())) 
           return false; // Invalid Surplus
                
        if (!validateRetailer(foodItem.getRetailerID())) 
           return false; // Invalid Retailer Name
        
        return true; // All validations passed
    }
	
}
