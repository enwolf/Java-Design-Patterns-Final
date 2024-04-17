package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.TransactionDAO;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.UserTemp;

public class ConsumerManager implements UserTypeInterface {
	
	private UserTemp userObject;
	private InventoryManager inventoryManagerObj;
	private TransactionDAO transcationDAO;
	
	public ConsumerManager(UserTemp user,TransactionDAO transcationDAO, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;
		this.transcationDAO = transcationDAO;
	System.out.println();
	}
	

	@Override
	public boolean VerifiyUserType(UserTemp user) 
	{
		return user.getType().equals(UserType.CONSUMER);
	}
	
	public void PurchaseItem(UserTemp userObject) 
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
