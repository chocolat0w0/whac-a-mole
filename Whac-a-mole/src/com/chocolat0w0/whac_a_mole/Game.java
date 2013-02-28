package com.chocolat0w0.whac_a_mole;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Game extends Activity {
	private RelativeLayout viewGroup;
	private TextView txtPointTitle;
	private TextView txtPoint;
	private TextView txtTimer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		displayBackground();		
		setContentView(viewGroup);
	}

	private void displayBackground() {
		viewGroup.setBackgroundColor(Color.GREEN);
		
		txtTimer = new TextView(this);
		txtTimer.setText("hello");
		LayoutParams lpTimer = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		viewGroup.addView(txtTimer, lpTimer);
		
		txtPointTitle = new TextView(this);
		LayoutParams lpPointTitle = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		txtPointTitle.setText(R.string.point_title);
		txtPointTitle.setId(1);
		viewGroup.addView(txtPointTitle, lpPointTitle);
		
		txtPoint = new TextView(this);
		LayoutParams lpPoint = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lpPoint.addRule(RelativeLayout.RIGHT_OF, 1);
		txtPoint.setText(R.string.point_init);
		viewGroup.addView(txtPoint, lpPoint);
	}
	

}
