package org.cst8288.finalproject.userdao;

import java.util.List;

import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.User;

public interface ManageUserDAOInterface {
    
	void addUser(User user);
    
    void removeUser(int userID);
    
    User returnUser(int userID);
    
    void updateUser(int userID, User updatedUser);
    
    List<AbstractUser> returnAllUsers();
    
}
