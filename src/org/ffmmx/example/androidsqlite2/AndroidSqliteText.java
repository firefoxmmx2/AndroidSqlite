package org.ffmmx.example.androidsqlite2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AndroidSqliteText extends Activity {
	private EditText usernameEdit, passwordEdit;
	private Button loginBtn, registerBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_sqlite_text);

		usernameEdit = (EditText) this.findViewById(R.id.usernameEdit);
		passwordEdit = (EditText) this.findViewById(R.id.passwordEdit);
		loginBtn = (Button) this.findViewById(R.id.loginBtn);
		registerBtn = (Button) this.findViewById(R.id.registerBtn);
		OnClickListener clickLisenter = new ClickLisenter();
		loginBtn.setOnClickListener(clickLisenter);
		registerBtn.setOnClickListener(clickLisenter);

	}

	class ClickLisenter implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.loginBtn:

				break;
			case R.id.registerBtn:
				Intent intent = new Intent();
				intent.setClass(AndroidSqliteText.this, RegisterActivity.class);
				startActivity(intent);
				break;
			default:
				break;
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.android_sqlite_text, menu);
		return true;
	}

}
