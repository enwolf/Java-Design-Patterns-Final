package org.cst8288.dto;

import java.util.Date;

public abstract class Item {
	
    protected int itemID;
    protected String retailerID;
    protected String itemName;
    protected int quantity;
    protected Date expirationDate;
    protected double price;
    protected boolean isSurplus;

	public Item() 
	{
		
	}
	
	
	/**
	 * @return the itemID
	 */
	public abstract int getItemID();

	
	/**
	 * @return the retailerID
	 */
	public abstract String getRetailerID();

	/**
	 * @return the itemName
	 */
	public abstract String getItemName();

	/**
	 * @return the quantity
	 */
	public abstract int getQuantity();

	/**
	 * @return the expirationDate
	 */
	public abstract Date getExpirationDate();

	/**
	 * @return the price
	 */
	public abstract double getPrice();

	/**
	 * @return the isSurplus
	 */
	public abstract boolean isSurplus();

	/**
	 * @param itemID the itemID to set
	 */
	public abstract void setItemID(int itemID);

	/**
	 * @param retailerID the retailerID to set
	 */
	public abstract void setRetailerID(String retailerID);

	/**
	 * @param itemName the itemName to set
	 */
	public abstract void setItemName(String itemName);

	/**
	 * @param quantity the quantity to set
	 */
	public abstract void setQuantity(int quantity);

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public abstract void setExpirationDate(Date expirationDate);
	/**
	 * @param price the price to set
	 */
	public abstract void setPrice(double price);

	/**
	 * @param isSurplus the isSurplus to set
	 */
	public abstract void setSurplus(boolean isSurplus);
	
}
