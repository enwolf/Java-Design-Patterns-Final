package org.cst8288.finalproject.interfaces;

public interface UserAuthenticationDAOInterface {
    
	void setPassword(int userID, String password);    
    boolean verifyPassword(String usereEmail, String password);
}
