package org.cst8288.finalproject.users;

public class UserInterface {
    private int userID;
    private String userFirstName;
    private String userLastName;
    private String emailAddress;
    private UserType userType;

    public enum UserType {
        CONSUMER,
        RETAILER,
        CHARITABLE_ORGANIZATION
    }

    // Constructor
    public UserInterface(int userID, String userFirstName, String userLastName, String emailAddress, UserType userType) {
        this.userID = userID;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.emailAddress = emailAddress;
        this.userType = userType;
    }

    // Getters and Setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "UserInterface{" +
                "userID=" + userID +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userType=" + userType +
                '}';
    }


}
