package com.chocolat0w0.whac_a_mole;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

import junit.framework.TestCase;

public class HighPointMoleTest extends TestCase {

	private Mole mMole;
	private long birthTime = 0;
	private int holeNumber = 0;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mMole = new HighPointMole(birthTime, holeNumber);
	}
	
	public void test_寿命内の場合isLivingの返り値がtrueである() throws Exception {
		long currentTime = 1000;
		boolean expected = true;
		assertEquals(expected, mMole.isLiving(currentTime));
	}
	
	public void test_寿命超えの場合isLivingの返り値がfalseである() throws Exception {
		long currentTime = 3000;
		boolean expected = false;
		assertEquals(expected, mMole.isLiving(currentTime));
	}
	
	public void test_正しいタイプが取得できる() throws Exception {
		EnumMoleType expected = EnumMoleType.HIGH;
		assertEquals(expected, mMole.getType());
	}
	
	public void test_正しいポイントが取得できる() throws Exception {
		int expected = 500;
		assertEquals(expected, mMole.getPoint());
	}
}
