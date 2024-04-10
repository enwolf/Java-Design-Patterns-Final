package org.cst8288.finalproject.userdao;

public class UserLoginManager {
    private ManageUserDAO manageUserDAO;
    private UserPasswordManager userPasswordManager;

    public UserLoginManager(ManageUserDAO manageUserDAO, UserPasswordManager userPasswordManager) {
        this.manageUserDAO = manageUserDAO;
        this.userPasswordManager = userPasswordManager;
    }

    public boolean loginUser(int userID, String password) {
        if (userPasswordManager.verifyPassword(userID, password)) {
            System.out.println("User logged in successfully.");
            return true;
        } else {
            System.out.println("User login failed. Invalid credentials.");
            return false;
        }
    }

    public void logoutUser(int userID) {
        manageUserDAO.removeUser(userID);
        System.out.println("User logged out.");
    }

    
}
