package org.cst8288.finalproject.users;

import java.util.regex.Pattern;

public class UserValidator {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static boolean validateUserID(int userID) {
        return userID > 0; 
    }

    public static boolean validateUserFirstName(String userFirstName) {
        return userFirstName != null && !userFirstName.isEmpty();
    }

    public static boolean validateUserLastName(String userLastName) {
        return userLastName != null && !userLastName.isEmpty();
    }

    public static boolean validateUserEmailAddress(String emailAddress) {
        if (emailAddress == null || emailAddress.isEmpty()) {
            return false;
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(emailAddress).matches();
    }

    public static boolean validateUserType(UserInterface.UserType userType) {
        return userType != null;
    }

    public static boolean validateUser(UserInterface user) {
        return validateUserID(user.getUserID()) &&
                validateUserFirstName(user.getUserFirstName()) &&
                validateUserLastName(user.getUserLastName()) &&
                validateUserEmailAddress(user.getEmailAddress()) &&
                validateUserType(user.getUserType());
    }
}