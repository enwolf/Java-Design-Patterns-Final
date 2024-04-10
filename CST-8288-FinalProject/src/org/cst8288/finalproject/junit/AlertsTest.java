package org.cst8288.finalproject.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.cst8288.finalproject.subscriptions.Alert;
import org.cst8288.finalproject.subscriptions.Subscriber;
import org.cst8288.finalproject.dao.SubscriberDAO;
import org.cst8288.finalproject.enums.ContactMethod;
import org.cst8288.finalproject.manager.SubscriptionManager;
import org.junit.jupiter.api.Test;

class AlertsTest {
	Alert alert = new Alert();
	SubscriberDAO dao = new SubscriberDAO();
	SubscriptionManager sm = new SubscriptionManager(dao);
	
	@Test
	void testSendEmail() {
		Subscriber subscriber = new Subscriber();
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
	// Tests that adding a subscriber to the table works. Must ensure there is a user already in the user table with UserID 1
	@Test
	void testAddSubscriber() {
		Subscriber subscriber = new Subscriber();
		subscriber.setId(1);
		subscriber.setMethod(ContactMethod.EMAIL);
		subscriber.setInfo("kroo0008@algonquinlive.com");
		
		try {
		sm.subscribe(subscriber);
		}catch(Exception e){
		    System.err.println("Error adding subscriber: " + e.getMessage());
		    e.printStackTrace();
		}
	}
	// Tests functionality of changing subscription info
		@Test
		void testEditSubscriber() {
			Subscriber subscriber = new Subscriber();
			subscriber.setId(1);
			subscriber.setMethod(ContactMethod.PHONE);
			subscriber.setInfo("123-456-7890");
			
			try {
			sm.updateSubsContactInfo(subscriber.getId(), subscriber.getMethod(), subscriber.getInfo());
			}catch(Exception e){
			    System.err.println("Error editing subscriber: " + e.getMessage());
			    e.printStackTrace();
			}
		}
		// Tests functionality of changing subscription info
		@Test
		void testDeleteSub() {
			Subscriber subscriber = new Subscriber();
			subscriber.setId(1);
			subscriber.setMethod(ContactMethod.PHONE);
			subscriber.setInfo("123-456-7890");
			
			try {
			sm.unsubscribe(subscriber.getId());
			}catch(Exception e){
			    System.err.println("Error deleting subscriber: " + e.getMessage());
			    e.printStackTrace();
			}
		}
		// Tests functionality of retrieving subscription info
		@Test
		void testRetrieveSub() {
			Subscriber subscriber = new Subscriber();

			try {
			subscriber = sm.getSubDetails(1);
			System.out.println("Sub ID: " + subscriber.getId() + ", Sub Contact Method: " + subscriber.getMethod() + ", Sub Contact Info: " + subscriber.getInfo());
			}catch(Exception e){
			    System.err.println("Error retrieving subscriber: " + e.getMessage());
			    e.printStackTrace();
			}
		}
		// Tests functionality of retrieving list of subs
		@Test
		void testListSubs() {
			List<Subscriber> subList;

			try {
			subList = sm.listSubs();
			for (Subscriber listedSubscriber : subList){
				System.out.println("Sub ID: " + listedSubscriber.getId() + ", Sub Contact Method: " + listedSubscriber.getMethod() + ", Sub Contact Info: " + listedSubscriber.getInfo());
			}
			}catch(Exception e){
			    System.err.println("Error getting sub list: " + e.getMessage());
			    e.printStackTrace();
			}
		}
}
