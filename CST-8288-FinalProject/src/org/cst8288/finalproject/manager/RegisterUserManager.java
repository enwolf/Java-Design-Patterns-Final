package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.interfaces.ManageUserDAOInterface;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.validator.UserValidator;

public class RegisterUserManager {
    
    private ManageUserDAOInterface manageUserDAO;
    private UserValidator userValidator;

    public RegisterUserManager(ManageUserDAOInterface manageUserDAO, UserValidator userValidator) {
        this.manageUserDAO = manageUserDAO;
        this.userValidator = userValidator;
    }

    public int registerUser(AbstractUser user) 
    {
        if (userValidator.validateUser(user)) 
        {
        	int ID;
        	ID = manageUserDAO.addUser(user);
        	System.out.println("User registered successfully.");
            return ID;
            
        } else {
            System.out.println("User registration failed. Please check the input data.");
            return 0;
        }
		
    }

    public void registerConsumer(Consumer consumer) 
    {
        manageUserDAO.addConsumerDetails(consumer); 
        System.out.println("Consumer registered successfully.");     
    }

    public void registerRetailer(Retailer retailer) 
    {
    	manageUserDAO.addRetailerDetails(retailer); 
        System.out.println("Retailer registered successfully.");        
    }

    public void registerCharitableOrg(CharitableOrganization organization)
    {
        manageUserDAO.addCharitableOrgDetails(organization); 
        System.out.println("Charitable Organization registered successfully.");        
    }
}
