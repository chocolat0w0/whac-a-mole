package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

class HighPointMole extends BasicMole implements IMole {

	public HighPointMole(long birthTime) {
		super(birthTime);
		setPoint(500);
		setType(EnumMoleType.HIGH);
	}
	
	public HighPointMole(long birthTime, int holeNumber) {
		super(birthTime, holeNumber);
		setPoint(500);
		setType(EnumMoleType.HIGH);
	}
}
