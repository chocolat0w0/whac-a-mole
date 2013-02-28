package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewController extends View {
	private static final int LIMIT_TIME_SECONDS = 30;
	
	private RelativeLayout viewGroup = null;
	private Paint mPaint = null;
	
	public ViewController(Context context, RelativeLayout viewGroup) {
		super(context);
		this.viewGroup = viewGroup;
	}
	
	public void displayBackGround() {
		viewGroup.setBackgroundColor(Color.GREEN);
		mPaint = new Paint();
		mPaint.setColor(Color.GRAY);
		mPaint.setAntiAlias(true);

	}

	public void displayGameStatus(View gameStatus) {
		viewGroup.addView(gameStatus);
		TextView txtTimer = (TextView) gameStatus.findViewById(R.id.txt_timer);
		String time = secondsToMMSS(LIMIT_TIME_SECONDS);
		txtTimer.setText(time);
		
	}
	
	private String secondsToMMSS(int timeSeconds) {
		int minit;
		int seconds;
		String result;
		minit = timeSeconds / 60;
		seconds = timeSeconds % 60;
		result = Integer.toString(minit) + ":" + Integer.toString(seconds);
		return result;
	}


}
