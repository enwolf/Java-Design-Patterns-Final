package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

/**
 * Represents a generic user within the system. This abstract class provides a framework for defining user attributes
 * and is extended by specific types of users who may require additional fields and behaviors.
 *
 * Each user in the system is identified uniquely by a userID and has basic attributes such as name, email, and userType,
 * which dictate additional permissions and functionalities available to the user within the application.
 * See also derived classes and UserType enum for understanding specific user types and permissions.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see UserType  
 */
public abstract class AbstractUser {

    protected int userID;
    protected String userFirstName;
    protected String userLastName;
    protected String emailAddress;
    protected UserType userType;

    /**
     * Gets the unique ID for the user.
     * @return the user's unique ID
     */
    public abstract int getUserId();

    /**
     * Gets the user's first name.
     * @return the user's first name as a String
     */
    public abstract String getUserFirstName();

    /**
     * Gets the user's last name.
     * @return the user's last name as a String
     */
    public abstract String getUserLastName();

    /**
     * Gets the user's email address.
     * @return the user's email address as a String
     */
    public abstract String getEmailAddress();

    /**
     * Gets the user type, indicating the user's role within the system.
     * @return the user's type as a UserType enum
     */
    public abstract UserType getUserType();

    /**
     * Sets the user's unique ID.
     * @param userId the new unique ID for the user
     */
    public abstract void setUserId(int userId);

    /**
     * Sets the user's first name.
     * @param userFirstName the new first name for the user
     */
    public abstract void setUserFirstName(String userFirstName);

    /**
     * Sets the user's last name.
     * @param userLastName the new last name for the user
     */
    public abstract void setUserLastName(String userLastName);

    /**
     * Sets the user's email address.
     * @param emailAddress the new email address for the user
     */
    public abstract void setEmailAddress(String emailAddress);

    /**
     * Sets the user type, defining the user's role and permissions within the system.
     * @param userType the new type for the user as a UserType enum
     */
    public abstract void setUserType(UserType userType);

}
