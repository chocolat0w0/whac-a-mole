package com.chocolat0w0.whac_a_mole;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	private Handler mHandler = null;
	private ViewController viewController = null;
	private HoleController holeController = null;
	private Timer mTimer = null;
	private long startTimeSeconds = 0;
	private long elapsedTimeSeconds = 0;
	
	public TimerController(ViewController viewCtr, HoleController holeCtr, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.holeController = holeCtr;
		this.mTimer = mTimer;
		startTimeSeconds = currentTimeSeconds();
	}
	
	@Override
	public void run() {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				elapsedTimeSeconds = currentTimeSeconds() - startTimeSeconds;
				viewController.changeTime(ViewController.LIMIT_TIME_SECONDS - elapsedTimeSeconds);
				
				if (elapsedTimeSeconds == 3) {
					holeController.createMole(3);
				}
				viewController.refresh();
				
				if(ViewController.LIMIT_TIME_SECONDS <= elapsedTimeSeconds) {
					mTimer.cancel();
					viewController.displayEndMenu();
				}
			}
		});
	}
	
	private long currentTimeSeconds() {
		return System.currentTimeMillis() / 1000;
	}

}
