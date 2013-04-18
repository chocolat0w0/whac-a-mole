package com.chocolat0w0.whac_a_mole;


import static org.mockito.Mockito.*;
import android.test.InstrumentationTestCase;
import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class HoleImageTest extends InstrumentationTestCase {

	HoleImage holeView;
	IMole mockedMole;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mockedMole = mock(IMole.class);
		// モックのgetTypeを上書き
		when(mockedMole.getType()).thenReturn(EnumMoleType.MIDDLE);
		holeView = new HoleImage(mockedMole, null, null);
	}
	
	public void test_getImageによって_正しい画像が取得される() throws Exception {
		int expected = R.drawable.mole1;
		int actual = holeView.getImageId();
		assertEquals(expected, actual);
	}
}
