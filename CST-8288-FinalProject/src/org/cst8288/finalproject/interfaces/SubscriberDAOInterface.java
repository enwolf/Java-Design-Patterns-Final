package org.cst8288.finalproject.interfaces;

import java.util.List;

import org.cst8288.finalproject.subscriptions.Subscriber;

public interface SubscriberDAOInterface {
	public void createSubscriber (Subscriber subscriber);
	
	public Subscriber retrieveSubscriber(int subsriberID);
	
	public void updateSubscriber(Subscriber subscriber);
	
	public void deleteSubscriber(int subscriberID);
	
	public List<Subscriber> listSubscribers();
}
