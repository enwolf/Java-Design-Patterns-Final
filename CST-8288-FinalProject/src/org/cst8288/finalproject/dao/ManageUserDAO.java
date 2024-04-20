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

/**
 * Data Access Object (DAO) for managing user-related operations with the database.
 * This class handles CRUD operations (Create, Read, Update, Delete) for user data,
 * including specific operations for different types of users (Consumer, Retailer, Charitable Organization).
 * It utilizes {@link UserDataExtractorService} for extracting user data from {@link ResultSet}.
 * The operations are performed using SQL queries executed via JDBC.
 *
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see org.cst8288.finalproject.users.AbstractUser
 * @see org.cst8288.finalproject.users.Consumer
 * @see org.cst8288.finalproject.users.Retailer
 * @see org.cst8288.finalproject.users.CharitableOrganization
 * @see org.cst8288.finalproject.interfaces.ManageUserDAOInterface
 * @see UserDataExtractorService
 */
public class ManageUserDAO implements ManageUserDAOInterface {

	private static final LMSLogger LOGGER = LMSLogger.getInstance();
	private UserDataExtractorService userDataExtractorService;
    private Connection connection;
    
    public ManageUserDAO(UserDataExtractorService userDataExtractorService) 
    {
    	this.userDataExtractorService = new UserDataExtractorService();
    }

    /**
     * Adds a user to the database.
     * This method inserts user information into the 'user' table, including first name, last name, email, password, and user type.
     * It generates a unique user ID assigned by the database and returns it for further reference.
     * If the insertion is successful, it logs the number of affected rows and provides details about the added user.
     *
     * @param user The user object containing information to be added to the database.
     * @return The generated user ID assigned by the database upon successful insertion.
     */
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
    
    /**
     * Updates user information in the database.
     * This method updates the user's first name, last name, email, and password in the 'user' table based on the provided updatedUser object.
     * It checks for duplicate email addresses before performing the update to maintain data integrity.
     * Upon successful update, it logs the number of affected rows and details of the updated user.
     *
     * @param updatedUser The updated user object containing new information.
     */
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
    
    /**
     * Adds consumer-specific details to the database.
     * This method inserts consumer-specific information, including phone number, street address, city, province, postal code, account balance, and user ID, into the 'consumer' table.
     * It logs the addition of consumer details along with the associated user ID.
     *
     * @param consumer The consumer object containing details to be added to the database.
     */
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

    /**
     * Adds retailer-specific details to the database.
     * This method inserts retailer-specific information, such as store name, street address, city, province, postal code, and user ID, into the 'retailer' table.
     * After successful insertion, it logs the addition of retailer details and the associated user ID.
     *
     * @param retailer The retailer object containing details to be added to the database.
     */
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

    /**
     * Adds charitable organization-specific details to the database.
     * This method inserts charitable organization-specific information, including organization name, street address, city, province, postal code, and user ID, into the 'charitableOrganization' table.
     * It logs the addition of charitable organization details and the associated user ID.
     *
     * @param organization The charitable organization object containing details to be added to the database.
     */
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
    
    /**
     * Removes a user from the database.
     * This method deletes the user's record from the 'users' table based on the provided user ID.
     * It logs the deletion of the user and the associated user ID.
     *
     * @param userID The ID of the user to be removed from the database.
     */
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
    
    /**
     * Retrieves a user from the database based on user ID.
     * This method queries the 'users' table to retrieve user information for the specified user ID and returns the corresponding user object.
     * If the user is found, it returns the user object; otherwise, it returns null.
     * It logs any SQL errors that occur during the retrieval process.
     *
     * @param userID The ID of the user to retrieve from the database.
     * @return The user object retrieved from the database, or null if the user is not found.
     */
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
    /**
     * Retrieves retailer-specific data from the database based on user ID.
     * This method queries the 'retailer' table to retrieve retailer-specific information for the specified user ID.
     * If the retailer data is found, it extracts and returns the retailer object using the UserDataExtractorService.
     * If no data is found or an SQL error occurs, it logs the error and returns null.
     *
     * @param userID The ID of the user for whom retailer-specific data is to be retrieved.
     * @return The retailer object containing retailer-specific data, or null if not found or an error occurs.
     */
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
    
    /**
     * Retrieves consumer-specific data from the database based on user ID.
     * This method queries the 'consumer' table to retrieve consumer-specific information for the specified user ID.
     * If the consumer data is found, it extracts and returns the consumer object using the UserDataExtractorService.
     * If no data is found or an SQL error occurs, it logs the error and returns null.
     *
     * @param userID The ID of the user for whom consumer-specific data is to be retrieved.
     * @return The consumer object containing consumer-specific data, or null if not found or an error occurs.
     */
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
    
    /**
     * Retrieves charitable organization-specific data from the database based on user ID.
     * This method queries the 'charitableOrganization' table to retrieve organization-specific information for the specified user ID.
     * If the organization data is found, it extracts and returns the charitable organization object using the UserDataExtractorService.
     * If no data is found or an SQL error occurs, it logs the error and returns null.
     *
     * @param userID The ID of the user for whom organization-specific data is to be retrieved.
     * @return The charitable organization object containing organization-specific data, or null if not found or an error occurs.
     */
    @Override
    public CharitableOrganization getCharitableOrganizationSpecificData(int userID) 
    {
        String sqlQuery = "SELECT * FROM charitableOrganization WHERE UserID = ?";
    
        try (Connection dbConnection = DataSource.getInstance().getConnectionToDatabase();
        	 PreparedStatement selectStatement = dbConnection.prepareStatement(sqlQuery)) 
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

    /**
     * Retrieves a user from the database based on email address.
     * This method queries the 'user' table to retrieve user information for the specified email address.
     * If the user is found, it extracts and returns the user object using the UserDataExtractorService.
     * If no data is found or an SQL error occurs, it logs the error and returns null.
     *
     * @param email The email address of the user to retrieve from the database.
     * @return The user object retrieved from the database, or null if not found or an error occurs.
     */
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
    
    /**
     * Retrieves all users from the database.
     * This method queries the 'users' table to retrieve information for all users stored in the database.
     * It iterates over the result set, extracts each user object using the UserDataExtractorService, and adds them to a list.
     * If no users are found or an SQL error occurs, it logs the error and returns an empty list.
     *
     * @return A list containing all user objects retrieved from the database, or an empty list if no users are found or an error occurs.
     */
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
