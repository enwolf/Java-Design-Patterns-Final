package org.cst8288.finalproject.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.cst8288.finalproject.subscriptions.Alert;
import org.cst8288.finalproject.subscriptions.Subscriber;
import org.cst8288.finalproject.dao.SubscriberDAO;
import org.cst8288.finalproject.enums.ContactMethod;
import org.cst8288.finalproject.manager.SubscriptionManager;
import org.junit.jupiter.api.Test;

class AlertsTest {
	Subscriber subscriber = new Subscriber();
	Alert alert = new Alert();
	SubscriberDAO dao = new SubscriberDAO();
	SubscriptionManager sm = new SubscriptionManager(dao);
	
	@Test
	void testSendEmail() {
		subscriber.setId(1);
		subscriber.setMethod(ContactMethod.EMAIL);
		subscriber.setInfo("kroo0008@algonquinlive.com");
		
		
		alert.setAlertMessage("This is a test alert");
		
		try {
		sm.sendAlertToSubscriber(subscriber, alert);
		}catch(Exception e){
		    System.err.println("Error sending alert to subscriber: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	
	@Test
	void testAddSubscriber() {
		subscriber.setId(1);
		subscriber.setMethod(ContactMethod.EMAIL);
		subscriber.setInfo("kroo0008@algonquinlive.com");
		
		try {
		sm.subscribe(subscriber);
		}catch(Exception e){
		    System.err.println("Error adding subscribing: " + e.getMessage());
		    e.printStackTrace();
		}
	}
}
