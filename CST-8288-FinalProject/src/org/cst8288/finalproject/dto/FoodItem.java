package org.cst8288.finalproject.dto;

import java.math.BigDecimal;
import java.sql.Date;

public class FoodItem extends Item
{

	 // Constructor
    public FoodItem() 
    {

    }

    @Override
    public int getItemID() 
    {
        return super.itemID;
    }

    @Override
    public int getRetailerID() 
    {
        return super.retailerID;
    }

    @Override
    public String getItemName() 
    {
        return super.itemName;
    }

    @Override
    public int getQuantity() 
    {
        return super.quantity;
    }

    @Override
    public Date getExpirationDate() 
    {
        return super.expirationDate;
    }

    @Override
    public double getPrice() 
    {
        return super.price;
    }
    
	@Override
	public BigDecimal getDiscountRate() 
	{
	 
		return super.discountRate;
	}

	@Override
	public BigDecimal getDiscountAmount() 
	{
	
		return super.discountAmount;
	}

    @Override
    public void setItemID(int itemID) 
    {
        super.itemID = itemID;
    }

    @Override
    public void setRetailerID(int retailerID) 
    {
        super.retailerID = retailerID;
    }

    @Override
    public void setItemName(String itemName) 
    {
        super.itemName = itemName;
    }

    @Override
    public void setQuantity(int quantity) 
    {
        super.quantity = quantity;
    }

    @Override
    public void setExpirationDate(Date expirationDate) 
    {
        super.expirationDate = expirationDate;
    }

    @Override
    public void setPrice(double price) 
    {
        super.price = price;
    }
    
	@Override
	public void setDiscountRate(BigDecimal discountRate) 
	{
		super.discountRate = discountRate;
	}

	@Override
	public void setDiscountAmount(BigDecimal discountAmount) 
	{
		super.discountAmount = discountAmount;
			
	}

    // Override toString method
    @Override
    public String toString() 
    {
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



