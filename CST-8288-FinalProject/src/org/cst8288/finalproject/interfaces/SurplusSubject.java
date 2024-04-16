package org.cst8288.finalproject.interfaces;

public interface SurplusSubject {
    void registerObserver(SurplusObserver observer);
    void unregisterObserver(SurplusObserver observer);
    void notifyObservers(String data);
}
