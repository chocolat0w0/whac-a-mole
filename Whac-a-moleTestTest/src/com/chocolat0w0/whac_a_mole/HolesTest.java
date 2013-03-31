package com.chocolat0w0.whac_a_mole;

import java.util.EnumSet;
import junit.framework.TestCase;
import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class HolesTest extends TestCase {
	
	Holes holes;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		holes = new Holes();
		holes.removeAllMole();
		holes.notifyObservers();
	}
	
	public void test_randomHoleNumberによって_0以上_穴の数xランダム係数未満の整数が得られる() 
			throws Exception {
		int expected_minNum = 0;
		int expected_maxNum = Holes.HOLE_NUMBER 
				* Holes.RANDOM_FACTOR;
		int randomNum = holes.randomHoleNumber();
		assertTrue(expected_minNum <= randomNum);
		assertTrue(randomNum < expected_maxNum);
	}
	
	public void test_createMoleによって_指定された穴にモグラが生成される() throws Exception {
		int holeNum = 0;
		int type = 0;
		holes.createMole(holeNum, type);
		Mole createdMole = holes.getMole(holeNum);
		assertNotNull(createdMole);
		assertTrue(holes.hasChanged());
	}
	
	public void test_createMoleによって_指定された穴が存在しない場合は新たにモグラを生成しない() throws Exception {
		int holeNum = Holes.HOLE_NUMBER + 1;
		int type = 0;
		holes.createMole(holeNum, type);
		Mole createdMole = holes.getMole(holeNum);
		assertNull(createdMole);
		assertFalse(holes.hasChanged());
	}

	public void test_createMoleによって_指定されたモグラタイプが存在しない場合新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = EnumSet.allOf(EnumMoleType.class).size() + 1;
		EnumMoleType expected = EnumMoleType.NULL;
		holes.createMole(holeNum, type);
		Mole createdMole = holes.getMole(holeNum);
		assertEquals(expected, createdMole.getType());
		assertFalse(holes.hasChanged());
	}
	
	public void test_createMoleによって_指定された穴にモグラが既に出現している場合は新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = 0;
		holes.createMole(holeNum, type);
		assertTrue(holes.hasChanged());
		holes.notifyObservers();
		holes.createMole(holeNum, type);
		assertFalse(holes.hasChanged());
		Mole createdMole = holes.getMole(holeNum);
		assertNotNull(createdMole);
	}
	
	public void test_touchによって_モグラがいた場合叩ける() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		holes.createMole(holeNum, type);
		int point = holes.getTouchedMolePoint(holeNum);
		holes.touch(holeNum);
		Mole existMole = holes.getMole(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(point != 0);
		assertTrue(holes.hasChanged());
	}
	
	public void test_touchによって_モグラがいない場合叩けない() throws Exception {
		int holeNum = 0;
		int point = holes.getTouchedMolePoint(holeNum);
		EnumMoleType expected = EnumMoleType.NULL;
		holes.touch(holeNum);
		Mole existMole = holes.getMole(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(point == 0);
		assertFalse(holes.hasChanged());
	}

	public void test_removeMoleLifeTimeEndedによって_寿命を超えているモグラは消える() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		holes.createMole(holeNum, type);
		holes.notifyObservers();
		holes.removeMoleLifeTimeEnded(holeNum, Long.MAX_VALUE);
		Mole existMole = holes.getMole(holeNum);
		assertEquals(expected, existMole.getType());
		assertTrue(holes.hasChanged());
	}
	
	public void test_removeMoleLifeTimeEndedによって_寿命内のモグラはそのまま() throws Exception {
		int holeNum = 0;
		int type = 0;
		EnumMoleType expected = EnumMoleType.MIDDLE;
		long currentTime = System.currentTimeMillis();
		holes.createMole(holeNum, type);
		holes.notifyObservers();
		holes.removeMoleLifeTimeEnded(holeNum, currentTime);
		Mole existMole = holes.getMole(holeNum);
		assertEquals(expected, existMole.getType());
		assertFalse(holes.hasChanged());
	}
	
	public void test_removeAllMoleによって_すべてのモグラが消される() throws Exception {
		int holeNum1 = 0;
		int holeNum2 = 1;
		int type = 0;
		EnumMoleType expected = EnumMoleType.NULL;
		holes.createMole(holeNum1, type);
		holes.createMole(holeNum2, type);
		holes.notifyObservers();
		holes.removeAllMole();
		Mole existMole1 = holes.getMole(holeNum1);
		Mole existMole2 = holes.getMole(holeNum2);
		assertEquals(expected, existMole1.getType());
		assertEquals(expected, existMole2.getType());
		assertTrue(holes.hasChanged());
	}

	
}
