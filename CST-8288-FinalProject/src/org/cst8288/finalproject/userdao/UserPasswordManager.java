package org.cst8288.finalproject.userdao;

public class UserPasswordManager {
    private UserAuthenticationDAO userAuthenticationDAO;

    public UserPasswordManager(UserAuthenticationDAO userAuthenticationDAO) {
        this.userAuthenticationDAO = userAuthenticationDAO;
    }



    public void setPassword(int userID, String password) {
        userAuthenticationDAO.setPassword(userID, password);
    }

    public boolean verifyPassword(int UserID, String password) {
        return userAuthenticationDAO.verifyPassword(UserID, password);
    }

    //

}
