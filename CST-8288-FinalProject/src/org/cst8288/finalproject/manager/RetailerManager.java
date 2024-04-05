package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.users.User;

public class RetailerManager {

	private User userObject;
	private InventoryManager inventoryManagerObj;
	
	public RetailerManager(User user, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;	
	}
	
	
	
}
