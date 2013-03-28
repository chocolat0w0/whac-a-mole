package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

class MinusPointMole extends BasicMole implements Mole {

	public MinusPointMole(long birthTime) {
		super(birthTime);
		setPoint(-600);
		setType(EnumMoleType.MINUS);
	}

}
