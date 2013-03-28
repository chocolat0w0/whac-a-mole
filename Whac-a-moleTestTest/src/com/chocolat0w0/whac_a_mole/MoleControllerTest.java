package com.chocolat0w0.whac_a_mole;

import java.io.Console;
import java.util.EnumSet;

import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

import junit.framework.TestCase;

public class MoleControllerTest extends TestCase {
	
	MoleController moleController;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		moleController = new MoleController();
		moleController.removeAllMole();
	}
	
	public void test_randomHoleNumberによって_0以上_穴の数xランダム係数未満の整数が得られる() 
			throws Exception {
		int expected_minNum = 0;
		int expected_maxNum = MoleController.HOLE_NUMBER 
				* MoleController.RANDOM_FACTOR;
		int randomNum = moleController.randomHoleNumber();
		assertTrue(expected_minNum <= randomNum);
		assertTrue(randomNum < expected_maxNum);
	}
	
	public void test_createMoleによって_指定された穴にモグラが生成される() throws Exception {
		int holeNum = 0;
		int type = 0;
		moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertNotNull(createdMole);
	}
	
	public void test_createMoleによって_指定された穴が存在しない場合は新たにモグラを生成しない() throws Exception {
		int holeNum = MoleController.HOLE_NUMBER + 1;
		int type = 0;
		moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertNull(createdMole);
	}

	public void test_createMoleによって_指定されたモグラタイプが存在しない場合新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = EnumSet.allOf(EnumMoleType.class).size() + 1;
		moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertNull(createdMole);
	}
	
	public void test_createMoleによって_指定された穴にモグラが既に出現している場合は新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = 0;
		moleController.createMole(holeNum, type);
		moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertNotNull(createdMole);
	}
	
	public void test_touchによって_モグラがいた場合叩ける() throws Exception {
		int holeNum = 0;
		int type = 0;
		moleController.createMole(holeNum, type);
		int point = moleController.getTouchedMolePoint(holeNum);
		moleController.touch(holeNum);
		Mole existMole = moleController.getMole(holeNum);
		assertNull(existMole);
		assertTrue(point != 0);
	}
	
	public void test_touchによって_モグラがいない場合叩けない() throws Exception {
		int holeNum = 0;
		int point = moleController.getTouchedMolePoint(holeNum);
		moleController.touch(holeNum);
		Mole existMole = moleController.getMole(holeNum);
		assertNull(existMole);
		assertTrue(point == 0);
	}

	public void test_removeMoleLifeTimeEndedによって_寿命を超えているモグラは消える() throws Exception {
		int holeNum = 0;
		long birthTime = 0;
		moleController.mole[holeNum] = new MiddlePointMole(birthTime);
		moleController.removeMoleLifeTimeEnded(holeNum);
		Mole existMole = moleController.getMole(holeNum);
		assertNull(existMole);
	}
	
	public void test_removeMoleLifeTimeEndedによって_寿命内のモグラはそのまま() throws Exception {
		int holeNum = 0;
		long birthTime = System.currentTimeMillis();
		moleController.mole[holeNum] = new MiddlePointMole(birthTime);
		moleController.removeMoleLifeTimeEnded(holeNum);
		Mole existMole = moleController.getMole(holeNum);
		assertNotNull(existMole);
	}
	
	public void test_removeAllMoleによって_すべてのモグラが消される() throws Exception {
		int holeNum1 = 0;
		int holeNum2 = 1;
		long birthTime = System.currentTimeMillis();
		moleController.mole[holeNum1] = new MiddlePointMole(birthTime);
		moleController.mole[holeNum2] = new MiddlePointMole(birthTime);
		moleController.removeAllMole();
		Mole existMole1 = moleController.getMole(holeNum1);
		Mole existMole2 = moleController.getMole(holeNum2);
		assertNull(existMole1);
		assertNull(existMole2);
	}

	
}
