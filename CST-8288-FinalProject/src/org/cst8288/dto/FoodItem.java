package org.cst8288.dto;

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
    public String getRetailerID() 
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
    public boolean isSurplus() 
    {
        return super.isSurplus;
    }

    @Override
    public void setItemID(int itemID) 
    {
        super.itemID = itemID;
    }

    @Override
    public void setRetailerID(String retailerID) 
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
    public void setSurplus(boolean isSurplus) 
    {
        super.isSurplus = isSurplus;
    }
}



