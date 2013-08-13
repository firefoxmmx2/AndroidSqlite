package org.ffmmx.example.androidsqlite2.activity;

import java.text.ParseException;

import org.ffmmx.example.androidsqlite2.R;
import org.ffmmx.example.androidsqlite2.business.Login;
import org.ffmmx.example.androidsqlite2.common.SqlUtil;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AndroidSqliteText extends Activity {
	private EditText usernameEdit, passwordEdit;
	private Button loginBtn, registerBtn;
	private SQLiteOpenHelper sqlhelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		if (Login.isLogon()) {
			Intent intent = new Intent();
			intent.setClass(AndroidSqliteText.this, NoteActivity.class);
		}

		sqlhelper = new SqlUtil(AndroidSqliteText.this);

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
				SQLiteDatabase db = sqlhelper.getReadableDatabase();
				boolean result = false;
				try {
					result = Login.login(db, usernameEdit.getText().toString(),
							passwordEdit.getText().toString());
				} catch (ParseException e) {
					e.printStackTrace();
				}
				db.close();
				if (result) {
					Intent intent = new Intent();
					intent.setClass(AndroidSqliteText.this, NoteActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(AndroidSqliteText.this, "用户名密码错误或者用户不存在",
							Toast.LENGTH_LONG).show();

				}
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
