package org.cst8288.finalproject.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.users.User;

/**
 * Provides services to extract user data from SQL {@link ResultSet} objects.
 * This class includes methods to deserialize result sets into various user-related
 * objects such as {@link User}, {@link Retailer}, {@link Consumer}, and 
 * {@link CharitableOrganization}. These services centralize the transformation of JDBC
 * data into business objects, adhering to the DRY principle and improving maintainability.
 * 
 * @author Robin Phillis
 * @version 1.0
 * @since 2024-04-20
 * @see User
 * @see Retailer
 * @see Consumer
 * @see CharitableOrganization
 */
public class UserDataExtractorService {
    
    /**
     * Constructs a new UserDataExtractorService.
     */
    public UserDataExtractorService() { }

    /**
     * Extracts a {@link User} object from the provided SQL {@link ResultSet}.
     * This method reads user-related data fields from the result set and creates
     * a User object populated with this data.
     *
     * @param resultSet The ResultSet from which to extract user data.
     * @return A User object populated with data from the ResultSet.
     * @throws SQLException if accessing the ResultSet data fails.
     */
    public AbstractUser extractUserFromResultSet(ResultSet resultSet) throws SQLException 
    {
        User user = new User();
        
        user.setUserId(resultSet.getInt("UserID"));
        user.setUserFirstName(resultSet.getString("FirstName"));
        user.setUserLastName(resultSet.getString("LastName"));
        user.setEmailAddress(resultSet.getString("Email"));
        user.setPassword(resultSet.getString("Password"));
        user.setUserType(UserType.valueOf(resultSet.getString("UserType").toUpperCase()));
    
        return user;
    }

    /**
     * Extracts a {@link Retailer} object from the provided SQL {@link ResultSet}.
     * This method reads retailer-specific data fields from the result set and creates
     * a Retailer object populated with this data.
     *
     * @param resultSet The ResultSet from which to extract retailer data.
     * @return A Retailer object populated with data from the ResultSet.
     * @throws SQLException if accessing the ResultSet data fails.
     */
    public Retailer extractRetailerDataFromResultSet(ResultSet resultSet) throws SQLException 
    {
        Retailer retailer = new Retailer();
        
        retailer.setUserId(resultSet.getInt("UserID"));
        retailer.setStoreName(resultSet.getString("StoreName"));
        retailer.setStreetAddress(resultSet.getString("StreetAddress"));
        retailer.setCity(resultSet.getString("City"));
        retailer.setProvince(resultSet.getString("Province"));
        retailer.setPostalCode(resultSet.getString("PostalCode"));
        
        return retailer;
    }

    /**
     * Extracts a {@link Consumer} object from the provided SQL {@link ResultSet}.
     * This method reads consumer-specific data fields from the result set and creates
     * a Consumer object populated with this data.
     *
     * @param resultSet The ResultSet from which to extract consumer data.
     * @return A Consumer object populated with data from the ResultSet.
     * @throws SQLException if accessing the ResultSet data fails.
     */
    public Consumer extractConsumerDataFromResultSet(ResultSet resultSet) throws SQLException 
    {
        Consumer consumer = new Consumer();
    
        consumer.setUserId(resultSet.getInt("UserID"));
        consumer.setPhoneNumber(resultSet.getString("PhoneNumber"));
        consumer.setStreetAddress(resultSet.getString("StreetAddress"));
        consumer.setCity(resultSet.getString("City"));
        consumer.setProvince(resultSet.getString("Province"));
        consumer.setPostalCode(resultSet.getString("PostalCode"));
        consumer.setAccountBalance(resultSet.getDouble("AccountBalance"));
        
        return consumer;
    }

    /**
     * Extracts a {@link CharitableOrganization} object from the provided SQL {@link ResultSet}.
     * This method reads data specific to charitable organizations from the result set and creates
     * a CharitableOrganization object populated with this data.
     *
     * @param resultSet The ResultSet from which to extract charitable organization data.
     * @return A CharitableOrganization object populated with data from the ResultSet.
     * @throws SQLException if accessing the ResultSet data fails.
     */
    public CharitableOrganization extractCharitableOrganizationDataFromResultSet(ResultSet resultSet) throws SQLException 
    {       
        CharitableOrganization charitableOrganization = new CharitableOrganization();
    
        charitableOrganization.setUserId(resultSet.getInt("UserID"));
        charitableOrganization.setOrganizationName(resultSet.getString("OrganizationName"));
        charitableOrganization.setStreetAddress(resultSet.getString("StreetAddress"));
        charitableOrganization.setCity(resultSet.getString("City"));
        charitableOrganization.setProvince(resultSet.getString("Province"));
        charitableOrganization.setPostalCode(resultSet.getString("PostalCode"));
        
        return charitableOrganization;
    }

}

