package com.chocolat0w0.whac_a_mole;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class EndMenu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.end_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_end_menu, menu);
		return true;
	}

}
