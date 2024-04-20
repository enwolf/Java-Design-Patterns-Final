package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.interfaces.ManageUserDAOInterface;
import org.cst8288.finalproject.logger.LMSLogger;
import org.cst8288.finalproject.service.UserDataExtractorService;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.users.User;

public class ManageUserDAO implements ManageUserDAOInterface {

	private static final LMSLogger LOGGER = LMSLogger.getInstance();
	private UserDataExtractorService userDataExtractorService;
    private Connection connection;
    
    public ManageUserDAO(UserDataExtractorService userDataExtractorService) 
    {
    	this.userDataExtractorService = new UserDataExtractorService();
    }

    @Override
    public int addUser(AbstractUser user) 
    {
    	int userId = 0;
    	String sqlQuery = "INSERT INTO user (FirstName, LastName, Email, Password, UserType) VALUES (?, ?, ?, ?, ?)";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
        		PreparedStatement insertStatement = dbConnection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS))
        {
            insertStatement.setString(1, user.getUserFirstName());
            insertStatement.setString(2, user.getUserLastName());
            insertStatement.setString(3, user.getEmailAddress());
            insertStatement.setString(4, ((User) user).getPassword());
            insertStatement.setString(5, user.getUserType().name());
            
            int rowsAffected = insertStatement.executeUpdate();
            
            if (rowsAffected > 0) 
            {
                try (ResultSet getUserIDKey = insertStatement.getGeneratedKeys()) 
                {
                    if (getUserIDKey.next()) 
                    {
                        userId = getUserIDKey.getInt(1);  
                    }
                }
            }
            LMSLogger.getInstance().info("Added " + rowsAffected + " user(s) to the database from ManageUserDAO class.");
        }
        catch (SQLException e) 
        {
            LMSLogger.getInstance().error("Error occurred while adding user to the database from ManageUserDAO class: " + e.getMessage());
            e.printStackTrace();
        }
        return userId;
    }
    
    @Override
    public void updateUser(AbstractUser updatedUser) 
    {
        String sqlSelect = "SELECT userID FROM user WHERE Email = ? AND UserID != ?";
        String sqlUpdate = "UPDATE user SET FirstName = ?, LastName = ?, Email = ?, Password = ? WHERE UserID = ?";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement selectStatement = dbConnection.prepareStatement(sqlSelect);
             PreparedStatement updateStatement = dbConnection.prepareStatement(sqlUpdate)) 
        {
            // Setting parameters for the SELECT statement, releasing the local email address for UPDATE, this resolved error in not being able to update email addres.
            selectStatement.setString(1, updatedUser.getEmailAddress());
            selectStatement.setInt(2, updatedUser.getUserId());  

            ResultSet resultSet = selectStatement.executeQuery();

            if (!resultSet.next()) 
            {
                updateStatement.setString(1, updatedUser.getUserFirstName());
                updateStatement.setString(2, updatedUser.getUserLastName());
                updateStatement.setString(3, updatedUser.getEmailAddress());
                updateStatement.setString(4, ((User) updatedUser).getPassword());
                updateStatement.setInt(5, updatedUser.getUserId());

                LOGGER.debug("Preparing to update database with: " + sqlUpdate);
                int rowsAffected = updateStatement.executeUpdate();
                
                LOGGER.info("Updated " + rowsAffected + " user(s). User details: " + updatedUser.toString());
                LMSLogger.getInstance().info("Updated " + rowsAffected + " user(s) in the database. Details: " + updatedUser.toString());
            }
            else 
            {
                LOGGER.info("Email update attempted to duplicate existing email. No changes made.");
            }
        } 
        catch (SQLException e) 
        {
            LMSLogger.getInstance().error("Error occurred while updating user in the database: " + e.getMessage());
            LOGGER.error("SQL Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
    

    @Override
    public void addConsumerDetails(Consumer consumer) 
    {
        String sqlInsert = "INSERT INTO consumer (PhoneNumber, StreetAddress, City, Province, PostalCode, AccountBalance, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement insertStatement = dbConnection.prepareStatement(sqlInsert)) 
        {
        	insertStatement.setString(1, consumer.getPhoneNumber());
        	insertStatement.setString(2, consumer.getStreetAddress());
        	insertStatement.setString(3, consumer.getCity());
        	insertStatement.setString(4, consumer.getProvince());
        	insertStatement.setString(5, consumer.getPostalCode());
        	insertStatement.setDouble(6, consumer.getAccountBalance());
        	insertStatement.setInt(7, consumer.getUserId());
        	
        	insertStatement.executeUpdate();
            LOGGER.info("Consumer details added for UserID: " + consumer.getUserId());
        } 
        catch (SQLException e) 
        {
            LOGGER.error("Failed to add consumer details: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void addRetailerDetails(Retailer retailer) 
    {
        String sqlInsert = "INSERT INTO retailer (StoreName, StreetAddress, City, Province, PostalCode, UserID) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement insertStatement = dbConnection.prepareStatement(sqlInsert)) 
        {
            insertStatement.setString(1, retailer.getStoreName());
            insertStatement.setString(2, retailer.getStreetAddress());
            insertStatement.setString(3, retailer.getCity());
            insertStatement.setString(4, retailer.getProvince());
            insertStatement.setString(5, retailer.getPostalCode());
            insertStatement.setInt(6, retailer.getUserId());
            
            insertStatement.executeUpdate();
            LOGGER.info("Retailer details added for UserID: " + retailer.getUserId());
        }
        catch (SQLException e) 
        {
            LOGGER.error("Failed to add retailer details: " + e.getMessage());
            e.printStackTrace();
        }
    }


    @Override
    public void addCharitableOrganizationDetails(CharitableOrganization organization)     
    {    
    	String sqlInsert = "INSERT INTO charitableOrganization (OrganizationName, StreetAddress, City, Province, PostalCode, UserID) VALUES (?, ?, ?, ?, ?, ?)";
        
    	try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement insertStatement = dbConnection.prepareStatement(sqlInsert)) 
        {
            insertStatement.setString(1, organization.getOrganizationName());
            insertStatement.setString(2, organization.getStreetAddress());
            insertStatement.setString(3, organization.getCity());
            insertStatement.setString(4, organization.getProvince());
            insertStatement.setString(5, organization.getPostalCode());
            insertStatement.setInt(6, organization.getUserId());
            
            insertStatement.executeUpdate();
            LOGGER.info("Charitable organization details added for UserID: " + organization.getUserId());
        }
        catch (SQLException e) 
        {
            LOGGER.error("Failed to add charitable organization details: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void removeUser(int userID) 
    {
        
    	String sqlQuery = "DELETE FROM users WHERE userID = ?";
        
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
        	 PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) 
        {
            selectStatement.setInt(1, userID);
            selectStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public AbstractUser returnUser(int userID) 
    {
    	String sqlQuery = "SELECT * FROM users WHERE userID = ?";
        
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
        	 PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) 
        {
            selectStatement.setInt(1, userID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) 
            {
                return userDataExtractorService.extractUserFromResultSet(resultSet);
            }
        } 
        catch (SQLException e) 
        {
            LOGGER.error("SQL Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public Retailer getRetailerSpecificData(int userID) 
    {
      	String sqlQuery = "SELECT * FROM retailer WHERE UserID = ?";
        
    	try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
    		 PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) 
    	{
            selectStatement.setInt(1, userID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) 
            {
                return userDataExtractorService.extractRetailerDataFromResultSet(resultSet);
            }
        } 
    	catch (SQLException e) 
    	{
            LOGGER.error("SQL Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Consumer getConsumerSpecificData(int userID)
    {
        String sqlQuery = "SELECT * FROM consumer WHERE UserID = ?";
    
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
        	 PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) 
        {
            selectStatement.setInt(1, userID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) 
            {
                return userDataExtractorService.extractConsumerDataFromResultSet(resultSet);
            }
        } 
        catch (SQLException e) 
        {
            LOGGER.error("SQL Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CharitableOrganization getCharitableOrganizationSpecificData(int userID) 
    {
        String sqlQuery = "SELECT * FROM charitableOrganization WHERE UserID = ?";
    
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
        	 PreparedStatement selectStatement = connection.prepareStatement(sqlQuery)) 
        {
            selectStatement.setInt(1, userID);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) 
            {
                return userDataExtractorService.extractCharitableOrganizationDataFromResultSet(resultSet);
            }
        } 
        catch (SQLException e) 
        {
            LOGGER.error("SQL Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    
    @Override
    public AbstractUser returnUserByEmail(String email) {
        String sqlQuery = "SELECT * FROM user WHERE Email = ?";

        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
             PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) 
        {
            
            selectStatement.setString(1, email);
            
            try (ResultSet resultSet = selectStatement.executeQuery()) 
            {
                if (resultSet.next()) 
                {
                    return userDataExtractorService.extractUserFromResultSet(resultSet);
                }
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        
        }
        return null;
    }

    @Override
    public List<AbstractUser> returnAllUsers() 
    {
        List<AbstractUser> users = new ArrayList<>();
        String sqlQuery = "SELECT * FROM users";
        try (Statement selectStatement = connection.createStatement();
             ResultSet resultSet = selectStatement.executeQuery(sqlQuery)) 
        {
            while (resultSet.next()) 
            {
                User user = (User) userDataExtractorService.extractUserFromResultSet(resultSet);
                users.add(user);
            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }
        return users;
    }
    

}
