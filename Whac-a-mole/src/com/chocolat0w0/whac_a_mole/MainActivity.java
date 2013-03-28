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
	// TODO: タイトルバー分タッチポイントがずれるのを修正。動的に取得したい。。。
	private static final int TITLE_BAR_HEIGHT = 100;

	private static final long TIMER_DELAY_MS = 100;
	private static final long TIMER_PERIOD_MS = 100;
	
	private Button btnStart;
	private RelativeLayout viewGroup = null;
	private ViewController viewController = null;
	private HoleView holeView = null;
	private Timer mTimer;
	private TimerController timerController;
	private MoleController moleController = null;
	private Point mPoint = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		holeView = new HoleView(this);
		viewController = new ViewController(this, viewGroup);
		viewGroup.addView(holeView);
		mPoint  = new Point();
		mPoint.addObserver(viewController);
		moleController = new MoleController();
		moleController.addObserver(holeView);
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
		mPoint.init();
		mTimer = new Timer(true);
		timerController = new TimerController(viewController, holeView, moleController, mTimer);
		mTimer.schedule(timerController, TIMER_DELAY_MS, TIMER_PERIOD_MS);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if ( holeView.isExisted(event.getX(), event.getY() - TITLE_BAR_HEIGHT)) {
			int touchedHoleNum = holeView.getTouchedHoleNum(event.getX(), event.getY() - TITLE_BAR_HEIGHT);
			int point = moleController.getTouchedMolePoint(touchedHoleNum);
			moleController.touch(touchedHoleNum);
			mPoint.add(point);
		}
		return true;
	}

}
