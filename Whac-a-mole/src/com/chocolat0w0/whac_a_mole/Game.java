package com.chocolat0w0.whac_a_mole;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Game extends Activity {
	private RelativeLayout viewGroup = null;
	private View gameMenu = null;
	private ViewController viewController = null;
	private PointController pointController = null;
	private HoleController holeController = null;
	private Paint mPaint = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		viewController = new ViewController(this, viewGroup);
		pointController = new PointController(viewController);
		holeController = new HoleController(pointController);
		viewController.displayBackGround();
		viewController.displayGameStatus(getLayoutInflater().inflate(R.layout.game_menu, viewGroup, isChild()));
		setContentView(viewGroup);
	}
}
