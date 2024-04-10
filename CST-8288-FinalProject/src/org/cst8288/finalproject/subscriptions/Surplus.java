package org.cst8288.finalproject.subscriptions;

import java.util.ArrayList;
import java.util.List;

import org.cst8288.finalproject.interfaces.SurplusObserver;
import org.cst8288.finalproject.interfaces.SurplusSubject;

public class Surplus implements SurplusSubject{
	private List<SurplusObserver> observers = new ArrayList<>();

	@Override
	public void registerObserver(SurplusObserver observer) {
        observers.add(observer);		
	}

	@Override
	public void unregisterObserver(SurplusObserver observer) {
		observers.remove(observer);
	}
	/* !!! IMPORTANT !!! 
	*  NEEDS TO BE ADDED TO SURPLUS FOOD INSERTION TO NOTIFY OBSERVER OF SURPLUS FOOD.
	*  The String data is the alert message that will be sent out to all users that are subscribed to them.
	*/
	@Override
	public void notifyObservers(String data) {
		for (SurplusObserver observer : observers) {
            observer.update(data);
        }
	}
}
