package com.chocolat0w0.whac_a_mole;

import java.util.Observable;

public class TotalPoint extends Observable {

	private int totalPoint;
	
	public TotalPoint() {
		this.totalPoint = 0;
	}
	
	public void add(int point) {
		totalPoint += point;
		setChanged();
		notifyObservers();
	}
	
	public void init() {
		totalPoint = 0;
		setChanged();
		notifyObservers();
	}
	
	public int getPoint() {
		return totalPoint;
	}
}
