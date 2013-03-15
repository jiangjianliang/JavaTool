package com.wander.paper.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
	private List<Observer> obList = new ArrayList<Observer>();
	
	public void registerObserver(Observer observer) {
		obList.add(observer);
	}
	
	public boolean removeObserver(Observer observer) {
		return obList.remove(observer);
	}
	
	public void notifyObservsers() {
		for(Observer ob: obList){
			ob.update(this);
		}
	}
}
