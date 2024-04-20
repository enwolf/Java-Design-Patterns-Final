package org.cst8288.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Abstract class representing the generic structure of an item in the inventory system.
 * This class serves as a base class for various types of items that can be managed within the system.
 * It defines common properties such as item ID, retailer ID, item name, quantity, expiration date,
 * price, discount rate, and discount amount which are essential for inventory management.
 *
 * The class provides abstract methods to enforce implementation of basic getters and setters in derived classes,
 * ensuring that all item types have a consistent interface for accessing and modifying their properties.
 *
 * This design allows for easy extension of the inventory system to include different types of items without
 * modifying the core functionality related to inventory management.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see org.cst8288.finalproject.dto.FoodItem 
 */
public abstract class Item {
	
    protected int itemID;
    protected int retailerID;
    protected String itemName;
    protected int quantity;
    protected Date expirationDate;
    protected double price;
    protected BigDecimal discountRate;
    protected BigDecimal discountAmount;
    protected boolean isSurplus;


    /**
     * Constructs a new item.
     */
    public Item() {
        
    }

    /**
     * Retrieves the item's ID.
     *
     * @return The item's ID.
     */
    public abstract int getItemID();

    /**
     * Retrieves the retailer's ID associated with the item.
     *
     * @return The retailer's ID.
     */
    public abstract int getRetailerID();

    /**
     * Retrieves the name of the item.
     *
     * @return The name of the item.
     */
    public abstract String getItemName();

    /**
     * Retrieves the quantity of the item.
     *
     * @return The quantity of the item.
     */
    public abstract int getQuantity();

    /**
     * Retrieves the expiration date of the item.
     *
     * @return The expiration date of the item.
     */
    public abstract Date getExpirationDate();

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    public abstract double getPrice();
    
    /**
     * Retrieves the discount rate of the item.
     *
     * @return The discount rate of the item.
     */
    public abstract BigDecimal getDiscountRate();
    
    /**
     * Retrieves the discount amount of the item.
     *
     * @return The discount amount of the item.
     */
    public abstract BigDecimal getDiscountAmount();

    /**
     * Sets the item's ID.
     *
     * @param itemID The ID to set.
     */
    public abstract void setItemID(int itemID);

    /**
     * Sets the retailer's ID associated with the item.
     *
     * @param retailerID The retailer's ID to set.
     */
    public abstract void setRetailerID(int retailerID);

    /**
     * Sets the name of the item.
     *
     * @param itemName The name to set.
     */
    public abstract void setItemName(String itemName);

    /**
     * Sets the quantity of the item.
     *
     * @param quantity The quantity to set.
     */
    public abstract void setQuantity(int quantity);

    /**
     * Sets the expiration date of the item.
     *
     * @param expirationDate The expiration date to set.
     */
    public abstract void setExpirationDate(Date expirationDate);
    
    /**
     * Sets the price of the item.
     *
     * @param price The price to set.
     */
    public abstract void setPrice(double price);
    
    /**
     * Sets the discount rate of the item.
     *
     * @param discountRate The discount rate to set.
     */
    public abstract void setDiscountRate(BigDecimal discountRate);
    
    /**
     * Sets the discount amount of the item.
     *
     * @param discountAmount The discount amount to set.
     */
    public abstract void setDiscountAmount(BigDecimal discountAmount);
}