package com.chocolat0w0.whac_a_mole;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;

public class TimerController extends TimerTask {

	static final long LIMIT_TIME_MILLIS = 10 * 1000;
	
	private Handler mHandler = null;
	private GameStatusView viewController = null;
	private HolesView holesView = null;
	private HolesViewController holesController = null;
	private MolesController molesController = null;
	private Timer mTimer = null;
	private long startTimeMillis = 0;
	private long elapsedTimeMillis = 0;
	
	
	public TimerController(GameStatusView viewCtr, HolesView holesView, MolesController molesController, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.holesView = holesView;
		this.molesController = molesController;
		this.mTimer = mTimer;
		startTimeMillis = System.currentTimeMillis();
	}
	
	public TimerController(GameStatusView viewCtr, HolesViewController holesController, MolesController molesController, Timer mTimer) {
		super();
		mHandler = new Handler();
		this.viewController = viewCtr;
		this.holesController = holesController;
		this.molesController = molesController;
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
					molesController.removeAllMole();
					molesController.notifyObservers();
					viewController.refresh();
					viewController.displayEndMenu();
					viewController.refresh();
//					holesView.invalidate();
					holesController.refresh();
					return;
				}

				viewController.changeTime(LIMIT_TIME_MILLIS - elapsedTimeMillis);
				int randomHoleNum = molesController.getRandomHoleNumber();
				molesController.createMole(randomHoleNum, new Random().nextInt(3));
				for (int i = 0; i < MolesController.HOLE_NUMBER; i++) {
					molesController.removeMoleLifeTimeEnded(i, System.currentTimeMillis());
				}
				molesController.notifyObservers();
				viewController.refresh();
//				holesView.invalidate();
				holesController.refresh();
			}
		});
	}
}
