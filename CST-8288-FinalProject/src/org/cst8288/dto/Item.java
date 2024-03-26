package org.cst8288.dto;

import java.util.Date;

public abstract class Item {
	
	private int itemID;
	private String retailerID;
	private String itemName;
	private int quantity;
	private Date expirationDate;
	private double price;
	private boolean isSurplus;

	public Item() 
	{
		
	}
	
	
	/**
	 * @return the itemID
	 */
	public int getItemID() 
	{
		return itemID;
	}

	
	/**
	 * @return the retailerID
	 */
	public String getRetailerID() 
	{
		return retailerID;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName() 
	{
		return itemName;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() 
	{
		return quantity;
	}

	/**
	 * @return the expirationDate
	 */
	public Date getExpirationDate() 
	{
		return expirationDate;
	}

	/**
	 * @return the price
	 */
	public double getPrice() 
	{
		return price;
	}

	/**
	 * @return the isSurplus
	 */
	public boolean isSurplus() 
	{
		return isSurplus;
	}

	/**
	 * @param itemID the itemID to set
	 */
	public void setItemID(int itemID) 
	{
		this.itemID = itemID;
	}

	/**
	 * @param retailerID the retailerID to set
	 */
	public void setRetailerID(String retailerID) 
	{
		this.retailerID = retailerID;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) 
	{
		this.itemName = itemName;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

	/**
	 * @param expirationDate the expirationDate to set
	 */
	public void setExpirationDate(Date expirationDate) 
	{
		this.expirationDate = expirationDate;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @param isSurplus the isSurplus to set
	 */
	public void setSurplus(boolean isSurplus) 
	{
		this.isSurplus = isSurplus;
	}
	
}
