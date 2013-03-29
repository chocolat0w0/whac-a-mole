package com.chocolat0w0.whac_a_mole;

import java.util.Observable;
import java.util.Random;

public class MoleController extends Observable{
	
	static final int HOLE_ROW = 3;
	static final int HOLE_COLUMN = 3;
	static final int HOLE_NUMBER = HOLE_ROW * HOLE_COLUMN;
	// モグラ出現確率。数字が大きいほど低確率。モグラの種類数より大きい必要がある。全モグラ同確率。
	static final int RANDOM_FACTOR = 10;
	
	// TODO: テストのためにアクセス制限緩くしたよ
	Mole[] mole = new Mole[HOLE_NUMBER];
	
	public MoleController() {
	}
	
	public int randomHoleNumber() {
		Random r = new Random();
		return r.nextInt(HOLE_NUMBER * RANDOM_FACTOR);
	}
	
	public void createMole(int holeNum, int type) {
		if (HOLE_NUMBER <= holeNum) {
			return;
		}
		if (mole[holeNum] == null) {
			switch(type) {
			case 0:
				// TODO: 増えてきたら分離必要
				// TODO: 状態クラスを渡してnew　とか　パラメータ渡して作る　とか
				this.mole[holeNum] = new MiddlePointMole(System.currentTimeMillis());
				break;
			case 1:
				this.mole[holeNum] = new HighPointMole(System.currentTimeMillis());
				break;
			case 2:
				this.mole[holeNum] = new MinusPointMole(System.currentTimeMillis());
				break;
			default:
				return;
			}
			setChanged();
		}
	}
	
	public int getTouchedMolePoint(int holeNum) {
		if (mole[holeNum] == null) {
			return 0;
		}
		return mole[holeNum].getPoint();
	}
	
	public void touch(int holeNum) {
		if (mole[holeNum] != null) {
			mole[holeNum].whac();
			setChanged();
//			うまくうごかなーい
//			viewController.popGotPoint(holeNum, mole[holeNum]);
			mole[holeNum] = null;
		}
	}

	public void removeMoleLifeTimeEnded(int holeNum) {
		if(mole[holeNum] != null && !mole[holeNum].isLiving(System.currentTimeMillis())) {
			mole[holeNum] = null;
			setChanged();
		}
	}

	public void removeAllMole() {
		for(int i = 0; i < HOLE_NUMBER; i++) {
			setChanged();
			mole[i] = null;
		}
	}

	public Mole getMole(int holeNum) {
		if (MoleController.HOLE_NUMBER <= holeNum) {
			return null;
		}
		return mole[holeNum];
	}	
}
