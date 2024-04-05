package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.TransactionDAO;
import org.cst8288.finalproject.users.User;

public class ConsumerManager {
	
	private User userObject;
	private InventoryManager inventoryManagerObj;
	private TransactionDAO transcationDAO;
	
	public ConsumerManager(User user,TransactionDAO transcationDAO, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;
		this.transcationDAO = transcationDAO;
	
	}
	
}
