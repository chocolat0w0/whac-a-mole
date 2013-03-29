package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class NullMole extends BasicMole implements Mole {

	public NullMole(long birthTime) {
		super(birthTime);
		setPoint(0);
		setType(EnumMoleType.NULL);
		setLifeTimeMillis(Long.MAX_VALUE);
	}

}
