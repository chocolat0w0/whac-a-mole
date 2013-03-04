package com.chocolat0w0.whac_a_mole;

import java.util.Currency;
import java.util.TimerTask;

import android.os.Handler;
import android.widget.RelativeLayout;

public class TimerController extends TimerTask {

	private static final long GAME_TIME_SECONDS = 30;
	private Handler mHandler = null;
	private ViewController viewController = null;
	private long startTimeSeconds = 0;
	private long elapsedTimeSeconds = 0;
	
	public TimerController(ViewController viewController) {
		super();
		mHandler = new Handler();
		this.viewController = viewController;
		startTimeSeconds = System.currentTimeMillis() / 1000;
	}
	
	@Override
	public void run() {
		mHandler.post(new Runnable() {
			
			@Override
			public void run() {
				elapsedTimeSeconds = System.currentTimeMillis() / 1000 - startTimeSeconds; 
				viewController.changeTime(GAME_TIME_SECONDS - elapsedTimeSeconds);
			}
		});
	}

}
