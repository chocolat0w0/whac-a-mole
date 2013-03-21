package com.chocolat0w0.whac_a_mole;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.widget.RelativeLayout;

public class PointControllerTest extends InstrumentationTestCase {

	PointController pointCtr;
	Context context;
	
	private Context getApplicationContext() {
        return this.getInstrumentation().getTargetContext().getApplicationContext();
    }
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = getApplicationContext();
		pointCtr 
			= new PointController(new ViewController(
					context, new RelativeLayout(context)
					));
	}
	
	public void test_pointの初期化が実行される() throws Exception {
		pointCtr.initPoint();
		assertEquals(0, pointCtr.totalPoint);
		
	}

	public void test_pointが正しく加算される() throws Exception {
		int addingPoint = 10;
		pointCtr.initPoint();
		pointCtr.add(addingPoint);
		assertEquals(addingPoint, pointCtr.totalPoint);
	}
}
