package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public abstract class BasicMole {
	private int point = 100;
	private long lifeTimeMillis = 2000;
	private EnumMoleType type = EnumMoleType.BASIC;
	
	private final long deathTime;

	public BasicMole(long birthTime) {
		this.deathTime = birthTime + lifeTimeMillis;
	}

	public void whac() {
//		ViewController.debugInfo("whaced!");
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

	protected void setType(EnumMoleType type) {
		this.type = type;
	}
	
	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public void setLifeTimeMillis(long lifeTimeMillis) {
		this.lifeTimeMillis = lifeTimeMillis;
	}

}
