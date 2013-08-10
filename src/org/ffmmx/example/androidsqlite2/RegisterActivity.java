package org.ffmmx.example.androidsqlite2;

import android.app.Activity;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity {
	private EditText usernameEdit, passwordEdit, passwordRepeatEdit, emailEdit,
			birthEdit;
	private Button registerBtn, birthSelectBtn;

	private SQLiteOpenHelper util;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.register);

		util = new SqlUtil(RegisterActivity.this);
		usernameEdit = (EditText) this.findViewById(R.id.regUsernameEdit);
		passwordEdit = (EditText) this.findViewById(R.id.regPasswordEdit);
		passwordRepeatEdit = (EditText) this
				.findViewById(R.id.regPasswordRepeatEdit);
		emailEdit = (EditText) this.findViewById(R.id.regEmailEdit);
		birthEdit = (EditText) this.findViewById(R.id.regBirthEdit);

		registerBtn = (Button) this.findViewById(R.id.regRegisterBtn);
		birthSelectBtn = (Button) this.findViewById(R.id.regDateSelectBtn);
		RegisterButtonListener btnClickListener = new RegisterButtonListener();
		registerBtn.setOnClickListener(btnClickListener);
		birthSelectBtn.setOnClickListener(btnClickListener);

	}

	class RegisterButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.regRegisterBtn:

				break;
			case R.id.regDateSelectBtn:
				break;
			default:
				break;
			}
		}

	}
}
