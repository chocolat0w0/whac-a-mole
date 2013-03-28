package com.chocolat0w0.whac_a_mole;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewController implements Observer{
	
	private RelativeLayout viewGroup = null;
	private TextView txtTimer = null;
	private TextView txtPoint = null;
	private Context context = null;
	private Button startBtn = null;
	private RelativeLayout.LayoutParams layout = null;
	private static TextView txtDebug = null;
	
	public ViewController(Context context, RelativeLayout viewGroup) {
		this.viewGroup = viewGroup;
		this.context = context;
	}
	
	public void initDisplay(View gameStatus) {
		viewGroup.setBackgroundColor(Color.GREEN);
		viewGroup.addView(gameStatus);
		txtTimer = (TextView) gameStatus.findViewById(R.id.txt_timer);
		String time = toTextTimerFormat(TimerController.LIMIT_TIME_MILLIS);
		txtTimer.setText(time);
		txtPoint = (TextView) gameStatus.findViewById(R.id.txt_point);
		txtDebug = (TextView) gameStatus.findViewById(R.id.textDebug);
	}
	
	public void displayStartButton() {
		startBtn = new Button(context);
		startBtn.setText(R.string.start);
		startBtn.setId(R.id.btn_start);
		layout = new RelativeLayout.LayoutParams(200, 100);
		layout.addRule(RelativeLayout.CENTER_IN_PARENT);
		viewGroup.addView(startBtn, layout);
	}

	public void closeStartBtn() {
		viewGroup.removeView(startBtn);
	}
	
	private String toTextTimerFormat(long timeMillis) {
		String result;
		long millis = timeMillis % 1000;
		long seconds = timeMillis / 1000 % 60;
		long minits = timeMillis / 1000 / 60;
		result = String.format("%02d : %02d . %03d", minits, seconds, millis);
		return result;
	}

	public void changeTime(long timeMillis) {
		txtTimer.setText(toTextTimerFormat(timeMillis));
	}

	public void displayEndMenu() {
		changeTime(0);
		viewGroup.removeView(startBtn);
		startBtn.setText(R.string.restart);
		viewGroup.addView(startBtn, layout);
	}

	@Override
	public void update(Observable o, Object arg) {
		float fromX = 1.0F;
		float toX = 2.5F;
		float fromY = 1.0F;
		float toY = 2.5F;
		long durationMillis = 30;
		Point point = (Point) arg;
		txtPoint.setText(Integer.toString(point.getPoint()));
		ScaleAnimation animation = new ScaleAnimation(fromX, toX, fromY, toY);
		animation.setDuration(durationMillis);
		txtPoint.startAnimation(animation);
	}

	public void refresh() {
		viewGroup.invalidate();
	}

//	public void popGotPoint(int holeNum, Mole mole) {
//		holeView.popGotPoint(holeNum, mole);
//		
//	}

	public static void debugInfo(String string) {
		txtDebug.setText(string);
	}

}
