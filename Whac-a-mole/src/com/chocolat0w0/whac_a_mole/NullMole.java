package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class NullMole extends BasicMole implements IMole {

	public NullMole(long birthTime) {
		super(birthTime);
		setPoint(0);
		setType(EnumMoleType.NULL);
		setLifeTimeMillis(Long.MAX_VALUE);
	}
	
	public NullMole(long birthTime, int holeNumber) {
		super(birthTime, holeNumber);
		setPoint(0);
		setType(EnumMoleType.NULL);
		setLifeTimeMillis(Long.MAX_VALUE);
	}

}
