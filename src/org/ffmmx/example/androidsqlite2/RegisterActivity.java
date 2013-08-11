package org.ffmmx.example.androidsqlite2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

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

				ContentValues values = new ContentValues();
				values.put("username", usernameEdit.getText().toString());
				values.put("password", passwordEdit.getText().toString());
				values.put("email", emailEdit.getText().toString());
				values.put("birth", birthEdit.getText().toString());
				if (!validate(values)) {
					break;
				}
				SQLiteDatabase db = util.getWritableDatabase();
				db.beginTransaction();

				db.insert("t_user", null, values);
				db.endTransaction();
				db.close();
				break;
			case R.id.regDateSelectBtn:
				Calendar now = Calendar.getInstance();
				now.setTime(new Date());
				DatePickerDialog datePickerDialog = new DatePickerDialog(
						RegisterActivity.this, new OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								Calendar selectCal = Calendar.getInstance();
								selectCal.set(year, monthOfYear, dayOfMonth);
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy-MM-dd");
								birthEdit.setText(sdf.format(selectCal
										.getTime()));
							}
						}, now.get(Calendar.YEAR), now.get(Calendar.MONTH),
						now.get(Calendar.DAY_OF_MONTH));
				datePickerDialog.show();
				break;
			default:
				break;
			}
		}

	}

	private boolean validate(ContentValues values) {
		if (values.getAsString("username") == null
				|| "".equals(values.getAsString("username"))) {
			usernameEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG)
					.show();
			;
			return false;
		}
		if (values.getAsString("password") == null
				|| "".equals(values.getAsString("password"))) {
			passwordEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if (values.getAsString("passwordRepeat") == null
				|| "".equals(values.getAsString("passwordRepeat"))) {
			passwordRepeatEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "密码重复不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if (values.getAsString("email") == null
				|| "".equals(values.getAsString("email"))) {
			emailEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "邮箱不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		if (values.getAsString("birth") == null
				|| "".equals(values.getAsString("birth"))) {
			birthEdit.forceLayout();
			Toast.makeText(RegisterActivity.this, "生日不能为空", Toast.LENGTH_LONG)
					.show();
			return false;
		}
		return true;
	}
}
