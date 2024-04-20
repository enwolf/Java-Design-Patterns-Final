package org.cst8288.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Represents a food item in the inventory system. This class extends the abstract {@link Item} class
 * and includes additional attributes specific to food items such as expiration date, discount rate, and discount amount.
 * It provides getters and setters for managing these attributes effectively.
 *
 * This class is used throughout the system wherever food item data is required. It is part of the Data Transfer Object (DTO)
 * pattern and is used for transferring data between layers of the application, particularly between the DAO classes
 * and the business logic layers.
 *
 * Each food item is associated with a retailer and can have a discount applied to it which can be either a rate or a direct amount.
 * The class also includes methods for printing the food item's details in a string format which is useful for logging and debugging purposes.
 *
 * @author Robin Phillis
 * @version 2.0
 * @since 2024-04-19
 * @see org.cst8288.finalproject.dto.Item
 */
public class FoodItem extends Item
{

	 /**
     * Constructs a new FoodItem object.
     */
    public FoodItem() {

    }

    /**
     * Retrieves the item ID.
     *
     * @return The item ID.
     */
    @Override
    public int getItemID() {
        return super.itemID;
    }

    /**
     * Retrieves the retailer ID.
     *
     * @return The retailer ID.
     */
    @Override
    public int getRetailerID() {
        return super.retailerID;
    }

    /**
     * Retrieves the item name.
     *
     * @return The item name.
     */
    @Override
    public String getItemName() {
        return super.itemName;
    }

    /**
     * Retrieves the quantity of the item.
     *
     * @return The quantity of the item.
     */
    @Override
    public int getQuantity() {
        return super.quantity;
    }

    /**
     * Retrieves the expiration date of the item.
     *
     * @return The expiration date of the item.
     */
    @Override
    public Date getExpirationDate() {
        return super.expirationDate;
    }

    /**
     * Retrieves the price of the item.
     *
     * @return The price of the item.
     */
    @Override
    public double getPrice() {
        return super.price;
    }

    /**
     * Retrieves the discount rate of the item.
     *
     * @return The discount rate of the item.
     */
    @Override
    public BigDecimal getDiscountRate() {
        return super.discountRate;
    }

    /**
     * Retrieves the discount amount of the item.
     *
     * @return The discount amount of the item.
     */
    @Override
    public BigDecimal getDiscountAmount() {
        return super.discountAmount;
    }

    /**
     * Sets the item ID.
     *
     * @param itemID The item ID to set.
     */
    @Override
    public void setItemID(int itemID) {
        super.itemID = itemID;
    }

    /**
     * Sets the retailer ID.
     *
     * @param retailerID The retailer ID to set.
     */
    @Override
    public void setRetailerID(int retailerID) {
        super.retailerID = retailerID;
    }

    /**
     * Sets the item name.
     *
     * @param itemName The item name to set.
     */
    @Override
    public void setItemName(String itemName) {
        super.itemName = itemName;
    }

    /**
     * Sets the quantity of the item.
     *
     * @param quantity The quantity to set.
     */
    @Override
    public void setQuantity(int quantity) {
        super.quantity = quantity;
    }

    /**
     * Sets the expiration date of the item.
     *
     * @param expirationDate The expiration date to set.
     */
    @Override
    public void setExpirationDate(Date expirationDate) {
        super.expirationDate = expirationDate;
    }

    /**
     * Sets the price of the item.
     *
     * @param price The price to set.
     */
    @Override
    public void setPrice(double price) {
        super.price = price;
    }

    /**
     * Sets the discount rate of the item.
     *
     * @param discountRate The discount rate to set.
     */
    @Override
    public void setDiscountRate(BigDecimal discountRate) {
        super.discountRate = discountRate;
    }

    /**
     * Sets the discount amount of the item.
     *
     * @param discountAmount The discount amount to set.
     */
    @Override
    public void setDiscountAmount(BigDecimal discountAmount) {
        super.discountAmount = discountAmount;
    }

    /**
     * Overrides the toString method to provide a string representation of the FoodItem object.
     *
     * @return A string representation of the FoodItem object.
     */
    @Override
    public String toString() {
        return "FoodItem{" +
                "itemID=" + getItemID() +
                ", retailerID=" + getRetailerID() +
                ", itemName='" + getItemName() + '\'' +
                ", quantity=" + getQuantity() +
                ", expirationDate=" + getExpirationDate() +
                ", price=" + getPrice() +
                ", discountRate=" + getDiscountRate() +
                ", discountAmount=" + getDiscountAmount() +
                '}';
    }
}



