package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class MiddlePointMole extends BasicMole implements Mole {

	public MiddlePointMole(long birthTime) {
		super(birthTime);
		setPoint(300);
		setType(EnumMoleType.MIDDLE);
	}

}
