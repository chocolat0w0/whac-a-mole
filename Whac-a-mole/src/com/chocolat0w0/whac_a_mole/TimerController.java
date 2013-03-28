package com.chocolat0w0.whac_a_mole;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	static final long LIMIT_TIME_MILLIS = 10 * 1000;
	
	private Handler mHandler = null;
	private ViewController viewController = null;
	private HoleView holeView = null;
	private MoleController moleController = null;
	private Timer mTimer = null;
	private long startTimeMillis = 0;
	private long elapsedTimeMillis = 0;
	
	
	public TimerController(ViewController viewCtr, HoleView holeView, MoleController moleCtr, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.holeView = holeView;
		this.moleController = moleCtr;
		this.mTimer = mTimer;
		startTimeMillis = System.currentTimeMillis();
	}
	
	@Override
	public void run() {
		mHandler.post(new Runnable() {
			

			@Override
			public void run() {
				elapsedTimeMillis = System.currentTimeMillis() - startTimeMillis;
				if(LIMIT_TIME_MILLIS <= elapsedTimeMillis) {
					mTimer.cancel();
					moleController.removeAllMole();
					moleController.notifyObservers(moleController);
					viewController.refresh();
					viewController.displayEndMenu();
					viewController.refresh();
					holeView.invalidate();
					return;
				}

				viewController.changeTime(LIMIT_TIME_MILLIS - elapsedTimeMillis);
				int randomHoleNum = moleController.randomHoleNumber();
				moleController.createMole(randomHoleNum, new Random().nextInt(3));
				for (int i = 0; i < MoleController.HOLE_NUMBER; i++) {
					moleController.removeMoleLifeTimeEnded(i);
				}
				moleController.notifyObservers(moleController);
				viewController.refresh();
				holeView.invalidate();
			}
		});
	}
}
