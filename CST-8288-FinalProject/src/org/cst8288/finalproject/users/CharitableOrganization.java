package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

public class CharitableOrganization extends AbstractUser {

    private int organizationID;
    private String organizationName;
    private String city;
    private String province;
    private String postalCode;
    private String country;

    // Getters
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
    
    public int getOrganizationID() 
    {
        return organizationID;
    }

    public String getOrganizationName() 
    {
        return organizationName;
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

    public String getCountry() 
    {
        return country;
    }

    // Setters
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
    public void setOrganizationID(int organizationID) 
    {
        this.organizationID = organizationID;
    }

    public void setOrganizationName(String organizationName) 
    {
        this.organizationName = organizationName;
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

    public void setCountry(String country) 
    {
        this.country = country;
    }

}
