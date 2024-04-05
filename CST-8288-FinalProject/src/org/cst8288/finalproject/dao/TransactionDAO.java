package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.interfaces.TransactionDAOInterface;

public class TransactionDAO implements TransactionDAOInterface{

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
	        preparedStatement.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis())); // Current time for the transaction date

	        preparedStatement.executeUpdate();	    
	    }
	}
}
