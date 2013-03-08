package com.chocolat0w0.whac_a_mole;

public class PointController {

	private int totalPoint = 0;
	private ViewController viewController;
	
	public PointController(ViewController viewController) {
		this.viewController = viewController;
	}

	public void add(int point) {
		totalPoint += point;
		viewController.changePoint(totalPoint);
	}

	public void initPoint() {
		this.totalPoint = 0;
	}
}
