package com.chocolat0w0.whac_a_mole;

public class Mole {
	private static final int POINT = 100;
	
	private PointController pointController = null;
	
	public Mole(PointController pointCtr) {
		this.pointController = pointCtr;
	}

	public void whac() {
		ViewController.debugInfo("whaced!");
		pointController.add(POINT);
		
		
	}
}
