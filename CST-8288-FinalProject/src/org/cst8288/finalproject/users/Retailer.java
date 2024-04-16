package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

public class Retailer extends AbstractUser {
    
	private int retailerID;
    private String storeName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;

    //Getters
    
    @Override
    public int getUserId() 
    {
        return super.userID;
    }

    @Override
    public String getUserFirstName() 
    {
        return super.userFirstName;
    }

    @Override
    public String getUserLastName() 
    {
        return super.userLastName;
    }

    @Override
    public String getEmailAddress() 
    {
        return super.emailAddress;
    }

    @Override
    public UserType getUserType() 
    {
        return super.userType;
    }

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
    
    @Override
    public void setUserId(int userId) 
    {
        super.userID = userId;
    }

    @Override
    public void setUserFirstName(String userFirstName) 
    {
        super.userFirstName = userFirstName;
    }

    @Override
    public void setUserLastName(String userLastName) 
    {
        super.userLastName = userLastName;
    }

    @Override
    public void setEmailAddress(String emailAddress) 
    {
        super.emailAddress = emailAddress;
    }

    @Override
    public void setUserType(UserType userType) 
    {
        super.userType = userType;
    }

    public void setRetailerID(int retailerID) 
    {
        this.retailerID = retailerID;
    }

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
