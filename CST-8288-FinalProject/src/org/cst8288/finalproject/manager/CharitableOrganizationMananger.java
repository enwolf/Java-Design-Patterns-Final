package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.users.User;

public class CharitableOrganizationMananger {
	
	private User userObject;
	private InventoryManager inventoryManagerObj;
	
	public CharitableOrganizationMananger(User user, InventoryManager inventoryManager)
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;
	}
	
	

}
