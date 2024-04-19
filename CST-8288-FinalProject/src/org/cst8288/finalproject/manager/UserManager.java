package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.ManageUserDAO;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;
import org.cst8288.finalproject.validator.UserValidator;

public class UserManager {
    private ManageUserDAO manageUserDAO;
    private UserValidator userValidator;

    public UserManager(ManageUserDAO manageUserDAO, UserValidator userValidator) 
    {
        this.manageUserDAO = manageUserDAO;
        this.userValidator = userValidator;
    }

    public boolean validateAndAddUser(AbstractUser user) 
    {
        if (userValidator.validateUser(user)) 
        {
            try 
            {   manageUserDAO.addUser(user);
                return true; // User successfully added
            }
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
        return false; // Validation failed or an exception occurred
    }

    public boolean updateUser(AbstractUser user) 
    {
        try 
        {
            manageUserDAO.updateUser(user);
            return true; // User successfully updated
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false; // An exception occurred
    }

    public boolean deleteUser(int userId) 
    {
        try 
        {
            manageUserDAO.removeUser(userId);
            return true; 
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return false; 
    }

    public User getUserById(int userId) 
    {
        try 
        {
            return (User) manageUserDAO.returnUser(userId);
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null; // An exception occurred, return null
    }

    public User getUserByEmail(String email) 
    {
        try 
        {
            return (User) manageUserDAO.returnUserByEmail(email);
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null; // An exception occurred, return null
    }
}
