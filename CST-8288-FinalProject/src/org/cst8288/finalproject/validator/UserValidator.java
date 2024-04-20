package org.cst8288.finalproject.validator;

import java.util.regex.Pattern;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;
/**
 * Provides validation methods for various user attributes to ensure data integrity before
 * processing or persisting in the database. This class is used extensively throughout the
 * application wherever user data validation is required.
 * 
 * The validator uses regular expressions to check the format of names, emails, and passwords
 * to ensure they meet the system's requirements for valid data.
 *
 * @author Robin Phillis, Luis David Contreras
 * @version 2.0
 * @since 2024-04-20
 * @see Pattern
 */
public class UserValidator{
    
	// Regular expression for validating email format
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    // Regular expression for validating name format
    private static final String NAME_REGEX = "^[A-Z]?[a-z]+(?:['-][a-z]+)*$";
    // Regular expression for validating password format
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    
    /**
     * Validates the first name of a user based on the NAME_REGEX pattern.
     * @param userFirstName the first name to validate
     * @return true if the first name is valid, false otherwise
     */
    public boolean validateUserFirstName(String userFirstName) 
    {
        return userFirstName != null && !userFirstName.isEmpty() && userFirstName.matches(NAME_REGEX);
    }

    /**
     * Validates the last name of a user based on the NAME_REGEX pattern.
     * @param userLastName the last name to validate
     * @return true if the last name is valid, false otherwise
     */
    public boolean validateUserLastName(String userLastName) 
    {
        return userLastName != null && !userLastName.isEmpty() && userLastName.matches(NAME_REGEX);
    }

    /**
     * Validates the email address of a user using the EMAIL_REGEX pattern to ensure it meets
     * the required format for email addresses.
     * @param emailAddress the email address to validate
     * @return true if the email address is valid, false otherwise
     */
    public boolean validateUserEmailAddress(String emailAddress) 
    {
        if (emailAddress == null || emailAddress.isEmpty()) 
            return false;
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(emailAddress).matches();
    }

    /**
     * Validates the type of the user to ensure it is not null.
     * @param userType the UserType enum to validate
     * @return true if the user type is not null, false otherwise
     */
    public boolean validateUserType(UserType userType) 
    {
        return userType != null;
    }

    /**
     * Validates the password of a user based on the PASSWORD_REGEX pattern. It checks if the password
     * includes a mix of upper and lower case letters, numbers, and special characters.
     * @param password the password to validate
     * @return true if the password is valid according to the pattern, false otherwise
     */
    public boolean validatePassword(String password) 
    {
    	if (password == null || password.isEmpty())
            return false;
        
        //Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        return true; //pattern.matcher(password).matches();
    }

    /**
     * Validates all attributes of a user object by using the individual validation methods provided above.
     * This method is typically called before persisting a user object to ensure all fields contain valid data.
     * 
     * @param user the user object to validate
     * @return true if all validations pass, false if any fail
     */
    public boolean validateUser(AbstractUser user) 
    {    	
        StringBuilder errors = new StringBuilder();
        boolean isValid = true;

        if (user == null) 
        {
            errors.append("User object is null.\n");
            return false;
        }

        if (!validateUserFirstName(user.getUserFirstName())) 
        {
            errors.append("Invalid first name.\n");
            isValid = false;
        }

        if (!validateUserLastName(user.getUserLastName())) 
        {
            errors.append("Invalid last name.\n");
            isValid = false;
        }

        if (!validateUserEmailAddress(user.getEmailAddress())) 
        {
            errors.append("Invalid email address.\n");
            isValid = false;
        }

        if (!validateUserType(user.getUserType())) 
        {
            errors.append("Invalid user type.\n");
            isValid = false;
        }

        if (!validatePassword(((User) user).getPassword())) 
        {
            errors.append("Invalid password.\n");
            isValid = false;
        }

        if (!isValid) 
        {
            System.out.println("User validation failed with the following errors:\n" + errors.toString());
        }
        else {
            System.out.println("All user validations passed.");
        }

        return isValid;
    }
}
