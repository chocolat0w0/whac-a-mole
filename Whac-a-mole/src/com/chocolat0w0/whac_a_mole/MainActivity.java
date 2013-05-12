package com.chocolat0w0.whac_a_mole;

import java.util.Timer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	
	private static final long TIMER_DELAY_MS = 100;
	private static final long TIMER_PERIOD_MS = 100;
	
	private Button btnStart;
	private RelativeLayout viewGroup = null;
	private GameStatusView mGameStatusView = null;
	private MolesView mMolesView = null;
	private Timer mTimer;
	private TimerController mTimerController;
	private Moles mMoles = null;
	private TotalPoint mTotalPoint = null;
	private int titleBarHeight = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		Debug.startMethodTracing("whac.trace");
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		viewGroup = new RelativeLayout(this);
		mGameStatusView = new GameStatusView(this, viewGroup);
		mTotalPoint  = new TotalPoint();
		mTotalPoint.addObserver(mGameStatusView);
		mMoles = new Moles();
		mMolesView = new MolesView(this, mMoles, mTotalPoint);
		viewGroup.addView(mMolesView);
		mMoles.addObserver(mMolesView);
		mGameStatusView.initDisplay(getLayoutInflater().inflate(R.layout.game_menu, viewGroup, isChild()));
		mGameStatusView.displayStartButton();
		setContentView(viewGroup);
		setListenerOnStartButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
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
		mGameStatusView.closeStartBtn();
		mTotalPoint.init();
		mTimer = new Timer(true);
		mTimerController = new TimerController(mGameStatusView, mMolesView, mMoles, mTimer);
		mTimer.schedule(mTimerController, TIMER_DELAY_MS, TIMER_PERIOD_MS);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mMolesView.touch(event);
		return true;
	}

	@Override
	public void onDestroy() {
//		Debug.stopMethodTracing();
		super.onDestroy();
	}
}
