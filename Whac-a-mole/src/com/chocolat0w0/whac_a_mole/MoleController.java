package com.chocolat0w0.whac_a_mole;

import java.util.Random;

public class MoleController {
	
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
	
	public boolean createMole(int holeNum, int type) {
		if (HOLE_NUMBER <= holeNum) {
			return false;
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
				return false;
			}
			return true;
		}
		return false;
	}
	
	public int touch(int holeNum) {
		if (mole[holeNum] != null) {
			mole[holeNum].whac();
//			うまくうごかなーい
//			viewController.popGotPoint(holeNum, mole[holeNum]);
			int point = mole[holeNum].getPoint();
			mole[holeNum] = null;
			return point;
		}
		return 0;
	}

	public boolean removeMoleLifeTimeEnded(int holeNum) {
			if(mole[holeNum] != null && !mole[holeNum].isLiving(System.currentTimeMillis())) {
				mole[holeNum] = null;
				return true;
			}
			return false;
	}

	public void removeAllMole() {
		for(int i = 0; i < HOLE_NUMBER; i++) {
			mole[i] = null;
		}
	}

	// TODO: テストのために作ったアクセッサだよ
	public Mole getMole(int holeNum) {
		if (MoleController.HOLE_NUMBER <= holeNum) {
			return null;
		}
		return mole[holeNum];
	}	
}
