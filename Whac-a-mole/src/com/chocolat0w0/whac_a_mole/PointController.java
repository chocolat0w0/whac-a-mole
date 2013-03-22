package com.chocolat0w0.whac_a_mole;

public class PointController {

	private ViewController viewController;
	private Point mPoint;
	
	public PointController(ViewController viewController) {
		this.viewController = viewController;
		this.mPoint = new Point();
	}

	public void add(int point) {
		mPoint.add(point);
		int totalPoint = mPoint.getPoint();
		viewController.changePoint(totalPoint);
	}

	public void initPoint() {
		mPoint.init();
	}
}
