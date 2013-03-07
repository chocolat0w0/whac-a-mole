package com.chocolat0w0.whac_a_mole;

import java.util.Random;

public class MoleController {
	
	static final int HOLE_ROW = 3;
	static final int HOLE_COLUMN = 3;
	static final int HOLE_NUMBER = HOLE_ROW * HOLE_COLUMN;
	private static final int RANDOM_FACTOR = 10;
	
	private PointController pointController = null;
	private ViewController viewController = null;
	private Mole[] mole = new Mole[HOLE_NUMBER];
	
	public MoleController(PointController pointCtr, ViewController viewCtr) {
		this.pointController = pointCtr;
		this.viewController = viewCtr;
	}
	
	public int randomHoleNumber() {
		Random r = new Random();
		return r.nextInt(HOLE_NUMBER * RANDOM_FACTOR);
	}
	
	public void createMole(int holeNum) {
		if (HOLE_NUMBER <= holeNum) {
			return;
		}
		if (mole[holeNum] == null) {
			this.mole[holeNum] = new Mole(pointController, System.currentTimeMillis());
			viewController.addMole(holeNum);
		}
	}
	
	public void touch(int holeNum) {
		if (mole[holeNum] != null) {
			mole[holeNum].whac();
			mole[holeNum] = null;
			viewController.removeMole(holeNum);
		}
	}

	public void removeAllMole() {
		for(int i = 0; i < HOLE_NUMBER; i++) {
			mole[i] = null;
			viewController.removeMole(i);
		}
	}
	
	public void endMoleLifeTime() {
		for(int i = 0; i < HOLE_NUMBER; i++) {
			if(mole[i] != null && !mole[i].isLiving(System.currentTimeMillis())) {
				mole[i] = null;
				viewController.removeMole(i);
			}
		}
	}	
}
