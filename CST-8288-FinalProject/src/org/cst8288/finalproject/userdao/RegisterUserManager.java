package org.cst8288.finalproject.userdao;

import org.cst8288.finalproject.users.UserInterface;
import org.cst8288.finalproject.validator.UserValidator;

public class RegisterUserManager {
    private ManageUserDAO manageUserDAO;
    private UserValidator userValidator;

    public RegisterUserManager() {
        this.manageUserDAO = new ManageUserDAO();
        this.userValidator = new UserValidator();
    }

    public void registerUser(int userID, String userFirstName, String userLastName, String emailAddress, UserInterface.UserType userType) {
        if (userValidator.validateUserID(userID) &&
            userValidator.validateUserFirstName(userFirstName) &&
            userValidator.validateUserLastName(userLastName) &&
            userValidator.validateUserEmailAddress(emailAddress) &&
            userValidator.validateUserType(userType)) {
            UserInterface user = new UserInterface(userID, userFirstName, userLastName, emailAddress, userType);
            manageUserDAO.addUser(user);
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User registration failed. Please check the input data.");
        }
    }

    
}
