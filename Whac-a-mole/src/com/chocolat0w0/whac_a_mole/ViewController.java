package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewController{
	private static final long LIMIT_TIME_SECONDS = 30;
	
	private RelativeLayout viewGroup = null;
	private TextView txtTimer = null;
	private Paint mPaint = null;
	private HoleViewController holeViewController = null;
	
	public ViewController(Context context, RelativeLayout viewGroup) {
		this.viewGroup = viewGroup;
		holeViewController = new HoleViewController(context);
	}
	
	public void initDisplay(View gameStatus) {
		viewGroup.setBackgroundColor(Color.GREEN);
		viewGroup.addView(gameStatus);
		txtTimer = (TextView) gameStatus.findViewById(R.id.txt_timer);
		String time = secondsToMMSS(LIMIT_TIME_SECONDS);
		txtTimer.setText(time);
		
		viewGroup.addView(holeViewController);
	}
	
	public void displayStartButton(Context context) {
		Button startBtn = new Button(context);
		startBtn.setText(R.string.start);
		startBtn.setId(R.id.btn_start);
		// サイズ指定仮
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(200, 100);
		layout.addRule(RelativeLayout.CENTER_IN_PARENT);
		viewGroup.addView(startBtn, layout);
	}

	private String secondsToMMSS(long timeSeconds) {
		long minits;
		long seconds;
		String result;
		minits = timeSeconds / 60;
		seconds = timeSeconds % 60;
		result = Long.toString(minits) + ":" + Long.toString(seconds);
		return result;
	}

	public void changeTime(long timeSeconds) {
		txtTimer.setText(secondsToMMSS(timeSeconds));
//		viewGroup.invalidate();
		
	}
}
