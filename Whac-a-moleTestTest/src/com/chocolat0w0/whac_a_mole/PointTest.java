package com.chocolat0w0.whac_a_mole;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.Observable;

import android.test.InstrumentationTestCase;

public class PointTest extends InstrumentationTestCase {

	private TotalPoint mPoint;
	private MolesView mockedHolesView;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mPoint = new TotalPoint();
		mockedHolesView = mock(MolesView.class);
		mPoint.addObserver(mockedHolesView);
	}
	
	public void test_pointの初期化がされる() throws Exception {
		int expectedPoint = 0;
		mPoint.init();
		assertEquals(expectedPoint, mPoint.getPoint());
		verify(mockedHolesView).update(any(Observable.class), any());
	}
	
	public void test_pointの加算がされる() throws Exception {
		int basePoint = mPoint.getPoint();
		int addedPoint = 15;
		int expectedPoint = basePoint + addedPoint;
		mPoint.add(addedPoint);
		assertEquals(expectedPoint, mPoint.getPoint());
		verify(mockedHolesView).update(any(Observable.class), any());
	}
}
