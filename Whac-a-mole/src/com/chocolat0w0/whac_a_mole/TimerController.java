package com.chocolat0w0.whac_a_mole;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	static final long LIMIT_TIME_MILLIS = 10 * 1000;
	
	private Handler mHandler = null;
	private GameStatusView viewController = null;
	private MolesView holesView = null;
	private Moles moles = null;
	private Timer mTimer = null;
	private long startTimeMillis = 0;
	private long elapsedTimeMillis = 0;
	
	
	public TimerController(GameStatusView viewCtr, MolesView holesView, Moles moles, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.holesView = holesView;
		this.moles = moles;
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
					moles.removeAllMole();
					moles.notifyObservers();
					viewController.refresh();
					viewController.displayEndMenu();
					viewController.refresh();
					holesView.invalidate();
					return;
				}

				viewController.changeTime(LIMIT_TIME_MILLIS - elapsedTimeMillis);
				int randomHoleNum = moles.getRandomHoleNumber();
				moles.createMole(randomHoleNum, new Random().nextInt(3));
				for (int i = 0; i < Moles.HOLE_NUMBER; i++) {
					moles.removeMoleLifeTimeEnded(i, System.currentTimeMillis());
				}
				moles.notifyObservers();
				viewController.refresh();
				holesView.invalidate();
			}
		});
	}
}
