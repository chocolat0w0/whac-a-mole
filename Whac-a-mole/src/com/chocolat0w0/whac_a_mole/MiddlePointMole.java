package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

class MiddlePointMole extends BasicMole implements IMole {

	public MiddlePointMole(long birthTime) {
		super(birthTime);
		setPoint(300);
		setType(EnumMoleType.MIDDLE);
	}

	public MiddlePointMole(long birthTime, int holeNumber) {
		super(birthTime, holeNumber);
		setPoint(300);
		setType(EnumMoleType.MIDDLE);
	}

}
