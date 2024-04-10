package org.cst8288.finalproject.subscriptions;

import org.cst8288.finalproject.dao.SubscriberDAO;
import org.cst8288.finalproject.interfaces.SurplusObserver;
import org.cst8288.finalproject.manager.SubscriptionManager;

public class SurplusNotifier implements SurplusObserver{
	SubscriberDAO dao = new SubscriberDAO();
	SubscriptionManager sm = new SubscriptionManager(dao);
	
	@Override
	public void update(String alertMessage) {
		Alert alert = new Alert();
		// TODO Auto-generated method stub
		alert.setAlertMessage(alertMessage);
		System.out.println("Sending email notification...");
		sm.notifySubs(alert);
		
	}

}
