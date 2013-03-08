package com.chocolat0w0.whac_a_mole;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	static final long LIMIT_TIME_MILLIS = 10 * 1000;
	
	private Handler mHandler = null;
	private ViewController viewController = null;
	private MoleController moleController = null;
	private Timer mTimer = null;
	private long startTimeMillis = 0;
	private long elapsedTimeMillis = 0;
	
	public TimerController(ViewController viewCtr, MoleController moleCtr, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
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
					viewController.displayEndMenu();
					viewController.refresh();
					return;
				}

				viewController.changeTime(LIMIT_TIME_MILLIS - elapsedTimeMillis);
				moleController.createMole(moleController.randomHoleNumber(), new Random().nextInt(3));
				moleController.removeMoleLifeTimeEnded();
				viewController.refresh();
			}
		});
	}
	
//	private long currentTimeSeconds() {
//		return System.currentTimeMillis() / 1000;
//	}

}
