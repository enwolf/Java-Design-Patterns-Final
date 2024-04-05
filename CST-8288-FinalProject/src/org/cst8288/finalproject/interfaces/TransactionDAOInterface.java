package org.cst8288.finalproject.interfaces;

import java.sql.SQLException;

public interface TransactionDAOInterface {

	void createNewTransactionRecord(int userID, int itemID, double amountPaid) throws SQLException;
	
	
	
}
