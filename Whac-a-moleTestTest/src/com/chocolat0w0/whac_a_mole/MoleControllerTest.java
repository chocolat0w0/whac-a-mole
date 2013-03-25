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
		boolean isCreated = moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertTrue(isCreated);
		assertNotNull(createdMole);
	}
	
	public void test_createMoleによって_指定された穴が存在しない場合は新たにモグラを生成しない() throws Exception {
		int holeNum = MoleController.HOLE_NUMBER + 1;
		int type = 0;
		boolean isCreated = moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertFalse(isCreated);
		assertNull(createdMole);
	}

	public void test_createMoleによって_指定されたモグラタイプが存在しない場合新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = EnumSet.allOf(EnumMoleType.class).size() + 1;
		boolean isCreated = moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertFalse(isCreated);
		assertNull(createdMole);
	}
	
	public void test_createMoleによって_指定された穴にモグラが既に出現している場合は新たにモグラを生成しない() throws Exception {
		int holeNum = 0;
		int type = 0;
		moleController.createMole(holeNum, type);
		boolean isCreated = moleController.createMole(holeNum, type);
		Mole createdMole = moleController.getMole(holeNum);
		assertFalse(isCreated);
		assertNotNull(createdMole);
	}
	

	
}
