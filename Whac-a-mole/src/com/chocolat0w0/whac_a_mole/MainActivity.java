package com.chocolat0w0.whac_a_mole;

import java.util.Timer;

import android.app.Activity;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {

	private static final long TIMER_DELAY = 0;
	private static final long TIMER_PERIOD = 100;
	
	private Button btnStart;
	private RelativeLayout viewGroup = null;
	private ViewController viewController = null;
	private PointController pointController = null;
	private Timer mTimer;
	private TimerController timerController;
	private HoleController holeController = null;
	private Paint mPaint = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		viewController = new ViewController(this, viewGroup);
		pointController = new PointController(viewController);
		holeController = new HoleController(pointController, viewController);
		viewController.initDisplay(getLayoutInflater().inflate(R.layout.game_menu, viewGroup, isChild()));
		viewController.displayStartButton();
		setContentView(viewGroup);
		setListenerOnStartButton();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	private void setListenerOnStartButton() {
		btnStart = (Button) findViewById(R.id.btn_start);
		btnStart.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.btn_start :
			startGame();
			break;
		default :
			break;
		}
		
	}

	private void startGame() {
		viewController.closeStartBtn();
		mTimer = new Timer(true);
		timerController = new TimerController(viewController, holeController, mTimer);
		mTimer.schedule(timerController, TIMER_DELAY, TIMER_PERIOD);
	}

}
