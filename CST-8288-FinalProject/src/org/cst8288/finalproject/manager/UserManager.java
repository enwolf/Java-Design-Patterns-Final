package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.ManageUserDAO;
import org.cst8288.finalproject.dao.UserAuthenticationDAO;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.validator.UserValidator;

public class UserManager {
    private ManageUserDAO manageUserDAO;
    private UserValidator userValidator;
    private UserPasswordManager userPasswordManager; 
    private LMSLogger logger = LMSLogger.getInstance(); 
    
    public UserManager(ManageUserDAO manageUserDAO, UserValidator userValidator, UserAuthenticationDAO userAuthDAO) 
    {
        this.manageUserDAO = manageUserDAO;
        this.userValidator = userValidator;
        this.userPasswordManager = new UserPasswordManager(userAuthDAO); 
    }

    public boolean authenticateUserPassword(String email, String password) 
    {
    	AbstractUser user = getUserByEmail(email);
        if (user != null && userPasswordManager.verifyPassword(email, password)) 
        {
            logger.debug("Authentication successful for user: " + email);
            return true;
        }
        logger.warn("Authentication failed for user: " + email);
        return false;
    }

    public boolean validateAndAddUser(AbstractUser user) 
    {
        if (userValidator.validateUser(user)) 
        {
            try 
            {
                manageUserDAO.addUser(user);
                logger.info("User added successfully: " + user.getEmailAddress());
                return true;
            }
            catch (Exception e) 
            {
                logger.error("Error adding user: " + user.getEmailAddress());
                logger.logException(e);
            }
        } 
        else 
        {
            logger.warn("Validation failed for user: " + user.getEmailAddress());
        }
        return false;
    }

    public boolean updateUser(AbstractUser user) 
    {
        try 
        {
            manageUserDAO.updateUser(user);
            logger.info("User updated successfully: " + user.getEmailAddress());
            return true;
        }
        catch (Exception e) 
        {
            logger.error("Error updating user: " + user.getEmailAddress());
            logger.logException(e);
        }
        return false;
    }

    public boolean deleteUser(int userId) 
    {
        try 
        {
            manageUserDAO.removeUser(userId);
            logger.info("User deleted successfully: UserID = " + userId);
            return true;
        }
        catch (Exception e) 
        {
            logger.error("Error deleting user: UserID = " + userId);
            logger.logException(e);
        }
        return false;
    }

    public AbstractUser getUserById(int userId) 
    {
        try 
        {
            return manageUserDAO.returnUser(userId);
        }
        catch (Exception e) 
        {
            logger.error("Error retrieving user by ID: " + userId);
            logger.logException(e);
        }
        return null;
    }

    public AbstractUser getUserByEmail(String email) 
    {
        try 
        {
            return manageUserDAO.returnUserByEmail(email);
        }
        catch (Exception e) 
        {
            logger.error("Error retrieving user by email: " + email);
            logger.logException(e);
        }
        return null;
    }
    
    public AbstractUser populateUserDetails(AbstractUser user) 
    {        
    	if (user == null) return null;
        
        switch (user.getUserType()) 
        {
		    case RETAILER:
		        return manageUserDAO.getRetailerSpecificData(user.getUserId());
		    case CONSUMER:
		        return manageUserDAO.getConsumerSpecificData(user.getUserId());
		    case CHARITABLE_ORGANIZATION:
		        return manageUserDAO.getCharitableOrganizationSpecificData(user.getUserId());
		    default:
		        return manageUserDAO.returnUser(user.getUserId());
		}        
    }    
}
