package com.chocolat0w0.whac_a_mole;

import java.util.Observable;
import java.util.Random;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class Holes extends Observable{
	
	static final int HOLE_ROW = 3;
	static final int HOLE_COLUMN = 3;
	static final int HOLE_NUMBER = HOLE_ROW * HOLE_COLUMN;
	// モグラ出現確率。数字が大きいほど低確率。モグラの種類数より大きい必要がある。全モグラ同確率。
	static final int RANDOM_FACTOR = 10;
	
	private Mole[] mole = new Mole[HOLE_NUMBER];
	
	public Holes() {
		long birthTime = System.currentTimeMillis();
		for (int i = 0; i < HOLE_NUMBER; i++) {
			mole[i] = new NullMole(birthTime, i);
		}
	}
	
	public int getRandomHoleNumber() {
		Random r = new Random();
		return r.nextInt(HOLE_NUMBER * RANDOM_FACTOR);
	}
	
	public void createMole(int holeNum, int type) {
		if (HOLE_NUMBER <= holeNum) {
			return;
		}
		if (getMole(holeNum).getType() == EnumMoleType.NULL) {
			switch(type) {
			case 0:
				// TODO: 増えてきたら分離必要
				// TODO: 状態クラスを渡してnew　とか　パラメータ渡して作る　とか
				this.mole[holeNum] = new MiddlePointMole(System.currentTimeMillis(), holeNum);
				break;
			case 1:
				this.mole[holeNum] = new HighPointMole(System.currentTimeMillis(), holeNum);
				break;
			case 2:
				this.mole[holeNum] = new MinusPointMole(System.currentTimeMillis(), holeNum);
				break;
			default:
				return;
			}
			setChanged();
		}
	}
	
	public int getTouchedMolePoint(int holeNum) {
		return getMole(holeNum).getPoint();
	}
	
	public void touch(int holeNum) {
		if (getMole(holeNum).getType() != EnumMoleType.NULL) {
			getMole(holeNum).whac();
			setChanged();
//			うまくうごかなーい
//			viewController.popGotPoint(holeNum, mole[holeNum]);
			mole[holeNum] = new NullMole(System.currentTimeMillis(), holeNum);
		}
	}

	public void removeMoleLifeTimeEnded(int holeNum, long currentTime) {
		if(getMole(holeNum) != null && !getMole(holeNum).isLiving(currentTime)) {
			mole[holeNum] = new NullMole(System.currentTimeMillis(), holeNum);
			setChanged();
		}
	}

	public void removeAllMole() {
		long birthTime = System.currentTimeMillis();
		for(int i = 0; i < HOLE_NUMBER; i++) {
			setChanged();
			mole[i] = new NullMole(birthTime, i);
		}
	}

	public Mole getMole(int holeNum) {
		if (Holes.HOLE_NUMBER <= holeNum) {
			return null;
		}
		for (Mole m : mole) {
			if (m.getHoleNumber() == holeNum) {
				return m;
			}
		}
		return null;
	}	
}
