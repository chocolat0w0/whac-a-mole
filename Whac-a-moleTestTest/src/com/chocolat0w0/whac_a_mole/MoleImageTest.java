package com.chocolat0w0.whac_a_mole;


import static org.mockito.Mockito.*;
import android.test.InstrumentationTestCase;
import com.chocolat0w0.whac_a_mole.MoleType.EnumMoleType;

public class MoleImageTest extends InstrumentationTestCase {

	MoleImage holeView;
	IMole mockedMole;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		mockedMole = mock(IMole.class);
		// モックのgetTypeを上書き
		when(mockedMole.getType()).thenReturn(EnumMoleType.MIDDLE);
		holeView = new MoleImage(mockedMole, null, null);
	}
}
