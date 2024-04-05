package org.cst8288.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Date;

public abstract class Item {
	
    protected int itemID;
    protected String retailerID;
    protected String itemName;
    protected int quantity;
    protected Date expirationDate;
    protected double price;
    protected BigDecimal discountRate;
    protected BigDecimal discountAmount;
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
	 * @return the getDiscountRate
	 */
	public abstract BigDecimal getDiscountRate();
	
	/**
	 * @return the getDiscountAmount
	 */
	public abstract BigDecimal getDiscountAmount();

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
	 * @param price the setDiscountRate to set
	 */
	public abstract void setDiscountRate(BigDecimal discountRate);
	
	/**
	 * @param price the setDiscountAmount to set
	 */
	public abstract void setDiscountAmount(BigDecimal discountAmount);

	/**
	 * @param isSurplus the isSurplus to set
	 */
	public abstract void setSurplus(boolean isSurplus);
	
}
