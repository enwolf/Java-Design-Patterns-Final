package org.cst8288.finalproject.interfaces;

import java.util.List;

import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;

public interface ManageUserDAOInterface {
    
	int addUser(AbstractUser user);
    
    void removeUser(int userID);
    
    AbstractUser returnUserByEmail(String email);
    
    AbstractUser returnUser(int userID);
    
    void updateUser(AbstractUser updatedUser);
    
    List<AbstractUser> returnAllUsers();

	void addConsumerDetails(Consumer consumer);

	void addRetailerDetails(Retailer retailer);

	void addCharitableOrgDetails(CharitableOrganization organization);

	
    
}
