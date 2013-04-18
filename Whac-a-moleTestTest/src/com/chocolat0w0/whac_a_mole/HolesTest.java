package com.chocolat0w0.whac_a_mole;

import java.util.EnumSet;
import junit.framework.TestCase;
import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class HolesTest extends TestCase {
	
	MolesController molesController;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		molesController = new MolesController();
		molesController.removeAllMole();
		molesController.notifyObservers();
	}
	
	public void test_randomHoleNumberによって_0以上_穴の数xランダム係数未満の整数が得られる() 
			throws Exception {
		int expected_minNum = 0;
		int expected_maxNum = MolesController.HOLE_NUMBER 
				* MolesController.RANDOM_FACTOR;
		int randomNum = molesController.getRandomHoleNumber();
		assertTrue(expected_minNum <= randomNum);
		assertTrue(randomNum < expected_maxNum);
	}
	
	public void test_createMoleによって_指定された穴にモグラが生成される() throws Exception {
		int holeNum = 0;
		int type = 0;
		molesController.createMole(holeNum, type);
		IMole createdMole = molesController.getMoleAt(holeNum);
		assertNotNull(createdMole);
		assertTrue(molesController.hasChanged());
	}
	
	public void test_createMoleによって_指定された穴が存在しない場合は新たにモグラを生成しない() throws Exception {
		int holeNum = MolesController.HOLE_NUMBER + 1;
		int type = 0;
		molesController.createMole(holeNum, type);
		IMole createdMole = molesController.getMoleAt(holeNum);
		assertNull(createdMole);
		assertFalse(molesController.hasChanged());
	}

	public void test_createMoleによって_指定されたモグラタイプが存在しない場合新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = EnumSet.allOf(EnumMoleType.class).size() + 1;
		EnumMoleType expected = EnumMoleType.NULL;
		molesController.createMole(holeNum, type);
		IMole createdMole = molesController.getMoleAt(holeNum);
		assertEquals(expected, createdMole.getType());
		assertFalse(molesController.hasChanged());
	}
	
	public void test_createMoleによって_指定された穴にモグラが既に出現している場合は新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = 0;
		molesController.createMole(holeNum, type);
		assertTrue(molesController.hasChanged());
		molesController.notifyObservers();
		molesController.createMole(holeNum, type);
		assertFalse(molesController.hasChanged());
		IMole createdMole = molesController.getMoleAt(holeNum);
		assertNotNull(createdMole);
	}
	
	public void test_touchによって_モグラがいた場合叩ける() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		molesController.createMole(holeNum, type);
		int point = molesController.getTouchedMolePoint(holeNum);
		molesController.touch(holeNum);
		IMole existMole = molesController.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(point != 0);
		assertTrue(molesController.hasChanged());
	}
	
	public void test_touchによって_モグラがいない場合叩けない() throws Exception {
		int holeNum = 0;
		int point = molesController.getTouchedMolePoint(holeNum);
		EnumMoleType expected = EnumMoleType.NULL;
		molesController.touch(holeNum);
		IMole existMole = molesController.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(point == 0);
		assertFalse(molesController.hasChanged());
	}

	public void test_removeMoleLifeTimeEndedによって_寿命を超えているモグラは消える() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		molesController.createMole(holeNum, type);
		molesController.notifyObservers();
		molesController.removeMoleLifeTimeEnded(holeNum, Long.MAX_VALUE);
		IMole existMole = molesController.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(molesController.hasChanged());
	}
	
	public void test_removeMoleLifeTimeEndedによって_寿命内のモグラはそのまま() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.MIDDLE;
		long currentTime = System.currentTimeMillis();
		molesController.createMole(holeNum, type);
		molesController.notifyObservers();
		molesController.removeMoleLifeTimeEnded(holeNum, currentTime);
		IMole existMole = molesController.getMoleAt(holeNum);
		assertEquals(expected, existMole.getType());
		assertFalse(molesController.hasChanged());
	}
	
	public void test_removeAllMoleによって_すべてのモグラが消される() throws Exception {
		int holeNum1 = 0;
		int holeNum2 = 1;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		molesController.createMole(holeNum1, type);
		molesController.createMole(holeNum2, type);
		molesController.notifyObservers();
		molesController.removeAllMole();
		IMole existMole1 = molesController.getMoleAt(holeNum1);
		IMole existMole2 = molesController.getMoleAt(holeNum2);
		assertEquals(expected, existMole1.getType());
		assertEquals(expected, existMole2.getType());
		assertTrue(molesController.hasChanged());
	}

	
}
