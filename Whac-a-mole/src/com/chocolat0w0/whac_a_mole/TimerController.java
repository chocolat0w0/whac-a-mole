package com.chocolat0w0.whac_a_mole;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	static final long LIMIT_TIME_MILLIS = 10 * 1000;
	
	private Handler mHandler = null;
	private ViewController viewController = null;
	private HolesView holesView = null;
	private Holes holes = null;
	private Timer mTimer = null;
	private long startTimeMillis = 0;
	private long elapsedTimeMillis = 0;
	
	
	public TimerController(ViewController viewCtr, HolesView holesView, Holes holes, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.holesView = holesView;
		this.holes = holes;
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
					holes.removeAllMole();
					holes.notifyObservers();
					viewController.refresh();
					viewController.displayEndMenu();
					viewController.refresh();
					holesView.invalidate();
					return;
				}

				viewController.changeTime(LIMIT_TIME_MILLIS - elapsedTimeMillis);
				int randomHoleNum = holes.randomHoleNumber();
				holes.createMole(randomHoleNum, new Random().nextInt(3));
				for (int i = 0; i < Holes.HOLE_NUMBER; i++) {
					holes.removeMoleLifeTimeEnded(i, System.currentTimeMillis());
				}
				holes.notifyObservers();
				viewController.refresh();
				holesView.invalidate();
			}
		});
	}
}
