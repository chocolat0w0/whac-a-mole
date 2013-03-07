package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public abstract class BasicMole {
	private int point = 100;
	private long life_time_millis = 2000;
	private EnumMoleType type = EnumMoleType.MIDDLE;
	
	private PointController pointController = null;
	private final long deathTime;

	public BasicMole(PointController pointCtr, long birthTime) {
		this.pointController = pointCtr;
		this.deathTime = birthTime + life_time_millis;
	}

	public void whac() {
		ViewController.debugInfo("whaced!");
		pointController.add(getPoint());
	}
	
	public boolean isLiving(long currentTime) {
		if (currentTime  < deathTime) {
			return true;
		}
		return false;
	}

	public EnumMoleType getType() {
		return type;
	}

	public void setType(EnumMoleType type) {
		this.type = type;
	}
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

}
