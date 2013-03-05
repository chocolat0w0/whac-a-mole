package com.chocolat0w0.whac_a_mole;

public class PointController {

	private int totalPoint;
	private ViewController viewController;
	
	public PointController(ViewController viewController) {
		this.totalPoint = 0;
		this.viewController = viewController;
	}

	public void add(int point) {
		totalPoint += point;
		viewController.changePoint(totalPoint);
	}
}
