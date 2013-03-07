package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

interface Mole {
	public void whac();
	public boolean isLiving(long currentTime);
	public EnumMoleType getType();
}
