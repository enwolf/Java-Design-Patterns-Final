package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.interfaces.TransactionDAOInterface;


/**
 * Data Access Object (DAO) for managing transactions within the database.
 * This class provides functionality for creating new transaction records. Each transaction record captures
 * details about user purchases including the user ID, item ID, amount paid, and the purchase date.
 * It uses standard SQL queries executed against a database to store transaction information securely and efficiently.
 *
 * The TransactionDAO specifically handles the insertion of new records into the 'transactions' table
 * of the database. It utilizes prepared statements to ensure that SQL injection vulnerabilities are mitigated
 * and data integrity is maintained.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see org.cst8288.finalproject.interfaces.TransactionDAOInterface
 * @see org.cst8288.finalproject.dataaccess.DataSource
 */
public class TransactionDAO implements TransactionDAOInterface{

    /**
     * Creates a new transaction record in the database.
     *
     * @param userID     The ID of the user involved in the transaction.
     * @param itemID     The ID of the item involved in the transaction.
     * @param amountPaid The amount paid in the transaction.
     * @throws SQLException If an SQL exception occurs while accessing the database.
     */
	@Override
	public void createNewTransactionRecord(int userID, int itemID, double amountPaid) throws SQLException 
	{
	    String insertQuery = "INSERT INTO transactions (UserID, ItemID, AmountPaid, PurchaseDate) VALUES (?, ?, ?, ?)";

	    try (Connection connection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) 
	    {
	        preparedStatement.setInt(1, userID);
	        preparedStatement.setInt(2, itemID);
	        preparedStatement.setDouble(3, amountPaid);
	        preparedStatement.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis())); 

	        preparedStatement.executeUpdate();	    
	    }
	}
}
