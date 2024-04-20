package org.cst8288.finalproject.users;

import org.cst8288.finalproject.enums.UserType;

/**
 * An abstract representation of a user template within the system. This class is designed to hold
 * common information applicable across various user types defined in the application, particularly
 * focusing on the user type itself.
 * 
 * This class can be extended by more specific user classes who might need to implement additional
 * attributes or methods specific to their functionality. The {@link UserType} is maintained here
 * to ensure that all user objects can be treated polymorphically when handling user type-based operations.
 *
 * @author    Robin Phillis
 * @version   1.0
 * @since     2024-04-13
 * @see       UserType  
 */
public abstract class UserTemp {

    /**
     * Stores the type of the user, encapsulated as an enum {@link UserType}. This attribute
     * helps in managing user-specific actions and permissions throughout the application.
     */
    private UserType type;

    /**
     * Gets the user type of this user object.
     * 
     * This method provides read access to the {@code type} field, allowing for decisions and
     * UI adjustments based on the user type, among other things.
     *
     * @return the user type as a {@link UserType} enum
     */
    public UserType getType() {
        return type;
    }

    /**
     * Sets the user type of this user object.
     *
     * This method provides write access to the {@code type} field, enabling dynamic changes to
     * the user's type. It is particularly useful during user creation and editing processes where
     * the user type might need to be adjusted based on business rules.
     * 
     * @param type the new user type as a {@link UserType} enum
     */
    public void setType(UserType type) {
        this.type = type;
    }
}
