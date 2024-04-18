package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

public class User extends AbstractUser {

    private String password;  

   
    //Constructor's
    public User() 
    {
    	
    }
    
    public User(String userFirstName, String userLastName, String emailAddress, String password, UserType userType) 
    {
        super.userFirstName = userFirstName;
        super.userLastName = userLastName;
        super.emailAddress = emailAddress;
        this.password = password;
        super.userType = userType;
    }

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

    public String getPassword() 
    {
        return password;
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

    public void setPassword(String password) 
    {
        this.password = password;
    }

    // ToString Method
    @Override    
    public String toString() 
    {    
        return "User{" +
               "userID=" + super.userID +
               ", userFirstName='" + super.userFirstName + '\'' +
               ", userLastName='" + super.userLastName + '\'' +
               ", emailAddress='" + super.emailAddress + '\'' +
               ", password='" + password + '\'' + 
               ", userType=" + super.userType +
               '}';
    }
}
