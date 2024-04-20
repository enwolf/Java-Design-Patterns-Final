package org.cst8288.finalproject.manager;

import org.cst8288.finalproject.dao.TransactionDAO;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.interfaces.UserTypeInterface;
import org.cst8288.finalproject.users.UserTemp;
/**
 * Manages consumer-specific functionalities, ensuring that operations relevant to consumers such as
 * purchasing items are handled appropriately. This class encapsulates all the logic necessary for 
 * managing consumer interactions with the inventory and handling transactions.
 *
 * Implements the {@link UserTypeInterface} to ensure that the operations are appropriate for the user's type.
 * This class uses {@link TransactionDAO} for handling financial transactions and {@link InventoryManager}
 * for inventory related operations.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see UserTypeInterface
 * @see TransactionDAO
 * @see InventoryManager
 * @see UserTemp
 */
public class ConsumerManager implements UserTypeInterface {
	
	private UserTemp userObject;
	private InventoryManager inventoryManagerObj;
	private TransactionDAO transcationDAO;
	
	/**
     * Constructs a new ConsumerManager with specified user, transaction handler, and inventory manager.
     * 
     * @param user The user object containing data about the consumer.
     * @param transactionDAO The transaction DAO object to handle financial transactions.
     * @param inventoryManager The inventory manager object to handle inventory operations.
     */
	public ConsumerManager(UserTemp user,TransactionDAO transcationDAO, InventoryManager inventoryManager) 
	{
		this.userObject = user;
		this.inventoryManagerObj = inventoryManager;
		this.transcationDAO = transcationDAO;
	System.out.println();
	}
	
	 /**
     * Verifies if the provided UserTemp object represents a user of type Consumer.
     * This method checks the user type of the given UserTemp object against the CONSUMER enum.
     * 
     * @param user The UserTemp object to be verified.
     * @return true if the user is of type Consumer, false otherwise.
     */
	@Override
	public boolean VerifiyUserType(UserTemp user) 
	{
		return user.getType().equals(UserType.CONSUMER);
	}
	
    /**
     * Processes the purchase of an item by a consumer. This method ensures that the user is a consumer
     * and calculates the total cost of the item being purchased.
     *
     * @param userObject The UserTemp object representing the consumer.
     */
	public void PurchaseItem(UserTemp userObject) 
	{
		
		if (!VerifiyUserType(userObject)) 
		{
			 System.out.println("invalid User type");
			 return;
		}

		
	}
	
    /**
     * Calculates the total cost for a given item based on its price and the quantity purchased.
     *
     * @param item The item being purchased.
     * @param quantity The quantity of the item being purchased.
     * @return The total cost for the item.
     */
	private double CalculateTotalCost(Item item, int quantity) 
	{

	    return item.getPrice() * quantity;

	}
	
	
}
