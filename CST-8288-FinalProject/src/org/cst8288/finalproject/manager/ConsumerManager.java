package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.TransactionDAO;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.User;

public class ConsumerManager implements UserTypeInterface {
	
	private User userObject;
	private InventoryManager inventoryManagerObj;
	private TransactionDAO transcationDAO;
	
	public ConsumerManager(User user,TransactionDAO transcationDAO, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;
		this.transcationDAO = transcationDAO;
	System.out.println();
	}
	

	@Override
	public boolean VerifiyUserType(User user) 
	{
		return user.getType().equals(UserType.CONSUMER);
	}
	
	public void PurchaseItem(User userObject) 
	{
		
		if (!VerifiyUserType(userObject)) 
		{
			 System.out.println("invalid User type");
			 return;
		}

		
	}
	
	private double CalculateTotalCost(Item item, int quantity) 
	{

	    return item.getPrice() * quantity;

	}
	
	
}
