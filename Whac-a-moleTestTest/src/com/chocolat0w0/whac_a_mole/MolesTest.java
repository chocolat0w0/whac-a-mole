package com.chocolat0w0.whac_a_mole;

import java.util.EnumSet;
import junit.framework.TestCase;
import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class MolesTest extends TestCase {
	
	Moles moles;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		moles = new Moles();
		moles.removeAllMole();
		moles.notifyObservers();
	}
	
	public void test_randomHoleNumberによって_0以上_穴の数xランダム係数未満の整数が得られる() 
			throws Exception {
		int expected_minNum = 0;
		int expected_maxNum = Moles.HOLE_NUMBER 
				* Moles.RANDOM_FACTOR;
		int randomNum = moles.getRandomHoleNumber();
		assertTrue(expected_minNum <= randomNum);
		assertTrue(randomNum < expected_maxNum);
	}
	
	public void test_createMoleによって_指定された穴にモグラが生成される() throws Exception {
		int holeNum = 0;
		int type = 0;
		moles.createMole(holeNum, type);
		IMole createdMole = moles.getMoleAt(holeNum);
		assertNotNull(createdMole);
		assertTrue(moles.hasChanged());
	}
	
	public void test_createMoleによって_指定された穴が存在しない場合は新たにモグラを生成しない() throws Exception {
		int holeNum = Moles.HOLE_NUMBER + 1;
		int type = 0;
		moles.createMole(holeNum, type);
		IMole createdMole = moles.getMoleAt(holeNum);
		assertNull(createdMole);
		assertFalse(moles.hasChanged());
	}

	public void test_createMoleによって_指定されたモグラタイプが存在しない場合新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = EnumSet.allOf(EnumMoleType.class).size() + 1;
		EnumMoleType expected = EnumMoleType.NULL;
		moles.createMole(holeNum, type);
		IMole createdMole = moles.getMoleAt(holeNum);
		assertEquals(expected, createdMole.getType());
		assertFalse(moles.hasChanged());
	}
	
	public void test_createMoleによって_指定された穴にモグラが既に出現している場合は新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = 0;
		moles.createMole(holeNum, type);
		assertTrue(moles.hasChanged());
		moles.notifyObservers();
		moles.createMole(holeNum, type);
		assertFalse(moles.hasChanged());
		IMole createdMole = moles.getMoleAt(holeNum);
		assertNotNull(createdMole);
	}
	
	public void test_touchによって_モグラがいた場合叩ける() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		moles.createMole(holeNum, type);
		int point = moles.getTouchedMolePoint(holeNum);
		moles.touch(holeNum);
		IMole existMole = moles.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(point != 0);
		assertTrue(moles.hasChanged());
	}
	
	public void test_touchによって_モグラがいない場合叩けない() throws Exception {
		int holeNum = 0;
		int point = moles.getTouchedMolePoint(holeNum);
		EnumMoleType expected = EnumMoleType.NULL;
		moles.touch(holeNum);
		IMole existMole = moles.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(point == 0);
		assertFalse(moles.hasChanged());
	}

	public void test_removeMoleLifeTimeEndedによって_寿命を超えているモグラは消える() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		moles.createMole(holeNum, type);
		moles.notifyObservers();
		moles.removeMoleLifeTimeEnded(holeNum, Long.MAX_VALUE);
		IMole existMole = moles.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(moles.hasChanged());
	}
	
	public void test_removeMoleLifeTimeEndedによって_寿命内のモグラはそのまま() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.MIDDLE;
		long currentTime = System.currentTimeMillis();
		moles.createMole(holeNum, type);
		moles.notifyObservers();
		moles.removeMoleLifeTimeEnded(holeNum, currentTime);
		IMole existMole = moles.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertFalse(moles.hasChanged());
	}
	
	public void test_removeAllMoleによって_すべてのモグラが消される() throws Exception {
		int holeNum1 = 0;
		int holeNum2 = 1;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		moles.createMole(holeNum1, type);
		moles.createMole(holeNum2, type);
		moles.notifyObservers();
		moles.removeAllMole();
		IMole existMole1 = moles.getMoleAt(holeNum1);
		IMole existMole2 = moles.getMoleAt(holeNum2);
		assertEquals(expected, existMole1.getType());
		assertEquals(expected, existMole2.getType());
		assertTrue(moles.hasChanged());
	}

	
}
