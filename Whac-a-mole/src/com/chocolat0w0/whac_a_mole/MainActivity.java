package com.chocolat0w0.whac_a_mole;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Debug;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	// TODO: タイトルバー分タッチポイントがずれるのを修正。動的に取得したい。。。
	private static final int TITLE_BAR_HEIGHT = 100;

	private static final long TIMER_DELAY_MS = 100;
	private static final long TIMER_PERIOD_MS = 100;
	
	private Button btnStart;
	private RelativeLayout viewGroup = null;
	private GameStatusView viewController = null;
	private HolesView holeView = null;
	private HolesController holesController = null;
	private Timer mTimer;
	private TimerController timerController;
	private Holes holes = null;
	private TotalPoint mPoint = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		Debug.startMethodTracing("whac.trace");
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		viewController = new GameStatusView(this, viewGroup);
		mPoint  = new TotalPoint();
		mPoint.addObserver(viewController);
		holes = new Holes();
		holesController = new HolesController(this, holes, mPoint);
		holesController.addViewGroup(viewGroup);
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
//		timerController = new TimerController(viewController, holesView, holes, mTimer);
		timerController = new TimerController(viewController, holesController, holes, mTimer);
		mTimer.schedule(timerController, TIMER_DELAY_MS, TIMER_PERIOD_MS);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO: Viewに処理を任せる
//		if ( holesView.isExisted(event.getX(), event.getY() - TITLE_BAR_HEIGHT)) {
//			int touchedHoleNum = holesView.getTouchedHoleNum(event.getX(), event.getY() - TITLE_BAR_HEIGHT);
//			int point = holes.getTouchedMolePoint(touchedHoleNum);
//			holes.touch(touchedHoleNum);
//			mPoint.add(point);
//		}
		holesController.touch(event);
		
		return true;
	}

	@Override
	public void onDestroy() {
//		Debug.stopMethodTracing();
		super.onDestroy();
	}
}
