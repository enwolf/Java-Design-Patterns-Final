package org.cst8288.finalproject.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.cst8288.finalproject.enums.UserType;
import org.cst8288.finalproject.users.AbstractUser;
import org.cst8288.finalproject.users.CharitableOrganization;
import org.cst8288.finalproject.users.Consumer;
import org.cst8288.finalproject.users.Retailer;
import org.cst8288.finalproject.users.User;

public class UserDataExtractorService {
	
	public UserDataExtractorService()
	{
		
	}

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
