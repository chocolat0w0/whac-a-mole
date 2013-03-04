package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewController{
	static final long LIMIT_TIME_SECONDS = 10;
	
	private RelativeLayout viewGroup = null;
	private TextView txtTimer = null;
	private HoleView holeView = null;
	private Context context = null;
	private Button startBtn = null;
	
	public ViewController(Context context, RelativeLayout viewGroup) {
		this.viewGroup = viewGroup;
		this.context = context;
		holeView = new HoleView(context);
	}
	
	public void initDisplay(View gameStatus) {
		viewGroup.setBackgroundColor(Color.GREEN);
		viewGroup.addView(gameStatus);
		txtTimer = (TextView) gameStatus.findViewById(R.id.txt_timer);
		String time = secondsToMMSS(LIMIT_TIME_SECONDS);
		txtTimer.setText(time);
		
		viewGroup.addView(holeView);
	}
	
	public void displayStartButton() {
		startBtn = new Button(context);
		startBtn.setText(R.string.start);
		startBtn.setId(R.id.btn_start);
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(200, 100);
		layout.addRule(RelativeLayout.CENTER_IN_PARENT);
		viewGroup.addView(startBtn, layout);
	}

	public void closeStartBtn() {
		viewGroup.removeView(startBtn);
	}
	
	private String secondsToMMSS(long timeSeconds) {
		String strMin;
		String strSec;
		String result;
		long minits = timeSeconds / 60;
		long seconds = timeSeconds % 60;
		strMin = minits < 10 ? '0' + Long.toString(minits) : Long.toString(minits);
		strSec = seconds < 10 ? '0' + Long.toString(seconds) : Long.toString(seconds);
		result = strMin + ":" + strSec;
		return result;
	}

	public void changeTime(long timeSeconds) {
		txtTimer.setText(secondsToMMSS(timeSeconds));
	}

	public void displayEndMenu() {
		Button endBtn = new Button(context);
		endBtn.setText(R.string.close);
		endBtn.setId(R.id.btn_close);
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(200, 100);
		layout.addRule(RelativeLayout.CENTER_IN_PARENT);
		viewGroup.addView(endBtn, layout);
	}

	public void addMole(int holeNum) {
		holeView.addMole(holeNum);
	}
	
	public void refresh() {
		viewGroup.invalidate();
	}

}
