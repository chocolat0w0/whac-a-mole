package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewController{
	// TODO: タイトルバー分タッチポイントがずれるのを修正。動的に取得したい。。。
	private static final int TITLE_BAR_HEIGHT = 100;
	
	private RelativeLayout viewGroup = null;
	private TextView txtTimer = null;
	private TextView txtPoint = null;
	private HoleView holeView = null;
	private Context context = null;
	private Button startBtn = null;
	private static TextView txtDebug = null;
	
	public ViewController(Context context, RelativeLayout viewGroup) {
		this.viewGroup = viewGroup;
		this.context = context;
		holeView = new HoleView(context);
	}
	
	public void initDisplay(View gameStatus) {
		viewGroup.setBackgroundColor(Color.GREEN);
		viewGroup.addView(gameStatus);
		txtTimer = (TextView) gameStatus.findViewById(R.id.txt_timer);
		String time = toTextTimerFormat(TimerController.LIMIT_TIME_MILLIS);
		txtTimer.setText(time);
		txtPoint = (TextView) gameStatus.findViewById(R.id.txt_point);
		
		txtDebug = (TextView) gameStatus.findViewById(R.id.textDebug);
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
		RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(200, 100);
		layout.addRule(RelativeLayout.CENTER_IN_PARENT);
//		viewGroup.addView(startBtn, layout);
	}

	public void addMole(int holeNum, Mole mole) {
		holeView.addMole(holeNum, mole);
	}
	
	public void refresh() {
		holeView.invalidate();
		viewGroup.invalidate();
		debugInfo("りふれーーーっしゅ！");
	}

	public static void debugInfo(String string) {
		txtDebug.setText(string);
	}

	public boolean isHole(float x, float y) {
		return holeView.isExisted(x, y - TITLE_BAR_HEIGHT);
	}

	public int touchHoleNum(float x, float y) {
		return holeView.touchedHoleNum(x, y - TITLE_BAR_HEIGHT);
	}

	public void removeMole(int holeNum) {
		holeView.removeMole(holeNum);
		
	}

	public void changePoint(int totalPoint) {
		float fromX = 1.0F;
		float toX = 2.5F;
		float fromY = 1.0F;
		float toY = 2.5F;
		long durationMillis = 30;
		txtPoint.setText(Integer.toString(totalPoint));
		ScaleAnimation animation = new ScaleAnimation(fromX, toX, fromY, toY);
		animation.setDuration(durationMillis);
		txtPoint.startAnimation(animation);
	}

}
