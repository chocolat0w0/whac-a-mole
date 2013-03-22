package com.chocolat0w0.whac_a_mole;

import junit.framework.TestCase;

public class PointTest extends TestCase {

	Point mPoint;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mPoint = new Point();
	}
	
	public void test_pointの初期化がされる() throws Exception {
		int expectedPoint = 0;
		mPoint.init();
		assertEquals(expectedPoint, mPoint.getPoint());
	}
	
	public void test_pointの加算がされる() throws Exception {
		int basePoint = mPoint.getPoint();
		int addedPoint = 15;
		int expectedPoint = basePoint + addedPoint;
		mPoint.add(addedPoint);
		assertEquals(expectedPoint, mPoint.getPoint());
	}
}
