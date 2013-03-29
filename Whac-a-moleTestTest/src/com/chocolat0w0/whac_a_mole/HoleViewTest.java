package com.chocolat0w0.whac_a_mole;


import static org.mockito.Mockito.*;
import android.test.InstrumentationTestCase;
import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class HoleViewTest extends InstrumentationTestCase {

	HoleView holeView;
	Mole mockedMole;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mockedMole = mock(Mole.class);
		// モックのgetTypeを上書き
		when(mockedMole.getType()).thenReturn(EnumMoleType.MIDDLE);
		holeView = new HoleView(0, mockedMole);
	}
	
	public void test_getImageによって_正しい画像が取得される() throws Exception {
		int expected = R.drawable.mole1;
		int actual = holeView.getImageId();
		assertEquals(expected, actual);
	}
}
