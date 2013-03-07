package com.chocolat0w0.whac_a_mole;

public class Mole {
	private static final int POINT = 100;
	private static final long LIFE_TIME_MILLIS = 1000;;
	
	private PointController pointController = null;
	private final long deathTime;

	private long currentTime = 0;
	
	public Mole(PointController pointCtr, long birthTime) {
		this.pointController = pointCtr;
		this.deathTime = birthTime + LIFE_TIME_MILLIS;
	}

	public void whac() {
		ViewController.debugInfo("whaced!");
		pointController.add(POINT);
	}
	
	public boolean isLiving(long currentTime) {
		if (currentTime  < deathTime) {
			return true;
		}
		return false;
	}
}
