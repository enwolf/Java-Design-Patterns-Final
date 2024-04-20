package org.cst8288.finalproject.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.dto.FoodItem;
import org.cst8288.finalproject.dto.Item;
import org.cst8288.finalproject.interfaces.InventoryManagerDAOInterface;
import org.cst8288.finalproject.logger.LMSLogger;

/**
 * Data Access Object (DAO) for handling inventory management operations in the database.
 * This class provides functionality for adding, updating, deleting, and retrieving inventory items,
 * including checking whether an item is surplus. It interacts with the database using SQL queries
 * and adheres to the InventoryManagerDAOInterface.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-03-22
 * @see org.cst8288.finalproject.interfaces.InventoryManagerDAOInterface
 */
public class InventoryManagerDAO implements InventoryManagerDAOInterface {
	private static final LMSLogger logger = LMSLogger.getInstance();
	/**
	 * Adds a new inventory item to the database. This method inserts a record into the 'inventory'
	 * table using the attributes of the provided inventoryItem object. It handles both standard and
	 * null values like discountRate and discountAmount. The PreparedStatement is used to ensure
	 * safe parameter insertion and prevention of SQL injection.
	 * 
	 * The method executes these key steps:
	 * - Prepares the SQL insert statement.
	 * - Sets the parameters based on the inventoryItem's attributes.
	 * - Handles null values for discountRate and discountAmount.
	 * - Executes the insertion into the database.
	 * 
	 * @param inventoryItem The inventory item to be added. 
	 * @throws SQLException If a database access error occurs or the SQL query is incorrect.
	 */
	@Override
	public void addInventoryItem(Item inventoryItem) throws SQLException {
	    String insertSqlQuery = "INSERT INTO inventory (retailerID, itemName, quantity, expirationDate, price, discountRate, discountAmount) "
	                          + "VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSqlQuery)) 
	    {
	        preparedStatement.setInt(1, inventoryItem.getRetailerID());
	        preparedStatement.setString(2, inventoryItem.getItemName().trim());
	        preparedStatement.setInt(3, inventoryItem.getQuantity());
	        preparedStatement.setDate(4, new java.sql.Date(inventoryItem.getExpirationDate().getTime()));
	        preparedStatement.setDouble(5, inventoryItem.getPrice());
        
	        // Handling null BigDecimal values
	        BigDecimal discountRate = inventoryItem.getDiscountRate();
	        
	        if (discountRate != null) 
	        {
	            preparedStatement.setBigDecimal(6, discountRate);
	        } 
	        else
	        {
	            preparedStatement.setNull(6, java.sql.Types.DECIMAL);
	        }

	        BigDecimal discountAmount = inventoryItem.getDiscountAmount();
	        
	        if (discountAmount != null) 
	        {
	            preparedStatement.setBigDecimal(7, discountAmount);
	        }
	        else 
	        {
	            preparedStatement.setNull(7, java.sql.Types.DECIMAL);
	        }

	        logger.debug("Setting preparedStatement parameters: RetailerID=" + inventoryItem.getRetailerID() +
	                ", ItemName='" + inventoryItem.getItemName().trim() + "', Quantity=" + inventoryItem.getQuantity() +
	                ", ExpirationDate=" + inventoryItem.getExpirationDate() +
	                ", Price=" + inventoryItem.getPrice() +
	                ", DiscountRate=" + inventoryItem.getDiscountRate() +
	                ", DiscountAmount=" + inventoryItem.getDiscountAmount());
	        
	        int affectedRows = preparedStatement.executeUpdate();
	        
	        if (affectedRows > 0) 
	        {
	            logger.info("Item successfully added to inventory. Affected rows: " + affectedRows);
	        }
	        else 
	        {
	            logger.warn("No rows affected while adding inventory item.");
	        }
	    } 
	    catch (SQLException e) 
	    {
	        logger.error("SQL Exception occurred while adding inventory item: " + e.getMessage());
	        throw e;  	    }

	    logger.info("Exiting addInventoryItem method.");
	}
	

	/**
	 * Updates the details of an existing inventory item in the database.
	 * This method modifies the attributes of an inventory item based on the provided
	 * inventoryItem object. It uses a SQL UPDATE statement to modify the record in the 
	 * 'inventory' table corresponding to the itemID of the inventoryItem.
	 *
	 * The method performs the following operations:
	 * - Prepares a SQL UPDATE statement with placeholder's for parameters.
	 * - Sets the parameters based on the inventoryItem's updated attributes.
	 * - Handles the null values for discountRate and discountAmount.
	 * - Executes the update operation in the database.
	 *
	 * Note: The itemID of the inventoryItem is used to identify which record to update.
	 *
	 * @param inventoryItem The inventory item containing updated details. 
	 *        It must have a valid itemID for the update to be successful.
	 * @throws SQLException If any issue occurs during the database operation.
	 */
	@Override
	public void updateInventoryItem(Item inventoryItem) throws SQLException {
	    String updateSqlQuery = "UPDATE inventory SET retailerID = ?, itemName = ?, quantity = ?, expirationDate = ?, price = ?, discountRate = ?, discountAmount = ? "
	    					  + "WHERE InventoryID = ?";
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(updateSqlQuery)) 
	    {
	        preparedStatement.setInt(1, inventoryItem.getRetailerID());
	        preparedStatement.setString(2, inventoryItem.getItemName().trim());
	        preparedStatement.setInt(3, inventoryItem.getQuantity());
	        preparedStatement.setDate(4, new java.sql.Date(inventoryItem.getExpirationDate().getTime()));
	        preparedStatement.setDouble(5, inventoryItem.getPrice());

	        BigDecimal discountRate = inventoryItem.getDiscountRate();
	        if (discountRate != null) 
	        {
	            preparedStatement.setBigDecimal(6, discountRate);
	        } 
	        else 
	        {
	            preparedStatement.setNull(6, java.sql.Types.DECIMAL);
	        }

	        BigDecimal discountAmount = inventoryItem.getDiscountAmount();
	        if (discountAmount != null) 
	        {
	            preparedStatement.setBigDecimal(7, discountAmount);
	        } 
	        else 
	        {
	            preparedStatement.setNull(7, java.sql.Types.DECIMAL);
	        }

	        preparedStatement.setInt(8, inventoryItem.getItemID());
	        preparedStatement.executeUpdate();
	    }
	}

    /**
     * Removes an inventory item from the database based on its ID.
     * 
     * @param itemID The ID of the inventory item to be removed.
     * @throws SQLException if any database operation fails.
     */
    @Override
    public void removeInventoryitem(int itemID) throws SQLException 
    {
        String deleteSqlQuery = "DELETE FROM inventory "
        					  + "WHERE InventoryID = ?";
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSqlQuery)) 
        {
            preparedStatement.setInt(1, itemID);
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Retrieves an inventory item by its ID from the database. Executes a query to find an
     * item in the 'inventory' table that matches the given itemID. If such an item is found,
     * it creates and returns an Item object populated with the data from the database; otherwise,
     * it returns null. This method is useful for fetching detailed information about a specific 
     * inventory item.
     *
     * @param itemID The unique identifier of the inventory item to be retrieved.
     * @return An Item object representing the inventory item, or null if no item matches the given ID.
     * @throws SQLException if a database access error occurs or the query fails to execute.
     */
    @Override
    public Item getSingleInventoryItemByID(int itemID) throws SQLException 
    {        
    	Item inventoryItem = null;
        String selectSqlQuery = "SELECT * FROM inventory "
        		              + "WHERE InventoryID = ?";
        
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSqlQuery)) 
        {
            preparedStatement.setInt(1, itemID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    inventoryItem = new FoodItem();
                    inventoryItem.setItemID(resultSet.getInt("InventoryID"));
                    inventoryItem.setRetailerID(resultSet.getInt("RetailerID"));
                    inventoryItem.setItemName(resultSet.getString("ItemName"));
                    inventoryItem.setQuantity(resultSet.getInt("Quantity"));
                    inventoryItem.setExpirationDate(resultSet.getDate("ExpirationDate"));
                    inventoryItem.setPrice(resultSet.getDouble("Price"));
                    inventoryItem.setDiscountRate(resultSet.getBigDecimal("DiscountRate"));
                    inventoryItem.setDiscountAmount(resultSet.getBigDecimal("DiscountAmount"));
                    
                }
            }
        }
        return inventoryItem;
    }
    
    /**
     * Determines whether a specific inventory item is marked as surplus in the database.
     * This method queries the 'surplusFood' table to check if the item corresponding to
     * the provided itemID is classified as surplus. 
     *
     * The method performs:
     * - Preparation and execution of a SQL query to find the item in the 'surplusFood' table.
     * - Returns true if the item is found (indicating it's surplus), false if not found.
     *
     * @param itemID The ID of the inventory item to check for surplus status.
     * @return true if the item is marked as surplus, false otherwise.
     * @throws SQLException if there is an error during database access or query execution.
     */
    @Override
	public boolean isItemSurplus(int itemID) throws SQLException 
	{
        
		String query = "SELECT 1 FROM surplusFood WHERE InventoryID = ?";
        
		try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(query)) 
        {
            preparedStatement.setInt(1, itemID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) 
            {
                if (resultSet.next()) 
                    return true;              
            }        
        }
        return false;
	}
    

    /**
     * Retrieves a list of all inventory items from the database. 
     * This method queries the 'inventory' table and constructs a list of Item objects, each 
     * representing an inventory item. It's useful for getting an overview of all items in stock.
     *
     * Steps in the method:
     * - Prepares and executes a SQL query to select all records from the 'inventory' table.
     * - Iterates over the ResultSet, creating a FoodItem object for each record.
     * - Populates each FoodItem object with data from the database.
     * - Adds each FoodItem to the list of inventory items.
     *
     * @return A List containing Item objects for every record in the inventory table.
     * @throws SQLException if there is an error during database access or query execution.
     */
    @Override
    public List<Item> getALLInventoryItems() throws SQLException 
    {
        
    	List<Item> inventoryItems = new ArrayList<>();
        String selectAllSqlQuery = "SELECT * FROM inventory";
        
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAllSqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) 
        {
            while (resultSet.next()) 
            {
                Item inventoryItem = new FoodItem();
                inventoryItem.setItemID(resultSet.getInt("InventoryID"));
                inventoryItem.setRetailerID(resultSet.getInt("RetailerID"));
                inventoryItem.setItemName(resultSet.getString("ItemName"));
                inventoryItem.setQuantity(resultSet.getInt("Quantity"));
                inventoryItem.setExpirationDate(resultSet.getDate("ExpirationDate"));
                inventoryItem.setPrice(resultSet.getDouble("Price"));
                inventoryItem.setDiscountRate(resultSet.getBigDecimal("DiscountRate"));
                inventoryItem.setDiscountAmount(resultSet.getBigDecimal("DiscountAmount"));
                inventoryItems.add(inventoryItem);
            }
        }
        return inventoryItems;
    }
    
    /**
     * Retrieves all inventory items that are marked as surplus from the database. 
     * This method joins the 'inventory' table with the 'surplusFood' table to fetch items
     * that are recorded as surplus. It creates a list of Item objects, each representing 
     * an item from the inventory that is also listed in the surplusFood table.
     * 
     * The method:
     * - Executes a SQL join query between 'inventory' and 'surplusFood'.
     * - Iterates over the ResultSet to create and populate FoodItem objects.
     * - Assumes all retrieved items are surplus, and sets their surplus status accordingly.
     * - Adds each FoodItem to a list of surplus inventory items.
     * 
     * @return A List of Item objects, where each item is a surplus inventory item.
     * @throws SQLException if a database access error occurs or the query fails to execute.
     */
    @Override
    public List<Item> getALLSurplusInventoryItems() throws SQLException 
    {
        List<Item> surplusInventoryItems = new ArrayList<>();
        // SQL query to join the inventory table with the surplusFood table
        String selectSurplusQuery = "SELECT inventory.* FROM inventory " 
        						  + "JOIN surplusFood "
        						  + "ON inventory.InventoryID = surplusFood.InventoryID";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSurplusQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) 
        {

            while (resultSet.next()) 
            {
                Item inventoryItem = new FoodItem();
                inventoryItem.setItemID(resultSet.getInt("InventoryID"));
                inventoryItem.setRetailerID(resultSet.getInt("RetailerID"));
                inventoryItem.setItemName(resultSet.getString("ItemName"));
                inventoryItem.setQuantity(resultSet.getInt("Quantity"));
                inventoryItem.setExpirationDate(resultSet.getDate("ExpirationDate"));
                inventoryItem.setPrice(resultSet.getDouble("Price"));
                inventoryItem.setDiscountRate(resultSet.getBigDecimal("DiscountRate"));
                inventoryItem.setDiscountAmount(resultSet.getBigDecimal("DiscountAmount"));
                                
                surplusInventoryItems.add(inventoryItem);
            }
        }
        return surplusInventoryItems;
    }
}


