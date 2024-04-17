package org.cst8288.finalproject.interfaces;

public interface UserAuthenticationDAOInterface {
    void setPassword(int userID, String password);
    
    boolean verifyPassword(int UserID, String password);
}
