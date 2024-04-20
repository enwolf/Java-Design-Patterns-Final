package org.cst8288.finalproject.users;

public class Retailer extends User {
    
	private int retailerID;
    private String storeName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;

    //Getters
    public int getRetailerID() 
    {
        return retailerID;
    }

    public String getStoreName() 
    {
        return storeName;
    }

    public String getStreetAddress() 
    {
        return streetAddress;
    }

    public String getCity() 
    {
        return city;
    }

    public String getProvince() 
    {
        return province;
    }

    public String getPostalCode() 
    {
        return postalCode;
    }

    //Setters
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public void setStreetAddress(String streetAddress) 
    {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city) 
    {
        this.city = city;
    }

    public void setProvince(String province) 
    {
        this.province = province;
    }

    public void setPostalCode(String postalCode) 
    {
        this.postalCode = postalCode;
    }
}
