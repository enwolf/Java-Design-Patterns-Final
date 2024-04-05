package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.User;

public class RetailerManager implements UserTypeInterface{

	private User userObject;
	private InventoryManager inventoryManagerObj;
	
	public RetailerManager(User user, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;	
	}

	@Override
	public boolean VerifiyUserType(User user) {
		
		if (user.getType() == UserType.RETAILER) 
			return true;
		else
			return false;
	}
	
	
	
}
