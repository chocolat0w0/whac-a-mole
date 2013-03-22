package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class HighPointMole extends BasicMole implements Mole {

	public HighPointMole(long birthTime) {
		super(birthTime);
		setPoint(500);
		setType(EnumMoleType.HIGH);
	}
}
