package org.ffmmx.example.androidsqlite2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AndroidSqliteText extends Activity {
	private EditText usernameEdit, passwordEdit;
	private Button loginBtn, registerBtn;
	private SQLiteOpenHelper sqlhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_sqlite_text);

		sqlhelper = new SqlUtil(AndroidSqliteText.this);

		usernameEdit = (EditText) this.findViewById(R.id.usernameEdit);
		passwordEdit = (EditText) this.findViewById(R.id.passwordEdit);
		loginBtn = (Button) this.findViewById(R.id.loginBtn);
		registerBtn = (Button) this.findViewById(R.id.registerBtn);
		OnClickListener clickLisenter = new ClickLisenter();
		loginBtn.setOnClickListener(clickLisenter);
		registerBtn.setOnClickListener(clickLisenter);

	}

	public void login(String username, String password) {
		SQLiteDatabase db = sqlhelper.getReadableDatabase();
		Cursor cur = db.query("t_user", new String[] { "id", "username",
				"password", "email", "name", "birth" },
				"username=? and passord=?",
				new String[] { username, password }, null, null, null);
		if(cur.moveToFirst()) {
			SharedPreferences sharedPreferences=this.getSharedPreferences("config", 0);
			SharedPreferences.Editor settingEditor=sharedPreferences.edit();
			settingEditor.
		}
		cur.close();
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
