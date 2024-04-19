package org.cst8288.finalproject.validator;

import java.util.regex.Pattern;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;

public class UserValidator{
    
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final String NAME_REGEX = "^[A-Z]?[a-z]+(?:['-][a-z]+)*$";
    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    
    public boolean validateUserFirstName(String userFirstName) 
    {
        return userFirstName != null && !userFirstName.isEmpty() && userFirstName.matches(NAME_REGEX);
    }

    public boolean validateUserLastName(String userLastName) 
    {
        return userLastName != null && !userLastName.isEmpty() && userLastName.matches(NAME_REGEX);
    }

    public boolean validateUserEmailAddress(String emailAddress) 
    {
        if (emailAddress == null || emailAddress.isEmpty()) 
            return false;
        
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(emailAddress).matches();
    }

    public boolean validateUserType(UserType userType) 
    {
        return userType != null;
    }

    public boolean validatePassword(String password) 
    {
    	if (password == null || password.isEmpty())
            return false;
        
        //Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        return true; //pattern.matcher(password).matches();
    }

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
