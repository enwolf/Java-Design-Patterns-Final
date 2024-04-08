package org.cst8288.finalproject.manager;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.cst8288.finalproject.subscriptions.Alert;
import org.cst8288.finalproject.subscriptions.Subscriber;
import org.cst8288.finalproject.dao.SubscriberDAO;
import org.cst8288.finalproject.dataaccess.DataSource;
import org.cst8288.finalproject.enums.ContactMethod;

public class SubscriptionManager extends SubscriberDAO{
	public SubscriberDAO subscriberDAO;
	public List<Subscriber> subscribers;
	public Alert alertMessage;
	
	private Subscriber subscriber;
			
	public SubscriptionManager(SubscriberDAO subscriberDAO) {
		
	}
	
	public void subscribe(Subscriber subscriber) {
		
	}
	
	public void unsubscribe(int subscriberID) {
        
	}
	
	public List<Subscriber> listSubscribers(){
		return subscribers;	
	}

	public void updateSubsContactInfo(int subID, ContactMethod contactMethod, String contactInfo) {
		subscriber.setId(subID);
		subscriber.setMethod(contactMethod);
		subscriber.setInfo(contactInfo);
	}
	
	public Subscriber getSubDetails(){
		return subscriber;
	}
	
	public void notifySub(Alert alert) {
		
	}
	
	public void sendAlertToSubscriber(Subscriber subscriber, Alert alert) {
		
	}
}
