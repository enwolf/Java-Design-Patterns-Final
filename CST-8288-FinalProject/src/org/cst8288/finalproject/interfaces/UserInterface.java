package org.cst8288.finalproject.interfaces;

import org.cst8288.finalproject.enums.UserType;

public interface UserInterface {
	
//	    private int userId;
//	    private String userFirstName;
//	    private String userLastName;
//	    private String emailAddress;
//	    private Enum userType;

    public int getUserId();
    public String getUserFirstName();
    public String getUserLastName();
    public String getEmailAddress();
    public UserType getUserType();

    public void setUserId(int userId);
    public void setUserFirstName(String userFirstName);
    public void setUserLastName(String userLastName);
    public void setEmailAddress(String emailAddress);
    public void setUserType(UserType userType);
	    
}
