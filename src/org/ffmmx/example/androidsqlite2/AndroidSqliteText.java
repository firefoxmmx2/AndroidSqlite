package org.ffmmx.example.androidsqlite2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AndroidSqliteText extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_sqlite_text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_sqlite_text, menu);
		return true;
	}

}
