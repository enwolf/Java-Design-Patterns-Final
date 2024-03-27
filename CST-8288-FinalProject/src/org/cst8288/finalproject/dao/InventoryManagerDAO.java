package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.dto.FoodItem;
import org.cst8288.finalproject.dto.Item;

public class InventoryManagerDAO implements InventoryManagerDAOInterface {

    @Override
    public void addInventoryItem(Item inventoryItem) throws SQLException 
    {
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
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateInventoryItem(Item inventoryItem) throws SQLException {
        String updateSqlQuery = "UPDATE inventory SET retailerID = ?, itemName = ?, quantity = ?, expirationDate = ?, price = ?, isSurplus = ? WHERE itemID = ?";
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(updateSqlQuery))
        {
            preparedStatement.setString(1, inventoryItem.getRetailerID().trim());
            preparedStatement.setString(2, inventoryItem.getItemName().trim());
            preparedStatement.setInt(3, inventoryItem.getQuantity());
            preparedStatement.setDate(4, new java.sql.Date(inventoryItem.getExpirationDate().getTime()));
            preparedStatement.setDouble(5, inventoryItem.getPrice());
            preparedStatement.setBoolean(6, inventoryItem.isSurplus());
            preparedStatement.setInt(7, inventoryItem.getItemID());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void removeInventoryitem(int itemID) throws SQLException 
    {
        String deleteSqlQuery = "DELETE FROM inventory WHERE itemID = ?";
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(deleteSqlQuery)) 
        {
            preparedStatement.setInt(1, itemID);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public Item getSingleInventoryItemByID(int itemID) throws SQLException 
    {
        Item inventoryItem = null;
        String selectSqlQuery = "SELECT * FROM inventory WHERE itemID = ?";
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSqlQuery)) 
        {
            preparedStatement.setInt(1, itemID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
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
        return inventoryItem;
    }

    @Override
    public List<Item> getALLInventoryItems() throws SQLException 
    {
        List<Item> inventoryItems = new ArrayList<>();
        String selectAllSqlQuery = "SELECT * FROM inventory";
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectAllSqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Item inventoryItem = new FoodItem();
                inventoryItem.setItemID(resultSet.getInt("itemID"));
                inventoryItem.setRetailerID(resultSet.getString("retailerID"));
                inventoryItem.setItemName(resultSet.getString("itemName"));
                inventoryItem.setQuantity(resultSet.getInt("quantity"));
                inventoryItem.setExpirationDate(resultSet.getDate("expirationDate"));
                inventoryItem.setPrice(resultSet.getDouble("price"));
                inventoryItem.setSurplus(resultSet.getBoolean("isSurplus"));
                inventoryItems.add(inventoryItem);
            }
        }
        return inventoryItems;
    }


    @Override
    public List<Item> getALLSurplusInventoryItems() throws SQLException 
    {
        List<Item> surplusInventoryItems = new ArrayList<>();
        String selectSurplusSqlQuery = "SELECT * FROM inventory WHERE isSurplus = true";
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement preparedStatement = dbConnection.prepareStatement(selectSurplusSqlQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) 
        {
            while (resultSet.next()) 
            {
                Item inventoryItem = new FoodItem();
                inventoryItem.setItemID(resultSet.getInt("itemID"));
                inventoryItem.setRetailerID(resultSet.getString("retailerID"));
                inventoryItem.setItemName(resultSet.getString("itemName"));
                inventoryItem.setQuantity(resultSet.getInt("quantity"));
                inventoryItem.setExpirationDate(resultSet.getDate("expirationDate"));
                inventoryItem.setPrice(resultSet.getDouble("price"));
                inventoryItem.setSurplus(resultSet.getBoolean("isSurplus"));
                surplusInventoryItems.add(inventoryItem);
            }
        }
        return surplusInventoryItems;
    }
}


