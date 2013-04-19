package com.chocolat0w0.whac_a_mole;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class Moles extends Observable{
	
	static final int HOLE_ROW = 3;
	static final int HOLE_COLUMN = 3;
	static final int HOLE_NUMBER = HOLE_ROW * HOLE_COLUMN;
	// モグラ出現確率。数字が大きいほど低確率。モグラの種類数より大きい必要がある。全モグラ同確率。
	static final int RANDOM_FACTOR = 10;
	
	private List<IMole> moles = new ArrayList<IMole>(HOLE_NUMBER);
	
	public Moles() {
		long birthTime = System.currentTimeMillis();
		for (int i = 0; i < HOLE_NUMBER; i++) {
			moles.add(new NullMole(birthTime, i));
		}
	}
	
	int getRandomHoleNumber() {
		Random r = new Random();
		return r.nextInt(HOLE_NUMBER * RANDOM_FACTOR);
	}
	
	void createMole(int holeNum, int type) {
		if (HOLE_NUMBER <= holeNum) {
			return;
		}
		if (getMoleAt(holeNum).getType() == EnumMoleType.NULL) {
			moles.remove(getMoleAt(holeNum));
			switch(type) {
			case 0:
				// TODO: 増えてきたら分離必要
				// TODO: 状態クラスを渡してnew　とか　パラメータ渡して作る　とか
				moles.add(new MiddlePointMole(System.currentTimeMillis(), holeNum));
				break;
			case 1:
				moles.add(new HighPointMole(System.currentTimeMillis(), holeNum));
				break;
			case 2:
				moles.add(new MinusPointMole(System.currentTimeMillis(), holeNum));
				break;
			default:
				moles.add(new NullMole(System.currentTimeMillis(), holeNum));
				return;
			}
			setChanged();
		}
	}
	
	IMole getMoleAt(int holeNum) {
		for(IMole m : moles) {
			if (m.getHoleNumber() == holeNum) {
				return m;
			}
		}
		// TODO: 例外投げる
		return null;
	}

	int getTouchedMolePoint(int holeNum) {
		if(HOLE_NUMBER < holeNum) {
			return 0;
		}
		return getMoleAt(holeNum).getPoint();
	}
	
	void touch(int holeNum) {
		if (getMoleAt(holeNum) == null) {
			return;
		}
		if (getMoleAt(holeNum).getType() != EnumMoleType.NULL) {
			getMoleAt(holeNum).whac();
			setChanged();
//			うまくうごかなーい
//			viewController.popGotPoint(holeNum, mole[holeNum]);
			removeMoleAt(holeNum);
		}
	}

	void removeMoleLifeTimeEnded(int holeNum, long currentTime) {
		if(getMoleAt(holeNum) != null && !getMoleAt(holeNum).isLiving(currentTime)) {
			removeMoleAt(holeNum);
			setChanged();
		}
	}

	private void removeMoleAt(int holeNum) {
		moles.remove(getMoleAt(holeNum));
		moles.add(new NullMole(System.currentTimeMillis(), holeNum));
	}

	void removeAllMole() {
		for(int i = 0; i < HOLE_NUMBER; i++) {
			setChanged();
			removeMoleAt(i);
		}
	}

}
