package com.chocolat0w0.whac_a_mole;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class Game extends Activity {
	private static final int LIMIT_TIME_SECONDS = 30;
	private RelativeLayout viewGroup;
	private View gameMenu;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setViews();
	}

	private void setViews() {
		viewGroup = new RelativeLayout(this);
		gameMenu = getLayoutInflater().inflate(R.layout.game_menu, viewGroup, isChild());
		viewGroup.setBackgroundColor(Color.GREEN);
		displayInitTime();		
		viewGroup.addView(gameMenu);
		setContentView(viewGroup);		
	}

	private void displayInitTime() {
		TextView txtTimer = (TextView) gameMenu.findViewById(R.id.txt_timer);
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
