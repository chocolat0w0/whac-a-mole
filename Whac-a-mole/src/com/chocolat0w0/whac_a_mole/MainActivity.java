package com.chocolat0w0.whac_a_mole;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final long TIMER_DELAY_MS = 50;
	private static final long TIMER_PERIOD_MS = 50;
	
	private Button btnStart;
	private RelativeLayout viewGroup = null;
	private ViewController viewController = null;
	private PointController pointController = null;
	private Timer mTimer;
	private TimerController timerController;
	private MoleController moleController = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		viewController = new ViewController(this, viewGroup);
		pointController = new PointController(viewController);
		moleController = new MoleController();
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
		pointController.initPoint();
		viewController.changePoint(0);
		mTimer = new Timer(true);
		timerController = new TimerController(viewController, moleController, mTimer);
		mTimer.schedule(timerController, TIMER_DELAY_MS, TIMER_PERIOD_MS);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if( viewController.isHole(event.getX(), event.getY()) ) {
			int touchedHoleNum = viewController.touchHoleNum(event.getX(), event.getY());
			viewController.removeMole(touchedHoleNum);
			int point = moleController.touch(touchedHoleNum);
			pointController.add(point);
		}
		return true;
	}

}
