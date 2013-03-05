package com.chocolat0w0.whac_a_mole;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	static final long LIMIT_TIME_SECONDS = 10;
	
	private Handler mHandler = null;
	private ViewController viewController = null;
	private MoleController moleController = null;
	private Timer mTimer = null;
	private long startTimeSeconds = 0;
	private long elapsedTimeSeconds = 0;
	
	public TimerController(ViewController viewCtr, MoleController moleCtr, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.moleController = moleCtr;
		this.mTimer = mTimer;
		startTimeSeconds = currentTimeSeconds();
	}
	
	@Override
	public void run() {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				elapsedTimeSeconds = currentTimeSeconds() - startTimeSeconds;
				viewController.changeTime(LIMIT_TIME_SECONDS - elapsedTimeSeconds);
				
				moleController.createMole(moleController.randomHoleNumber());
				viewController.refresh();
				
				if(LIMIT_TIME_SECONDS <= elapsedTimeSeconds) {
					mTimer.cancel();
					moleController.removeAllMole();
					viewController.displayEndMenu();
				}
			}
		});
	}
	
	private long currentTimeSeconds() {
		return System.currentTimeMillis() / 1000;
	}

}
