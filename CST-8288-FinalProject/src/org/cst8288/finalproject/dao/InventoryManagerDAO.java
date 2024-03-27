package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.dto.FoodItem;
import org.cst8288.dto.Item;
import org.cst8288.finalproject.dataaccess.DataSource;


public class InventoryManagerDAO implements InventoryManagerDAOInterface{

	@Override
	public void addInventoryItem(Item inventoryItem) {
	    int rowsAffected = 0;
	    String insertSqlQuery = "INSERT INTO inventory (retailerID, itemName, quantity, expirationDate, price, isSurplus) "
	            + "VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(insertSqlQuery)) 
	    {
	        preparedStatement.setString(1, inventoryItem.getRetailerID().trim());
	        preparedStatement.setString(2, inventoryItem.getItemName().trim());
	        preparedStatement.setInt(3, inventoryItem.getQuantity());
	        preparedStatement.setDate(4, new java.sql.Date(inventoryItem.getExpirationDate().getTime()));
	        preparedStatement.setDouble(5, inventoryItem.getPrice());
	        preparedStatement.setBoolean(6, inventoryItem.isSurplus());
	        
	        rowsAffected = preparedStatement.executeUpdate();
	    }
	    catch (SQLIntegrityConstraintViolationException e) 
	    {
	        e.printStackTrace();
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Override
	public void updateInventoryItem(Item inventoryItem) {
	    int rowsAffected = 0;
	    
	    String updateSqlQuery = "UPDATE inventory SET retailerID = ?, itemName = ?, quantity = ?, expirationDate = ?, price = ?, isSurplus = ? WHERE itemID = ?";
	    
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(updateSqlQuery)) 
	    {
	        preparedStatement.setString(1, inventoryItem.getRetailerID().trim());
	        preparedStatement.setString(2, inventoryItem.getItemName().trim());
	        preparedStatement.setInt(3, inventoryItem.getQuantity());
	        preparedStatement.setDate(4, (Date) (inventoryItem.getExpirationDate()));
	        preparedStatement.setDouble(5, inventoryItem.getPrice());
	        preparedStatement.setBoolean(6, inventoryItem.isSurplus());
	        preparedStatement.setInt(7, inventoryItem.getItemID());
	        
	        rowsAffected = preparedStatement.executeUpdate();
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Override
	public void removeInventoryitem(int itemID) {
	    int rowsAffected = 0;
	    String deleteSqlQuery = "DELETE FROM inventory WHERE itemID = ?";
	    
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSqlQuery)) 
	    {
	        preparedStatement.setInt(1, itemID);
	        
	        rowsAffected = preparedStatement.executeUpdate();
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	}

	@Override
	public Item getSingleInventoryItemByID(int itemID) 
	{
	    Item inventoryItem = null;
	    String selectSqlQuery = "SELECT * FROM inventory WHERE itemID = ?";
	    
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSqlQuery)) 
	    {
	        preparedStatement.setInt(1, itemID);
	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet.next()) {
	                // Retrieve values from the ResultSet and create an Item object
	                inventoryItem = new FoodItem();
	                inventoryItem.setItemID(resultSet.getInt("itemID"));
	                inventoryItem.setRetailerID(resultSet.getString("retailerID"));
	                inventoryItem.setItemName(resultSet.getString("itemName"));
	                inventoryItem.setQuantity(resultSet.getInt("quantity"));
	                inventoryItem.setExpirationDate(resultSet.getDate("expirationDate"));
	                inventoryItem.setPrice(resultSet.getDouble("price"));
	                inventoryItem.setSurplus(resultSet.getBoolean("isSurplus"));
	            }
	        }
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return inventoryItem;
	}

	@Override
	public List<Item> getALLInventoryItems() 
	{
	    List<Item> inventoryItems = new ArrayList<>();
	    String selectAllSqlQuery = "SELECT * FROM inventory";
	    
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAllSqlQuery);
	         ResultSet resultSet = preparedStatement.executeQuery()) 
	    {
	        while (resultSet.next()) {
	            // Instantiate the concrete subclass of Item
	            Item inventoryItem = new FoodItem(); // Replace ConcreteItem with your actual concrete subclass
	            // Set the attributes of the concrete item
	            inventoryItem.setItemID(resultSet.getInt("itemID"));
	            inventoryItem.setRetailerID(resultSet.getString("retailerID"));
	            inventoryItem.setItemName(resultSet.getString("itemName"));
	            inventoryItem.setQuantity(resultSet.getInt("quantity"));
	            inventoryItem.setExpirationDate(resultSet.getDate("expirationDate"));
	            inventoryItem.setPrice(resultSet.getDouble("price"));
	            inventoryItem.setSurplus(resultSet.getBoolean("isSurplus"));
	            // Add the item to the list
	            inventoryItems.add(inventoryItem);
	        }
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return inventoryItems;
	}

	@Override
	public List<Item> getALLSurplusInventoryItems() 
	{
	    List<Item> surplusInventoryItems = new ArrayList<>();
	    String selectSurplusSqlQuery = "SELECT * FROM inventory WHERE isSurplus = true";
	    
	    try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
	         PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSurplusSqlQuery);
	         ResultSet resultSet = preparedStatement.executeQuery()) 
	    {
	        while (resultSet.next()) {
	            // Instantiate the concrete subclass of Item
	            Item inventoryItem = new FoodItem(); // Replace ConcreteItem with your actual concrete subclass
	            // Set the attributes of the concrete item
	            inventoryItem.setItemID(resultSet.getInt("itemID"));
	            inventoryItem.setRetailerID(resultSet.getString("retailerID"));
	            inventoryItem.setItemName(resultSet.getString("itemName"));
	            inventoryItem.setQuantity(resultSet.getInt("quantity"));
	            inventoryItem.setExpirationDate(resultSet.getDate("expirationDate"));
	            inventoryItem.setPrice(resultSet.getDouble("price"));
	            inventoryItem.setSurplus(resultSet.getBoolean("isSurplus"));
	            // Add the item to the list
	            surplusInventoryItems.add(inventoryItem);
	        }
	    }
	    catch (SQLException e) 
	    {
	        e.printStackTrace();
	    }
	    
	    return surplusInventoryItems;
	}

}


