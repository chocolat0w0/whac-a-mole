package com.chocolat0w0.whac_a_mole;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Observable;

import android.test.InstrumentationTestCase;

public class PointTest extends InstrumentationTestCase {

<<<<<<< HEAD
	private Point mPoint;
	private GameStatusView mockedViewCtrl;
=======
	private TotalPoint mPoint;
	private ViewController mockedViewCtrl;
>>>>>>> d589424bfa0f46c9d68b358c8f41568d2d12996b
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
<<<<<<< HEAD
		mPoint = new Point();
		mockedViewCtrl = mock(GameStatusView.class);
=======
		mPoint = new TotalPoint();
		mockedViewCtrl = mock(ViewController.class);
>>>>>>> d589424bfa0f46c9d68b358c8f41568d2d12996b
		mPoint.addObserver(mockedViewCtrl);
	}
	
	public void test_pointの初期化がされる() throws Exception {
		int expectedPoint = 0;
		mPoint.init();
		assertEquals(expectedPoint, mPoint.getPoint());
		verify(mockedViewCtrl).update(any(Observable.class), any());
	}
	
	public void test_pointの加算がされる() throws Exception {
		int basePoint = mPoint.getPoint();
		int addedPoint = 15;
		int expectedPoint = basePoint + addedPoint;
		mPoint.add(addedPoint);
		assertEquals(expectedPoint, mPoint.getPoint());
		verify(mockedViewCtrl).update(any(Observable.class), any());
	}
}
