package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.UserAuthenticationDAO;

public class UserPasswordManager {
    
	private UserAuthenticationDAO userAuthenticationDAO;

    public UserPasswordManager(UserAuthenticationDAO userAuthenticationDAO) 
    {
        this.userAuthenticationDAO = userAuthenticationDAO;
    }

    public void setPassword(int userID, String password) 
    {
        userAuthenticationDAO.setPassword(userID, password);
    }

    public boolean verifyPassword(String UserEmail, String password) 
    {
        return userAuthenticationDAO.verifyPassword(UserEmail, password);
    }

}
