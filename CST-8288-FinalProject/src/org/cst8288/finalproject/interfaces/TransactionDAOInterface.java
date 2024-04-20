package org.cst8288.finalproject.interfaces;

import java.sql.SQLException;

/**
 * Interface for handling database operations related to financial transactions.
 * This interface defines the required actions for creating records of financial transactions
 * involving users and items in the system. It is designed to ensure that any class implementing
 * this interface will provide functionality to insert new transaction data into the database.
 *
 * The primary method in this interface includes creating a new transaction record which is essential
 * for maintaining the integrity and tracking of financial operations within the application.
 *
 * @author Robin Phillis 
 * @version 1.0
 * @since 2024-04-20
 */
public interface TransactionDAOInterface {

    /**
     * Creates a new transaction record in the database. This method will insert a new row into
     * the transactions table with details about the user, item purchased, and the amount paid.
     * 
     * @param userID The user ID of the customer involved in the transaction.
     * @param itemID The item ID of the product involved in the transaction.
     * @param amountPaid The total amount paid in the transaction.
     * @throws SQLException If there is any issue with the database operation, including connectivity issues or SQL syntax errors.
     */
    void createNewTransactionRecord(int userID, int itemID, double amountPaid) throws SQLException;
    
}
