package com.chocolat0w0.whac_a_mole;

public class HoleController {
	private PointController pointController = null;
	private ViewController viewController = null;
	private Mole mole = null;
	
	public HoleController(PointController pointCtr, ViewController viewCtr) {
		this.pointController = pointCtr;
		this.viewController = viewCtr;
	}
	
	public void createMole(int holeNum) {
		this.mole = new Mole();
		viewController.addMole(holeNum);
	}
	
}
