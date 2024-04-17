package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.UserTemp;

public class RetailerManager implements UserTypeInterface{

	private UserTemp userObject;
	private InventoryManager inventoryManagerObj;
	
	public RetailerManager(UserTemp user, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;	
	}

	@Override
	public boolean VerifiyUserType(UserTemp user) {
		
		if (user.getType() == UserType.RETAILER) 
			return true;
		else
			return false;
	}
	
	
	
}
