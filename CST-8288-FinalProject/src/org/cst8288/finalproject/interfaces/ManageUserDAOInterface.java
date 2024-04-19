package org.cst8288.finalproject.interfaces;

import java.util.List;

import org.cst8288.finalproject.users.AbstractUser;

public interface ManageUserDAOInterface {
    
	void addUser(AbstractUser user);
    
    void removeUser(int userID);
    
    AbstractUser returnUserByEmail(String email);
    
    AbstractUser returnUser(int userID);
    
    void updateUser(AbstractUser updatedUser);
    
    List<AbstractUser> returnAllUsers();

	
    
}
