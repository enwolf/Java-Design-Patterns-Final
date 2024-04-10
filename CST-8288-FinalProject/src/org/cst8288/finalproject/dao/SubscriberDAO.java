package org.cst8288.finalproject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.enums.ContactMethod;
import org.cst8288.finalproject.interfaces.SubscriberDAOInterface;
import org.cst8288.finalproject.subscriptions.Subscriber;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SubscriberDAO implements SubscriberDAOInterface{
	private int subID;
	private ContactMethod contactMethod;
	private String contactInfo;
	
	private Subscriber subscriber;
	
	DataSource instance = DataSource.getInstance(); //datasource
	
	@Override
	public void createSubscriber(Subscriber subscriber) {
		try (Connection connection = instance.getConnectionToDatabase()) {
            if (connection != null) {
                System.out.println("Database connection established successfully.");

                //Insert data into a table
                String insertQuery = "INSERT INTO subscription (UserID, ContactMethod, ContactInformation) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, subscriber.getId());
                    preparedStatement.setString(2, subscriber.getMethod().name());//using .name() to convert the ENUM to a string to insert into mySQL using JDBC
                    preparedStatement.setString(3, subscriber.getInfo());
                    int rowsInserted = preparedStatement.executeUpdate();
                    System.out.println(rowsInserted + " row inserted.");
                } catch (SQLException e) {
                    System.err.println("Error executing insert query: " + e.getMessage());
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
	}

	@Override
	public Subscriber retrieveSubscriber(int subscriberID) {
		Subscriber subcriberRetrieved = new Subscriber();
		
		try (Connection connection = instance.getConnectionToDatabase()) {
            if (connection != null) {
                System.out.println("Database connection established successfully.");

                //Insert data into a table
                String insertQuery = "SELECT * FROM subscription WHERE UserID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {             	
                    preparedStatement.setInt(1, subscriberID);
                    
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                        	subcriberRetrieved.setId(subscriberID);
                            ContactMethod contactMethod = ContactMethod.valueOf(resultSet.getString("ContactMethod").toUpperCase());
                            subcriberRetrieved.setMethod(contactMethod);
                            subcriberRetrieved.setInfo(resultSet.getString("ContactInformation"));
                        }
                    } catch (SQLException e) {
                        System.err.println("Error executing inner query: " + e.getMessage());
                    }
                } catch (SQLException e) {
                    System.err.println("Error executing insert query: " + e.getMessage());
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
		return subcriberRetrieved;
	}

	@Override
	public void updateSubscriber(int subID, ContactMethod contactMethod, String contactInfo) {		
		try (Connection connection = instance.getConnectionToDatabase()) {
            if (connection != null) {
                System.out.println("Database connection established successfully.");

                //Insert data into a table
                String insertQuery = "UPDATE subscription SET ContactMethod = ?, ContactInformation = ? WHERE UserID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {             	
                    preparedStatement.setString(1, contactMethod.name());
                    preparedStatement.setString(2, contactInfo);
                    preparedStatement.setInt(3, subID);
                    int rowsInserted = preparedStatement.executeUpdate();
                    System.out.println(rowsInserted + " row edited.");
                } catch (SQLException e) {
                    System.err.println("Error executing insert query: " + e.getMessage());
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
	}

	@Override
	public void deleteSubscriber(int subscriberID) {
		try (Connection connection = instance.getConnectionToDatabase()) {
            if (connection != null) {
                System.out.println("Database connection established successfully.");

                // Remove data from a table
                String deleteQuery = "DELETE FROM subscription WHERE UserID = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                    preparedStatement.setInt(1, subscriberID);
                    int rowsAffected = preparedStatement.executeUpdate();
                    System.out.println(rowsAffected + " row deleted.");
                } catch (SQLException e) {
                    System.err.println("Error executing insert query: " + e.getMessage());
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
	}

	@Override
	public List<Subscriber> listSubscribers() {
		Subscriber subcriberRetrieved = new Subscriber();
		List<Subscriber> list = new ArrayList();
		
		try (Connection connection = instance.getConnectionToDatabase()) {
            if (connection != null) {
                System.out.println("Database connection established successfully.");

                //Insert data into a table
                String insertQuery = "SELECT * FROM subscription";
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                        	subcriberRetrieved.setId(resultSet.getInt("UserId"));
                            ContactMethod contactMethod = ContactMethod.valueOf(resultSet.getString("ContactMethod").toUpperCase());
                            subcriberRetrieved.setMethod(contactMethod);
                            subcriberRetrieved.setInfo(resultSet.getString("ContactInformation"));
                            list.add(subcriberRetrieved);
                        }
                    } catch (SQLException e) {
                        System.err.println("Error executing inner query: " + e.getMessage());
                    }
                } catch (SQLException e) {
                    System.err.println("Error executing insert query: " + e.getMessage());
                }
            } else {
                System.err.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
		return list;
	}
	//Observer for sending newsletter

}
