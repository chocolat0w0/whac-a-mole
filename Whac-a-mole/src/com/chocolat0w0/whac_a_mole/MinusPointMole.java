package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class MinusPointMole extends BasicMole implements Mole {

	public MinusPointMole(PointController pointCtr, long birthTime) {
		super(pointCtr, birthTime);
		setPoint(-100);
		setType(EnumMoleType.MINUS);
	}

}
