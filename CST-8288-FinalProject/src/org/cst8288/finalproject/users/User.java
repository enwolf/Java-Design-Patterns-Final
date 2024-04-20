package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

/**
 * Represents a user in the system with basic credentials and identification details. This class
 * stores common information such as name, email, and password, which are essential for authentication
 * and identification processes across various parts of the application.
 * <p>
 * The {@link User} class is designed to be extended by other specific types of users (like {@link Retailer},
 * {@link Consumer}, and {@link CharitableOrganization}) that inherit these basic properties and potentially
 * include more specific attributes related to their particular context.
 *
 * @author    Robin Phillis
 * @version   1.0
 * @since     2024-04-20
 * @see AbstractUser
 * @see UserType
 */
public class User extends AbstractUser {

    private String password;  // User's password for login verification

    /**
     * Default constructor for creating a new User instance with no initial details.
     */
    public User() {
    }
    
    /**
     * Constructs a new User instance with specified details, initializing all class fields.
     * @param userFirstName The first name of the user
     * @param userLastName The last name of the user
     * @param emailAddress The email address of the user
     * @param password The password for the user's account
     * @param userType The type of the user as defined in {@link UserType}
     */
    public User(String userFirstName, String userLastName, String emailAddress, String password, UserType userType) {
        super.userFirstName = userFirstName;
        super.userLastName = userLastName;
        super.emailAddress = emailAddress;
        this.password = password;
        super.userType = userType;
    }

    /**
     * Returns the unique user ID.
     * @return the user's ID as an integer
     */
    @Override
    public int getUserId() 
    {
        return super.userID;
    }

    /**
     * Returns the user's first name.
     * @return the first name as a String
     */
    @Override
    public String getUserFirstName() 
    {
        return super.userFirstName;
    }

    /**
     * Returns the user's last name.
     * @return the last name as a String
     */
    @Override
    public String getUserLastName() 
    {
        return super.userLastName;
    }

    /**
     * Returns the user's email address.
     * @return the email address as a String
     */
    @Override
    public String getEmailAddress() 
    {
        return super.emailAddress;
    }

    /**
     * Returns the type of the user.
     * @return the user type as an enum value of {@link UserType}
     */
    @Override
    public UserType getUserType() 
    {
        return super.userType;
    }

    /**
     * Returns the user's password. This method is used for login and authentication purposes.
     * @return the password as a String
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * Sets the user ID. This is typically used when retrieving user details from a database.
     * @param userId the new user ID
     */
    @Override
    public void setUserId(int userId) 
    {
        super.userID = userId;
    }

    /**
     * Sets the user's first name.
     * @param userFirstName the new first name
     */
    @Override
    public void setUserFirstName(String userFirstName) 
    {
        super.userFirstName = userFirstName;
    }

    /**
     * Sets the user's last name.
     * @param userLastName the new last name
     */
    @Override
    public void setUserLastName(String userLastName) 
    {
        super.userLastName = userLastName;
    }

    /**
     * Sets the user's email address. This is critical for login and communication purposes.
     * @param emailAddress the new email address
     */
    @Override
    public void setEmailAddress(String emailAddress) 
    {
        super.emailAddress = emailAddress;
    }

    /**
     * Sets the user's type. This determines the permissions and capabilities within the system.
     * @param userType the new user type
     */
    @Override
    public void setUserType(UserType userType) 
    {
        super.userType = userType;
    }

    /**
     * Sets a new password for the user. This is essential for maintaining account security.
     * @param password the new password
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }

    /**
     * Provides a string representation of the user object for debugging and logging purposes.
     * @return a string describing the user
     */
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