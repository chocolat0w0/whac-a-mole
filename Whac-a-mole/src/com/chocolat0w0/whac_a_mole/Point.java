package com.chocolat0w0.whac_a_mole;

import java.util.Observable;

public class Point extends Observable {

	private int totalPoint;
	
	public Point() {
		this.totalPoint = 0;
	}
	
	public void add(int point) {
		totalPoint += point;
		setChanged();
		notifyObservers(this);
	}
	
	public void init() {
		totalPoint = 0;
		setChanged();
		notifyObservers(this);
	}
	
	public int getPoint() {
		return totalPoint;
	}
}
