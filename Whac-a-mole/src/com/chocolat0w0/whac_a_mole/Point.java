package com.chocolat0w0.whac_a_mole;

public class Point {

	private int totalPoint;
	
	public Point() {
		this.totalPoint = 0;
	}
	
	public void add(int point) {
		totalPoint += point;
	}
	
	public void init() {
		totalPoint = 0;
	}
	
	public int getPoint() {
		return totalPoint;
	}
}
