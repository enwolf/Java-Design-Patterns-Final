package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.User;

public class CharitableOrganizationMananger implements UserTypeInterface{
	
	private User userObject;
	private InventoryManager inventoryManagerObj;
	
	public CharitableOrganizationMananger(User user, InventoryManager inventoryManager)
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;
	}

	@Override
	public boolean VerifiyUserType(User user) 
	{		
		if (user.getType() == UserType.CHARITABLE_ORGANIZATION) 
			return true;
		else
			return false;
	}
	
	

}
