package com.chocolat0w0.whac_a_mole;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


class HoleView {

	private Mole mole = null;
	
	public HoleView(int holeNum, Mole mole) {
		this.mole = mole;
	}
	
	public void changeMole(Mole mole) {
		this.mole = mole;
	}
	
	int getImageId() {
		if(mole == null) {
			return R.drawable.hole;
		}
		switch(mole.getType()) {
		case MIDDLE :
			return R.drawable.mole1;
		case HIGH :
			return R.drawable.mole2;
		case MINUS :
			return R.drawable.mole3;
		case NULL :
			return R.drawable.hole;
		default :
			break;
		}

		return R.drawable.hole;
	}

	public Bitmap getImage(HolesView holesView) {
		return BitmapFactory.decodeResource(holesView.getResources(), getImageId());
	}
	
}
