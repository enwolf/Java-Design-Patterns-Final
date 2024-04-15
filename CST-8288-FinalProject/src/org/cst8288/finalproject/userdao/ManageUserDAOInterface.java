package org.cst8288.finalproject.userdao;

import java.util.List;

import org.cst8288.finalproject.users.UserInterface;

public interface ManageUserDAOInterface {
    void addUser(UserInterface user);
    
    void removeUser(int userID);
    
    UserInterface returnUser(int userID);
    
    void updateUser(int userID, UserInterface updatedUser);
    
    List<UserInterface> returnAllUsers();
}
