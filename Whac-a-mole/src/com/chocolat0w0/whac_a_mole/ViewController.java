package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewController extends View {
	TextView pointTitle;
	RelativeLayout viewGroup;
	
	public ViewController(Context context) {
		super(context);
		viewGroup = new RelativeLayout(context);
		pointTitle = new TextView(context);
		pointTitle.setText(R.string.point_title);
		viewGroup.addView(pointTitle);
	}
		
	
	public void displayBackground() {
		setBackgroundColor(Color.GREEN);
	}
	

}
