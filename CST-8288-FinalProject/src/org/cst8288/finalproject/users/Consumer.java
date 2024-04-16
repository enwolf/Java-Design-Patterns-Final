package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

public class Consumer extends AbstractUser {

    private int consumerID;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;
        
    
    @Override
    public int getUserId() 
    {
        return super.userID;  
    }

    @Override
    public UserType getUserType() 
    {
        return super.userType;  
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
    
    public int getConsumerID() 
    {
        return consumerID;
    }
    
    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    
    public String getStreetAddress() 
    {
        return streetAddress;
    }
    
    public String getProvince() 
    {
        return province;
    }
    
    public String getCity() 
    {
        return city;
    }
    
    public String getPostalCode() 
    {
        return postalCode;
    }

    @Override
    public void setUserId(int userId) 
    {
        super.userID = userId;  
    }

    @Override
    public void setUserType(UserType userType) 
    {
        super.userType = userType; 
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

    public void setConsumerID(int consumerID) 
    {
        this.consumerID = consumerID;
    }

    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
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
