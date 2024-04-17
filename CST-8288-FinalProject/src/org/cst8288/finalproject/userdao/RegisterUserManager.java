package org.cst8288.finalproject.userdao;

import org.cst8288.finalproject.users.User;
import org.cst8288.finalproject.validator.UserValidator;

public class RegisterUserManager {
    
	private ManageUserDAOInterface manageUserDAO;
    private UserValidator userValidator;

    public RegisterUserManager(ManageUserDAOInterface manageUserDAO, UserValidator userValidator) {
        
    	this.manageUserDAO = manageUserDAO;
        this.userValidator = userValidator;
    }

    public void registerUser(User user) 
    {

        if (userValidator.validateUser(user)) 
        {

            manageUserDAO.addUser(user);
            System.out.println("User registered successfully.");
        }
        else 
        {
            System.out.println("User registration failed. Please check the input data.");
        }
    }
}
